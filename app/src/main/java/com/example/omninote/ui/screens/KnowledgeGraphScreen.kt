package com.example.omninote.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CenterFocusStrong
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.omninote.data.Note
import com.example.omninote.viewmodel.NoteViewModel
import com.example.omninote.viewmodel.Screen
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KnowledgeGraphScreen(
    viewModel: NoteViewModel,
    onOpenNote: (Long) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val notes = uiState.notes
    val graph = uiState.knowledgeGraph

    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var selectedNodeId by remember { mutableStateOf<Long?>(null) }
    var physicsEnabled by remember { mutableStateOf(true) }

    val nodes = remember(notes) {
        mutableStateListOf<GraphNode>().apply {
            addAll(createInitialLayout(notes))
        }
    }
    val connections = remember(graph) { buildConnections(graph) }

    // Physics simulation
    LaunchedEffect(physicsEnabled, notes.size) {
        while (physicsEnabled && nodes.isNotEmpty()) {
            updatePhysics(nodes, connections)
            delay(16)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Knowledge Graph", fontWeight = FontWeight.Medium) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.changeScreen(Screen.Explorer) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { physicsEnabled = !physicsEnabled }) {
                        Icon(
                            if (physicsEnabled) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                            if (physicsEnabled) "Pause" else "Play"
                        )
                    }
                    IconButton(onClick = {
                        scale = 1f
                        offset = Offset.Zero
                    }) {
                        Icon(Icons.Filled.CenterFocusStrong, "Center")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale = (scale * zoom).coerceIn(0.2f, 5f)
                            // Check for potential overflow before updating offset
                            val newOffset = offset + pan
                            offset = Offset(
                                newOffset.x.coerceIn(-Float.MAX_VALUE, Float.MAX_VALUE),
                                newOffset.y.coerceIn(-Float.MAX_VALUE, Float.MAX_VALUE)
                            )
                        }
                    }
                    .pointerInput(nodes) {
                        detectTapGestures { tapPos ->
                            val worldPos = (tapPos - offset) / scale
                            val tapped = nodes.find { node ->
                                (node.position - worldPos).getDistance() < 40f
                            }
                            selectedNodeId = tapped?.id
                            tapped?.let { onOpenNote(it.id) }
                        }
                    }
            ) {
                withTransform({
                    translate(offset.x + size.width / 2, offset.y + size.height / 2)
                    scale(scale, scale)
                }) {
                    drawGraph(
                        nodes = nodes,
                        connections = connections,
                        selectedId = selectedNodeId
                    )
                }
            }

            // Status indicator
            Card(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (physicsEnabled) Color.Green else Color.Gray)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "${notes.size} notes",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

private data class GraphNode(
    val id: Long,
    var position: Offset,
    var velocity: Offset = Offset.Zero,
    var force: Offset = Offset.Zero
)

private fun createInitialLayout(notes: List<Note>): List<GraphNode> {
    if (notes.isEmpty()) return emptyList()

    return notes.mapIndexed { index, note ->
        val angle = 2 * PI * index / notes.size
        val radius = 200f + (notes.size * 10f).coerceAtMost(300f)
        GraphNode(
            id = note.id,
            position = Offset(
                (radius * cos(angle)).toFloat(),
                (radius * sin(angle)).toFloat()
            )
        )
    }
}

private fun buildConnections(graph: Map<Long, List<Long>>): List<Pair<Long, Long>> {
    val connections = mutableSetOf<Pair<Long, Long>>()
    graph.forEach { (source, targets) ->
        targets.forEach { target ->
            val pair = if (source < target) source to target else target to source
            connections.add(pair)
        }
    }
    return connections.toList()
}

