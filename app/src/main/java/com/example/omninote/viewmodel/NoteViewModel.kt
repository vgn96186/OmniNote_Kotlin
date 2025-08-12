package com.example.omninote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omninote.data.Note
import com.example.omninote.data.Stroke
import com.example.omninote.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()
    
    private val _currentNote = MutableStateFlow<Note?>(null)
    val currentNote: StateFlow<Note?> = _currentNote.asStateFlow()
    
    private val _strokes = MutableStateFlow<List<Stroke>>(emptyList())
    val strokes: StateFlow<List<Stroke>> = _strokes.asStateFlow()
    
    private val _searchResults = MutableStateFlow<List<Note>>(emptyList())
    val searchResults: StateFlow<List<Note>> = _searchResults.asStateFlow()
    
    private val _knowledgeGraph = MutableStateFlow<Map<Long, List<Long>>>(emptyMap())
    val knowledgeGraph: StateFlow<Map<Long, List<Long>>> = _knowledgeGraph.asStateFlow()
    
    init {
        viewModelScope.launch {
            repository.ensureSampleDataLoaded()
        }
        loadNotes()
        loadKnowledgeGraph()
    }
    
    private fun loadNotes() {
        viewModelScope.launch {
            repository.getAllNotes().collect { notesList ->
                _notes.value = notesList
            }
        }
    }
    
    private fun loadKnowledgeGraph() {
        viewModelScope.launch {
            _knowledgeGraph.value = repository.getKnowledgeGraph()
        }
    }
    
    fun createNote(title: String, content: String = "") {
        viewModelScope.launch {
            val note = Note(
                title = title,
                content = content,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
            val noteId = repository.insertNote(note)
            loadNotes()
        }
    }
    
    fun selectNote(note: Note) {
        _currentNote.value = note
        loadStrokesForNote(note.id)
    }
    
    fun updateNote(note: Note) {
        viewModelScope.launch {
            val updatedNote = note.copy(updatedAt = LocalDateTime.now())
            repository.updateNote(updatedNote)
            if (_currentNote.value?.id == note.id) {
                _currentNote.value = updatedNote
            }
        }
    }
    
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
            if (_currentNote.value?.id == note.id) {
                _currentNote.value = null
                _strokes.value = emptyList()
            }
        }
    }
    
    fun searchNotes(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                _searchResults.value = emptyList()
            } else {
                _searchResults.value = repository.searchNotes(query)
            }
        }
    }
    
    fun addStroke(stroke: Stroke) {
        viewModelScope.launch {
            repository.insertStroke(stroke)
        }
    }
    
    fun addStrokes(strokes: List<Stroke>) {
        viewModelScope.launch {
            repository.insertStrokes(strokes)
        }
    }
    
    private fun loadStrokesForNote(noteId: Long) {
        viewModelScope.launch {
            repository.getStrokesForNote(noteId).collect { strokesList ->
                _strokes.value = strokesList
            }
        }
    }
    
    fun clearStrokes() {
        viewModelScope.launch {
            _currentNote.value?.let { note ->
                repository.deleteStrokesForNote(note.id)
            }
        }
    }
    
    fun getRelatedNotes(noteId: Long) {
        viewModelScope.launch {
            val related = repository.getRelatedNotes(noteId)
            // You could add a separate state flow for related notes if needed
        }
    }
    
    fun createLink(sourceId: Long, targetId: Long) {
        viewModelScope.launch {
            repository.createLink(sourceId, targetId)
            loadKnowledgeGraph()
        }
    }
    
    fun deleteLink(sourceId: Long, targetId: Long) {
        viewModelScope.launch {
            repository.deleteLink(sourceId, targetId)
            loadKnowledgeGraph()
        }
    }
}
