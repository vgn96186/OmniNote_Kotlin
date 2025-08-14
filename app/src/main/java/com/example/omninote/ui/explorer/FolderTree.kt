package com.example.omninote.ui.explorer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.omninote.data.Note
import com.example.omninote.data.NoteType

@Composable
fun FolderTree(
    notes: List<Note>,
    currentFolderId: Long?,
    expandedIds: Set<Long>,
    onFolderClick: (Long?) -> Unit,
    onToggleExpand: (Long) -> Unit
) {
    val rootNotes = notes.filter { it.parentNoteId == null && it.type == NoteType.FOLDER }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceContainerLow)) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Workspace", style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = { onFolderClick(null) }) {
                Icon(Icons.Default.Home, contentDescription = "Go to Root")
            }
        }

        // Tree
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            items(rootNotes, key = { it.id }) { note ->
                TreeItem(
                    note = note,
                    allNotes = notes,
                    currentFolderId = currentFolderId,
                    expandedIds = expandedIds,
                    onFolderClick = onFolderClick,
                    onToggleExpand = onToggleExpand,
                    depth = 0
                )
            }
        }
    }
}

@Composable
private fun TreeItem(
    note: Note,
    allNotes: List<Note>,
    currentFolderId: Long?,
    expandedIds: Set<Long>,
    onFolderClick: (Long?) -> Unit,
    onToggleExpand: (Long) -> Unit,
    depth: Int
) {
    if (note.type != NoteType.FOLDER) return

    val isExpanded = expandedIds.contains(note.id)
    val isSelected = currentFolderId == note.id
    val childNotes = allNotes.filter { it.parentNoteId == note.id && it.type == NoteType.FOLDER }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = (depth * 12).dp)
                .clip(MaterialTheme.shapes.medium)
                .background(if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
                .clickable { onFolderClick(note.id) }
                .padding(horizontal = 8.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
                contentDescription = "Expand/Collapse",
                modifier = Modifier.clickable { onToggleExpand(note.id) }
            )
            Icon(
                imageVector = if (isExpanded) Icons.Filled.FolderOpen else Icons.Filled.Folder,
                contentDescription = "Folder",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = note.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }

        AnimatedVisibility(visible = isExpanded) {
            Column {
                childNotes.forEach { child ->
                    TreeItem(
                        note = child,
                        allNotes = allNotes,
                        currentFolderId = currentFolderId,
                        expandedIds = expandedIds,
                        onFolderClick = onFolderClick,
                        onToggleExpand = onToggleExpand,
                        depth = depth + 1
                    )
                }
            }
        }
    }
}
