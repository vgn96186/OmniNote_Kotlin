package com.example.omninote.ui.explorer;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a]\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0014\u0010\t\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\nH\u0007\u00a2\u0006\u0002\u0010\f\u001am\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00042\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0014\u0010\t\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0003\u00a2\u0006\u0002\u0010\u0012\u001a\u0016\u0010\u0013\u001a\u00020\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0007\u00a8\u0006\u0016"}, d2 = {"FolderTree", "", "notes", "", "Lcom/example/omninote/data/Note;", "currentFolderId", "", "expandedIds", "", "onFolderClick", "Lkotlin/Function1;", "onToggleExpand", "(Ljava/util/List;Ljava/lang/Long;Ljava/util/Set;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "TreeItem", "note", "allNotes", "depth", "", "(Lcom/example/omninote/data/Note;Ljava/util/List;Ljava/lang/Long;Ljava/util/Set;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;I)V", "HomeButton", "onClick", "Lkotlin/Function0;", "app_release"})
public final class FolderTreeKt {
    
    @androidx.compose.runtime.Composable()
    public static final void FolderTree(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.omninote.data.Note> notes, @org.jetbrains.annotations.Nullable()
    java.lang.Long currentFolderId, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.Long> expandedIds, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onFolderClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onToggleExpand) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TreeItem(com.example.omninote.data.Note note, java.util.List<com.example.omninote.data.Note> allNotes, java.lang.Long currentFolderId, java.util.Set<java.lang.Long> expandedIds, kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onFolderClick, kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onToggleExpand, int depth) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void HomeButton(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}