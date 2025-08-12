# OmniNote - Advanced Tablet Note-Taking App

OmniNote is a sophisticated Android tablet application designed specifically for Samsung Galaxy Tab S7 FE with S Pen support. It combines smooth handwritten note-taking with an intelligent knowledge graph system, similar to Obsidian, providing a powerful tool for knowledge management and creative thinking.

## 🚀 Features

### ✍️ Infinite Canvas with Low-Latency Handwriting
- **S Pen Optimized**: Near-zero latency drawing with Samsung S Pen SDK integration
- **Pressure Sensitivity**: Full pressure and tilt support for natural writing experience
- **Palm Rejection**: Intelligent palm rejection for comfortable writing
- **Infinite Canvas**: Scrollable and zoomable canvas for unlimited note space
- **Vector Storage**: High-quality stroke storage that scales without quality loss

### 🛠️ Advanced Drawing Tools
- **Customizable Pen Stack**: Multiple pen types with adjustable thickness, opacity, and color
- **Quick Tool Switching**: Instant switching between pens, highlighters, and erasers
- **Shape Recognition**: "Hold to create shape/line" with automatic smoothing
- **Layer Support**: Multiple drawing layers for complex diagrams

### 🧠 Knowledge Graph Integration
- **Automatic Linking**: Intelligent detection of repeated words/phrases across notes
- **Manual Linking**: Select text and create links to existing or new notes
- **Interactive Graph View**: Zoomable node map showing note relationships
- **Visual Connections**: Clear visualization of how notes are connected
- **Graph Navigation**: Tap nodes to instantly open related notes

### 🔍 Advanced Search & OCR
- **Handwriting Recognition**: Offline OCR for searchable handwritten content
- **Multi-Modal Search**: Search across typed text, handwriting, and linked topics
- **Real-time Results**: Instant search results with highlighting
- **Graph Integration**: Search results show connections in knowledge graph

### 📱 Tablet-Optimized UI/UX
- **Landscape Orientation**: Optimized for tablet landscape mode
- **Split-Screen Support**: Works seamlessly with Android's multi-window mode
- **Gesture Controls**: Multi-finger gestures for pan, zoom, undo/redo
- **Dark/Light Mode**: Automatic theme switching based on system preference
- **Responsive Design**: Adapts to different tablet screen sizes

### 💾 File Management & Export
- **Local Storage**: Offline-first design with local note storage
- **Multiple Export Formats**: PDF, PNG, JPEG, and Markdown export
- **Version History**: Automatic backup and version tracking
- **Cloud Sync Ready**: Architecture prepared for Google Drive/Dropbox integration

## 🏗️ Architecture

OmniNote follows modern Android development best practices:

- **MVVM Architecture**: Clean separation of concerns with ViewModels
- **Jetpack Compose**: Modern declarative UI framework
- **Room Database**: Local SQLite database with type converters
- **Repository Pattern**: Clean data access layer
- **Coroutines & Flow**: Asynchronous programming with reactive streams
- **Material 3 Design**: Latest Material Design components

### Key Components

```
app/src/main/java/com/example/omninote/
├── data/                    # Data layer
│   ├── Note.kt             # Note entity
│   ├── Stroke.kt           # Drawing stroke entity
│   ├── NoteLink.kt         # Knowledge graph links
│   ├── OmniNoteDatabase.kt # Room database
│   └── dao/                # Data Access Objects
├── repository/             # Repository layer
│   └── NoteRepository.kt   # Business logic
├── viewmodel/              # ViewModels
│   └── NoteViewModel.kt    # Main app state management
├── ui/                     # UI components
│   ├── components/         # Reusable UI components
│   ├── screens/            # Main app screens
│   └── theme/              # App theming
└── MainActivity.kt         # Main activity
```

## 🛠️ Setup Instructions

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 34
- Kotlin 1.8.21 or later
- Samsung Galaxy Tab S7 FE (recommended) or any Android tablet

### Installation

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd OmniNote_Kotlin
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the `OmniNote_Kotlin` folder and open it

3. **Sync Dependencies**
   - Wait for Gradle sync to complete
   - If prompted, update any dependencies

4. **Run the App**
   - Connect your tablet via USB or use an emulator
   - Click the "Run" button (green play icon)
   - Select your device and install the app

### Configuration

1. **Enable Developer Options** (if using physical device)
   - Go to Settings > About tablet
   - Tap "Build number" 7 times
   - Enable USB debugging in Developer options

2. **Grant Permissions**
   - The app will request storage permissions on first launch
   - Grant all requested permissions for full functionality

## 📖 Usage Guide

### Creating Your First Note

1. **Launch OmniNote** on your tablet
2. **Tap the "+" button** in the notes panel to create a new note
3. **Enter a title** for your note
4. **Start writing** with your S Pen on the canvas
5. **Switch tools** using the toolbar at the top

### Using the Knowledge Graph

1. **Create multiple notes** with related content
2. **View the graph** by tapping the graph icon in the top bar
3. **See connections** automatically created between related notes
4. **Tap nodes** to navigate between connected notes
5. **Zoom and pan** to explore the knowledge network

### Advanced Features

#### Drawing Tools
- **Pen**: Standard writing tool with pressure sensitivity
- **Highlighter**: Semi-transparent highlighting tool
- **Eraser**: Remove strokes by drawing over them
- **Shape Drawer**: Hold to create perfect shapes and lines

#### Search & Organization
- **Search Bar**: Tap the search icon to find notes
- **Tags**: Add tags to notes for better organization
- **Graph View**: Visualize note relationships
- **Export**: Share notes as PDF, images, or Markdown

#### Gestures
- **Two-finger pan**: Move around the canvas
- **Pinch to zoom**: Zoom in/out of the canvas
- **Three-finger undo/redo**: Quick undo/redo actions

## 🔧 Development

### Adding New Features

1. **Database Changes**: Add new entities in the `data` package
2. **Repository Layer**: Implement business logic in repositories
3. **ViewModel**: Add state management for new features
4. **UI Components**: Create reusable components in `ui/components`
5. **Screens**: Add new screens in `ui/screens`

### Testing

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

### Building for Release

```bash
# Build release APK
./gradlew assembleRelease

# Build release AAB
./gradlew bundleRelease
```

## 📱 Device Compatibility

### Recommended Devices
- **Samsung Galaxy Tab S7 FE** (Primary target)
- **Samsung Galaxy Tab S8/S8+/S8 Ultra**
- **Samsung Galaxy Tab S9/S9+/S9 Ultra**
- **Other Android tablets with stylus support**

### Minimum Requirements
- Android 7.0 (API 24) or higher
- 4GB RAM (8GB recommended)
- 64GB storage (128GB recommended)
- Stylus support (S Pen, Wacom, or similar)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- **Samsung S Pen SDK** for low-latency drawing support
- **Jetpack Compose** team for the modern UI framework
- **Material Design** team for design guidelines
- **Obsidian** for inspiration on knowledge graph concepts

## 📞 Support

For support, questions, or feature requests:
- Create an issue on GitHub
- Check the documentation in the `/docs` folder
- Review the code comments for implementation details

---

**OmniNote** - Transform your tablet into a powerful knowledge management tool! 🚀