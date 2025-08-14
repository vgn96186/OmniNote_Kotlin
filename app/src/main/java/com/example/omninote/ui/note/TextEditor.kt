package com.example.omninote.ui.note

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TextEditor(
    initialContent: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf(initialContent) }

    // This effect notifies the parent composable whenever the text changes.
    LaunchedEffect(text) {
        onContentChange(text)
    }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = modifier.fillMaxSize(),
        textStyle = MaterialTheme.typography.bodyLarge.copy(lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.5),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = { Text("Start writing your note here...") }
    )
}
