package com.example.omninote.ui.note

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.omninote.data.Note
import com.example.omninote.data.NoteType
import com.example.omninote.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    note: Note,
    viewModel: NoteViewModel,
    onClose: () -> Unit
) {
    // Local state to hold the current content of the note being edited.
    var currentContent by remember { mutableStateOf(note.content) }

    // This effect will automatically save the note when the user leaves the screen.
    DisposableEffect(note.id) {
        onDispose {
            // Check if the content has actually changed before updating.
            if (currentContent != note.content) {
                viewModel.updateNote(note.copy(content = currentContent))
            }
        }
    }

    val uiState by viewModel.uiState.collectAsState()
    var showGrid by rememberSaveable { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(note.title) },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(Icons.Default.Close, contentDescription = "Close Note")
                    }
                },
                actions = {
                    if (note.type == NoteType.TEXT) {
                        IconButton(onClick = {
                            viewModel.updateNote(note.copy(content = currentContent))
                            onClose()
                        }) {
                            Icon(Icons.Default.Done, contentDescription = "Save and Close")
                        }
                    }
                }
            )
        },

    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(horizontal = 16.dp)) {
            when (note.type) {
                NoteType.TEXT -> {
                    // Use the new TextEditor composable
                    TextEditor(
                        initialContent = note.content,
                        onContentChange = { newContent ->
                            currentContent = newContent
                        }
                    )
                }
                NoteType.CANVAS -> {
                    CanvasScreen(note = note, viewModel = viewModel)
                }
                else -> {
                    Text("This is a ${note.type.name} note.")
                }
            }
        }
    }
}
