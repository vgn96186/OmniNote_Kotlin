package com.example.omninote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import com.example.omninote.data.ToolType

@Composable
fun DrawingToolbar(
    currentTool: ToolType,
    currentColor: Color,
    strokeWidth: Float,
    onToolChanged: (ToolType) -> Unit,
    onColorChanged: (Color) -> Unit,
    onStrokeWidthChanged: (Float) -> Unit,
    onClearCanvas: () -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tool selection
            ToolButton(
                tool = ToolType.PEN,
                icon = Icons.Default.Edit,
                isSelected = currentTool == ToolType.PEN,
                onClick = { onToolChanged(ToolType.PEN) }
            )
            
            ToolButton(
                tool = ToolType.HIGHLIGHTER,
                icon = Icons.Default.Brush,
                isSelected = currentTool == ToolType.HIGHLIGHTER,
                onClick = { onToolChanged(ToolType.HIGHLIGHTER) }
            )
            
            ToolButton(
                tool = ToolType.ERASER,
                icon = Icons.Default.Clear,
                isSelected = currentTool == ToolType.ERASER,
                onClick = { onToolChanged(ToolType.ERASER) }
            )
            
            Divider(
                modifier = Modifier
                    .height(40.dp)
                    .width(1.dp),
                color = MaterialTheme.colorScheme.outline
            )
            
            // Color selection
            ColorPalette(
                currentColor = currentColor,
                onColorSelected = onColorChanged
            )
            
            Divider(
                modifier = Modifier
                    .height(40.dp)
                    .width(1.dp),
                color = MaterialTheme.colorScheme.outline
            )
            
            // Stroke width
            StrokeWidthSelector(
                currentWidth = strokeWidth,
                onWidthChanged = onStrokeWidthChanged
            )
            
            Divider(
                modifier = Modifier
                    .height(40.dp)
                    .width(1.dp),
                color = MaterialTheme.colorScheme.outline
            )
            
            // Action buttons
            ActionButton(
                icon = Icons.Default.Undo,
                onClick = onUndo,
                contentDescription = "Undo"
            )
            
            ActionButton(
                icon = Icons.Default.Redo,
                onClick = onRedo,
                contentDescription = "Redo"
            )
            
            ActionButton(
                icon = Icons.Default.Clear,
                onClick = onClearCanvas,
                contentDescription = "Clear Canvas"
            )
        }
    }
}

@Composable
private fun ToolButton(
    tool: ToolType,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surface
    }
    
    val iconTint = if (isSelected) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurface
    }
    
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = tool.name,
            tint = iconTint,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun ColorPalette(
    currentColor: Color,
    onColorSelected: (Color) -> Unit
) {
    val colors = listOf(
        Color.Black,
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Magenta,
        Color.Cyan,
        Color.Gray
    )
    
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(color)
                    .border(
                        width = if (currentColor == color) 3.dp else 1.dp,
                        color = if (currentColor == color) MaterialTheme.colorScheme.primary else Color.Gray,
                        shape = CircleShape
                    )
                    .clickable { onColorSelected(color) }
            )
        }
    }
}

@Composable
private fun StrokeWidthSelector(
    currentWidth: Float,
    onWidthChanged: (Float) -> Unit
) {
    val widths = listOf(2f, 4f, 6f, 8f, 12f)
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Width",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            widths.forEach { width ->
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(
                            if (currentWidth == width) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surface
                        )
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = CircleShape
                        )
                        .clickable { onWidthChanged(width) },
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(width.dp)
                            .clip(CircleShape)
                            .background(
                                if (currentWidth == width) Color.White
                                else Color.Black
                            )
                    )
                }
            }
        }
    }
}

@Composable
private fun ActionButton(
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription: String
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(40.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun NoteToolbar(
    onNewNote: () -> Unit,
    onSearch: () -> Unit,
    onShowGraph: () -> Unit,
    onExport: () -> Unit,
    onSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ActionButton(
                icon = Icons.Default.Add,
                onClick = onNewNote,
                contentDescription = "New Note"
            )
            
            ActionButton(
                icon = Icons.Default.Search,
                onClick = onSearch,
                contentDescription = "Search"
            )
            
            ActionButton(
                icon = Icons.Default.AccountTree,
                onClick = onShowGraph,
                contentDescription = "Knowledge Graph"
            )
            
            ActionButton(
                icon = Icons.Default.Download,
                onClick = onExport,
                contentDescription = "Export"
            )
            
            ActionButton(
                icon = Icons.Default.Settings,
                onClick = onSettings,
                contentDescription = "Settings"
            )
        }
    }
}
