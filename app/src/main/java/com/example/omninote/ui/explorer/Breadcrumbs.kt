package com.example.omninote.ui.explorer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.omninote.data.Note

fun buildBreadcrumbs(allNotes: List<Note>, folderId: Long?): List<Note> {
    if (folderId == null) return emptyList()
    val path = mutableListOf<Note>()
    val noteMap = allNotes.associateBy { it.id }
    var current = noteMap[folderId]
    while (current != null) {
        path.add(0, current)
        current = current.parentNoteId?.let { noteMap[it] }
    }
    return path
}

@Composable
fun Breadcrumbs(path: List<Note>) {
    Row(
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Root",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        path.forEach { note ->
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = note.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
