package com.example.omninote.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Highlight
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.omninote.R
import com.example.omninote.ui.theme.OmniNoteTheme
import com.example.omninote.viewmodel.NoteViewModel
import java.io.File
import java.io.FileOutputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FolderExplorer(
    onCreateCanvas: () -> Unit,
    onCreateFolder: () -> Unit,
    onCreateTextNote: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Folder Explorer", style = MaterialTheme.typography.headlineMedium)

        Button(onClick = onCreateCanvas) {
            Text("Create Infinite Canvas")
        }

        Button(onClick = onCreateFolder) {
            Text("Create New Folder")
        }

        Button(onClick = onCreateTextNote) {
            Text("Create Text Note")
        }
    }
}

@Composable
fun PageSettings(
    onPageSizeSelected: (String) -> Unit,
    onGridToggle: (Boolean) -> Unit,
    onDotsToggle: (Boolean) -> Unit
) {
    var selectedPageSize by remember { mutableStateOf("Infinite Canvas") }
    var isGridEnabled by remember { mutableStateOf(false) }
    var isDotsEnabled by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Page Settings", style = MaterialTheme.typography.headlineMedium)

        Button(onClick = { expanded = true }) {
            Text(selectedPageSize)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listOf("Infinite Canvas", "A4", "A3", "Letter").forEach { pageSize ->
                DropdownMenuItem(
                    text = { Text(pageSize) },
                    onClick = {
                        selectedPageSize = pageSize
                        onPageSizeSelected(pageSize)
                        expanded = false
                    }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Grid")
            Switch(
                checked = isGridEnabled,
                onCheckedChange = {
                    isGridEnabled = it
                    onGridToggle(it)
                }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Dots")
            Switch(
                checked = isDotsEnabled,
                onCheckedChange = {
                    isDotsEnabled = it
                    onDotsToggle(it)
                }
            )
        }
    }
}

@Composable
fun InfiniteCanvas() {
    val path = remember { Path() }
    val scale = remember { mutableStateOf(1f) }
    val offset = remember { mutableStateOf(Offset.Zero) }
    val density = LocalDensity.current

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale.value *= zoom
                    offset.value = Offset(
                        offset.value.x + pan.x,
                        offset.value.y + pan.y
                    )
                }
            }
    ) {
        drawPath(
            path = path,
            color = Color.Black,
            style = Stroke(width = with(density) { 2.dp.toPx() })
        )
    }
}

