package com.example.omninote.data.dao

import androidx.room.*
import com.example.omninote.data.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes WHERE isArchived = 0 ORDER BY updatedAt DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun getNoteById(noteId: Long): Note?

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    suspend fun searchNotes(query: String): List<Note>

    @Query("SELECT * FROM notes WHERE tags LIKE '%' || :tag || '%'")
    suspend fun getNotesByTag(tag: String): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note): Long

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("UPDATE notes SET isArchived = 1 WHERE id = :noteId")
    suspend fun archiveNote(noteId: Long)

    @Query("SELECT * FROM notes WHERE parentNoteId = :parentId")
    suspend fun getChildNotes(parentId: Long): List<Note>
}
