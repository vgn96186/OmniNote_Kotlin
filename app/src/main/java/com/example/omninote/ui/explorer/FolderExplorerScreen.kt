package com.example.omninote.ui.explorer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.omninote.viewmodel.NoteViewModel

@Composable
fun FolderExplorerScreen(viewModel: NoteViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Row {
        // Resizable Folder Tree Panel
        Box(modifier = Modifier.width(280.dp).fillMaxHeight()) {
            FolderTree(
                notes = uiState.notes,
                currentFolderId = uiState.currentFolderId,
                expandedIds = uiState.expandedFolderIds,
                onFolderClick = { viewModel.setCurrentFolder(it) },
                onToggleExpand = { viewModel.toggleFolder(it) }
            )
        }

        VerticalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

        // Note Grid Panel
        Box(modifier = Modifier.weight(1f)) {
            NoteGrid(
                notes = uiState.notes,
                currentFolderId = uiState.currentFolderId,
                selectedNoteId = uiState.selectedNoteId,
                onNoteClick = { viewModel.selectNote(it.id) },
                onFolderClick = { viewModel.setCurrentFolder(it.id) },
                onCreateNote = { type ->
                    viewModel.createNote("New Note", type, uiState.currentFolderId)
                }
            )
        }
    }
}
