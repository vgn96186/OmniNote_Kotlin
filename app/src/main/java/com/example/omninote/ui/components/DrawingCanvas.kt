package com.example.omninote.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope // Added import
// import androidx.compose.ui.graphics.drawscope.Stroke // Removed for disambiguation
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.unit.dp
import com.example.omninote.data.Point
import com.example.omninote.data.Stroke // This is our data class
import com.example.omninote.data.ToolType
import kotlin.math.abs
import androidx.compose.ui.graphics.toArgb

@Composable
fun DrawingCanvas(
    strokes: List<Stroke>,
    currentStroke: Stroke?,
    onStrokeStart: (Stroke) -> Unit,
    onStrokeUpdate: (Stroke) -> Unit,
    onStrokeEnd: (Stroke) -> Unit,
    toolType: ToolType = ToolType.PEN,
    strokeColor: Color = Color.Black,
    strokeWidth: Float = 6f,
    modifier: Modifier = Modifier
) {
    var currentPoints by remember { mutableStateOf(listOf<Point>()) }
    var velocityTracker by remember { mutableStateOf<VelocityTracker?>(null) }
    
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        velocityTracker = VelocityTracker()
                        currentPoints = listOf(
                            Point(
                                x = offset.x,
                                y = offset.y,
                                pressure = 1.0f,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        
                        val stroke = Stroke(
                            noteId = 0, // Will be set by parent
                            points = currentPoints,
                            color = strokeColor.toArgb(),
                            strokeWidth = strokeWidth,
                            toolType = toolType
                        )
                        onStrokeStart(stroke)
                    },
                    onDrag = { change, _ ->
                        velocityTracker?.addPosition(
                            change.uptimeMillis,
                            change.position
                        )
                        
                        val pressure = when (toolType) {
                            ToolType.PEN -> 1.0f
                            ToolType.HIGHLIGHTER -> 0.6f
                            ToolType.ERASER -> 1.0f
                            ToolType.SHAPE_DRAWER -> 1.0f
                        }
                        
                        val newPoint = Point(
                            x = change.position.x,
                            y = change.position.y,
                            pressure = pressure,
                            timestamp = System.currentTimeMillis()
                        )
                        
                        currentPoints = currentPoints + newPoint
                        
                        val stroke = Stroke(
                            noteId = 0,
                            points = currentPoints,
                            color = strokeColor.toArgb(),
                            strokeWidth = strokeWidth,
                            pressure = pressure,
                            toolType = toolType
                        )
                        onStrokeUpdate(stroke)
                    },
                    onDragEnd = {
                        velocityTracker?.let { tracker ->
                            val velocity = tracker.calculateVelocity()
                            val speed = kotlin.math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y)
                            
                            // Apply smoothing based on speed
                            val smoothedPoints = if (speed > 1000f) {
                                smoothPoints(currentPoints)
                            } else {
                                currentPoints
                            }
                            
                            val finalStroke = Stroke(
                                noteId = 0,
                                points = smoothedPoints,
                                color = strokeColor.toArgb(),
                                strokeWidth = strokeWidth,
                                toolType = toolType
                            )
                            onStrokeEnd(finalStroke)
                        }
                        
                        currentPoints = emptyList()
                        velocityTracker = null
                    }
                )
            }
    ) {
        // Draw existing strokes
        strokes.forEach { stroke ->
            drawStroke(stroke)
        }
        
        // Draw current stroke
        currentStroke?.let { stroke ->
            drawStroke(stroke)
        }
    }
}

private fun DrawScope.drawStroke(stroke: Stroke) { // Added DrawScope as receiver
    if (stroke.points.isEmpty()) return
    
    val path = Path().apply {
        val firstPoint = stroke.points.first()
        moveTo(firstPoint.x, firstPoint.y)
        
        stroke.points.drop(1).forEach { point ->
            lineTo(point.x, point.y)
        }
    }
    
    val color = Color(stroke.color)
    val width = stroke.strokeWidth * stroke.pressure
    
    when (stroke.toolType) {
        ToolType.PEN -> {
            drawPath(
                path = path,
                color = color,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = width, cap = androidx.compose.ui.graphics.StrokeCap.Round)
            )
        }
        ToolType.HIGHLIGHTER -> {
            drawPath(
                path = path,
                color = color.copy(alpha = 0.3f),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = width * 2, cap = androidx.compose.ui.graphics.StrokeCap.Round)
            )
        }
        ToolType.ERASER -> {
            drawPath(
                path = path,
                color = Color.White, // Assuming eraser draws with background color
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = width * 2, cap = androidx.compose.ui.graphics.StrokeCap.Round)
            )
        }
        ToolType.SHAPE_DRAWER -> {
            // For shape drawer, we might want to detect shapes and draw them differently
            drawPath(
                path = path,
                color = color,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = width, cap = androidx.compose.ui.graphics.StrokeCap.Round)
            )
        }
        else -> {}
    }
}

private fun smoothPoints(points: List<Point>): List<Point> {
    if (points.size < 3) return points
    
    val smoothed = mutableListOf<Point>()
    smoothed.add(points.first())
    
    for (i in 1 until points.size - 1) {
        val prev = points[i - 1]
        val current = points[i]
        val next = points[i + 1]
        
        val smoothedX = (prev.x + current.x + next.x) / 3f
        val smoothedY = (prev.y + current.y + next.y) / 3f
        
        smoothed.add(
            Point(
                x = smoothedX,
                y = smoothedY,
                pressure = current.pressure,
                timestamp = current.timestamp
            )
        )
    }
    
    smoothed.add(points.last())
    return smoothed
}
