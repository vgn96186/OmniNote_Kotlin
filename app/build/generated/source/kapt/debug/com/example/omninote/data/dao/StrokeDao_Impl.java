package com.example.omninote.data.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.example.omninote.data.Point;
import com.example.omninote.data.Stroke;
import com.example.omninote.data.ToolType;
import com.example.omninote.data.converters.Converters;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Long;
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
public final class StrokeDao_Impl implements StrokeDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Stroke> __insertAdapterOfStroke;

  private final Converters __converters = new Converters();

  private final EntityDeleteOrUpdateAdapter<Stroke> __deleteAdapterOfStroke;

  private final EntityDeleteOrUpdateAdapter<Stroke> __updateAdapterOfStroke;

  public StrokeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfStroke = new EntityInsertAdapter<Stroke>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `strokes` (`id`,`noteId`,`points`,`color`,`strokeWidth`,`pressure`,`toolType`,`timestamp`,`layer`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Stroke entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getNoteId());
        final String _tmp = __converters.toPointList(entity.getPoints());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, _tmp);
        }
        statement.bindLong(4, entity.getColor());
        statement.bindDouble(5, entity.getStrokeWidth());
        statement.bindDouble(6, entity.getPressure());
        statement.bindText(7, __ToolType_enumToString(entity.getToolType()));
        statement.bindLong(8, entity.getTimestamp());
        statement.bindLong(9, entity.getLayer());
      }
    };
    this.__deleteAdapterOfStroke = new EntityDeleteOrUpdateAdapter<Stroke>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `strokes` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Stroke entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfStroke = new EntityDeleteOrUpdateAdapter<Stroke>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `strokes` SET `id` = ?,`noteId` = ?,`points` = ?,`color` = ?,`strokeWidth` = ?,`pressure` = ?,`toolType` = ?,`timestamp` = ?,`layer` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Stroke entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getNoteId());
        final String _tmp = __converters.toPointList(entity.getPoints());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, _tmp);
        }
        statement.bindLong(4, entity.getColor());
        statement.bindDouble(5, entity.getStrokeWidth());
        statement.bindDouble(6, entity.getPressure());
        statement.bindText(7, __ToolType_enumToString(entity.getToolType()));
        statement.bindLong(8, entity.getTimestamp());
        statement.bindLong(9, entity.getLayer());
        statement.bindLong(10, entity.getId());
      }
    };
  }

  @Override
  public Object insertStroke(final Stroke stroke, final Continuation<? super Long> arg1) {
    if (stroke == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfStroke.insertAndReturnId(_connection, stroke);
    }, arg1);
  }

  @Override
  public Object insertStrokes(final List<Stroke> strokes, final Continuation<? super Unit> arg1) {
    if (strokes == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfStroke.insert(_connection, strokes);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Object deleteStroke(final Stroke stroke, final Continuation<? super Unit> arg1) {
    if (stroke == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfStroke.handle(_connection, stroke);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Object updateStroke(final Stroke stroke, final Continuation<? super Unit> arg1) {
    if (stroke == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfStroke.handle(_connection, stroke);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Flow<List<Stroke>> getStrokesForNote(final long noteId) {
    final String _sql = "SELECT * FROM strokes WHERE noteId = ? ORDER BY timestamp ASC";
    return FlowUtil.createFlow(__db, false, new String[] {"strokes"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, noteId);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "noteId");
        final int _columnIndexOfPoints = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "points");
        final int _columnIndexOfColor = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "color");
        final int _columnIndexOfStrokeWidth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strokeWidth");
        final int _columnIndexOfPressure = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "pressure");
        final int _columnIndexOfToolType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "toolType");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _columnIndexOfLayer = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "layer");
        final List<Stroke> _result = new ArrayList<Stroke>();
        while (_stmt.step()) {
          final Stroke _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final long _tmpNoteId;
          _tmpNoteId = _stmt.getLong(_columnIndexOfNoteId);
          final List<Point> _tmpPoints;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfPoints)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfPoints);
          }
          _tmpPoints = __converters.fromPointList(_tmp);
          final int _tmpColor;
          _tmpColor = (int) (_stmt.getLong(_columnIndexOfColor));
          final float _tmpStrokeWidth;
          _tmpStrokeWidth = (float) (_stmt.getDouble(_columnIndexOfStrokeWidth));
          final float _tmpPressure;
          _tmpPressure = (float) (_stmt.getDouble(_columnIndexOfPressure));
          final ToolType _tmpToolType;
          _tmpToolType = __ToolType_stringToEnum(_stmt.getText(_columnIndexOfToolType));
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          final int _tmpLayer;
          _tmpLayer = (int) (_stmt.getLong(_columnIndexOfLayer));
          _item = new Stroke(_tmpId,_tmpNoteId,_tmpPoints,_tmpColor,_tmpStrokeWidth,_tmpPressure,_tmpToolType,_tmpTimestamp,_tmpLayer);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getStrokesForNoteAndLayer(final long noteId, final int layer,
      final Continuation<? super List<Stroke>> arg2) {
    final String _sql = "SELECT * FROM strokes WHERE noteId = ? AND layer = ? ORDER BY timestamp ASC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, noteId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, layer);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "noteId");
        final int _columnIndexOfPoints = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "points");
        final int _columnIndexOfColor = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "color");
        final int _columnIndexOfStrokeWidth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strokeWidth");
        final int _columnIndexOfPressure = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "pressure");
        final int _columnIndexOfToolType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "toolType");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _columnIndexOfLayer = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "layer");
        final List<Stroke> _result = new ArrayList<Stroke>();
        while (_stmt.step()) {
          final Stroke _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final long _tmpNoteId;
          _tmpNoteId = _stmt.getLong(_columnIndexOfNoteId);
          final List<Point> _tmpPoints;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfPoints)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfPoints);
          }
          _tmpPoints = __converters.fromPointList(_tmp);
          final int _tmpColor;
          _tmpColor = (int) (_stmt.getLong(_columnIndexOfColor));
          final float _tmpStrokeWidth;
          _tmpStrokeWidth = (float) (_stmt.getDouble(_columnIndexOfStrokeWidth));
          final float _tmpPressure;
          _tmpPressure = (float) (_stmt.getDouble(_columnIndexOfPressure));
          final ToolType _tmpToolType;
          _tmpToolType = __ToolType_stringToEnum(_stmt.getText(_columnIndexOfToolType));
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          final int _tmpLayer;
          _tmpLayer = (int) (_stmt.getLong(_columnIndexOfLayer));
          _item = new Stroke(_tmpId,_tmpNoteId,_tmpPoints,_tmpColor,_tmpStrokeWidth,_tmpPressure,_tmpToolType,_tmpTimestamp,_tmpLayer);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg2);
  }

  @Override
  public Object getStrokesSince(final long noteId, final long sinceTimestamp,
      final Continuation<? super List<Stroke>> arg2) {
    final String _sql = "SELECT * FROM strokes WHERE noteId = ? AND timestamp > ? ORDER BY timestamp ASC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, noteId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, sinceTimestamp);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "noteId");
        final int _columnIndexOfPoints = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "points");
        final int _columnIndexOfColor = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "color");
        final int _columnIndexOfStrokeWidth = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "strokeWidth");
        final int _columnIndexOfPressure = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "pressure");
        final int _columnIndexOfToolType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "toolType");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _columnIndexOfLayer = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "layer");
        final List<Stroke> _result = new ArrayList<Stroke>();
        while (_stmt.step()) {
          final Stroke _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final long _tmpNoteId;
          _tmpNoteId = _stmt.getLong(_columnIndexOfNoteId);
          final List<Point> _tmpPoints;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfPoints)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfPoints);
          }
          _tmpPoints = __converters.fromPointList(_tmp);
          final int _tmpColor;
          _tmpColor = (int) (_stmt.getLong(_columnIndexOfColor));
          final float _tmpStrokeWidth;
          _tmpStrokeWidth = (float) (_stmt.getDouble(_columnIndexOfStrokeWidth));
          final float _tmpPressure;
          _tmpPressure = (float) (_stmt.getDouble(_columnIndexOfPressure));
          final ToolType _tmpToolType;
          _tmpToolType = __ToolType_stringToEnum(_stmt.getText(_columnIndexOfToolType));
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          final int _tmpLayer;
          _tmpLayer = (int) (_stmt.getLong(_columnIndexOfLayer));
          _item = new Stroke(_tmpId,_tmpNoteId,_tmpPoints,_tmpColor,_tmpStrokeWidth,_tmpPressure,_tmpToolType,_tmpTimestamp,_tmpLayer);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg2);
  }

  @Override
  public Object deleteAllStrokesForNote(final long noteId, final Continuation<? super Unit> arg1) {
    final String _sql = "DELETE FROM strokes WHERE noteId = ?";
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

  @Override
  public Object deleteStrokesForNoteAndLayer(final long noteId, final int layer,
      final Continuation<? super Unit> arg2) {
    final String _sql = "DELETE FROM strokes WHERE noteId = ? AND layer = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, noteId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, layer);
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, arg2);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __ToolType_enumToString(@NonNull final ToolType _value) {
    switch (_value) {
      case PEN: return "PEN";
      case HIGHLIGHTER: return "HIGHLIGHTER";
      case ERASER: return "ERASER";
      case SHAPE_DRAWER: return "SHAPE_DRAWER";
      case TEXT: return "TEXT";
      case BRUSH: return "BRUSH";
      case MASKING_TAPE: return "MASKING_TAPE";
      case LASSO: return "LASSO";
      case SHAPE_PEN: return "SHAPE_PEN";
      case LASER_PEN: return "LASER_PEN";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private ToolType __ToolType_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "PEN": return ToolType.PEN;
      case "HIGHLIGHTER": return ToolType.HIGHLIGHTER;
      case "ERASER": return ToolType.ERASER;
      case "SHAPE_DRAWER": return ToolType.SHAPE_DRAWER;
      case "TEXT": return ToolType.TEXT;
      case "BRUSH": return ToolType.BRUSH;
      case "MASKING_TAPE": return ToolType.MASKING_TAPE;
      case "LASSO": return ToolType.LASSO;
      case "SHAPE_PEN": return ToolType.SHAPE_PEN;
      case "LASER_PEN": return ToolType.LASER_PEN;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
