package com.example.omninote.ui.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import com.example.omninote.data.Note
import com.example.omninote.data.Stroke
import com.example.omninote.data.ToolType
import com.example.omninote.ui.common.DrawingCanvas
import com.example.omninote.ui.components.CanvasToolbar
import com.example.omninote.viewmodel.NoteViewModel
import com.example.omninote.ui.note.DrawingTool
import com.example.omninote.ui.note.GridState
import androidx.compose.foundation.gestures.detectDragGestures
import kotlin.math.roundToInt

@Composable
fun CanvasScreen(note: Note, viewModel: NoteViewModel) {
    val strokes by viewModel.strokes.collectAsState()
    var currentStroke by remember { mutableStateOf<Stroke?>(null) }
    val canvasUiState by viewModel.canvasUiState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isFloatingToolbar) {
        Box(modifier = Modifier.fillMaxSize()) {
            DrawingCanvas(
                modifier = Modifier.fillMaxSize(),
                strokes = strokes,
                currentStroke = currentStroke,
                onStrokeStart = { newStroke -> currentStroke = newStroke },
                onStrokeUpdate = { updatedStroke -> currentStroke = updatedStroke },
                onStrokeEnd = {
                    currentStroke?.let { viewModel.addStroke(it.copy(noteId = note.id)) }
                    currentStroke = null
                },
                toolType = when (canvasUiState.selectedTool) {
                    DrawingTool.Pen -> ToolType.PEN
                    DrawingTool.Highlighter -> ToolType.HIGHLIGHTER
                    DrawingTool.Eraser -> ToolType.ERASER
                    DrawingTool.Lasso -> ToolType.LASSO
                    DrawingTool.Text -> ToolType.TEXT
                },
                strokeColor = canvasUiState.penSlots.getOrNull(canvasUiState.selectedPenSlot)?.color ?: androidx.compose.ui.graphics.Color.Black,
                strokeWidth = canvasUiState.penSlots.getOrNull(canvasUiState.selectedPenSlot)?.strokeWidth ?: 1f,
                showGrid = canvasUiState.gridState == GridState.Grid || canvasUiState.gridState == GridState.Both,
                showDots = canvasUiState.gridState == GridState.Dots || canvasUiState.gridState == GridState.Both,
                stylusOnly = false,
                onEraseStrokes = { strokesToDelete ->
                    strokesToDelete.forEach { stroke -> viewModel.deleteStroke(stroke) }
                }
            )

            var offset by remember { mutableStateOf(Offset.Zero) }

            Box(
                modifier = Modifier
                    .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offset += dragAmount
                        }
                    }
            ) {
                CanvasToolbar(
                    uiState = canvasUiState,
                    onToolChange = { viewModel.setDrawingTool(it) },
                    onUndo = { viewModel.undo() },
                    onRedo = { viewModel.redo() },
                    onToggleGrid = { viewModel.toggleGrid() },
                    onZoomReset = { viewModel.resetZoom() },
                    onZoomIn = { viewModel.zoomIn() },
                    onZoomOut = { viewModel.zoomOut() },
                    onBrushSizeChange = { size ->
                        when (canvasUiState.selectedTool) {
                            DrawingTool.Pen -> viewModel.setPenStrokeWidth(size)
                            DrawingTool.Highlighter -> viewModel.setHighlighterStrokeWidth(size)
                            else -> {}
                        }
                    },
                    onSelectPenSlot = { viewModel.selectPenSlot(it) },
                    onSelectHighlighterSlot = { viewModel.selectHighlighterSlot(it) },
                    onMore = { viewModel.showMoreOptions() }
                )
            }
        }
    } else {
        Scaffold(
            topBar = {
                CanvasToolbar(
                    uiState = canvasUiState,
                    onToolChange = { viewModel.setDrawingTool(it) },
                    onUndo = { viewModel.undo() },
                    onRedo = { viewModel.redo() },
                    onToggleGrid = { viewModel.toggleGrid() },
                    onZoomReset = { viewModel.resetZoom() },
                    onZoomIn = { viewModel.zoomIn() },
                    onZoomOut = { viewModel.zoomOut() },
                    onBrushSizeChange = { size ->
                        when (canvasUiState.selectedTool) {
                            DrawingTool.Pen -> viewModel.setPenStrokeWidth(size)
                            DrawingTool.Highlighter -> viewModel.setHighlighterStrokeWidth(size)
                            else -> {}
                        }
                    },
                    onSelectPenSlot = { viewModel.selectPenSlot(it) },
                    onSelectHighlighterSlot = { viewModel.selectHighlighterSlot(it) },
                    onMore = { viewModel.showMoreOptions() }
                )
            }
        ) { innerPadding ->
            DrawingCanvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                strokes = strokes,
                currentStroke = currentStroke,
                onStrokeStart = { newStroke -> currentStroke = newStroke },
                onStrokeUpdate = { updatedStroke -> currentStroke = updatedStroke },
                onStrokeEnd = {
                    currentStroke?.let { viewModel.addStroke(it.copy(noteId = note.id)) }
                    currentStroke = null
                },
                toolType = when (canvasUiState.selectedTool) {
                    DrawingTool.Pen -> ToolType.PEN
                    DrawingTool.Highlighter -> ToolType.HIGHLIGHTER
                    DrawingTool.Eraser -> ToolType.ERASER
                    DrawingTool.Lasso -> ToolType.LASSO
                    DrawingTool.Text -> ToolType.TEXT
                },
                strokeColor = canvasUiState.penSlots.getOrNull(canvasUiState.selectedPenSlot)?.color ?: androidx.compose.ui.graphics.Color.Black,
                strokeWidth = canvasUiState.penSlots.getOrNull(canvasUiState.selectedPenSlot)?.strokeWidth ?: 1f,
                showGrid = canvasUiState.gridState == GridState.Grid || canvasUiState.gridState == GridState.Both,
                showDots = canvasUiState.gridState == GridState.Dots || canvasUiState.gridState == GridState.Both,
                stylusOnly = false,
                onEraseStrokes = { strokesToDelete ->
                    strokesToDelete.forEach { stroke -> viewModel.deleteStroke(stroke) }
                }
            )
        }
    }
}
