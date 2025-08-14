package com.example.omninote.repository

import com.example.omninote.data.*
import com.example.omninote.data.dao.NoteDao
import com.example.omninote.data.dao.NoteLinkDao
import com.example.omninote.data.dao.StrokeDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime

class NoteRepository(
    private val noteDao: NoteDao,
    private val strokeDao: StrokeDao,
    private val noteLinkDao: NoteLinkDao
) {
    // Note operations
    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()

    fun getTopLevelNotes(): Flow<List<Note>> = noteDao.getTopLevelNotes()

    fun getChildNotes(parentId: Long): Flow<List<Note>> = noteDao.getChildNotesFlow(parentId)
    
    suspend fun getNoteById(id: Long): Note? = noteDao.getNoteById(id)
    
    suspend fun searchNotes(query: String): List<Note> = noteDao.searchNotes(query)
    
    suspend fun getNotesByTag(tag: String): List<Note> = noteDao.getNotesByTag(tag)
    
    suspend fun insertNote(note: Note): Long {
        val noteId = noteDao.insertNote(note)
        // Auto-link based on content similarity
        autoLinkNote(noteId, note.content)
        return noteId
    }
    
    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
        // Re-analyze links when content changes
        autoLinkNote(note.id, note.content)
    }
    
    suspend fun deleteNote(note: Note) {
        strokeDao.deleteAllStrokesForNote(note.id)
        noteLinkDao.deleteAllLinksForNote(note.id)
        noteDao.deleteNote(note)
    }
    
    suspend fun archiveNote(noteId: Long) = noteDao.archiveNote(noteId)
    
    // Stroke operations
    fun getStrokesForNote(noteId: Long): Flow<List<Stroke>> = strokeDao.getStrokesForNote(noteId)
    
    suspend fun insertStroke(stroke: Stroke): Long = strokeDao.insertStroke(stroke)
    
    suspend fun insertStrokes(strokes: List<Stroke>) = strokeDao.insertStrokes(strokes)
    
    suspend fun deleteStrokesForNote(noteId: Long) = strokeDao.deleteAllStrokesForNote(noteId)

    suspend fun deleteStroke(stroke: Stroke) = strokeDao.deleteStroke(stroke)

    // Link operations
    fun getLinksForNote(noteId: Long): Flow<List<NoteLink>> = noteLinkDao.getLinksForNote(noteId)
    
    suspend fun createLink(sourceId: Long, targetId: Long, linkType: LinkType = LinkType.RELATED) {
        val link = NoteLink(sourceId, targetId, linkType)
        noteLinkDao.insertLink(link)
    }
    
    suspend fun deleteLink(sourceId: Long, targetId: Long) {
        noteLinkDao.getLink(sourceId, targetId)?.let { noteLinkDao.deleteLink(it) }
    }
    
    suspend fun getOutgoingLinks(noteId: Long): List<NoteLink> = noteLinkDao.getOutgoingLinks(noteId)
    
    suspend fun getIncomingLinks(noteId: Long): List<NoteLink> = noteLinkDao.getIncomingLinks(noteId)
    
    // Knowledge graph operations
    suspend fun getKnowledgeGraph(): Map<Long, List<Long>> {
        val allNotes = noteDao.getAllNotes().first()
        val graph = mutableMapOf<Long, MutableList<Long>>()
        
        allNotes.forEach { note ->
            val links = noteLinkDao.getOutgoingLinks(note.id)
            graph[note.id] = links.map { it.targetNoteId }.toMutableList()
        }
        
        return graph
    }
    
    // Auto-linking based on content similarity
    private suspend fun autoLinkNote(noteId: Long, content: String) {
        val allNotes = noteDao.getAllNotes().first()
        val words = content.lowercase().split(Regex("""\s+""")).filter { it.length > 3 }
        
        allNotes.filter { it.id != noteId }.forEach { otherNote ->
            val otherWords = otherNote.content.lowercase().split(Regex("""\s+""")).filter { it.length > 3 }
            val commonWords = words.intersect(otherWords.toSet())
            
            if (commonWords.size >= 2) {
                // Create automatic link if there are common words
                val existingLink = noteLinkDao.getLink(noteId, otherNote.id)
                if (existingLink == null) {
                    createLink(noteId, otherNote.id, LinkType.SIMILAR)
                }
            }
        }
    }

    // Public function to ensure sample data exists
    suspend fun ensureSampleDataLoaded() {
        // Clear all existing data
        val existingNotes = noteDao.getAllNotes().first()
        existingNotes.forEach { note ->
            deleteNote(note)
        }
    }
    
    // Get related notes for a given note
    suspend fun getRelatedNotes(noteId: Long): List<Note> {
        val links = noteLinkDao.getOutgoingLinks(noteId)
        val relatedIds = links.map { it.targetNoteId }
        return relatedIds.mapNotNull { noteDao.getNoteById(it) }
    }
}
