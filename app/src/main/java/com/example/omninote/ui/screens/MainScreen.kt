package com.example.omninote.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
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
            // Responsive navigation layout
            val configuration = LocalConfiguration.current
            val isCompact = configuration.screenWidthDp < 840
            Row(Modifier.fillMaxSize()) {
                // Responsive Navigation Rail with fixed width
                NavigationRail(
                    modifier = Modifier.width(if (isCompact) 72.dp else 80.dp),
                    header = {
                        Icon(
                            imageVector = Icons.Filled.Apps,
                            contentDescription = "OmniNote App",
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                ) {
                    NavigationRailItem(
                        selected = uiState.currentScreen == Screen.Explorer,
                        onClick = { viewModel.changeScreen(Screen.Explorer) },
                        icon = {
                            Icon(
                                Icons.Outlined.Folder,
                                contentDescription = "File Explorer"
                            )
                        },
                        label = if (!isCompact) {{ Text("Explorer") }} else null,
                        modifier = Modifier.semantics {
                            contentDescription = "Navigate to file explorer"
                            selected = uiState.currentScreen == Screen.Explorer
                        }
                    )
                    NavigationRailItem(
                        selected = uiState.currentScreen == Screen.Graph,
                        onClick = { viewModel.changeScreen(Screen.Graph) },
                        icon = {
                            Icon(
                                Icons.Outlined.AccountTree,
                                contentDescription = "Knowledge Graph"
                            )
                        },
                        label = if (!isCompact) {{ Text("Graph") }} else null,
                        modifier = Modifier.semantics {
                            contentDescription = "Navigate to knowledge graph"
                            selected = uiState.currentScreen == Screen.Graph
                        }
                    )
                    NavigationRailItem(
                        selected = uiState.currentScreen == Screen.Settings,
                        onClick = { viewModel.changeScreen(Screen.Settings) },
                        icon = {
                            Icon(
                                Icons.Outlined.Settings,
                                contentDescription = "Settings"
                            )
                        },
                        label = if (!isCompact) {{ Text("Settings") }} else null,
                        modifier = Modifier.semantics {
                            contentDescription = "Navigate to settings"
                            selected = uiState.currentScreen == Screen.Settings
                        }
                    )
                }

                // Main content area with weighted box to prevent rail overlap
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    // Top App Bar for current screen
                    when (uiState.currentScreen) {
                        Screen.Explorer -> {
                            ExplorerTopBar(viewModel = viewModel)
                            FolderExplorerScreen(viewModel = viewModel)
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
                            SettingsTopBar()
                            SettingsScreen(
                                uiState = uiState,
                                onToggleFloatingToolbar = { viewModel.setFloatingToolbar(it) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExplorerTopBar(viewModel: NoteViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    TopAppBar(
        title = { Text("Notes Explorer") },
        actions = {
            IconButton(onClick = { /* Search */ }) {
                Icon(Icons.Outlined.Search, "Search notes")
            }
            IconButton(onClick = { /* Add note */ }) {
                Icon(Icons.Outlined.Add, "Add note")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsTopBar() {
    TopAppBar(
        title = { Text("Settings") },
        actions = {
            IconButton(onClick = { /* Help */ }) {
                Icon(Icons.AutoMirrored.Outlined.Help, contentDescription = "Help")
            }
        }
    )
}