@Composable
fun InfiniteCanvasWithTools() {
    val paths = remember { mutableStateListOf<Pair<Path, Color>>() }
    val currentPath = remember { Path() }
    val currentColor = remember { mutableStateOf(Color.Black) }
    val scale = remember { mutableStateOf(1f) }
    val offset = remember { mutableStateOf(Offset.Zero) }
    val density = LocalDensity.current

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale.value *= zoom
                        offset.value = Offset(
                            offset.value.x + pan.x,
                            offset.value.y + pan.y
                        )
                    }
                }
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, _, _ ->
                        currentPath.lineTo(pan.x, pan.y)
                    }
                }
        ) {
            paths.forEach { (path, color) ->
                drawPath(
                    path = path,
                    color = color,
                    style = Stroke(
                        width = with(density) { 4.dp.toPx() },
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
            drawPath(
                path = currentPath,
                color = currentColor.value,
                style = Stroke(
                    width = with(density) { 4.dp.toPx() },
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { currentColor.value = Color.Black }) {
                Text("Black")
            }
            Button(onClick = { currentColor.value = Color.Red }) {
                Text("Red")
            }
            Button(onClick = { currentColor.value = Color.Blue }) {
                Text("Blue")
            }
            Button(onClick = {
                paths.add(Pair(Path().apply { addPath(currentPath) }, currentColor.value))
                currentPath.reset()
            }) {
                Text("Save Path")
            }
        }
    }
}

@Composable
fun InfiniteCanvasWithPalmRejection() {
    val paths = remember { mutableStateListOf<Pair<Path, Color>>() }
    val currentPath = remember { Path() }
    val currentColor = remember { mutableStateOf(Color.Black) }
    val scale = remember { mutableStateOf(1f) }
    val offset = remember { mutableStateOf(Offset.Zero) }
    val density = LocalDensity.current

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale.value *= zoom
                        offset.value = Offset(
                            offset.value.x + pan.x,
                            offset.value.y + pan.y
                        )
                    }
                }
                .pointerInput(Unit) {
                    // TODO: Implement palm rejection
                }
        ) {
            paths.forEach { (path, color) ->
                drawPath(
                    path = path,
                    color = color,
                    style = Stroke(
                        width = with(density) { 4.dp.toPx() },
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
            drawPath(
                path = currentPath,
                color = currentColor.value,
                style = Stroke(
                    width = with(density) { 4.dp.toPx() },
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { currentColor.value = Color.Black }) {
                Text("Black")
            }
            Button(onClick = { currentColor.value = Color.Red }) {
                Text("Red")
            }
            Button(onClick = { currentColor.value = Color.Blue }) {
                Text("Blue")
            }
            Button(onClick = {
                paths.add(Pair(Path().apply { addPath(currentPath) }, currentColor.value))
                currentPath.reset()
            }) {
                Text("Save Path")
            }
        }
    }
}

@Composable
fun SettingsPage() {
    var darkModeEnabled by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Enable Dark Mode")
            Switch(
                checked = darkModeEnabled,
                onCheckedChange = { darkModeEnabled = it }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Enable Notifications")
            Switch(
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )
        }
    }
}

@Composable
fun AdvancedDrawingTools() {
    var currentTool by remember { mutableStateOf("Pen") }
    var strokeWidth by remember { mutableStateOf(4f) }
    var strokeOpacity by remember { mutableStateOf(1f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Drawing Tools", style = MaterialTheme.typography.headlineMedium)

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(onClick = { currentTool = "Pen" }) {
                Icon(Icons.Filled.Brush, contentDescription = "Pen")
            }
            IconButton(onClick = { currentTool = "Highlighter" }) {
                Icon(Icons.Filled.Highlight, contentDescription = "Highlighter")
            }
            IconButton(onClick = { /*currentTool = "Eraser"*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_eraser), contentDescription = "Eraser")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Stroke Width: ${strokeWidth.toInt()} px")
        Slider(
            value = strokeWidth,
            onValueChange = { strokeWidth = it },
            valueRange = 1f..10f
        )

        Text("Opacity: ${(strokeOpacity * 100).toInt()}%")
        Slider(
            value = strokeOpacity,
            onValueChange = { strokeOpacity = it },
            valueRange = 0.1f..1f
        )
    }
}

@Composable
fun KnowledgeGraphView() {
    val nodes = remember { mutableStateListOf<Pair<String, Offset>>() }
    val connections = remember { mutableStateListOf<Pair<Offset, Offset>>() }
    val density = LocalDensity.current

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Draw connections
            connections.forEach { (start, end) ->
                drawLine(
                    color = Color.Gray,
                    start = start,
                    end = end,
                    strokeWidth = with(density) { 2.dp.toPx() },
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            }

            // Draw nodes
            nodes.forEach { (label, position) ->
                drawCircle(
                    color = Color.Blue,
                    center = position,
                    radius = with(density) { 20.dp.toPx() }
                )
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        label,
                        position.x,
                        position.y - with(density) { 30.dp.toPx() },
                        android.graphics.Paint().apply {
                            color = android.graphics.Color.BLACK
                            textSize = 40f
                            textAlign = android.graphics.Paint.Align.CENTER
                        }
                    )
                }
            }
        }

        // Add gesture detection for adding nodes and connections
        Modifier.pointerInput(Unit) {
            detectTapGestures(
                onTap = { offset ->
                    nodes.add(Pair("Node ${nodes.size + 1}", offset))
                },
                onDoubleTap = { offset ->
                    if (nodes.size > 1) {
                        connections.add(Pair(nodes.last().second, offset))
                    }
                }
            )
        }
    }
}

@Composable
fun AdvancedSearchAndOCR() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val searchResults = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Search & OCR", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                // Simulate OCR and search logic
                searchResults.clear()
                if (it.text.isNotEmpty()) {
                    searchResults.addAll(
                        listOf("Result 1 for \"${it.text}\"", "Result 2 for \"${it.text}\"")
                    )
                }
            },
            label = { Text("Search Notes") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(searchResults) { result ->
                Text(result, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

// The MainScreen now acts as the top-level container for your app's UI.
@Composable
fun MainScreen(
    modifier: Modifier = Modifier, // This modifier will now be used
    viewModel: NoteViewModel // Added viewModel as a parameter
) {
    OmniNoteTheme {
        FolderExplorer(
            onCreateCanvas = { /* TODO: Implement canvas creation */ },
            onCreateFolder = { /* TODO: Implement folder creation */ },
            onCreateTextNote = { /* TODO: Implement text note creation */ }
        )
    }
}

@Composable
fun MaterialYouTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Composable
fun MainScreenWithMaterialYou(viewModel: NoteViewModel) {
    MaterialYouTheme {
        TabletOptimizedUI()
    }
}

@Composable
fun GestureControlCanvas() {
    val scale = remember { mutableStateOf(1f) }
    val offset = remember { mutableStateOf(Offset.Zero) }
    val undoStack = remember { mutableStateListOf<Path>() }
    val redoStack = remember { mutableStateListOf<Path>() }
    val density = LocalDensity.current

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale.value *= zoom
                        offset.value = Offset(
                            offset.value.x + pan.x,
                            offset.value.y + pan.y
                        )
                    }
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            if (undoStack.isNotEmpty()) {
                                redoStack.add(undoStack.removeAt(undoStack.lastIndex))
                            }
                        },
                        onLongPress = {
                            if (redoStack.isNotEmpty()) {
                                undoStack.add(redoStack.removeAt(redoStack.lastIndex))
                            }
                        }
                    )
                }
        ) {
            // Draw paths from undo stack
            undoStack.forEach { path ->
                drawPath(
                    path = path,
                    color = Color.Black,
                    style = Stroke(width = with(density) { 4.dp.toPx() })
                )
            }
        }
    }
}

