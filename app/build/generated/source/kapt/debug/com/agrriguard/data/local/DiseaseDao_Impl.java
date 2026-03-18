package com.agrriguard.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.agrriguard.data.model.DiseaseKnowledge;
import com.agrriguard.data.model.DiseaseReport;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DiseaseDao_Impl implements DiseaseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DiseaseKnowledge> __insertionAdapterOfDiseaseKnowledge;

  private final EntityInsertionAdapter<DiseaseReport> __insertionAdapterOfDiseaseReport;

  private final SharedSQLiteStatement __preparedStmtOfMarkReportAsSynced;

  public DiseaseDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDiseaseKnowledge = new EntityInsertionAdapter<DiseaseKnowledge>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `diseases_knowledge` (`diseaseId`,`name`,`affectedCrops`,`symptoms`,`cause`,`naturalTreatment`,`chemicalTreatment`,`prevention`,`imageResId`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DiseaseKnowledge value) {
        if (value.getDiseaseId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDiseaseId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getAffectedCrops() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAffectedCrops());
        }
        if (value.getSymptoms() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSymptoms());
        }
        if (value.getCause() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCause());
        }
        if (value.getNaturalTreatment() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getNaturalTreatment());
        }
        if (value.getChemicalTreatment() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getChemicalTreatment());
        }
        if (value.getPrevention() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getPrevention());
        }
        stmt.bindLong(9, value.getImageResId());
      }
    };
    this.__insertionAdapterOfDiseaseReport = new EntityInsertionAdapter<DiseaseReport>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `disease_reports` (`id`,`diseaseName`,`cropType`,`latitude`,`longitude`,`timestamp`,`isSynced`,`rating`,`comment`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DiseaseReport value) {
        stmt.bindLong(1, value.getId());
        if (value.getDiseaseName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDiseaseName());
        }
        if (value.getCropType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCropType());
        }
        stmt.bindDouble(4, value.getLatitude());
        stmt.bindDouble(5, value.getLongitude());
        stmt.bindLong(6, value.getTimestamp());
        final int _tmp = value.isSynced() ? 1 : 0;
        stmt.bindLong(7, _tmp);
        stmt.bindDouble(8, value.getRating());
        if (value.getComment() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getComment());
        }
      }
    };
    this.__preparedStmtOfMarkReportAsSynced = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE disease_reports SET isSynced = 1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertKnowledge(final List<DiseaseKnowledge> knowledge,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDiseaseKnowledge.insert(knowledge);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertReport(final DiseaseReport report,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDiseaseReport.insert(report);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markReportAsSynced(final long reportId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkReportAsSynced.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, reportId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfMarkReportAsSynced.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DiseaseKnowledge>> getAllKnowledge() {
    final String _sql = "SELECT * FROM diseases_knowledge";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"diseases_knowledge"}, new Callable<List<DiseaseKnowledge>>() {
      @Override
      public List<DiseaseKnowledge> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDiseaseId = CursorUtil.getColumnIndexOrThrow(_cursor, "diseaseId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAffectedCrops = CursorUtil.getColumnIndexOrThrow(_cursor, "affectedCrops");
          final int _cursorIndexOfSymptoms = CursorUtil.getColumnIndexOrThrow(_cursor, "symptoms");
          final int _cursorIndexOfCause = CursorUtil.getColumnIndexOrThrow(_cursor, "cause");
          final int _cursorIndexOfNaturalTreatment = CursorUtil.getColumnIndexOrThrow(_cursor, "naturalTreatment");
          final int _cursorIndexOfChemicalTreatment = CursorUtil.getColumnIndexOrThrow(_cursor, "chemicalTreatment");
          final int _cursorIndexOfPrevention = CursorUtil.getColumnIndexOrThrow(_cursor, "prevention");
          final int _cursorIndexOfImageResId = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResId");
          final List<DiseaseKnowledge> _result = new ArrayList<DiseaseKnowledge>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DiseaseKnowledge _item;
            final String _tmpDiseaseId;
            if (_cursor.isNull(_cursorIndexOfDiseaseId)) {
              _tmpDiseaseId = null;
            } else {
              _tmpDiseaseId = _cursor.getString(_cursorIndexOfDiseaseId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpAffectedCrops;
            if (_cursor.isNull(_cursorIndexOfAffectedCrops)) {
              _tmpAffectedCrops = null;
            } else {
              _tmpAffectedCrops = _cursor.getString(_cursorIndexOfAffectedCrops);
            }
            final String _tmpSymptoms;
            if (_cursor.isNull(_cursorIndexOfSymptoms)) {
              _tmpSymptoms = null;
            } else {
              _tmpSymptoms = _cursor.getString(_cursorIndexOfSymptoms);
            }
            final String _tmpCause;
            if (_cursor.isNull(_cursorIndexOfCause)) {
              _tmpCause = null;
            } else {
              _tmpCause = _cursor.getString(_cursorIndexOfCause);
            }
            final String _tmpNaturalTreatment;
            if (_cursor.isNull(_cursorIndexOfNaturalTreatment)) {
              _tmpNaturalTreatment = null;
            } else {
              _tmpNaturalTreatment = _cursor.getString(_cursorIndexOfNaturalTreatment);
            }
            final String _tmpChemicalTreatment;
            if (_cursor.isNull(_cursorIndexOfChemicalTreatment)) {
              _tmpChemicalTreatment = null;
            } else {
              _tmpChemicalTreatment = _cursor.getString(_cursorIndexOfChemicalTreatment);
            }
            final String _tmpPrevention;
            if (_cursor.isNull(_cursorIndexOfPrevention)) {
              _tmpPrevention = null;
            } else {
              _tmpPrevention = _cursor.getString(_cursorIndexOfPrevention);
            }
            final int _tmpImageResId;
            _tmpImageResId = _cursor.getInt(_cursorIndexOfImageResId);
            _item = new DiseaseKnowledge(_tmpDiseaseId,_tmpName,_tmpAffectedCrops,_tmpSymptoms,_tmpCause,_tmpNaturalTreatment,_tmpChemicalTreatment,_tmpPrevention,_tmpImageResId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getKnowledgeByName(final String name,
      final Continuation<? super DiseaseKnowledge> $completion) {
    final String _sql = "SELECT * FROM diseases_knowledge WHERE name = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DiseaseKnowledge>() {
      @Override
      public DiseaseKnowledge call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDiseaseId = CursorUtil.getColumnIndexOrThrow(_cursor, "diseaseId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAffectedCrops = CursorUtil.getColumnIndexOrThrow(_cursor, "affectedCrops");
          final int _cursorIndexOfSymptoms = CursorUtil.getColumnIndexOrThrow(_cursor, "symptoms");
          final int _cursorIndexOfCause = CursorUtil.getColumnIndexOrThrow(_cursor, "cause");
          final int _cursorIndexOfNaturalTreatment = CursorUtil.getColumnIndexOrThrow(_cursor, "naturalTreatment");
          final int _cursorIndexOfChemicalTreatment = CursorUtil.getColumnIndexOrThrow(_cursor, "chemicalTreatment");
          final int _cursorIndexOfPrevention = CursorUtil.getColumnIndexOrThrow(_cursor, "prevention");
          final int _cursorIndexOfImageResId = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResId");
          final DiseaseKnowledge _result;
          if(_cursor.moveToFirst()) {
            final String _tmpDiseaseId;
            if (_cursor.isNull(_cursorIndexOfDiseaseId)) {
              _tmpDiseaseId = null;
            } else {
              _tmpDiseaseId = _cursor.getString(_cursorIndexOfDiseaseId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpAffectedCrops;
            if (_cursor.isNull(_cursorIndexOfAffectedCrops)) {
              _tmpAffectedCrops = null;
            } else {
              _tmpAffectedCrops = _cursor.getString(_cursorIndexOfAffectedCrops);
            }
            final String _tmpSymptoms;
            if (_cursor.isNull(_cursorIndexOfSymptoms)) {
              _tmpSymptoms = null;
            } else {
              _tmpSymptoms = _cursor.getString(_cursorIndexOfSymptoms);
            }
            final String _tmpCause;
            if (_cursor.isNull(_cursorIndexOfCause)) {
              _tmpCause = null;
            } else {
              _tmpCause = _cursor.getString(_cursorIndexOfCause);
            }
            final String _tmpNaturalTreatment;
            if (_cursor.isNull(_cursorIndexOfNaturalTreatment)) {
              _tmpNaturalTreatment = null;
            } else {
              _tmpNaturalTreatment = _cursor.getString(_cursorIndexOfNaturalTreatment);
            }
            final String _tmpChemicalTreatment;
            if (_cursor.isNull(_cursorIndexOfChemicalTreatment)) {
              _tmpChemicalTreatment = null;
            } else {
              _tmpChemicalTreatment = _cursor.getString(_cursorIndexOfChemicalTreatment);
            }
            final String _tmpPrevention;
            if (_cursor.isNull(_cursorIndexOfPrevention)) {
              _tmpPrevention = null;
            } else {
              _tmpPrevention = _cursor.getString(_cursorIndexOfPrevention);
            }
            final int _tmpImageResId;
            _tmpImageResId = _cursor.getInt(_cursorIndexOfImageResId);
            _result = new DiseaseKnowledge(_tmpDiseaseId,_tmpName,_tmpAffectedCrops,_tmpSymptoms,_tmpCause,_tmpNaturalTreatment,_tmpChemicalTreatment,_tmpPrevention,_tmpImageResId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DiseaseReport>> getAllReports() {
    final String _sql = "SELECT * FROM disease_reports ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"disease_reports"}, new Callable<List<DiseaseReport>>() {
      @Override
      public List<DiseaseReport> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDiseaseName = CursorUtil.getColumnIndexOrThrow(_cursor, "diseaseName");
          final int _cursorIndexOfCropType = CursorUtil.getColumnIndexOrThrow(_cursor, "cropType");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "isSynced");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
          final List<DiseaseReport> _result = new ArrayList<DiseaseReport>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DiseaseReport _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDiseaseName;
            if (_cursor.isNull(_cursorIndexOfDiseaseName)) {
              _tmpDiseaseName = null;
            } else {
              _tmpDiseaseName = _cursor.getString(_cursorIndexOfDiseaseName);
            }
            final String _tmpCropType;
            if (_cursor.isNull(_cursorIndexOfCropType)) {
              _tmpCropType = null;
            } else {
              _tmpCropType = _cursor.getString(_cursorIndexOfCropType);
            }
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp != 0;
            final float _tmpRating;
            _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
            final String _tmpComment;
            if (_cursor.isNull(_cursorIndexOfComment)) {
              _tmpComment = null;
            } else {
              _tmpComment = _cursor.getString(_cursorIndexOfComment);
            }
            _item = new DiseaseReport(_tmpId,_tmpDiseaseName,_tmpCropType,_tmpLatitude,_tmpLongitude,_tmpTimestamp,_tmpIsSynced,_tmpRating,_tmpComment);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getUnsyncedReports(final Continuation<? super List<DiseaseReport>> $completion) {
    final String _sql = "SELECT * FROM disease_reports WHERE isSynced = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DiseaseReport>>() {
      @Override
      public List<DiseaseReport> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDiseaseName = CursorUtil.getColumnIndexOrThrow(_cursor, "diseaseName");
          final int _cursorIndexOfCropType = CursorUtil.getColumnIndexOrThrow(_cursor, "cropType");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "isSynced");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
          final List<DiseaseReport> _result = new ArrayList<DiseaseReport>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DiseaseReport _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDiseaseName;
            if (_cursor.isNull(_cursorIndexOfDiseaseName)) {
              _tmpDiseaseName = null;
            } else {
              _tmpDiseaseName = _cursor.getString(_cursorIndexOfDiseaseName);
            }
            final String _tmpCropType;
            if (_cursor.isNull(_cursorIndexOfCropType)) {
              _tmpCropType = null;
            } else {
              _tmpCropType = _cursor.getString(_cursorIndexOfCropType);
            }
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp != 0;
            final float _tmpRating;
            _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
            final String _tmpComment;
            if (_cursor.isNull(_cursorIndexOfComment)) {
              _tmpComment = null;
            } else {
              _tmpComment = _cursor.getString(_cursorIndexOfComment);
            }
            _item = new DiseaseReport(_tmpId,_tmpDiseaseName,_tmpCropType,_tmpLatitude,_tmpLongitude,_tmpTimestamp,_tmpIsSynced,_tmpRating,_tmpComment);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
