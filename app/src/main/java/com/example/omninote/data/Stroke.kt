package com.example.omninote.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.omninote.data.converters.Converters

@Entity(tableName = "strokes")
@TypeConverters(Converters::class)
data class Stroke(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val noteId: Long,
    val points: List<Point>,
    val color: Int, // ARGB color
    val strokeWidth: Float,
    val pressure: Float = 1.0f,
    val toolType: ToolType = ToolType.PEN,
    val timestamp: Long = System.currentTimeMillis(),
    val layer: Int = 0
)

data class Point(
    val x: Float,
    val y: Float,
    val pressure: Float = 1.0f,
    val timestamp: Long = System.currentTimeMillis()
)

// Updated Enum to include all new tools from your design
enum class ToolType {
    PEN,
    HIGHLIGHTER,
    ERASER,
    SHAPE_DRAWER, // Kept from previous version, maps to Shape Pen
    TEXT,
    BRUSH,
    MASKING_TAPE,
    LASSO,
    SHAPE_PEN,
    LASER_PEN
}
