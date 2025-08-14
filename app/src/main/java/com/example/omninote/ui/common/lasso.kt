package com.example.omninote.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.omninote.data.ToolType


@Composable
fun AdvancedCanvasToolbar(
    modifier: Modifier = Modifier,
    currentTool: ToolType,
    onToolChange: (ToolType) -> Unit,
    currentColor: Color,
    onColorChange: (Color) -> Unit,
    currentWidth: Float,
    onWidthChange: (Float) -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit,
) {
    var showPens by remember { mutableStateOf(false) }
    var showColors by remember { mutableStateOf(false) }
    var showWidths by remember { mutableStateOf(false) }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Pen Selection
            Box {
                IconButton(onClick = { showPens = true }) {
                    Icon(Icons.Default.Edit, contentDescription = "Pens")
                }
                PensMenu(
                    expanded = showPens,
                    onDismiss = { showPens = false },
                    onToolSelect = {
                        onToolChange(it)
                        showPens = false
                    }
                )
            }

            // Eraser
            ToolbarIconButton(
                icon = Icons.Default.Crop,
                contentDescription = "Eraser",
                isSelected = currentTool == ToolType.ERASER,
                onClick = { onToolChange(ToolType.ERASER) }
            )

            // Lasso
            ToolbarIconButton(
                icon = Icons.Default.CropFree,
                contentDescription = "Lasso",
                isSelected = false, // Add logic for lasso selection state
                onClick = { /* onToolChange(ToolType.LASSO) */ }
            )

            VerticalDivider(modifier = Modifier.height(32.dp))

            // Color Palette
            Box {
                IconButton(onClick = { showColors = true }) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(currentColor)
                            .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
                    )
                }
                ColorsMenu(
                    expanded = showColors,
                    onDismiss = { showColors = false },
                    onColorSelect = {
                        onColorChange(it)
                        showColors = false
                    }
                )
            }

            VerticalDivider(modifier = Modifier.height(32.dp))

            // Brush Thickness
            Box {
                IconButton(onClick = { showWidths = true }) {
                    Icon(Icons.Default.LineWeight, contentDescription = "Brush Thickness")
                }
                WidthsMenu(
                    expanded = showWidths,
                    onDismiss = { showWidths = false },
                    onWidthSelect = {
                        onWidthChange(it)
                        showWidths = false
                    }
                )
            }

            VerticalDivider(modifier = Modifier.height(32.dp))

            // Undo/Redo
            IconButton(onClick = onUndo) {
                Icon(Icons.Default.Undo, contentDescription = "Undo")
            }
            IconButton(onClick = onRedo) {
                Icon(Icons.Default.Redo, contentDescription = "Redo")
            }
        }
    }
}

@Composable
private fun ToolbarIconButton(icon: ImageVector, contentDescription: String, isSelected: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else LocalContentColor.current
        )
    }
}

@Composable
private fun PensMenu(expanded: Boolean, onDismiss: () -> Unit, onToolSelect: (ToolType) -> Unit) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        offset = DpOffset(0.dp, 8.dp)
    ) {
        DropdownMenuItem(
            text = { Text("Pen") },
            leadingIcon = { Icon(Icons.Default.Edit, null) },
            onClick = { onToolSelect(ToolType.PEN) }
        )
        DropdownMenuItem(
            text = { Text("Highlighter") },
            leadingIcon = { Icon(Icons.Default.Brush, null) },
            onClick = { onToolSelect(ToolType.HIGHLIGHTER) }
        )
    }
}

@Composable
private fun ColorsMenu(expanded: Boolean, onDismiss: () -> Unit, onColorSelect: (Color) -> Unit) {
    val colors = listOf(
        Color.Black, Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Magenta
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        properties = PopupProperties(focusable = false)
    ) {
        Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            colors.forEach { color ->
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(color)
                        .clickable { onColorSelect(color) }
                )
            }
        }
    }
}

@Composable
private fun WidthsMenu(expanded: Boolean, onDismiss: () -> Unit, onWidthSelect: (Float) -> Unit) {
    val widths = listOf(4f, 6f, 8f, 12f, 16f)
    DropdownMenu(expanded = expanded, onDismissRequest = onDismiss) {
        widths.forEach { width ->
            DropdownMenuItem(
                text = { Text("Width ${width.toInt()}") },
                onClick = { onWidthSelect(width) }
            )
        }
    }
}
