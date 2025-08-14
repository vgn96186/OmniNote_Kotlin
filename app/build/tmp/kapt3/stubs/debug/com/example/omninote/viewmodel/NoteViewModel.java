package com.example.omninote.viewmodel;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0002\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0002\u0010\u0016J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cJ%\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0002\u0010#J\u000e\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020&J\u000e\u0010\'\u001a\u00020\u00132\u0006\u0010%\u001a\u00020&J\u0010\u0010(\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0015H\u0002J\u000e\u0010)\u001a\u00020\u00132\u0006\u0010*\u001a\u00020\u000fJ\u000e\u0010+\u001a\u00020\u00132\u0006\u0010*\u001a\u00020\u000fJ\u000e\u0010,\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0015J\b\u0010-\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f\u00a8\u0006."}, d2 = {"Lcom/example/omninote/viewmodel/NoteViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/omninote/repository/NoteRepository;", "<init>", "(Lcom/example/omninote/repository/NoteRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/omninote/viewmodel/UiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "_strokes", "", "Lcom/example/omninote/data/Stroke;", "strokes", "getStrokes", "setCurrentFolder", "", "folderId", "", "(Ljava/lang/Long;)V", "selectNote", "noteId", "toggleFolder", "changeScreen", "screen", "Lcom/example/omninote/viewmodel/Screen;", "createNote", "title", "", "type", "Lcom/example/omninote/data/NoteType;", "parentId", "(Ljava/lang/String;Lcom/example/omninote/data/NoteType;Ljava/lang/Long;)V", "updateNote", "note", "Lcom/example/omninote/data/Note;", "deleteNote", "loadStrokesForNote", "addStroke", "stroke", "deleteStroke", "clearStrokes", "loadKnowledgeGraph", "app_debug"})
public final class NoteViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.omninote.repository.NoteRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.omninote.viewmodel.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.omninote.viewmodel.UiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.omninote.data.Stroke>> _strokes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.omninote.data.Stroke>> strokes = null;
    
    public NoteViewModel(@org.jetbrains.annotations.NotNull()
    com.example.omninote.repository.NoteRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.omninote.viewmodel.UiState> getUiState() {
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
}