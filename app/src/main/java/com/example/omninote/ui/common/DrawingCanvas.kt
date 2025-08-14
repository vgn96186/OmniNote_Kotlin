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
import kotlin.math.abs

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
    
    val surfaceColor = MaterialTheme.colorScheme.surface
    val gridColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
    val dotColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(surfaceColor)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(0.3f, 4f)
                    offset += pan
                }
            }
            .pointerInput(toolType, strokeColor, strokeWidth, stylusOnly) {
                detectDragGestures(
                    onDragStart = { touchOffset ->
                        val startPoint = Point(
                            x = (touchOffset.x - offset.x) / scale,
                            y = (touchOffset.y - offset.y) / scale
                        )
                        val newStroke = DataStroke(
                            noteId = 0, // Will be set correctly in the ViewModel
                            points = listOf(startPoint),
                            color = strokeColor.toArgb(),
                            strokeWidth = strokeWidth,
                            toolType = toolType
                        )
                        onStrokeStart(newStroke)
                    },
                    onDrag = { change, _ ->
                        val newPoint = Point(
                            x = (change.position.x - offset.x) / scale,
                            y = (change.position.y - offset.y) / scale
                        )
                        currentStroke?.let {
                            onStrokeUpdate(it.copy(points = it.points + newPoint))
                        }
                    },
                    onDragEnd = {
                        currentStroke?.let { onStrokeEnd(it) }
                    }
                )
            }
    ) {
        // Draw grid and dots first, underneath the strokes
        if (showGrid) {
            drawGrid(step = 32f * scale, offset = offset, color = gridColor)
        }
        if (showDots) {
            drawDots(step = 32f * scale, offset = offset, radius = 1.5f * scale, color = dotColor)
        }

        // Apply transformations for drawing strokes
        withTransform({
            translate(offset.x, offset.y)
            scale(scale, scale)
        }) {
            strokes.forEach { stroke -> drawStroke(stroke) }
            currentStroke?.let { stroke -> drawStroke(stroke) }
        }
    }
}

private fun DrawScope.drawStroke(stroke: DataStroke) {
    if (stroke.points.size < 2) return

    val path = Path().apply {
        moveTo(stroke.points.first().x, stroke.points.first().y)
        for (i in 1 until stroke.points.size) {
            val p1 = stroke.points[i - 1]
            val p2 = stroke.points[i]
            // For smoother curves, you can use quadraticBezierTo
            quadraticBezierTo(p1.x, p1.y, (p1.x + p2.x) / 2, (p1.y + p2.y) / 2)
        }
    }
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
