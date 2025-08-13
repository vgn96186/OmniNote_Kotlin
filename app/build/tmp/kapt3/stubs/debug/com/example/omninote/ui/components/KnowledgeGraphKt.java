package com.example.omninote.ui.components;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a_\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00030\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u00a2\u0006\u0002\u0010\r\u001a&\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0000\u001a#\u0010\u0015\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000fH\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a+\u0010\u001b\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0000\u00a2\u0006\u0004\b!\u0010\"\u001a_\u0010#\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00030\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u00a2\u0006\u0002\u0010\r\u001a_\u0010$\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00030\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u00a2\u0006\u0002\u0010\r\u00a8\u0006%"}, d2 = {"KnowledgeGraph", "", "notes", "", "Lcom/example/omninote/data/Note;", "graphData", "", "", "onNoteSelected", "Lkotlin/Function1;", "selectedNoteId", "modifier", "Landroidx/compose/ui/Modifier;", "(Ljava/util/List;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Ljava/lang/Long;Landroidx/compose/ui/Modifier;)V", "calculateNodePositions", "Landroidx/compose/ui/geometry/Offset;", "nodeCount", "", "width", "", "height", "drawConnection", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "start", "end", "drawConnection-2x9bVx0", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JJ)V", "drawNode", "position", "title", "", "isSelected", "", "drawNode-ubNVwUQ", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JLjava/lang/String;Z)V", "KnowledgeGraphList", "EnhancedKnowledgeGraph", "app_debug"})
public final class KnowledgeGraphKt {
    
    @androidx.compose.runtime.Composable()
    public static final void KnowledgeGraph(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.omninote.data.Note> notes, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.Long, ? extends java.util.List<java.lang.Long>> graphData, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.omninote.data.Note, kotlin.Unit> onNoteSelected, @org.jetbrains.annotations.Nullable()
    java.lang.Long selectedNoteId, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<androidx.compose.ui.geometry.Offset> calculateNodePositions(int nodeCount, float width, float height) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void KnowledgeGraphList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.omninote.data.Note> notes, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.Long, ? extends java.util.List<java.lang.Long>> graphData, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.omninote.data.Note, kotlin.Unit> onNoteSelected, @org.jetbrains.annotations.Nullable()
    java.lang.Long selectedNoteId, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void EnhancedKnowledgeGraph(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.omninote.data.Note> notes, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.Long, ? extends java.util.List<java.lang.Long>> graphData, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.omninote.data.Note, kotlin.Unit> onNoteSelected, @org.jetbrains.annotations.Nullable()
    java.lang.Long selectedNoteId, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}