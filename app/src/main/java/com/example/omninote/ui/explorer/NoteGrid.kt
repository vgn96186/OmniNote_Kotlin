package com.example.omninote.ui.explorer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.omninote.data.Note
import com.example.omninote.data.NoteType

@Composable
fun NoteGrid(
    notes: List<Note>,
    currentFolderId: Long?,
    selectedNoteId: Long?,
    onNoteClick: (Note) -> Unit,
    onFolderClick: (Note) -> Unit,
    onCreateNote: (NoteType) -> Unit
) {
    val itemsInFolder = notes.filter { it.parentNoteId == currentFolderId }
    var showFabMenu by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            Column(horizontalAlignment = Alignment.End) {
                if (showFabMenu) {
                    SmallFloatingActionButton(
                        onClick = { onCreateNote(NoteType.FOLDER); showFabMenu = false },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) { Icon(Icons.Default.CreateNewFolder, "New Folder") }

                    SmallFloatingActionButton(
                        onClick = { onCreateNote(NoteType.CANVAS); showFabMenu = false },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) { Icon(Icons.Default.Draw, "New Canvas") }

                    SmallFloatingActionButton(
                        onClick = { onCreateNote(NoteType.TEXT); showFabMenu = false },
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) { Icon(Icons.Default.Description, "New Text Note") }
                }
                FloatingActionButton(onClick = { showFabMenu = !showFabMenu }) {
                    Icon(Icons.Default.Add, "Add")
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Breadcrumbs
            val path = remember(notes, currentFolderId) {
                buildBreadcrumbs(notes, currentFolderId)
            }
            Breadcrumbs(path = path)

            if (itemsInFolder.isEmpty()) {
                EmptyFolderView()
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(180.dp),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(itemsInFolder, key = { it.id }) { note ->
                        NoteThumbnail(
                            note = note,
                            isSelected = note.id == selectedNoteId,
                            onClick = {
                                if (note.type == NoteType.FOLDER) onFolderClick(note)
                                else onNoteClick(note)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyFolderView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Inbox,
                contentDescription = "Empty Folder",
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
            )
            Text(
                "Folder is empty",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            Text(
                "Use the '+' button to create a new note or folder.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}
