package com.example.omninote.data.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.example.omninote.data.LinkType;
import com.example.omninote.data.NoteLink;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class NoteLinkDao_Impl implements NoteLinkDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<NoteLink> __insertAdapterOfNoteLink;

  private final EntityDeleteOrUpdateAdapter<NoteLink> __deleteAdapterOfNoteLink;

  private final EntityDeleteOrUpdateAdapter<NoteLink> __updateAdapterOfNoteLink;

  public NoteLinkDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfNoteLink = new EntityInsertAdapter<NoteLink>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `note_links` (`sourceNoteId`,`targetNoteId`,`linkType`,`strength`,`createdAt`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final NoteLink entity) {
        statement.bindLong(1, entity.getSourceNoteId());
        statement.bindLong(2, entity.getTargetNoteId());
        statement.bindText(3, __LinkType_enumToString(entity.getLinkType()));
        statement.bindDouble(4, entity.getStrength());
        statement.bindLong(5, entity.getCreatedAt());
      }
    };
    this.__deleteAdapterOfNoteLink = new EntityDeleteOrUpdateAdapter<NoteLink>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `note_links` WHERE `sourceNoteId` = ? AND `targetNoteId` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final NoteLink entity) {
        statement.bindLong(1, entity.getSourceNoteId());
        statement.bindLong(2, entity.getTargetNoteId());
      }
    };
    this.__updateAdapterOfNoteLink = new EntityDeleteOrUpdateAdapter<NoteLink>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `note_links` SET `sourceNoteId` = ?,`targetNoteId` = ?,`linkType` = ?,`strength` = ?,`createdAt` = ? WHERE `sourceNoteId` = ? AND `targetNoteId` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final NoteLink entity) {
        statement.bindLong(1, entity.getSourceNoteId());
        statement.bindLong(2, entity.getTargetNoteId());
        statement.bindText(3, __LinkType_enumToString(entity.getLinkType()));
        statement.bindDouble(4, entity.getStrength());
        statement.bindLong(5, entity.getCreatedAt());
        statement.bindLong(6, entity.getSourceNoteId());
        statement.bindLong(7, entity.getTargetNoteId());
      }
    };
  }

  @Override
  public Object insertLink(final NoteLink link, final Continuation<? super Unit> $completion) {
    if (link == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfNoteLink.insert(_connection, link);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object deleteLink(final NoteLink link, final Continuation<? super Unit> $completion) {
    if (link == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfNoteLink.handle(_connection, link);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object updateLink(final NoteLink link, final Continuation<? super Unit> $completion) {
    if (link == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfNoteLink.handle(_connection, link);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<NoteLink>> getLinksForNote(final long noteId) {
    final String _sql = "SELECT * FROM note_links WHERE sourceNoteId = ? OR targetNoteId = ?";
    return FlowUtil.createFlow(__db, false, new String[] {"note_links"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, noteId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, noteId);
        final int _columnIndexOfSourceNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sourceNoteId");
        final int _columnIndexOfTargetNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "targetNoteId");
        final int _columnIndexOfLinkType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "linkType");
        final int _columnIndexOfStrength = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strength");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final List<NoteLink> _result = new ArrayList<NoteLink>();
        while (_stmt.step()) {
          final NoteLink _item;
          final long _tmpSourceNoteId;
          _tmpSourceNoteId = _stmt.getLong(_columnIndexOfSourceNoteId);
          final long _tmpTargetNoteId;
          _tmpTargetNoteId = _stmt.getLong(_columnIndexOfTargetNoteId);
          final LinkType _tmpLinkType;
          _tmpLinkType = __LinkType_stringToEnum(_stmt.getText(_columnIndexOfLinkType));
          final float _tmpStrength;
          _tmpStrength = (float) (_stmt.getDouble(_columnIndexOfStrength));
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _item = new NoteLink(_tmpSourceNoteId,_tmpTargetNoteId,_tmpLinkType,_tmpStrength,_tmpCreatedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getLink(final long sourceId, final long targetId,
      final Continuation<? super NoteLink> $completion) {
    final String _sql = "SELECT * FROM note_links WHERE sourceNoteId = ? AND targetNoteId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, sourceId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, targetId);
        final int _columnIndexOfSourceNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sourceNoteId");
        final int _columnIndexOfTargetNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "targetNoteId");
        final int _columnIndexOfLinkType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "linkType");
        final int _columnIndexOfStrength = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strength");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final NoteLink _result;
        if (_stmt.step()) {
          final long _tmpSourceNoteId;
          _tmpSourceNoteId = _stmt.getLong(_columnIndexOfSourceNoteId);
          final long _tmpTargetNoteId;
          _tmpTargetNoteId = _stmt.getLong(_columnIndexOfTargetNoteId);
          final LinkType _tmpLinkType;
          _tmpLinkType = __LinkType_stringToEnum(_stmt.getText(_columnIndexOfLinkType));
          final float _tmpStrength;
          _tmpStrength = (float) (_stmt.getDouble(_columnIndexOfStrength));
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _result = new NoteLink(_tmpSourceNoteId,_tmpTargetNoteId,_tmpLinkType,_tmpStrength,_tmpCreatedAt);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getOutgoingLinks(final long noteId,
      final Continuation<? super List<NoteLink>> $completion) {
    final String _sql = "SELECT * FROM note_links WHERE sourceNoteId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, noteId);
        final int _columnIndexOfSourceNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sourceNoteId");
        final int _columnIndexOfTargetNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "targetNoteId");
        final int _columnIndexOfLinkType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "linkType");
        final int _columnIndexOfStrength = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strength");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final List<NoteLink> _result = new ArrayList<NoteLink>();
        while (_stmt.step()) {
          final NoteLink _item;
          final long _tmpSourceNoteId;
          _tmpSourceNoteId = _stmt.getLong(_columnIndexOfSourceNoteId);
          final long _tmpTargetNoteId;
          _tmpTargetNoteId = _stmt.getLong(_columnIndexOfTargetNoteId);
          final LinkType _tmpLinkType;
          _tmpLinkType = __LinkType_stringToEnum(_stmt.getText(_columnIndexOfLinkType));
          final float _tmpStrength;
          _tmpStrength = (float) (_stmt.getDouble(_columnIndexOfStrength));
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _item = new NoteLink(_tmpSourceNoteId,_tmpTargetNoteId,_tmpLinkType,_tmpStrength,_tmpCreatedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getIncomingLinks(final long noteId,
      final Continuation<? super List<NoteLink>> $completion) {
    final String _sql = "SELECT * FROM note_links WHERE targetNoteId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, noteId);
        final int _columnIndexOfSourceNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sourceNoteId");
        final int _columnIndexOfTargetNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "targetNoteId");
        final int _columnIndexOfLinkType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "linkType");
        final int _columnIndexOfStrength = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strength");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final List<NoteLink> _result = new ArrayList<NoteLink>();
        while (_stmt.step()) {
          final NoteLink _item;
          final long _tmpSourceNoteId;
          _tmpSourceNoteId = _stmt.getLong(_columnIndexOfSourceNoteId);
          final long _tmpTargetNoteId;
          _tmpTargetNoteId = _stmt.getLong(_columnIndexOfTargetNoteId);
          final LinkType _tmpLinkType;
          _tmpLinkType = __LinkType_stringToEnum(_stmt.getText(_columnIndexOfLinkType));
          final float _tmpStrength;
          _tmpStrength = (float) (_stmt.getDouble(_columnIndexOfStrength));
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _item = new NoteLink(_tmpSourceNoteId,_tmpTargetNoteId,_tmpLinkType,_tmpStrength,_tmpCreatedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getLinksByType(final LinkType linkType,
      final Continuation<? super List<NoteLink>> $completion) {
    final String _sql = "SELECT * FROM note_links WHERE linkType = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindText(_argIndex, __LinkType_enumToString(linkType));
        final int _columnIndexOfSourceNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sourceNoteId");
        final int _columnIndexOfTargetNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "targetNoteId");
        final int _columnIndexOfLinkType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "linkType");
        final int _columnIndexOfStrength = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strength");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final List<NoteLink> _result = new ArrayList<NoteLink>();
        while (_stmt.step()) {
          final NoteLink _item;
          final long _tmpSourceNoteId;
          _tmpSourceNoteId = _stmt.getLong(_columnIndexOfSourceNoteId);
          final long _tmpTargetNoteId;
          _tmpTargetNoteId = _stmt.getLong(_columnIndexOfTargetNoteId);
          final LinkType _tmpLinkType;
          _tmpLinkType = __LinkType_stringToEnum(_stmt.getText(_columnIndexOfLinkType));
          final float _tmpStrength;
          _tmpStrength = (float) (_stmt.getDouble(_columnIndexOfStrength));
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          _item = new NoteLink(_tmpSourceNoteId,_tmpTargetNoteId,_tmpLinkType,_tmpStrength,_tmpCreatedAt);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteAllLinksForNote(final long noteId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM note_links WHERE sourceNoteId = ? OR targetNoteId = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, noteId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, noteId);
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __LinkType_enumToString(@NonNull final LinkType _value) {
    switch (_value) {
      case RELATED: return "RELATED";
      case PARENT_CHILD: return "PARENT_CHILD";
      case REFERENCE: return "REFERENCE";
      case SIMILAR: return "SIMILAR";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private LinkType __LinkType_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "RELATED": return LinkType.RELATED;
      case "PARENT_CHILD": return LinkType.PARENT_CHILD;
      case "REFERENCE": return LinkType.REFERENCE;
      case "SIMILAR": return LinkType.SIMILAR;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
