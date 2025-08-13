package com.example.omninote.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0017\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\f0\u000b2\u0006\u0010\u001f\u001a\u00020\u0010J\u0016\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020!H\u0086@\u00a2\u0006\u0002\u0010$J\u001c\u0010%\u001a\u00020\u001c2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020!0\fH\u0086@\u00a2\u0006\u0002\u0010\'J\u0016\u0010(\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\f0\u000b2\u0006\u0010\u001f\u001a\u00020\u0010J(\u0010+\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u00102\b\b\u0002\u0010.\u001a\u00020/H\u0086@\u00a2\u0006\u0002\u00100J\u001e\u00101\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u00102J\u001c\u00103\u001a\b\u0012\u0004\u0012\u00020*0\f2\u0006\u0010\u001f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020*0\f2\u0006\u0010\u001f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J \u00105\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\f06H\u0086@\u00a2\u0006\u0002\u00107J\u001e\u00108\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u0014H\u0082@\u00a2\u0006\u0002\u0010:J\u000e\u0010;\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u00107J\u001c\u0010<\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u001f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006="}, d2 = {"Lcom/example/omninote/repository/NoteRepository;", "", "noteDao", "Lcom/example/omninote/data/dao/NoteDao;", "strokeDao", "Lcom/example/omninote/data/dao/StrokeDao;", "noteLinkDao", "Lcom/example/omninote/data/dao/NoteLinkDao;", "<init>", "(Lcom/example/omninote/data/dao/NoteDao;Lcom/example/omninote/data/dao/StrokeDao;Lcom/example/omninote/data/dao/NoteLinkDao;)V", "getAllNotes", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/omninote/data/Note;", "getNoteById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchNotes", "query", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNotesByTag", "tag", "insertNote", "note", "(Lcom/example/omninote/data/Note;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNote", "", "deleteNote", "archiveNote", "noteId", "getStrokesForNote", "Lcom/example/omninote/data/Stroke;", "insertStroke", "stroke", "(Lcom/example/omninote/data/Stroke;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertStrokes", "strokes", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteStrokesForNote", "getLinksForNote", "Lcom/example/omninote/data/NoteLink;", "createLink", "sourceId", "targetId", "linkType", "Lcom/example/omninote/data/LinkType;", "(JJLcom/example/omninote/data/LinkType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteLink", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOutgoingLinks", "getIncomingLinks", "getKnowledgeGraph", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "autoLinkNote", "content", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ensureSampleDataLoaded", "getRelatedNotes", "app_release"})
public final class NoteRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.omninote.data.dao.NoteDao noteDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.omninote.data.dao.StrokeDao strokeDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.omninote.data.dao.NoteLinkDao noteLinkDao = null;
    
    public NoteRepository(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.dao.NoteDao noteDao, @org.jetbrains.annotations.NotNull()
    com.example.omninote.data.dao.StrokeDao strokeDao, @org.jetbrains.annotations.NotNull()
    com.example.omninote.data.dao.NoteLinkDao noteLinkDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.omninote.data.Note>> getAllNotes() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getNoteById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.omninote.data.Note> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchNotes(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.Note>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getNotesByTag(@org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.Note>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertNote(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Note note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateNote(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Note note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteNote(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Note note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object archiveNote(long noteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.omninote.data.Stroke>> getStrokesForNote(long noteId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertStroke(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.Stroke stroke, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertStrokes(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.omninote.data.Stroke> strokes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteStrokesForNote(long noteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.omninote.data.NoteLink>> getLinksForNote(long noteId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createLink(long sourceId, long targetId, @org.jetbrains.annotations.NotNull()
    com.example.omninote.data.LinkType linkType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteLink(long sourceId, long targetId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getOutgoingLinks(long noteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.NoteLink>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getIncomingLinks(long noteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.NoteLink>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getKnowledgeGraph(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<java.lang.Long, ? extends java.util.List<java.lang.Long>>> $completion) {
        return null;
    }
    
    private final java.lang.Object autoLinkNote(long noteId, java.lang.String content, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object ensureSampleDataLoaded(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRelatedNotes(long noteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.Note>> $completion) {
        return null;
    }
}