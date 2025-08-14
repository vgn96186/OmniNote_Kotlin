package com.example.omninote.data.dao;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u000f\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u001c\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0019\u001a\u00020\bH\'J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0019\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\u001b\u00c0\u0006\u0003"}, d2 = {"Lcom/example/omninote/data/dao/NoteDao;", "", "getAllNotes", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/omninote/data/Note;", "getNoteById", "noteId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchNotes", "query", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNotesByTag", "tag", "insertNote", "note", "(Lcom/example/omninote/data/Note;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNote", "", "deleteNote", "archiveNote", "getTopLevelNotes", "getChildNotesFlow", "parentId", "getChildNotesSuspend", "app_debug"})
@androidx.room.Dao()
public abstract interface NoteDao {
    
    @androidx.room.Query(value = "SELECT * FROM notes WHERE isArchived = 0 ORDER BY updatedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.omninote.data.Note>> getAllNotes();
    
    @androidx.room.Query(value = "SELECT * FROM notes WHERE id = :noteId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getNoteById(long noteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.omninote.data.Note> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM notes WHERE title LIKE \'%\' || :query || \'%\' OR content LIKE \'%\' || :query || \'%\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchNotes(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.Note>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM notes WHERE tags LIKE \'%\' || :tag || \'%\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getNotesByTag(@org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.Note>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertNote(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Note note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateNote(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Note note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteNote(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Note note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE notes SET isArchived = 1 WHERE id = :noteId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object archiveNote(long noteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM notes WHERE isArchived = 0 AND parentNoteId IS NULL ORDER BY updatedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.omninote.data.Note>> getTopLevelNotes();
    
    @androidx.room.Query(value = "SELECT * FROM notes WHERE isArchived = 0 AND parentNoteId = :parentId ORDER BY updatedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.omninote.data.Note>> getChildNotesFlow(long parentId);
    
    @androidx.room.Query(value = "SELECT * FROM notes WHERE parentNoteId = :parentId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getChildNotesSuspend(long parentId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.Note>> $completion);
}