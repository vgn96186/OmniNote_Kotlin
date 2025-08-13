package com.example.omninote.ui.components;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0007\u001a\u0097\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a.\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0003\u001a+\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\tH\u0003\u00a2\u0006\u0004\b\u001d\u0010\u001e\u001a$\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u00072\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a&\u0010\"\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\u0006\u0010#\u001a\u00020$H\u0003\u001aX\u0010%\u001a\u00020\u00012\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007\u00a8\u0006+"}, d2 = {"DrawingToolbar", "", "currentTool", "Lcom/example/omninote/data/ToolType;", "currentColor", "Landroidx/compose/ui/graphics/Color;", "strokeWidth", "", "onToolChanged", "Lkotlin/Function1;", "onColorChanged", "onStrokeWidthChanged", "onClearCanvas", "Lkotlin/Function0;", "onUndo", "onRedo", "modifier", "Landroidx/compose/ui/Modifier;", "DrawingToolbar-oC9nPe0", "(Lcom/example/omninote/data/ToolType;JFLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;)V", "ToolButton", "tool", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "isSelected", "", "onClick", "ColorPalette", "onColorSelected", "ColorPalette-DxMtmZc", "(JLkotlin/jvm/functions/Function1;)V", "StrokeWidthSelector", "currentWidth", "onWidthChanged", "ActionButton", "contentDescription", "", "NoteToolbar", "onNewNote", "onSearch", "onShowGraph", "onExport", "onSettings", "app_release"})
public final class ToolbarKt {
    
    @androidx.compose.runtime.Composable()
    private static final void ToolButton(com.example.omninote.data.ToolType tool, androidx.compose.ui.graphics.vector.ImageVector icon, boolean isSelected, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StrokeWidthSelector(float currentWidth, kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> onWidthChanged) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActionButton(androidx.compose.ui.graphics.vector.ImageVector icon, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, java.lang.String contentDescription) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void NoteToolbar(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNewNote, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSearch, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onShowGraph, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onExport, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSettings, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}