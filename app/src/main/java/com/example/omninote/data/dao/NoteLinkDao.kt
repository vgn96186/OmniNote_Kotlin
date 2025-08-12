package com.example.omninote.data.dao

import androidx.room.*
import com.example.omninote.data.NoteLink
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteLinkDao {
    @Query("SELECT * FROM note_links WHERE sourceNoteId = :noteId OR targetNoteId = :noteId")
    fun getLinksForNote(noteId: Long): Flow<List<NoteLink>>

    @Query("SELECT * FROM note_links WHERE sourceNoteId = :sourceId AND targetNoteId = :targetId")
    suspend fun getLink(sourceId: Long, targetId: Long): NoteLink?

    @Query("SELECT * FROM note_links WHERE sourceNoteId = :noteId")
    suspend fun getOutgoingLinks(noteId: Long): List<NoteLink>

    @Query("SELECT * FROM note_links WHERE targetNoteId = :noteId")
    suspend fun getIncomingLinks(noteId: Long): List<NoteLink>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLink(link: NoteLink)

    @Update
    suspend fun updateLink(link: NoteLink)

    @Delete
    suspend fun deleteLink(link: NoteLink)

    @Query("DELETE FROM note_links WHERE sourceNoteId = :noteId OR targetNoteId = :noteId")
    suspend fun deleteAllLinksForNote(noteId: Long)

    @Query("SELECT * FROM note_links WHERE linkType = :linkType")
    suspend fun getLinksByType(linkType: com.example.omninote.data.LinkType): List<NoteLink>
}