@Composable
fun SmoothPinchToZoomCanvas() {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var isZoomLocked by remember { mutableStateOf(false) }
    val density = LocalDensity.current

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        if (!isZoomLocked) {
                            scale *= zoom
                            offset = Offset(
                                offset.x + pan.x,
                                offset.y + pan.y
                            )
                        }
                    }
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            // Detect three-finger tap to toggle zoom lock
                            // TODO: Implement three-finger tap
                        }
                    )
                }
        ) {
            // Draw content with applied scale and offset
            withTransform({
                scale(scale, scale)
                translate(offset.x, offset.y)
            }) {
                drawRect(
                    color = Color.LightGray,
                    size = Size(with(density) { 200.dp.toPx() }, with(density) { 200.dp.toPx() })
                )
            }
        }

        Text(
            text = if (isZoomLocked) "Zoom Locked" else "Zoom Unlocked",
            modifier = Modifier.align(Alignment.TopCenter).padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun FileManagement() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("File Management", style = MaterialTheme.typography.headlineMedium)

        Button(onClick = {
            coroutineScope.launch(Dispatchers.IO) {
                val file = File(context.filesDir, "example.txt")
                FileOutputStream(file).use {
                    it.write("This is an example note.".toByteArray())
                }
            }
        }) {
            Text("Save Note as Text File")
        }

        Button(onClick = {
            coroutineScope.launch(Dispatchers.IO) {
                val file = File(context.filesDir, "example.txt")
                if (file.exists()) {
                    file.delete()
                }
            }
        }) {
            Text("Delete Note")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            coroutineScope.launch(Dispatchers.IO) {
                val file = File(context.filesDir, "example_backup.txt")
                FileOutputStream(file).use {
                    it.write("Backup of the note.".toByteArray())
                }
            }
        }) {
            Text("Create Backup")
        }
    }
}

@Composable
fun TabletOptimizedUI() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp), // Optimized padding
        horizontalArrangement = Arrangement.spacedBy(24.dp) // Consistent spacing
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp), // Inner padding for content separation
            verticalArrangement = Arrangement.spacedBy(16.dp) // Spacing between elements
        ) {
            FolderExplorer(
                onCreateCanvas = { /* TODO: Implement canvas creation */ },
                onCreateFolder = { /* TODO: Implement folder creation */ },
                onCreateTextNote = { /* TODO: Implement text note creation */ }
            )
        }

        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfiniteCanvasWithTools()
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            KnowledgeGraphView()
        }
    }
}

@Composable
fun AnimatedMainScreen(viewModel: NoteViewModel) {
    var isVisible by remember { mutableStateOf(true) }

    MaterialYouTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = { isVisible = !isVisible },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)
            ) {
                Text(if (isVisible) "Hide Content" else "Show Content")
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                TabletOptimizedUI()
            }
        }
    }
}
