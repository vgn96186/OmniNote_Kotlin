package com.example.omninote.ui.common;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\u001a\u0089\u0001\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012\u001a.\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0003\u001a2\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00192\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0007H\u0003\u001a2\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00192\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0007H\u0003\u001a2\u0010!\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00192\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u0007H\u0003\u00a8\u0006#"}, d2 = {"AdvancedCanvasToolbar", "", "modifier", "Landroidx/compose/ui/Modifier;", "currentTool", "Lcom/example/omninote/data/ToolType;", "onToolChange", "Lkotlin/Function1;", "currentColor", "Landroidx/compose/ui/graphics/Color;", "onColorChange", "currentWidth", "", "onWidthChange", "onUndo", "Lkotlin/Function0;", "onRedo", "AdvancedCanvasToolbar-fWhpE4E", "(Landroidx/compose/ui/Modifier;Lcom/example/omninote/data/ToolType;Lkotlin/jvm/functions/Function1;JLkotlin/jvm/functions/Function1;FLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "ToolbarIconButton", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "contentDescription", "", "isSelected", "", "onClick", "PensMenu", "expanded", "onDismiss", "onToolSelect", "ColorsMenu", "onColorSelect", "WidthsMenu", "onWidthSelect", "app_debug"})
public final class LassoKt {
    
    @androidx.compose.runtime.Composable()
    private static final void ToolbarIconButton(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String contentDescription, boolean isSelected, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PensMenu(boolean expanded, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function1<? super com.example.omninote.data.ToolType, kotlin.Unit> onToolSelect) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ColorsMenu(boolean expanded, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function1<? super androidx.compose.ui.graphics.Color, kotlin.Unit> onColorSelect) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void WidthsMenu(boolean expanded, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> onWidthSelect) {
    }
}