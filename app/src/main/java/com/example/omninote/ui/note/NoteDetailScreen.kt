package com.example.omninote.ui.note

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
                    // Show a save button only for text notes
                    if (note.type == NoteType.TEXT) {
                        IconButton(onClick = {
                            // Explicitly save the note and then close.
                            viewModel.updateNote(note.copy(content = currentContent))
                            onClose()
                        }) {
                            Icon(Icons.Default.Done, contentDescription = "Save and Close")
                        }
                    }
                }
            )
        }
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
