package com.example.omninote.ui.note

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class DrawingTool {
    Pen, Highlighter, Eraser, Lasso, Text
}

enum class GridState {
    Off, Grid, Dots, Both
}

enum class DockPosition {
    Left, Right
}

data class PenConfig(
    val color: Color = Color.Black,
    val strokeWidth: Float = 4f,
    val smoothing: Boolean = true
)

data class HighlighterConfig(
    val color: Color = Color.Yellow,
    val strokeWidth: Float = 20f,
    val opacity: Float = 0.5f
)

data class CanvasUiState(
    val selectedTool: DrawingTool = DrawingTool.Pen,
    val penSlots: List<PenConfig> = listOf(
        PenConfig(), // Black
        PenConfig(color = Color.Blue),
        PenConfig(color = Color.Red),
        PenConfig(color = Color.Green)
    ),
    val highlighterSlots: List<HighlighterConfig> = listOf(
        HighlighterConfig(), // Yellow
        HighlighterConfig(color = Color.Magenta.copy(alpha = 0.5f)), // Pink
        HighlighterConfig(color = Color.Cyan.copy(alpha = 0.5f)),
        HighlighterConfig(color = Color.LightGray.copy(alpha = 0.5f))
    ),
    val selectedPenSlot: Int = 0,
    val selectedHighlighterSlot: Int = 0,
    val eraserMode: String = "Stroke", // "Stroke" or "Pixel"
    val gridState: GridState = GridState.Off,
    val zoomScale: Float = 1.0f,
    val dockPosition: DockPosition = DockPosition.Left,
    val undoEnabled: Boolean = false,
    val redoEnabled: Boolean = false
)
