package com.example.omninote.data.dao;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\'J$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0014\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u001e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u001a\u00a8\u0006\u001b\u00c0\u0006\u0003"}, d2 = {"Lcom/example/omninote/data/dao/StrokeDao;", "", "getStrokesForNote", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/omninote/data/Stroke;", "noteId", "", "getStrokesForNoteAndLayer", "layer", "", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertStroke", "stroke", "(Lcom/example/omninote/data/Stroke;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertStrokes", "", "strokes", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStroke", "deleteStroke", "deleteAllStrokesForNote", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteStrokesForNoteAndLayer", "getStrokesSince", "sinceTimestamp", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface StrokeDao {
    
    @androidx.room.Query(value = "SELECT * FROM strokes WHERE noteId = :noteId ORDER BY timestamp ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.omninote.data.Stroke>> getStrokesForNote(long noteId);
    
    @androidx.room.Query(value = "SELECT * FROM strokes WHERE noteId = :noteId AND layer = :layer ORDER BY timestamp ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStrokesForNoteAndLayer(long noteId, int layer, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.Stroke>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertStroke(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Stroke stroke, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertStrokes(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.omninote.data.Stroke> strokes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateStroke(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Stroke stroke, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteStroke(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Stroke stroke, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM strokes WHERE noteId = :noteId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllStrokesForNote(long noteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM strokes WHERE noteId = :noteId AND layer = :layer")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteStrokesForNoteAndLayer(long noteId, int layer, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM strokes WHERE noteId = :noteId AND timestamp > :sinceTimestamp ORDER BY timestamp ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStrokesSince(long noteId, long sinceTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.Stroke>> $completion);
}