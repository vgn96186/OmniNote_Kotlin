package com.example.omninote.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "note_links",
    primaryKeys = ["sourceNoteId", "targetNoteId"]
)
data class NoteLink(
    val sourceNoteId: Long,
    val targetNoteId: Long,
    val linkType: LinkType = LinkType.RELATED,
    val strength: Float = 1.0f,
    val createdAt: Long = System.currentTimeMillis()
)

enum class LinkType {
    RELATED,
    PARENT_CHILD,
    REFERENCE,
    SIMILAR
}
