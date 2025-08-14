package com.example.omninote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omninote.data.Note
import com.example.omninote.data.NoteType
import com.example.omninote.data.Stroke
import com.example.omninote.repository.NoteRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime

// Represents the overall UI state of the application
data class UiState(
    val notes: List<Note> = emptyList(),
    val currentFolderId: Long? = null,
    val selectedNoteId: Long? = null,
    val expandedFolderIds: Set<Long> = setOf(),
    val currentScreen: Screen = Screen.Explorer,
    val knowledgeGraph: Map<Long, List<Long>> = emptyMap()
)

// Defines the different screens in the app
enum class Screen {
    Explorer,
    Graph,
    Settings
}

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // Separate flow for strokes of the currently selected note
    private val _strokes = MutableStateFlow<List<Stroke>>(emptyList())
    val strokes: StateFlow<List<Stroke>> = _strokes.asStateFlow()

    init {
        // Load initial data when the ViewModel is created
        viewModelScope.launch {
            repository.getAllNotes().collect { notes ->
                _uiState.update { it.copy(notes = notes) }
            }
        }
        loadKnowledgeGraph()
    }

    // Public functions to handle user actions and update the state

    fun setCurrentFolder(folderId: Long?) {
        _uiState.update { it.copy(currentFolderId = folderId, selectedNoteId = null) }
    }

    fun selectNote(noteId: Long?) {
        _uiState.update { it.copy(selectedNoteId = noteId) }
        if (noteId != null) {
            loadStrokesForNote(noteId)
        } else {
            _strokes.value = emptyList()
        }
    }

    fun toggleFolder(folderId: Long) {
        val currentExpanded = _uiState.value.expandedFolderIds
        _uiState.update {
            it.copy(
                expandedFolderIds = if (currentExpanded.contains(folderId)) {
                    currentExpanded - folderId
                } else {
                    currentExpanded + folderId
                }
            )
        }
    }

    fun changeScreen(screen: Screen) {
        _uiState.update { it.copy(currentScreen = screen) }
    }

    fun createNote(title: String, type: NoteType, parentId: Long?) {
        viewModelScope.launch {
            val newNote = Note(
                title = title,
                type = type,
                parentNoteId = parentId,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
            val newNoteId = repository.insertNote(newNote)
            // If it's not a folder, select it immediately
            if (type != NoteType.FOLDER) {
                selectNote(newNoteId)
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note.copy(updatedAt = LocalDateTime.now()))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
            // If the deleted note was selected, clear the selection
            if (_uiState.value.selectedNoteId == note.id) {
                selectNote(null)
            }
        }
    }

    // Stroke-related operations
    private fun loadStrokesForNote(noteId: Long) {
        viewModelScope.launch {
            repository.getStrokesForNote(noteId).collect { strokesList ->
                _strokes.value = strokesList
            }
        }
    }

    fun addStroke(stroke: Stroke) {
        viewModelScope.launch {
            repository.insertStroke(stroke)
        }
    }

    fun deleteStroke(stroke: Stroke) {
        viewModelScope.launch {
            repository.deleteStroke(stroke)
        }
    }

    fun clearStrokes(noteId: Long) {
        viewModelScope.launch {
            repository.deleteStrokesForNote(noteId)
        }
    }

    // Knowledge Graph
    private fun loadKnowledgeGraph() {
        viewModelScope.launch {
            val graph = repository.getKnowledgeGraph()
            _uiState.update { it.copy(knowledgeGraph = graph) }
        }
    }
}