private fun updatePhysics(nodes: MutableList<GraphNode>, connections: List<Pair<Long, Long>>) {
    val repelForce = 8000f
    val attractForce = 0.02f
    val damping = 0.9f
    val centerForce = 0.001f

    // Reset forces
    nodes.forEach { it.force = Offset.Zero }

    // Repulsion between all nodes
    for (i in nodes.indices) {
        for (j in i + 1 until nodes.size) {
            val nodeA = nodes[i]
            val nodeB = nodes[j]
            val delta = nodeB.position - nodeA.position
            val distance = delta.getDistance().coerceAtLeast(1f)
            val force = repelForce / (distance * distance)
            val direction = delta / distance

            nodeA.force -= direction * force
            nodeB.force += direction * force
        }
    }

    // Attraction for connected nodes
    connections.forEach { (idA, idB) ->
        val nodeA = nodes.find { it.id == idA }
        val nodeB = nodes.find { it.id == idB }
        if (nodeA != null && nodeB != null) {
            val delta = nodeB.position - nodeA.position
            val distance = delta.getDistance()
            val force = distance * attractForce
            val direction = if (distance > 0) delta / distance else Offset.Zero

            nodeA.force += direction * force
            nodeB.force -= direction * force
        }
    }

    // Center attraction
    nodes.forEach { node ->
        val centerDelta = -node.position
        node.force = node.force.safeAdd(centerDelta.times(centerForce))
    }

    // Update positions
    nodes.forEach { node ->
        node.velocity = (node.velocity + node.force) * damping
        val newPosition = node.position + node.velocity
        if (newPosition.x.isFinite() && newPosition.y.isFinite()) {
            node.position = newPosition
        }
    }
}

private fun DrawScope.drawGraph(
    nodes: List<GraphNode>,
    connections: List<Pair<Long, Long>>,
    selectedId: Long?
) {
    val primary = Color(0xFF6366F1)
    val secondary = Color(0xFF8B5CF6)
    val surface = Color(0xFFF8FAFC)
    val outline = Color(0xFFE2E8F0)

    // Draw connections
    connections.forEach { (idA, idB) ->
        val nodeA = nodes.find { it.id == idA }
        val nodeB = nodes.find { it.id == idB }
        if (nodeA != null && nodeB != null) {
            val isHighlighted = selectedId == idA || selectedId == idB
            drawLine(
                brush = if (isHighlighted) {
                    Brush.linearGradient(
                        colors = listOf(primary, secondary),
                        start = nodeA.position,
                        end = nodeB.position
                    )
                } else {
                    Brush.linearGradient(
                        colors = listOf(outline, outline.copy(alpha = 0.3f)),
                        start = nodeA.position,
                        end = nodeB.position
                    )
                },
                start = nodeA.position,
                end = nodeB.position,
                strokeWidth = if (isHighlighted) 3f else 1.5f,
                cap = StrokeCap.Round
            )
        }
    }

    // Draw nodes
    nodes.forEach { node ->
        val isSelected = selectedId == node.id
        val connectionCount = connections.count { it.first == node.id || it.second == node.id }
        val radius = (20f + connectionCount * 3f).coerceAtMost(40f)

        // Node shadow
        drawCircle(
            color = Color.Black.copy(alpha = 0.1f),
            radius = radius + 2f,
            center = node.position + Offset(2f, 2f)
        )

        // Node background
        drawCircle(
            brush = if (isSelected) {
                Brush.radialGradient(
                    colors = listOf(primary, secondary),
                    center = node.position,
                    radius = radius
                )
            } else {
                Brush.radialGradient(
                    colors = listOf(surface, outline),
                    center = node.position,
                    radius = radius
                )
            },
            radius = radius,
            center = node.position
        )

        // Node border
        drawCircle(
            color = if (isSelected) primary else outline,
            radius = radius,
            center = node.position,
            style = androidx.compose.ui.graphics.drawscope.Stroke(
                width = if (isSelected) 3f else 1.5f
            )
        )
    }
}

private fun Offset.getDistance(): Float = sqrt(x * x + y * y)

private fun Offset.safeAdd(other: Offset): Offset {
    val newX = x + other.x
    val newY = y + other.y
    return Offset(
        if (newX.isFinite()) newX else x,
        if (newY.isFinite()) newY else y
    )
}
