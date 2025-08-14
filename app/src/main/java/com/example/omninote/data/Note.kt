package com.example.omninote.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.omninote.data.converters.Converters
import java.time.LocalDateTime

@Entity(tableName = "notes")
@TypeConverters(Converters::class)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val tags: List<String> = emptyList(),
    val isArchived: Boolean = false,
    val parentNoteId: Long? = null,
    val positionX: Float = 0f,
    val positionY: Float = 0f,
    val width: Float = 300f,
    val height: Float = 400f,
    val type: NoteType = NoteType.TEXT
)

enum class NoteType {
    TEXT,
    CANVAS,
    KNOWLEDGE_GRAPH,
    FOLDER
}
