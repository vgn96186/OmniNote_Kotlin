package com.example.omninote.data.dao

import androidx.room.*
import com.example.omninote.data.Stroke
import kotlinx.coroutines.flow.Flow

@Dao
interface StrokeDao {
    @Query("SELECT * FROM strokes WHERE noteId = :noteId ORDER BY timestamp ASC")
    fun getStrokesForNote(noteId: Long): Flow<List<Stroke>>

    @Query("SELECT * FROM strokes WHERE noteId = :noteId AND layer = :layer ORDER BY timestamp ASC")
    suspend fun getStrokesForNoteAndLayer(noteId: Long, layer: Int): List<Stroke>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStroke(stroke: Stroke): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStrokes(strokes: List<Stroke>)

    @Update
    suspend fun updateStroke(stroke: Stroke)

    @Delete
    suspend fun deleteStroke(stroke: Stroke)

    @Query("DELETE FROM strokes WHERE noteId = :noteId")
    suspend fun deleteAllStrokesForNote(noteId: Long)

    @Query("DELETE FROM strokes WHERE noteId = :noteId AND layer = :layer")
    suspend fun deleteStrokesForNoteAndLayer(noteId: Long, layer: Int)

    @Query("SELECT * FROM strokes WHERE noteId = :noteId AND timestamp > :sinceTimestamp ORDER BY timestamp ASC")
    suspend fun getStrokesSince(noteId: Long, sinceTimestamp: Long): List<Stroke>
}
