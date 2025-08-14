package com.example.omninote.ui.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.omninote.data.Note
import com.example.omninote.data.Stroke
import com.example.omninote.data.ToolType
import com.example.omninote.ui.common.AdvancedCanvasToolbar
import com.example.omninote.ui.common.DrawingCanvas
import com.example.omninote.viewmodel.NoteViewModel

@Composable
fun CanvasScreen(note: Note, viewModel: NoteViewModel) {
    val strokes by viewModel.strokes.collectAsState()
    var currentStroke by remember { mutableStateOf<Stroke?>(null) }

    // Local state for canvas tools
    var currentTool by remember { mutableStateOf(ToolType.PEN) }
    var strokeColor by remember { mutableStateOf(Color.Black) }
    var strokeWidth by remember { mutableFloatStateOf(6f) }

    val redoStack = remember { mutableStateListOf<Stroke>() }

    Box(modifier = Modifier.fillMaxSize()) {
        DrawingCanvas(
            strokes = strokes,
            currentStroke = currentStroke,
            onStrokeStart = { newStroke -> currentStroke = newStroke },
            onStrokeUpdate = { updatedStroke -> currentStroke = updatedStroke },
            onStrokeEnd = { finalStroke ->
                viewModel.addStroke(finalStroke.copy(noteId = note.id))
                redoStack.clear()
                currentStroke = null
            },
            toolType = currentTool,
            strokeColor = strokeColor,
            strokeWidth = strokeWidth,
            showGrid = true,
            showDots = false,
            stylusOnly = false,
            onEraseStrokes = { strokesToDelete ->
                strokesToDelete.forEach { stroke -> viewModel.deleteStroke(stroke) }
            }
        )

        // New, more functional toolbar
        AdvancedCanvasToolbar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp),
            currentTool = currentTool,
            onToolChange = { currentTool = it },
            currentColor = strokeColor,
            onColorChange = { strokeColor = it },
            currentWidth = strokeWidth,
            onWidthChange = { strokeWidth = it },
            onUndo = {
                strokes.lastOrNull()?.let { lastStroke ->
                    redoStack.add(lastStroke)
                    viewModel.deleteStroke(lastStroke)
                }
            },
            onRedo = {
                redoStack.removeLastOrNull()?.let {
                    viewModel.addStroke(it)
                }
            },
            onClear = { viewModel.clearStrokes(note.id) }
        )
    }
}
