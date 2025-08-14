package com.example.omninote.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.omninote.ui.explorer.FolderExplorerScreen
import com.example.omninote.ui.note.NoteDetailScreen
import com.example.omninote.viewmodel.NoteViewModel
import com.example.omninote.viewmodel.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: NoteViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // --- THIS IS THE FULLSCREEN LOGIC ---
        // If a note ID is selected in the state, the app will ONLY compose the
        // NoteDetailScreen, which takes up the entire screen.
        if (uiState.selectedNoteId != null) {
            val selectedNote = uiState.notes.find { it.id == uiState.selectedNoteId }
            selectedNote?.let {
                NoteDetailScreen(
                    note = it,
                    viewModel = viewModel,
                    onClose = { viewModel.selectNote(null) }
                )
            }
        } else {
            // If NO note is selected, the app composes the main browsing layout
            // with the navigation rail and the explorer/graph/settings.
            Row(Modifier.fillMaxSize()) {
                // Main Navigation Rail
                NavigationRail(
                    header = {
                        Icon(
                            imageVector = Icons.Filled.Apps,
                            contentDescription = "App Logo",
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                ) {
                    Spacer(Modifier.weight(1f))
                    NavigationRailItem(
                        selected = uiState.currentScreen == Screen.Explorer,
                        onClick = { viewModel.changeScreen(Screen.Explorer) },
                        icon = { Icon(Icons.Outlined.Folder, "Explorer") },
                        label = { Text("Explorer") }
                    )
                    NavigationRailItem(
                        selected = uiState.currentScreen == Screen.Graph,
                        onClick = { viewModel.changeScreen(Screen.Graph) },
                        icon = { Icon(Icons.Outlined.AccountTree, "Graph") },
                        label = { Text("Graph") }
                    )
                    NavigationRailItem(
                        selected = uiState.currentScreen == Screen.Settings,
                        onClick = { viewModel.changeScreen(Screen.Settings) },
                        icon = { Icon(Icons.Outlined.Settings, "Settings") },
                        label = { Text("Settings") }
                    )
                    Spacer(Modifier.weight(1f))
                }

                // Main content area based on the selected screen
                when (uiState.currentScreen) {
                    Screen.Explorer -> {
                        Box(modifier = Modifier.weight(1f)) {
                            FolderExplorerScreen(viewModel = viewModel)
                        }
                    }
                    Screen.Graph -> {
                        KnowledgeGraphScreen(
                            viewModel = viewModel,
                            onOpenNote = { noteId ->
                                viewModel.changeScreen(Screen.Explorer)
                                viewModel.selectNote(noteId)
                            }
                        )
                    }
                    Screen.Settings -> {
                        SettingsScreen()
                    }
                }
            }
        }
    }
}
