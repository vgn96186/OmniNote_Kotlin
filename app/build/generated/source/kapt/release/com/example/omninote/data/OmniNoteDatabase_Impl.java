package com.example.omninote.data;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.example.omninote.data.dao.NoteDao;
import com.example.omninote.data.dao.NoteDao_Impl;
import com.example.omninote.data.dao.NoteLinkDao;
import com.example.omninote.data.dao.NoteLinkDao_Impl;
import com.example.omninote.data.dao.StrokeDao;
import com.example.omninote.data.dao.StrokeDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class OmniNoteDatabase_Impl extends OmniNoteDatabase {
  private volatile NoteDao _noteDao;

  private volatile StrokeDao _strokeDao;

  private volatile NoteLinkDao _noteLinkDao;

  @Override
  @NonNull
  protected RoomOpenDelegate createOpenDelegate() {
    final RoomOpenDelegate _openDelegate = new RoomOpenDelegate(3, "0c6d4aa4ea472af41dcb51cf4b6b15ef", "865ac94feee112d4cca085ef2f328e60") {
      @Override
      public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `notes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `content` TEXT NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL, `tags` TEXT NOT NULL, `isArchived` INTEGER NOT NULL, `parentNoteId` INTEGER, `positionX` REAL NOT NULL, `positionY` REAL NOT NULL, `width` REAL NOT NULL, `height` REAL NOT NULL, `type` TEXT NOT NULL)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `strokes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `noteId` INTEGER NOT NULL, `points` TEXT NOT NULL, `tool` TEXT NOT NULL, `color` INTEGER NOT NULL, `strokeWidth` REAL NOT NULL, `pressure` REAL NOT NULL, `timestamp` INTEGER NOT NULL, `layer` INTEGER NOT NULL)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `note_links` (`sourceNoteId` INTEGER NOT NULL, `targetNoteId` INTEGER NOT NULL, `linkType` TEXT NOT NULL, `strength` REAL NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`sourceNoteId`, `targetNoteId`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '0c6d4aa4ea472af41dcb51cf4b6b15ef')");
      }

      @Override
      public void dropAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `notes`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `strokes`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `note_links`");
      }

      @Override
      public void onCreate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      public void onOpen(@NonNull final SQLiteConnection connection) {
        internalInitInvalidationTracker(connection);
      }

      @Override
      public void onPreMigrate(@NonNull final SQLiteConnection connection) {
        DBUtil.dropFtsSyncTriggers(connection);
      }

      @Override
      public void onPostMigrate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      @NonNull
      public RoomOpenDelegate.ValidationResult onValidateSchema(
          @NonNull final SQLiteConnection connection) {
        final Map<String, TableInfo.Column> _columnsNotes = new HashMap<String, TableInfo.Column>(13);
        _columnsNotes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("tags", new TableInfo.Column("tags", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("isArchived", new TableInfo.Column("isArchived", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("parentNoteId", new TableInfo.Column("parentNoteId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("positionX", new TableInfo.Column("positionX", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("positionY", new TableInfo.Column("positionY", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("width", new TableInfo.Column("width", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("height", new TableInfo.Column("height", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysNotes = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesNotes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotes = new TableInfo("notes", _columnsNotes, _foreignKeysNotes, _indicesNotes);
        final TableInfo _existingNotes = TableInfo.read(connection, "notes");
        if (!_infoNotes.equals(_existingNotes)) {
          return new RoomOpenDelegate.ValidationResult(false, "notes(com.example.omninote.data.Note).\n"
                  + " Expected:\n" + _infoNotes + "\n"
                  + " Found:\n" + _existingNotes);
        }
        final Map<String, TableInfo.Column> _columnsStrokes = new HashMap<String, TableInfo.Column>(9);
        _columnsStrokes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStrokes.put("noteId", new TableInfo.Column("noteId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStrokes.put("points", new TableInfo.Column("points", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStrokes.put("tool", new TableInfo.Column("tool", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStrokes.put("color", new TableInfo.Column("color", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStrokes.put("strokeWidth", new TableInfo.Column("strokeWidth", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStrokes.put("pressure", new TableInfo.Column("pressure", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStrokes.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStrokes.put("layer", new TableInfo.Column("layer", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysStrokes = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesStrokes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStrokes = new TableInfo("strokes", _columnsStrokes, _foreignKeysStrokes, _indicesStrokes);
        final TableInfo _existingStrokes = TableInfo.read(connection, "strokes");
        if (!_infoStrokes.equals(_existingStrokes)) {
          return new RoomOpenDelegate.ValidationResult(false, "strokes(com.example.omninote.data.Stroke).\n"
                  + " Expected:\n" + _infoStrokes + "\n"
                  + " Found:\n" + _existingStrokes);
        }
        final Map<String, TableInfo.Column> _columnsNoteLinks = new HashMap<String, TableInfo.Column>(5);
        _columnsNoteLinks.put("sourceNoteId", new TableInfo.Column("sourceNoteId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoteLinks.put("targetNoteId", new TableInfo.Column("targetNoteId", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoteLinks.put("linkType", new TableInfo.Column("linkType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoteLinks.put("strength", new TableInfo.Column("strength", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNoteLinks.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysNoteLinks = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesNoteLinks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNoteLinks = new TableInfo("note_links", _columnsNoteLinks, _foreignKeysNoteLinks, _indicesNoteLinks);
        final TableInfo _existingNoteLinks = TableInfo.read(connection, "note_links");
        if (!_infoNoteLinks.equals(_existingNoteLinks)) {
          return new RoomOpenDelegate.ValidationResult(false, "note_links(com.example.omninote.data.NoteLink).\n"
                  + " Expected:\n" + _infoNoteLinks + "\n"
                  + " Found:\n" + _existingNoteLinks);
        }
        return new RoomOpenDelegate.ValidationResult(true, null);
      }
    };
    return _openDelegate;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final Map<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final Map<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "notes", "strokes", "note_links");
  }

  @Override
  public void clearAllTables() {
    super.performClear(false, "notes", "strokes", "note_links");
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final Map<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(NoteDao.class, NoteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StrokeDao.class, StrokeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NoteLinkDao.class, NoteLinkDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final Set<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public NoteDao noteDao() {
    if (_noteDao != null) {
      return _noteDao;
    } else {
      synchronized(this) {
        if(_noteDao == null) {
          _noteDao = new NoteDao_Impl(this);
        }
        return _noteDao;
      }
    }
  }

  @Override
  public StrokeDao strokeDao() {
    if (_strokeDao != null) {
      return _strokeDao;
    } else {
      synchronized(this) {
        if(_strokeDao == null) {
          _strokeDao = new StrokeDao_Impl(this);
        }
        return _strokeDao;
      }
    }
  }

  @Override
  public NoteLinkDao noteLinkDao() {
    if (_noteLinkDao != null) {
      return _noteLinkDao;
    } else {
      synchronized(this) {
        if(_noteLinkDao == null) {
          _noteLinkDao = new NoteLinkDao_Impl(this);
        }
        return _noteLinkDao;
      }
    }
  }
}
