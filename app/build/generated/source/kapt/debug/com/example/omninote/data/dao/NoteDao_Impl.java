package com.example.omninote.data.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.example.omninote.data.Note;
import com.example.omninote.data.NoteType;
import com.example.omninote.data.converters.Converters;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class NoteDao_Impl implements NoteDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Note> __insertAdapterOfNote;

  private final Converters __converters = new Converters();

  private final EntityDeleteOrUpdateAdapter<Note> __deleteAdapterOfNote;

  private final EntityDeleteOrUpdateAdapter<Note> __updateAdapterOfNote;

  public NoteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfNote = new EntityInsertAdapter<Note>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `notes` (`id`,`title`,`content`,`createdAt`,`updatedAt`,`tags`,`isArchived`,`parentNoteId`,`positionX`,`positionY`,`width`,`height`,`type`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Note entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        if (entity.getContent() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getContent());
        }
        final String _tmp = __converters.dateToTimestamp(entity.getCreatedAt());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, _tmp);
        }
        final String _tmp_1 = __converters.dateToTimestamp(entity.getUpdatedAt());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, _tmp_1);
        }
        final String _tmp_2 = __converters.toStringList(entity.getTags());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, _tmp_2);
        }
        final int _tmp_3 = entity.isArchived() ? 1 : 0;
        statement.bindLong(7, _tmp_3);
        if (entity.getParentNoteId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getParentNoteId());
        }
        statement.bindDouble(9, entity.getPositionX());
        statement.bindDouble(10, entity.getPositionY());
        statement.bindDouble(11, entity.getWidth());
        statement.bindDouble(12, entity.getHeight());
        statement.bindText(13, __NoteType_enumToString(entity.getType()));
      }
    };
    this.__deleteAdapterOfNote = new EntityDeleteOrUpdateAdapter<Note>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `notes` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Note entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfNote = new EntityDeleteOrUpdateAdapter<Note>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `notes` SET `id` = ?,`title` = ?,`content` = ?,`createdAt` = ?,`updatedAt` = ?,`tags` = ?,`isArchived` = ?,`parentNoteId` = ?,`positionX` = ?,`positionY` = ?,`width` = ?,`height` = ?,`type` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Note entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        if (entity.getContent() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getContent());
        }
        final String _tmp = __converters.dateToTimestamp(entity.getCreatedAt());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, _tmp);
        }
        final String _tmp_1 = __converters.dateToTimestamp(entity.getUpdatedAt());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, _tmp_1);
        }
        final String _tmp_2 = __converters.toStringList(entity.getTags());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, _tmp_2);
        }
        final int _tmp_3 = entity.isArchived() ? 1 : 0;
        statement.bindLong(7, _tmp_3);
        if (entity.getParentNoteId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getParentNoteId());
        }
        statement.bindDouble(9, entity.getPositionX());
        statement.bindDouble(10, entity.getPositionY());
        statement.bindDouble(11, entity.getWidth());
        statement.bindDouble(12, entity.getHeight());
        statement.bindText(13, __NoteType_enumToString(entity.getType()));
        statement.bindLong(14, entity.getId());
      }
    };
  }

  @Override
  public Object insertNote(final Note note, final Continuation<? super Long> arg1) {
    if (note == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfNote.insertAndReturnId(_connection, note);
    }, arg1);
  }

  @Override
  public Object deleteNote(final Note note, final Continuation<? super Unit> arg1) {
    if (note == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfNote.handle(_connection, note);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Object updateNote(final Note note, final Continuation<? super Unit> arg1) {
    if (note == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfNote.handle(_connection, note);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Flow<List<Note>> getAllNotes() {
    final String _sql = "SELECT * FROM notes WHERE isArchived = 0 ORDER BY updatedAt DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"notes"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfContent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "content");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfTags = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tags");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfParentNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "parentNoteId");
        final int _columnIndexOfPositionX = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionX");
        final int _columnIndexOfPositionY = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionY");
        final int _columnIndexOfWidth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "width");
        final int _columnIndexOfHeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "height");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final List<Note> _result = new ArrayList<Note>();
        while (_stmt.step()) {
          final Note _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpContent;
          if (_stmt.isNull(_columnIndexOfContent)) {
            _tmpContent = null;
          } else {
            _tmpContent = _stmt.getText(_columnIndexOfContent);
          }
          final LocalDateTime _tmpCreatedAt;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfCreatedAt);
          }
          _tmpCreatedAt = __converters.fromTimestamp(_tmp);
          final LocalDateTime _tmpUpdatedAt;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfUpdatedAt)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfUpdatedAt);
          }
          _tmpUpdatedAt = __converters.fromTimestamp(_tmp_1);
          final List<String> _tmpTags;
          final String _tmp_2;
          if (_stmt.isNull(_columnIndexOfTags)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfTags);
          }
          _tmpTags = __converters.fromStringList(_tmp_2);
          final boolean _tmpIsArchived;
          final int _tmp_3;
          _tmp_3 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_3 != 0;
          final Long _tmpParentNoteId;
          if (_stmt.isNull(_columnIndexOfParentNoteId)) {
            _tmpParentNoteId = null;
          } else {
            _tmpParentNoteId = _stmt.getLong(_columnIndexOfParentNoteId);
          }
          final float _tmpPositionX;
          _tmpPositionX = (float) (_stmt.getDouble(_columnIndexOfPositionX));
          final float _tmpPositionY;
          _tmpPositionY = (float) (_stmt.getDouble(_columnIndexOfPositionY));
          final float _tmpWidth;
          _tmpWidth = (float) (_stmt.getDouble(_columnIndexOfWidth));
          final float _tmpHeight;
          _tmpHeight = (float) (_stmt.getDouble(_columnIndexOfHeight));
          final NoteType _tmpType;
          _tmpType = __NoteType_stringToEnum(_stmt.getText(_columnIndexOfType));
          _item = new Note(_tmpId,_tmpTitle,_tmpContent,_tmpCreatedAt,_tmpUpdatedAt,_tmpTags,_tmpIsArchived,_tmpParentNoteId,_tmpPositionX,_tmpPositionY,_tmpWidth,_tmpHeight,_tmpType);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getNoteById(final long noteId, final Continuation<? super Note> arg1) {
    final String _sql = "SELECT * FROM notes WHERE id = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, noteId);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfContent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "content");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfTags = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tags");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfParentNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "parentNoteId");
        final int _columnIndexOfPositionX = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionX");
        final int _columnIndexOfPositionY = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionY");
        final int _columnIndexOfWidth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "width");
        final int _columnIndexOfHeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "height");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final Note _result;
        if (_stmt.step()) {
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpContent;
          if (_stmt.isNull(_columnIndexOfContent)) {
            _tmpContent = null;
          } else {
            _tmpContent = _stmt.getText(_columnIndexOfContent);
          }
          final LocalDateTime _tmpCreatedAt;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfCreatedAt);
          }
          _tmpCreatedAt = __converters.fromTimestamp(_tmp);
          final LocalDateTime _tmpUpdatedAt;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfUpdatedAt)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfUpdatedAt);
          }
          _tmpUpdatedAt = __converters.fromTimestamp(_tmp_1);
          final List<String> _tmpTags;
          final String _tmp_2;
          if (_stmt.isNull(_columnIndexOfTags)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfTags);
          }
          _tmpTags = __converters.fromStringList(_tmp_2);
          final boolean _tmpIsArchived;
          final int _tmp_3;
          _tmp_3 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_3 != 0;
          final Long _tmpParentNoteId;
          if (_stmt.isNull(_columnIndexOfParentNoteId)) {
            _tmpParentNoteId = null;
          } else {
            _tmpParentNoteId = _stmt.getLong(_columnIndexOfParentNoteId);
          }
          final float _tmpPositionX;
          _tmpPositionX = (float) (_stmt.getDouble(_columnIndexOfPositionX));
          final float _tmpPositionY;
          _tmpPositionY = (float) (_stmt.getDouble(_columnIndexOfPositionY));
          final float _tmpWidth;
          _tmpWidth = (float) (_stmt.getDouble(_columnIndexOfWidth));
          final float _tmpHeight;
          _tmpHeight = (float) (_stmt.getDouble(_columnIndexOfHeight));
          final NoteType _tmpType;
          _tmpType = __NoteType_stringToEnum(_stmt.getText(_columnIndexOfType));
          _result = new Note(_tmpId,_tmpTitle,_tmpContent,_tmpCreatedAt,_tmpUpdatedAt,_tmpTags,_tmpIsArchived,_tmpParentNoteId,_tmpPositionX,_tmpPositionY,_tmpWidth,_tmpHeight,_tmpType);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg1);
  }

  @Override
  public Object searchNotes(final String query, final Continuation<? super List<Note>> arg1) {
    final String _sql = "SELECT * FROM notes WHERE title LIKE '%' || ? || '%' OR content LIKE '%' || ? || '%'";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (query == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, query);
        }
        _argIndex = 2;
        if (query == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, query);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfContent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "content");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfTags = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tags");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfParentNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "parentNoteId");
        final int _columnIndexOfPositionX = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionX");
        final int _columnIndexOfPositionY = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionY");
        final int _columnIndexOfWidth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "width");
        final int _columnIndexOfHeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "height");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final List<Note> _result = new ArrayList<Note>();
        while (_stmt.step()) {
          final Note _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpContent;
          if (_stmt.isNull(_columnIndexOfContent)) {
            _tmpContent = null;
          } else {
            _tmpContent = _stmt.getText(_columnIndexOfContent);
          }
          final LocalDateTime _tmpCreatedAt;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfCreatedAt);
          }
          _tmpCreatedAt = __converters.fromTimestamp(_tmp);
          final LocalDateTime _tmpUpdatedAt;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfUpdatedAt)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfUpdatedAt);
          }
          _tmpUpdatedAt = __converters.fromTimestamp(_tmp_1);
          final List<String> _tmpTags;
          final String _tmp_2;
          if (_stmt.isNull(_columnIndexOfTags)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfTags);
          }
          _tmpTags = __converters.fromStringList(_tmp_2);
          final boolean _tmpIsArchived;
          final int _tmp_3;
          _tmp_3 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_3 != 0;
          final Long _tmpParentNoteId;
          if (_stmt.isNull(_columnIndexOfParentNoteId)) {
            _tmpParentNoteId = null;
          } else {
            _tmpParentNoteId = _stmt.getLong(_columnIndexOfParentNoteId);
          }
          final float _tmpPositionX;
          _tmpPositionX = (float) (_stmt.getDouble(_columnIndexOfPositionX));
          final float _tmpPositionY;
          _tmpPositionY = (float) (_stmt.getDouble(_columnIndexOfPositionY));
          final float _tmpWidth;
          _tmpWidth = (float) (_stmt.getDouble(_columnIndexOfWidth));
          final float _tmpHeight;
          _tmpHeight = (float) (_stmt.getDouble(_columnIndexOfHeight));
          final NoteType _tmpType;
          _tmpType = __NoteType_stringToEnum(_stmt.getText(_columnIndexOfType));
          _item = new Note(_tmpId,_tmpTitle,_tmpContent,_tmpCreatedAt,_tmpUpdatedAt,_tmpTags,_tmpIsArchived,_tmpParentNoteId,_tmpPositionX,_tmpPositionY,_tmpWidth,_tmpHeight,_tmpType);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg1);
  }

  @Override
  public Object getNotesByTag(final String tag, final Continuation<? super List<Note>> arg1) {
    final String _sql = "SELECT * FROM notes WHERE tags LIKE '%' || ? || '%'";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (tag == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, tag);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfContent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "content");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfTags = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tags");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfParentNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "parentNoteId");
        final int _columnIndexOfPositionX = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionX");
        final int _columnIndexOfPositionY = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionY");
        final int _columnIndexOfWidth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "width");
        final int _columnIndexOfHeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "height");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final List<Note> _result = new ArrayList<Note>();
        while (_stmt.step()) {
          final Note _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpContent;
          if (_stmt.isNull(_columnIndexOfContent)) {
            _tmpContent = null;
          } else {
            _tmpContent = _stmt.getText(_columnIndexOfContent);
          }
          final LocalDateTime _tmpCreatedAt;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfCreatedAt);
          }
          _tmpCreatedAt = __converters.fromTimestamp(_tmp);
          final LocalDateTime _tmpUpdatedAt;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfUpdatedAt)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfUpdatedAt);
          }
          _tmpUpdatedAt = __converters.fromTimestamp(_tmp_1);
          final List<String> _tmpTags;
          final String _tmp_2;
          if (_stmt.isNull(_columnIndexOfTags)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfTags);
          }
          _tmpTags = __converters.fromStringList(_tmp_2);
          final boolean _tmpIsArchived;
          final int _tmp_3;
          _tmp_3 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_3 != 0;
          final Long _tmpParentNoteId;
          if (_stmt.isNull(_columnIndexOfParentNoteId)) {
            _tmpParentNoteId = null;
          } else {
            _tmpParentNoteId = _stmt.getLong(_columnIndexOfParentNoteId);
          }
          final float _tmpPositionX;
          _tmpPositionX = (float) (_stmt.getDouble(_columnIndexOfPositionX));
          final float _tmpPositionY;
          _tmpPositionY = (float) (_stmt.getDouble(_columnIndexOfPositionY));
          final float _tmpWidth;
          _tmpWidth = (float) (_stmt.getDouble(_columnIndexOfWidth));
          final float _tmpHeight;
          _tmpHeight = (float) (_stmt.getDouble(_columnIndexOfHeight));
          final NoteType _tmpType;
          _tmpType = __NoteType_stringToEnum(_stmt.getText(_columnIndexOfType));
          _item = new Note(_tmpId,_tmpTitle,_tmpContent,_tmpCreatedAt,_tmpUpdatedAt,_tmpTags,_tmpIsArchived,_tmpParentNoteId,_tmpPositionX,_tmpPositionY,_tmpWidth,_tmpHeight,_tmpType);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg1);
  }

  @Override
  public Flow<List<Note>> getTopLevelNotes() {
    final String _sql = "SELECT * FROM notes WHERE isArchived = 0 AND parentNoteId IS NULL ORDER BY updatedAt DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"notes"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfContent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "content");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfTags = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tags");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfParentNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "parentNoteId");
        final int _columnIndexOfPositionX = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionX");
        final int _columnIndexOfPositionY = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionY");
        final int _columnIndexOfWidth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "width");
        final int _columnIndexOfHeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "height");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final List<Note> _result = new ArrayList<Note>();
        while (_stmt.step()) {
          final Note _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpContent;
          if (_stmt.isNull(_columnIndexOfContent)) {
            _tmpContent = null;
          } else {
            _tmpContent = _stmt.getText(_columnIndexOfContent);
          }
          final LocalDateTime _tmpCreatedAt;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfCreatedAt);
          }
          _tmpCreatedAt = __converters.fromTimestamp(_tmp);
          final LocalDateTime _tmpUpdatedAt;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfUpdatedAt)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfUpdatedAt);
          }
          _tmpUpdatedAt = __converters.fromTimestamp(_tmp_1);
          final List<String> _tmpTags;
          final String _tmp_2;
          if (_stmt.isNull(_columnIndexOfTags)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfTags);
          }
          _tmpTags = __converters.fromStringList(_tmp_2);
          final boolean _tmpIsArchived;
          final int _tmp_3;
          _tmp_3 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_3 != 0;
          final Long _tmpParentNoteId;
          if (_stmt.isNull(_columnIndexOfParentNoteId)) {
            _tmpParentNoteId = null;
          } else {
            _tmpParentNoteId = _stmt.getLong(_columnIndexOfParentNoteId);
          }
          final float _tmpPositionX;
          _tmpPositionX = (float) (_stmt.getDouble(_columnIndexOfPositionX));
          final float _tmpPositionY;
          _tmpPositionY = (float) (_stmt.getDouble(_columnIndexOfPositionY));
          final float _tmpWidth;
          _tmpWidth = (float) (_stmt.getDouble(_columnIndexOfWidth));
          final float _tmpHeight;
          _tmpHeight = (float) (_stmt.getDouble(_columnIndexOfHeight));
          final NoteType _tmpType;
          _tmpType = __NoteType_stringToEnum(_stmt.getText(_columnIndexOfType));
          _item = new Note(_tmpId,_tmpTitle,_tmpContent,_tmpCreatedAt,_tmpUpdatedAt,_tmpTags,_tmpIsArchived,_tmpParentNoteId,_tmpPositionX,_tmpPositionY,_tmpWidth,_tmpHeight,_tmpType);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<Note>> getChildNotesFlow(final long parentId) {
    final String _sql = "SELECT * FROM notes WHERE isArchived = 0 AND parentNoteId = ? ORDER BY updatedAt DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"notes"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, parentId);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfContent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "content");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfTags = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tags");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfParentNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "parentNoteId");
        final int _columnIndexOfPositionX = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionX");
        final int _columnIndexOfPositionY = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionY");
        final int _columnIndexOfWidth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "width");
        final int _columnIndexOfHeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "height");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final List<Note> _result = new ArrayList<Note>();
        while (_stmt.step()) {
          final Note _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpContent;
          if (_stmt.isNull(_columnIndexOfContent)) {
            _tmpContent = null;
          } else {
            _tmpContent = _stmt.getText(_columnIndexOfContent);
          }
          final LocalDateTime _tmpCreatedAt;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfCreatedAt);
          }
          _tmpCreatedAt = __converters.fromTimestamp(_tmp);
          final LocalDateTime _tmpUpdatedAt;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfUpdatedAt)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfUpdatedAt);
          }
          _tmpUpdatedAt = __converters.fromTimestamp(_tmp_1);
          final List<String> _tmpTags;
          final String _tmp_2;
          if (_stmt.isNull(_columnIndexOfTags)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfTags);
          }
          _tmpTags = __converters.fromStringList(_tmp_2);
          final boolean _tmpIsArchived;
          final int _tmp_3;
          _tmp_3 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_3 != 0;
          final Long _tmpParentNoteId;
          if (_stmt.isNull(_columnIndexOfParentNoteId)) {
            _tmpParentNoteId = null;
          } else {
            _tmpParentNoteId = _stmt.getLong(_columnIndexOfParentNoteId);
          }
          final float _tmpPositionX;
          _tmpPositionX = (float) (_stmt.getDouble(_columnIndexOfPositionX));
          final float _tmpPositionY;
          _tmpPositionY = (float) (_stmt.getDouble(_columnIndexOfPositionY));
          final float _tmpWidth;
          _tmpWidth = (float) (_stmt.getDouble(_columnIndexOfWidth));
          final float _tmpHeight;
          _tmpHeight = (float) (_stmt.getDouble(_columnIndexOfHeight));
          final NoteType _tmpType;
          _tmpType = __NoteType_stringToEnum(_stmt.getText(_columnIndexOfType));
          _item = new Note(_tmpId,_tmpTitle,_tmpContent,_tmpCreatedAt,_tmpUpdatedAt,_tmpTags,_tmpIsArchived,_tmpParentNoteId,_tmpPositionX,_tmpPositionY,_tmpWidth,_tmpHeight,_tmpType);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getChildNotesSuspend(final long parentId,
      final Continuation<? super List<Note>> arg1) {
    final String _sql = "SELECT * FROM notes WHERE parentNoteId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, parentId);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfContent = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "content");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfTags = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tags");
        final int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
        final int _columnIndexOfParentNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "parentNoteId");
        final int _columnIndexOfPositionX = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionX");
        final int _columnIndexOfPositionY = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "positionY");
        final int _columnIndexOfWidth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "width");
        final int _columnIndexOfHeight = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "height");
        final int _columnIndexOfType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "type");
        final List<Note> _result = new ArrayList<Note>();
        while (_stmt.step()) {
          final Note _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpContent;
          if (_stmt.isNull(_columnIndexOfContent)) {
            _tmpContent = null;
          } else {
            _tmpContent = _stmt.getText(_columnIndexOfContent);
          }
          final LocalDateTime _tmpCreatedAt;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfCreatedAt);
          }
          _tmpCreatedAt = __converters.fromTimestamp(_tmp);
          final LocalDateTime _tmpUpdatedAt;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfUpdatedAt)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfUpdatedAt);
          }
          _tmpUpdatedAt = __converters.fromTimestamp(_tmp_1);
          final List<String> _tmpTags;
          final String _tmp_2;
          if (_stmt.isNull(_columnIndexOfTags)) {
            _tmp_2 = null;
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfTags);
          }
          _tmpTags = __converters.fromStringList(_tmp_2);
          final boolean _tmpIsArchived;
          final int _tmp_3;
          _tmp_3 = (int) (_stmt.getLong(_columnIndexOfIsArchived));
          _tmpIsArchived = _tmp_3 != 0;
          final Long _tmpParentNoteId;
          if (_stmt.isNull(_columnIndexOfParentNoteId)) {
            _tmpParentNoteId = null;
          } else {
            _tmpParentNoteId = _stmt.getLong(_columnIndexOfParentNoteId);
          }
          final float _tmpPositionX;
          _tmpPositionX = (float) (_stmt.getDouble(_columnIndexOfPositionX));
          final float _tmpPositionY;
          _tmpPositionY = (float) (_stmt.getDouble(_columnIndexOfPositionY));
          final float _tmpWidth;
          _tmpWidth = (float) (_stmt.getDouble(_columnIndexOfWidth));
          final float _tmpHeight;
          _tmpHeight = (float) (_stmt.getDouble(_columnIndexOfHeight));
          final NoteType _tmpType;
          _tmpType = __NoteType_stringToEnum(_stmt.getText(_columnIndexOfType));
          _item = new Note(_tmpId,_tmpTitle,_tmpContent,_tmpCreatedAt,_tmpUpdatedAt,_tmpTags,_tmpIsArchived,_tmpParentNoteId,_tmpPositionX,_tmpPositionY,_tmpWidth,_tmpHeight,_tmpType);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg1);
  }

  @Override
  public Object archiveNote(final long noteId, final Continuation<? super Unit> arg1) {
    final String _sql = "UPDATE notes SET isArchived = 1 WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, noteId);
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, arg1);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __NoteType_enumToString(@NonNull final NoteType _value) {
    switch (_value) {
      case TEXT: return "TEXT";
      case CANVAS: return "CANVAS";
      case KNOWLEDGE_GRAPH: return "KNOWLEDGE_GRAPH";
      case FOLDER: return "FOLDER";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private NoteType __NoteType_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "TEXT": return NoteType.TEXT;
      case "CANVAS": return NoteType.CANVAS;
      case "KNOWLEDGE_GRAPH": return NoteType.KNOWLEDGE_GRAPH;
      case "FOLDER": return NoteType.FOLDER;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
