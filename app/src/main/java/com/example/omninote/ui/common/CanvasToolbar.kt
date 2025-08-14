package com.example.omninote.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    onClear: () -> Unit = {},
    onExport: () -> Unit = {},
    onImport: () -> Unit = {},
) {
    var showPenFavorites by remember { mutableStateOf(false) }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            // Navigation Section
            IconButton(
                onClick = { /* Navigate back */ },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = { /* Show canvas overview */ },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Outlined.GridView,
                    contentDescription = "Canvas Overview",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = { /* Show layers */ },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Outlined.Layers,
                    contentDescription = "Layers",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            // Undo/Redo
            IconButton(
                onClick = onUndo,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Default.Undo,
                    contentDescription = "Undo",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = onRedo,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Default.Redo,
                    contentDescription = "Redo",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Tools Section - Main Tools
            Box {
                ToolbarIconButton(
                    icon = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Pen Favorites",
                    isSelected = showPenFavorites,
                    onClick = { showPenFavorites = !showPenFavorites }
                )

                PenFavoritesMenu(
                    expanded = showPenFavorites,
                    onDismiss = { showPenFavorites = false },
                    onToolSelect = onToolChange
                )
            }

            ToolbarIconButton(
                icon = Icons.Outlined.TextFields,
                contentDescription = "Text Box",
                isSelected = currentTool == ToolType.TEXT,
                onClick = { onToolChange(ToolType.TEXT) }
            )

            ToolbarIconButton(
                icon = Icons.Outlined.Edit,
                contentDescription = "Ballpoint Pen",
                isSelected = currentTool == ToolType.PEN,
                onClick = { onToolChange(ToolType.PEN) }
            )

            ToolbarIconButton(
                icon = Icons.Outlined.Brush,
                contentDescription = "Pen Tool",
                isSelected = currentTool == ToolType.BRUSH,
                onClick = { onToolChange(ToolType.BRUSH) }
            )

            ToolbarIconButton(
                icon = Icons.Outlined.Highlight,
                contentDescription = "Highlighter",
                isSelected = currentTool == ToolType.HIGHLIGHTER,
                onClick = { onToolChange(ToolType.HIGHLIGHTER) }
            )

            ToolbarIconButton(
                icon = Icons.Outlined.Remove,
                contentDescription = "Masking Tape Tool",
                isSelected = currentTool == ToolType.MASKING_TAPE,
                onClick = { onToolChange(ToolType.MASKING_TAPE) }
            )

            ToolbarIconButton(
                icon = Icons.Default.AutoFixNormal,
                contentDescription = "Eraser Tool",
                isSelected = currentTool == ToolType.ERASER,
                onClick = { onToolChange(ToolType.ERASER) }
            )

            ToolbarIconButton(
                icon = Icons.Outlined.Gesture,
                contentDescription = "Lasso Tool",
                isSelected = currentTool == ToolType.LASSO,
                onClick = { onToolChange(ToolType.LASSO) }
            )

            ToolbarIconButton(
                icon = Icons.Outlined.Timeline,
                contentDescription = "Shape Pen Tool",
                isSelected = currentTool == ToolType.SHAPE_PEN,
                onClick = { onToolChange(ToolType.SHAPE_PEN) }
            )

            ToolbarIconButton(
                icon = Icons.Outlined.LinearScale,
                contentDescription = "Laser Pen Tool",
                isSelected = currentTool == ToolType.LASER_PEN,
                onClick = { onToolChange(ToolType.LASER_PEN) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Color Palette Section
            ColorPaletteSection(
                currentColor = currentColor,
                onColorChange = onColorChange
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Brush Thickness Section
            BrushThicknessSection(
                currentWidth = currentWidth,
                onWidthChange = onWidthChange
            )

            Spacer(modifier = Modifier.weight(1f))

            // Action Buttons
            IconButton(
                onClick = { /* Zoom */ },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Outlined.ZoomIn,
                    contentDescription = "Zoom",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = { /* Edit */ },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Outlined.Edit,
                    contentDescription = "Edit",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = { /* Move */ },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Outlined.OpenWith,
                    contentDescription = "Move",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = { /* Select */ },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Outlined.CropFree,
                    contentDescription = "Select",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = onExport,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Outlined.FileUpload,
                    contentDescription = "Export",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = { /* More options */ },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Outlined.MoreHoriz,
                    contentDescription = "More",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = { /* Menu */ },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    Icons.Outlined.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun ToolbarIconButton(
    icon: ImageVector,
    contentDescription: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(36.dp)
            .then(
                if (isSelected) {
                    Modifier
                        .background(
                            MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(8.dp)
                        )
                } else {
                    Modifier
                }
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (isSelected) {
                MaterialTheme.colorScheme.onPrimaryContainer
            } else {
                MaterialTheme.colorScheme.onSurface
            },
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun ColorPaletteSection(
    currentColor: Color,
    onColorChange: (Color) -> Unit
) {
    val colors = listOf(
        Color.Black, Color(0xFF1565C0), Color.Red, Color(0xFF8E24AA),
        Color(0xFF039BE5), Color(0xFF00ACC1), Color(0xFF43A047), Color(0xFFFFA726),
        Color.White
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        colors.forEach { color ->
            ColorDot(
                color = color,
                isSelected = color == currentColor,
                onClick = { onColorChange(color) }
            )
        }

        // Palette button
        IconButton(
            onClick = { /* Show color picker */ },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                Icons.Outlined.Palette,
                contentDescription = "Color Palette",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun ColorDot(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(color)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.outline
                },
                shape = CircleShape
            )
            .clickable(onClick = onClick)
    )
}

@Composable
private fun BrushThicknessSection(
    currentWidth: Float,
    onWidthChange: (Float) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ThicknessDot(
            size = 8.dp,
            width = 0.5f,
            isSelected = currentWidth == 0.5f,
            onClick = { onWidthChange(0.5f) }
        )

        ThicknessDot(
            size = 12.dp,
            width = 0.7f,
            isSelected = currentWidth == 0.7f,
            onClick = { onWidthChange(0.7f) }
        )

        ThicknessDot(
            size = 16.dp,
            width = 1.2f,
            isSelected = currentWidth == 1.2f,
            onClick = { onWidthChange(1.2f) }
        )
    }
}

@Composable
private fun ThicknessDot(
    size: androidx.compose.ui.unit.Dp,
    width: Float,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(
                    if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
        )
    }
}

@Composable
private fun PenFavoritesMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onToolSelect: (ToolType) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        properties = PopupProperties(focusable = true)
    ) {
        Text(
            "Favorites",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(16.dp, 8.dp)
        )

        DropdownMenuItem(
            text = { Text("Ballpoint Pen") },
            leadingIcon = { Icon(Icons.Outlined.Edit, null) },
            onClick = {
                onToolSelect(ToolType.PEN)
                onDismiss()
            }
        )

        DropdownMenuItem(
            text = { Text("Highlighter") },
            leadingIcon = { Icon(Icons.Outlined.Highlight, null) },
            onClick = {
                onToolSelect(ToolType.HIGHLIGHTER)
                onDismiss()
            }
        )

        DropdownMenuItem(
            text = { Text("Brush") },
            leadingIcon = { Icon(Icons.Outlined.Brush, null) },
            onClick = {
                onToolSelect(ToolType.BRUSH)
                onDismiss()
            }
        )
    }
}