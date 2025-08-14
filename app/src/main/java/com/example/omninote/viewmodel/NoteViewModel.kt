package com.example.omninote.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omninote.data.Note
import com.example.omninote.data.NoteType
import com.example.omninote.data.Stroke
import com.example.omninote.data.UserPreferencesRepository
import com.example.omninote.repository.NoteRepository
import com.example.omninote.ui.note.CanvasUiState
import com.example.omninote.ui.note.DrawingTool
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
    val knowledgeGraph: Map<Long, List<Long>> = emptyMap(),
    val isFloatingToolbar: Boolean = false
)

// Defines the different screens in the app
enum class Screen {
    Explorer,
    Graph,
    Settings
}

class NoteViewModel(
    private val repository: NoteRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY_SELECTED_NOTE_ID = "selected_note_id"
        private const val KEY_CURRENT_SCREEN = "current_screen"
    }

    private val _uiState = MutableStateFlow(
        UiState(
            selectedNoteId = savedStateHandle.get<Long>(KEY_SELECTED_NOTE_ID),
            currentScreen = Screen.valueOf(
                savedStateHandle.get<String>(KEY_CURRENT_SCREEN) ?: Screen.Explorer.name
            )
        )
    )
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _canvasUiState = MutableStateFlow(CanvasUiState())
    val canvasUiState: StateFlow<CanvasUiState> = _canvasUiState.asStateFlow()

    private val _strokes = MutableStateFlow<List<Stroke>>(emptyList())
    val strokes: StateFlow<List<Stroke>> = _strokes.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllNotes().collect { notes ->
                _uiState.update { it.copy(notes = notes) }
            }
        }
        loadKnowledgeGraph()
        // Load strokes for restored selected note
        _uiState.value.selectedNoteId?.let { noteId ->
            loadStrokesForNote(noteId)
        }

        viewModelScope.launch {
            userPreferencesRepository.isFloatingToolbar.collect { isFloating ->
                _uiState.update { it.copy(isFloatingToolbar = isFloating) }
            }
        }
    }

    // Public functions to handle user actions and update the state

    fun setCurrentFolder(folderId: Long?) {
        _uiState.update { it.copy(currentFolderId = folderId, selectedNoteId = null) }
    }

    fun selectNote(noteId: Long?) {
        savedStateHandle[KEY_SELECTED_NOTE_ID] = noteId
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
        savedStateHandle[KEY_CURRENT_SCREEN] = screen.name
        _uiState.update { it.copy(currentScreen = screen) }
    }

    fun setDrawingTool(tool: DrawingTool) {
        _canvasUiState.update { it.copy(selectedTool = tool) }
    }

    fun selectPenSlot(index: Int) {
        _canvasUiState.update { it.copy(selectedPenSlot = index) }
    }

    fun selectHighlighterSlot(index: Int) {
        _canvasUiState.update { it.copy(selectedHighlighterSlot = index) }
    }

    // Brush size controls
    fun setPenStrokeWidth(width: Float) {
        val clamped = width.coerceIn(1f, 20f)
        _canvasUiState.update { state ->
            val idx = state.selectedPenSlot.coerceIn(0, state.penSlots.lastIndex)
            val updated = state.penSlots.toMutableList().also {
                it[idx] = it[idx].copy(strokeWidth = clamped)
            }
            state.copy(penSlots = updated)
        }
    }

    fun setHighlighterStrokeWidth(width: Float) {
        val clamped = width.coerceIn(5f, 60f)
        _canvasUiState.update { state ->
            val idx = state.selectedHighlighterSlot.coerceIn(0, state.highlighterSlots.lastIndex)
            val updated = state.highlighterSlots.toMutableList().also {
                it[idx] = it[idx].copy(strokeWidth = clamped)
            }
            state.copy(highlighterSlots = updated)
        }
    }

    fun undo() {
        // Not implemented
    }

    fun redo() {
        // Not implemented
    }

    fun toggleGrid() {
        _canvasUiState.update {
            val nextState = when (it.gridState) {
                com.example.omninote.ui.note.GridState.Off -> com.example.omninote.ui.note.GridState.Grid
                com.example.omninote.ui.note.GridState.Grid -> com.example.omninote.ui.note.GridState.Dots
                com.example.omninote.ui.note.GridState.Dots -> com.example.omninote.ui.note.GridState.Both
                com.example.omninote.ui.note.GridState.Both -> com.example.omninote.ui.note.GridState.Off
            }
            it.copy(gridState = nextState)
        }
    }

    fun resetZoom() {
        _canvasUiState.update { it.copy(zoomScale = 1.0f) }
    }

    fun setZoom(scale: Float) {
        val clamped = scale.coerceIn(0.25f, 5.0f)
        _canvasUiState.update { it.copy(zoomScale = clamped) }
    }

    fun zoomIn() {
        val current = _canvasUiState.value.zoomScale
        setZoom(current * 1.25f)
    }

    fun zoomOut() {
        val current = _canvasUiState.value.zoomScale
        setZoom(current / 1.25f)
    }

    fun showMoreOptions() {
        // Not implemented
    }

    fun setFloatingToolbar(isFloating: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setFloatingToolbar(isFloating)
        }
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
