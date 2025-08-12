package com.example.omninote.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
// import androidx.compose.material3.MaterialTheme // Unused
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
// import androidx.compose.ui.graphics.StrokeCap // Unused
// import androidx.compose.ui.graphics.drawscope.Stroke // Unused
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.omninote.data.Note
// import kotlin.math.cos // Unused
// import kotlin.math.min // Unused
// import kotlin.math.sin // Unused

@Composable
fun KnowledgeGraphScreen(
    notes: List<Note>,
    graphData: Map<Long, List<Long>>,
    modifier: Modifier = Modifier
) {
    var scaleFactor by remember { mutableFloatStateOf(1f) }
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

            translate(left = offset.x, top = offset.y) {
                scale(scale = scaleFactor, pivot = center) {
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

                    notes.forEachIndexed { index, note ->
                        val position = nodePositions[index]
                        drawNode(position, note.title, false)
                    }
                }
            }
        }

        Button(
            onClick = {
                scaleFactor = 1f
                offset = Offset.Zero
            },
            modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
        ) {
            Text("Reset View")
        }
    }
}