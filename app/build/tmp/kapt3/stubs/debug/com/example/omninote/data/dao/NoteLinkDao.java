package com.example.omninote.data.dao;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\'J \u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0017\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u0019\u00a8\u0006\u001a\u00c0\u0006\u0003"}, d2 = {"Lcom/example/omninote/data/dao/NoteLinkDao;", "", "getLinksForNote", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/omninote/data/NoteLink;", "noteId", "", "getLink", "sourceId", "targetId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOutgoingLinks", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIncomingLinks", "insertLink", "", "link", "(Lcom/example/omninote/data/NoteLink;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLink", "deleteLink", "deleteAllLinksForNote", "getLinksByType", "linkType", "Lcom/example/omninote/data/LinkType;", "(Lcom/example/omninote/data/LinkType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface NoteLinkDao {
    
    @androidx.room.Query(value = "SELECT * FROM note_links WHERE sourceNoteId = :noteId OR targetNoteId = :noteId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.omninote.data.NoteLink>> getLinksForNote(long noteId);
    
    @androidx.room.Query(value = "SELECT * FROM note_links WHERE sourceNoteId = :sourceId AND targetNoteId = :targetId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLink(long sourceId, long targetId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.omninote.data.NoteLink> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM note_links WHERE sourceNoteId = :noteId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getOutgoingLinks(long noteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.NoteLink>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM note_links WHERE targetNoteId = :noteId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getIncomingLinks(long noteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.NoteLink>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertLink(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.NoteLink link, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateLink(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.NoteLink link, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteLink(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.NoteLink link, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM note_links WHERE sourceNoteId = :noteId OR targetNoteId = :noteId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllLinksForNote(long noteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM note_links WHERE linkType = :linkType")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLinksByType(@org.jetbrains.annotations.NotNull()
    com.example.omninote.data.LinkType linkType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.omninote.data.NoteLink>> $completion);
}