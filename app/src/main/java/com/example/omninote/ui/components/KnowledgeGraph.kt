package com.example.omninote.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.omninote.data.Note
import java.time.LocalDate
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Composable
fun KnowledgeGraph(
    notes: List<Note>,
    graphData: Map<Long, List<Long>>,
    onNoteSelected: (Note) -> Unit,
    selectedNoteId: Long? = null,
    modifier: Modifier = Modifier
) {
    var scaleFactor by remember { mutableStateOf(1f) } // Consider mutableFloatStateOf for performance
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scaleFactor = (scaleFactor * zoom).coerceIn(0.25f, 4f)
                        offset += pan
                    }
                }
        ) {
            val nodePositions = calculateNodePositions(notes.size, size.width, size.height)

            // Center the graph initially and apply transformations
            translate(left = offset.x, top = offset.y) {
                scale(scale = scaleFactor, pivot = center) {
                    // Draw connections
                    graphData.forEach { (sourceId, targetIds) ->
                        val sourceIndex = notes.indexOfFirst { it.id == sourceId }
                        if (sourceIndex != -1) {
                            val sourcePos = nodePositions[sourceIndex]
                            targetIds.forEach { targetId ->
                                val targetIndex = notes.indexOfFirst { it.id == targetId }
                                if (targetIndex != -1) {
                                    val targetPos = nodePositions[targetIndex]
                                    drawConnection(sourcePos, targetPos)
                                }
                            }
                        }
                    }

                    // Draw nodes
                    notes.forEachIndexed { index, note ->
                        val position = nodePositions[index]
                        val isSelected = note.id == selectedNoteId
                        drawNode(position, note.title, isSelected)
                    }
                }
            }
        }

        // Toolbar
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    scaleFactor = 1f
                    offset = Offset.Zero
                },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text("Reset View")
            }

            Text(
                text = "Notes: ${notes.size}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Connections: ${graphData.values.sumOf { it.size }}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

internal fun calculateNodePositions(
    nodeCount: Int,
    width: Float,
    height: Float
): List<Offset> {
    if (nodeCount == 0) return emptyList()

    val positions = mutableListOf<Offset>()
    val centerX = width / 2
    val centerY = height / 2
    val radius = min(width, height) * 0.4f // Increased radius for better spacing

    if (nodeCount == 1) {
        positions.add(Offset(centerX, centerY))
    } else {
        for (i in 0 until nodeCount) {
            val angle = (2 * Math.PI * i) / nodeCount
            val x = centerX + (radius * cos(angle)).toFloat()
            val y = centerY + (radius * sin(angle)).toFloat()
            positions.add(Offset(x, y))
        }
    }

    return positions
}

internal fun androidx.compose.ui.graphics.drawscope.DrawScope.drawConnection(
    start: Offset,
    end: Offset
) {
    drawLine(
        color = Color(0xFF2196F3).copy(alpha = 0.6f),
        start = start,
        end = end,
        strokeWidth = 2f,
        cap = StrokeCap.Round
    )
}

internal fun androidx.compose.ui.graphics.drawscope.DrawScope.drawNode(
    position: Offset,
    title: String,
    isSelected: Boolean
) {
    val nodeRadius = if (isSelected) 25f else 20f
    val nodeColor = if (isSelected) Color(0xFF2196F3) else Color(0xFF4CAF50)

    drawCircle(
        color = nodeColor,
        radius = nodeRadius,
        center = position
    )

    if (isSelected) {
        drawCircle(
            color = Color(0xFF1976D2),
            radius = nodeRadius + 2f,
            center = position,
            style = Stroke(width = 3f)
        )
    }

    drawCircle(
        color = Color.White,
        radius = 8f,
        center = position
    )
}

@Composable
fun KnowledgeGraphList(
    notes: List<Note>,
    graphData: Map<Long, List<Long>>,
    onNoteSelected: (Note) -> Unit,
    selectedNoteId: Long? = null,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(notes) { note ->
            val connectionCount = graphData[note.id]?.size ?: 0
            val isSelected = note.id == selectedNoteId

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (isSelected) 8.dp else 4.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected)
                        MaterialTheme.colorScheme.primaryContainer
                    else
                        MaterialTheme.colorScheme.surface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                        Text(
                            text = "Connections: $connectionCount",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        // Assuming you have an extension function to convert Long to LocalDate
                        Text(
                            text = "Updated: ${note.updatedAt}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    if (isSelected) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Selected",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}