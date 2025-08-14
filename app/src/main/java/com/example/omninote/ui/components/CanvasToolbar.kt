package com.example.omninote.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Redo
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.omninote.ui.note.CanvasUiState
import com.example.omninote.ui.note.DockPosition
import com.example.omninote.ui.note.DrawingTool
import com.example.omninote.ui.note.PenConfig
import com.example.omninote.ui.note.HighlighterConfig

@Composable
fun CanvasToolbar(
    uiState: CanvasUiState,
    onToolChange: (DrawingTool) -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit,
    onToggleGrid: () -> Unit,
    onZoomReset: () -> Unit,
    onZoomIn: () -> Unit,
    onZoomOut: () -> Unit,
    onBrushSizeChange: (Float) -> Unit,
    onSelectPenSlot: (Int) -> Unit,
    onSelectHighlighterSlot: (Int) -> Unit,
    onMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        tonalElevation = 3.dp,
        shadowElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Tool selection group
            ToolSelectionGroup(uiState, onToolChange)

            VerticalDivider(modifier = Modifier.height(32.dp))

            // Brush and color palette group
            if (uiState.selectedTool == DrawingTool.Pen || uiState.selectedTool == DrawingTool.Highlighter) {
                BrushAndColorGroup(
                    uiState = uiState,
                    onSelectPenSlot = onSelectPenSlot,
                    onSelectHighlighterSlot = onSelectHighlighterSlot,
                    onBrushSizeChange = onBrushSizeChange
                )
            }

            Spacer(Modifier.weight(1f))

            // Action group (Undo/Redo)
            ActionGroup(uiState, onUndo, onRedo)

            VerticalDivider(modifier = Modifier.height(32.dp))

            // View controls group (Zoom, Grid)
            ViewControlsGroup(onZoomIn, onZoomOut, onZoomReset, onToggleGrid)

            // More options
            DockToolButton(
                icon = Icons.Default.MoreVert,
                selected = false,
                onClick = onMore,
                contentDescription = "More Options"
            )
        }
    }
}

@Composable
private fun ToolSelectionGroup(uiState: CanvasUiState, onToolChange: (DrawingTool) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        DockToolButton(
            icon = Icons.Default.Create,
            selected = uiState.selectedTool == DrawingTool.Pen,
            onClick = { onToolChange(DrawingTool.Pen) },
            contentDescription = "Pen"
        )
        DockToolButton(
            icon = Icons.Default.Brush,
            selected = uiState.selectedTool == DrawingTool.Highlighter,
            onClick = { onToolChange(DrawingTool.Highlighter) },
            contentDescription = "Highlighter"
        )
        DockToolButton(
            icon = Icons.Default.EditOff,
            selected = uiState.selectedTool == DrawingTool.Eraser,
            onClick = { onToolChange(DrawingTool.Eraser) },
            contentDescription = "Eraser"
        )
        DockToolButton(
            icon = Icons.Default.Gesture,
            selected = uiState.selectedTool == DrawingTool.Lasso,
            onClick = { onToolChange(DrawingTool.Lasso) },
            contentDescription = "Lasso"
        )
        DockToolButton(
            icon = Icons.Default.TextFields, // New Text tool
            selected = uiState.selectedTool == DrawingTool.Text,
            onClick = { onToolChange(DrawingTool.Text) },
            contentDescription = "Text"
        )
    }
}

@Composable
private fun BrushAndColorGroup(
    uiState: CanvasUiState,
    onSelectPenSlot: (Int) -> Unit,
    onSelectHighlighterSlot: (Int) -> Unit,
    onBrushSizeChange: (Float) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        // Color Palette
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            when (uiState.selectedTool) {
                DrawingTool.Pen -> {
                    uiState.penSlots.forEachIndexed { index, penConfig ->
                        ColorSlotButton(
                            color = penConfig.color,
                            selected = uiState.selectedPenSlot == index,
                            onClick = { onSelectPenSlot(index) }
                        )
                    }
                }
                DrawingTool.Highlighter -> {
                    uiState.highlighterSlots.forEachIndexed { index, highlighterConfig ->
                        ColorSlotButton(
                            color = highlighterConfig.color,
                            selected = uiState.selectedHighlighterSlot == index,
                            onClick = { onSelectHighlighterSlot(index) }
                        )
                    }
                }
                else -> {}
            }
        }

        VerticalDivider(modifier = Modifier.height(32.dp))

        // Brush Size
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            val sizes = listOf(4f, 8f, 12f)
            sizes.forEach { size ->
                IconButton(onClick = { onBrushSizeChange(size) }) {
                    Icon(Icons.Default.Circle, contentDescription = "Set brush size to $size", modifier = Modifier.size((size + 4).dp))
                }
            }
        }
    }
}

@Composable
private fun ColorSlotButton(
    color: Color,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.size(40.dp),
        shape = CircleShape,
        color = color,
        border = if (selected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
        onClick = onClick
    ) {}
}

@Composable
private fun ActionGroup(uiState: CanvasUiState, onUndo: () -> Unit, onRedo: () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        DockToolButton(
            icon = Icons.AutoMirrored.Filled.Undo,
            selected = false,
            onClick = onUndo,
            contentDescription = "Undo",
            enabled = uiState.undoEnabled
        )
        DockToolButton(
            icon = Icons.AutoMirrored.Filled.Redo,
            selected = false,
            onClick = onRedo,
            contentDescription = "Redo",
            enabled = uiState.redoEnabled
        )
    }
}

@Composable
private fun ViewControlsGroup(onZoomIn: () -> Unit, onZoomOut: () -> Unit, onZoomReset: () -> Unit, onToggleGrid: () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        DockToolButton(
            icon = Icons.Default.ZoomOut,
            selected = false,
            onClick = onZoomOut,
            contentDescription = "Zoom Out"
        )
        DockToolButton(
            icon = Icons.Default.ZoomIn,
            selected = false,
            onClick = onZoomIn,
            contentDescription = "Zoom In"
        )
        DockToolButton(
            icon = Icons.Default.ZoomOutMap,
            selected = false,
            onClick = onZoomReset,
            contentDescription = "Reset Zoom"
        )
        DockToolButton(
            icon = Icons.Default.GridOn,
            selected = false,
            onClick = onToggleGrid,
            contentDescription = "Toggle Grid"
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DockToolButton(
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    onLongPress: (() -> Unit)? = null,
    contentDescription: String,
    enabled: Boolean = true
) {
    // Replaced FilledIconToggleButton with IconButton to ensure onClick fires every time
    val colors = if (selected) {
        IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    } else {
        IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent)
    }

    IconButton(
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        modifier = Modifier.semantics { this.contentDescription = contentDescription }
    ) {
        Icon(icon, contentDescription = null)
    }
}
