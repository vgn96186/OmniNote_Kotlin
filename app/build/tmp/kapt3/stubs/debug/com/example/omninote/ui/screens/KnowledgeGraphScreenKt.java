package com.example.omninote.ui.screens;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bH\u0002\u001a4\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\r0\b2\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b0\u000fH\u0002\u001a0\u0010\u0010\u001a\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u00122\u0018\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\r0\bH\u0002\u001aC\u0010\u0014\u001a\u00020\u0001*\u00020\u00152\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0018\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\r0\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0002\u0010\u0017\u001a\u0013\u0010\u0018\u001a\u00020\u0019*\u00020\u001aH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a\u001b\u0010\u001d\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b\u001f\u0010 \u00a8\u0006!"}, d2 = {"KnowledgeGraphScreen", "", "viewModel", "Lcom/example/omninote/viewmodel/NoteViewModel;", "onOpenNote", "Lkotlin/Function1;", "", "createInitialLayout", "", "Lcom/example/omninote/ui/screens/GraphNode;", "notes", "Lcom/example/omninote/data/Note;", "buildConnections", "Lkotlin/Pair;", "graph", "", "updatePhysics", "nodes", "", "connections", "drawGraph", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "selectedId", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;)V", "getDistance", "", "Landroidx/compose/ui/geometry/Offset;", "getDistance-k-4lQ0M", "(J)F", "safeAdd", "other", "safeAdd-0a9Yr6o", "(JJ)J", "app_debug"})
public final class KnowledgeGraphScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void KnowledgeGraphScreen(@org.jetbrains.annotations.NotNull()
    com.example.omninote.viewmodel.NoteViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onOpenNote) {
    }
    
    private static final java.util.List<com.example.omninote.ui.screens.GraphNode> createInitialLayout(java.util.List<com.example.omninote.data.Note> notes) {
        return null;
    }
    
    private static final java.util.List<kotlin.Pair<java.lang.Long, java.lang.Long>> buildConnections(java.util.Map<java.lang.Long, ? extends java.util.List<java.lang.Long>> graph) {
        return null;
    }
    
    private static final void updatePhysics(java.util.List<com.example.omninote.ui.screens.GraphNode> nodes, java.util.List<kotlin.Pair<java.lang.Long, java.lang.Long>> connections) {
    }
    
    private static final void drawGraph(androidx.compose.ui.graphics.drawscope.DrawScope $this$drawGraph, java.util.List<com.example.omninote.ui.screens.GraphNode> nodes, java.util.List<kotlin.Pair<java.lang.Long, java.lang.Long>> connections, java.lang.Long selectedId) {
    }
}