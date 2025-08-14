package com.example.omninote.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import com.example.omninote.data.Point
import com.example.omninote.data.Stroke as DataStroke
import com.example.omninote.data.ToolType
import kotlin.math.sqrt

@Composable
fun DrawingCanvas(
    strokes: List<DataStroke>,
    currentStroke: DataStroke?,
    onStrokeStart: (DataStroke) -> Unit,
    onStrokeUpdate: (DataStroke) -> Unit,
    onStrokeEnd: (DataStroke) -> Unit,
    toolType: ToolType,
    strokeColor: Color,
    strokeWidth: Float,
    showGrid: Boolean,
    showDots: Boolean,
    stylusOnly: Boolean,
    onEraseStrokes: (List<DataStroke>) -> Unit,
    modifier: Modifier = Modifier
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var pendingPoints by remember { mutableStateOf<List<Point>>(emptyList()) }
    var lastUpdateTime by remember { mutableLongStateOf(0L) }
    
    val surfaceColor = MaterialTheme.colorScheme.surface
    val gridColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
    val dotColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(surfaceColor)
            .pointerInput("pan-zoom") {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(0.3f, 4f)
                    offset += pan
                }
            }
            .pointerInput("draw") {
                detectDragGestures(
                    onDragStart = { startOffset ->
                        pendingPoints = emptyList()
                        val startPoint = Point(
                            x = (startOffset.x - offset.x) / scale,
                            y = (startOffset.y - offset.y) / scale
                        )
                        val newStroke = DataStroke(
                            noteId = 0,
                            points = listOf(startPoint),
                            color = strokeColor.toArgb(),
                            strokeWidth = strokeWidth,
                            tool = toolType
                        )
                        onStrokeStart(newStroke)
                    },
                    onDrag = { change, dragAmount ->
                        val currentTime = System.currentTimeMillis()
                        val newPoint = Point(
                            x = (change.position.x - offset.x) / scale,
                            y = (change.position.y - offset.y) / scale
                        )
                        pendingPoints = pendingPoints + newPoint
                        if (currentTime - lastUpdateTime > 16) {
                            currentStroke?.let {
                                val optimizedPoints = downsamplePoints(pendingPoints, epsilon = 2f)
                                onStrokeUpdate(it.copy(points = it.points + optimizedPoints))
                                pendingPoints = emptyList()
                                lastUpdateTime = currentTime
                            }
                        }
                    },
                    onDragEnd = {
                        if (pendingPoints.isNotEmpty()) {
                            currentStroke?.let {
                                val optimizedPoints = downsamplePoints(pendingPoints, epsilon = 2f)
                                onStrokeUpdate(it.copy(points = it.points + optimizedPoints))
                            }
                        }
                        currentStroke?.let { onStrokeEnd(it) }
                    }
                )
            }
    ) {
        if (showGrid) {
            drawGrid(step = 32f * scale, offset = offset, color = gridColor)
        }
        if (showDots) {
            drawDots(step = 32f * scale, offset = offset, radius = 1.5f * scale, color = dotColor)
        }

        withTransform({
            translate(offset.x, offset.y)
            scale(scale, scale)
        }) {
            strokes.forEach { stroke ->
                drawStroke(stroke)
            }
            currentStroke?.let { stroke ->
                drawStroke(stroke)
            }
        }
    }
}

private fun DrawScope.drawStroke(stroke: DataStroke) {
    if (stroke.points.size < 2) return

    val path = createPath(stroke.points)
    drawPath(
        path = path,
        color = Color(stroke.color),
        style = Stroke(width = stroke.strokeWidth, cap = StrokeCap.Round)
    )
}

private fun DrawScope.drawGrid(step: Float, offset: Offset, color: Color) {
    if (step <= 0) return
    val startX = ((-offset.x % step) - step) % step
    val startY = ((-offset.y % step) - step) % step

    var x = startX
    while (x <= size.width) {
        drawLine(color, Offset(x, 0f), Offset(x, size.height), strokeWidth = 0.5f)
        x += step
    }
    var y = startY
    while (y <= size.height) {
        drawLine(color, Offset(0f, y), Offset(size.width, y), strokeWidth = 0.5f)
        y += step
    }
}

private fun DrawScope.drawDots(step: Float, offset: Offset, radius: Float, color: Color) {
    if (step <= 0) return
    val startX = ((-offset.x % step) - step) % step
    val startY = ((-offset.y % step) - step) % step

    var x = startX
    while (x <= size.width) {
        var y = startY
        while (y <= size.height) {
            drawCircle(color, radius, center = Offset(x, y))
            y += step
        }
        x += step
    }
}

private fun createPath(points: List<Point>): Path {
    if (points.size < 2) return Path()
    
    return Path().apply {
        moveTo(points.first().x, points.first().y)
        for (i in 1 until points.size) {
            lineTo(points[i].x, points[i].y)
        }
    }
}

private fun downsamplePoints(points: List<Point>, epsilon: Float): List<Point> {
    if (points.size <= 2) return points
    
    val result = mutableListOf<Point>()
    result.add(points.first())
    
    for (i in 1 until points.size - 1) {
        val prev = result.last()
        val current = points[i]
        val distance = sqrt(
            (current.x - prev.x) * (current.x - prev.x) + 
            (current.y - prev.y) * (current.y - prev.y)
        )
        if (distance >= epsilon) {
            result.add(current)
        }
    }
    
    result.add(points.last())
    return result
}