package com.example.omninote.viewmodel;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 M2\u00020\u0001:\u0001MB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u00a2\u0006\u0002\u0010\u001eJ\u0015\u0010\u001f\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010\u001d\u00a2\u0006\u0002\u0010\u001eJ\u000e\u0010!\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\'J\u000e\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*J\u000e\u0010+\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*J\u000e\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.J\u000e\u0010/\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.J\u0006\u00100\u001a\u00020\u001bJ\u0006\u00101\u001a\u00020\u001bJ\u0006\u00102\u001a\u00020\u001bJ\u0006\u00103\u001a\u00020\u001bJ\u000e\u00104\u001a\u00020\u001b2\u0006\u00105\u001a\u00020.J\u0006\u00106\u001a\u00020\u001bJ\u0006\u00107\u001a\u00020\u001bJ\u0006\u00108\u001a\u00020\u001bJ\u000e\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020;J%\u0010<\u001a\u00020\u001b2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010\u001d\u00a2\u0006\u0002\u0010BJ\u000e\u0010C\u001a\u00020\u001b2\u0006\u0010D\u001a\u00020EJ\u000e\u0010F\u001a\u00020\u001b2\u0006\u0010D\u001a\u00020EJ\u0010\u0010G\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001dH\u0002J\u000e\u0010H\u001a\u00020\u001b2\u0006\u0010I\u001a\u00020\u0017J\u000e\u0010J\u001a\u00020\u001b2\u0006\u0010I\u001a\u00020\u0017J\u000e\u0010K\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001dJ\b\u0010L\u001a\u00020\u001bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010\u00a8\u0006N"}, d2 = {"Lcom/example/omninote/viewmodel/NoteViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/omninote/repository/NoteRepository;", "userPreferencesRepository", "Lcom/example/omninote/data/UserPreferencesRepository;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "<init>", "(Lcom/example/omninote/repository/NoteRepository;Lcom/example/omninote/data/UserPreferencesRepository;Landroidx/lifecycle/SavedStateHandle;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/omninote/viewmodel/UiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "_canvasUiState", "Lcom/example/omninote/ui/note/CanvasUiState;", "canvasUiState", "getCanvasUiState", "_strokes", "", "Lcom/example/omninote/data/Stroke;", "strokes", "getStrokes", "setCurrentFolder", "", "folderId", "", "(Ljava/lang/Long;)V", "selectNote", "noteId", "toggleFolder", "changeScreen", "screen", "Lcom/example/omninote/viewmodel/Screen;", "setDrawingTool", "tool", "Lcom/example/omninote/ui/note/DrawingTool;", "selectPenSlot", "index", "", "selectHighlighterSlot", "setPenStrokeWidth", "width", "", "setHighlighterStrokeWidth", "undo", "redo", "toggleGrid", "resetZoom", "setZoom", "scale", "zoomIn", "zoomOut", "showMoreOptions", "setFloatingToolbar", "isFloating", "", "createNote", "title", "", "type", "Lcom/example/omninote/data/NoteType;", "parentId", "(Ljava/lang/String;Lcom/example/omninote/data/NoteType;Ljava/lang/Long;)V", "updateNote", "note", "Lcom/example/omninote/data/Note;", "deleteNote", "loadStrokesForNote", "addStroke", "stroke", "deleteStroke", "clearStrokes", "loadKnowledgeGraph", "Companion", "app_debug"})
public final class NoteViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.omninote.repository.NoteRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.omninote.data.UserPreferencesRepository userPreferencesRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.SavedStateHandle savedStateHandle = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SELECTED_NOTE_ID = "selected_note_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CURRENT_SCREEN = "current_screen";
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.omninote.viewmodel.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.omninote.viewmodel.UiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.omninote.ui.note.CanvasUiState> _canvasUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.omninote.ui.note.CanvasUiState> canvasUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.omninote.data.Stroke>> _strokes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.omninote.data.Stroke>> strokes = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.omninote.viewmodel.NoteViewModel.Companion Companion = null;
    
    public NoteViewModel(@org.jetbrains.annotations.NotNull()
    com.example.omninote.repository.NoteRepository repository, @org.jetbrains.annotations.NotNull()
    com.example.omninote.data.UserPreferencesRepository userPreferencesRepository, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.omninote.viewmodel.UiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.omninote.ui.note.CanvasUiState> getCanvasUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.omninote.data.Stroke>> getStrokes() {
        return null;
    }
    
    public final void setCurrentFolder(@org.jetbrains.annotations.Nullable()
    java.lang.Long folderId) {
    }
    
    public final void selectNote(@org.jetbrains.annotations.Nullable()
    java.lang.Long noteId) {
    }
    
    public final void toggleFolder(long folderId) {
    }
    
    public final void changeScreen(@org.jetbrains.annotations.NotNull()
    com.example.omninote.viewmodel.Screen screen) {
    }
    
    public final void setDrawingTool(@org.jetbrains.annotations.NotNull()
    com.example.omninote.ui.note.DrawingTool tool) {
    }
    
    public final void selectPenSlot(int index) {
    }
    
    public final void selectHighlighterSlot(int index) {
    }
    
    public final void setPenStrokeWidth(float width) {
    }
    
    public final void setHighlighterStrokeWidth(float width) {
    }
    
    public final void undo() {
    }
    
    public final void redo() {
    }
    
    public final void toggleGrid() {
    }
    
    public final void resetZoom() {
    }
    
    public final void setZoom(float scale) {
    }
    
    public final void zoomIn() {
    }
    
    public final void zoomOut() {
    }
    
    public final void showMoreOptions() {
    }
    
    public final void setFloatingToolbar(boolean isFloating) {
    }
    
    public final void createNote(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    com.example.omninote.data.NoteType type, @org.jetbrains.annotations.Nullable()
    java.lang.Long parentId) {
    }
    
    public final void updateNote(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Note note) {
    }
    
    public final void deleteNote(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Note note) {
    }
    
    private final void loadStrokesForNote(long noteId) {
    }
    
    public final void addStroke(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Stroke stroke) {
    }
    
    public final void deleteStroke(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Stroke stroke) {
    }
    
    public final void clearStrokes(long noteId) {
    }
    
    private final void loadKnowledgeGraph() {
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/example/omninote/viewmodel/NoteViewModel$Companion;", "", "<init>", "()V", "KEY_SELECTED_NOTE_ID", "", "KEY_CURRENT_SCREEN", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}