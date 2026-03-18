package com.agrriguard.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile DiseaseDao _diseaseDao;

  private volatile UserDao _userDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `disease_reports` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `diseaseName` TEXT NOT NULL, `cropType` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `timestamp` INTEGER NOT NULL, `isSynced` INTEGER NOT NULL, `rating` REAL NOT NULL, `comment` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `diseases_knowledge` (`diseaseId` TEXT NOT NULL, `name` TEXT NOT NULL, `affectedCrops` TEXT NOT NULL, `symptoms` TEXT NOT NULL, `cause` TEXT NOT NULL, `naturalTreatment` TEXT NOT NULL, `chemicalTreatment` TEXT NOT NULL, `prevention` TEXT NOT NULL, `imageResId` INTEGER NOT NULL, PRIMARY KEY(`diseaseId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`email` TEXT NOT NULL, `name` TEXT NOT NULL, `password` TEXT NOT NULL, `cropType` TEXT NOT NULL, PRIMARY KEY(`email`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7e268139d6f770da5c12f5d66f49ae87')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `disease_reports`");
        _db.execSQL("DROP TABLE IF EXISTS `diseases_knowledge`");
        _db.execSQL("DROP TABLE IF EXISTS `users`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDiseaseReports = new HashMap<String, TableInfo.Column>(9);
        _columnsDiseaseReports.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseaseReports.put("diseaseName", new TableInfo.Column("diseaseName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseaseReports.put("cropType", new TableInfo.Column("cropType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseaseReports.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseaseReports.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseaseReports.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseaseReports.put("isSynced", new TableInfo.Column("isSynced", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseaseReports.put("rating", new TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseaseReports.put("comment", new TableInfo.Column("comment", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDiseaseReports = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDiseaseReports = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDiseaseReports = new TableInfo("disease_reports", _columnsDiseaseReports, _foreignKeysDiseaseReports, _indicesDiseaseReports);
        final TableInfo _existingDiseaseReports = TableInfo.read(_db, "disease_reports");
        if (! _infoDiseaseReports.equals(_existingDiseaseReports)) {
          return new RoomOpenHelper.ValidationResult(false, "disease_reports(com.agrriguard.data.model.DiseaseReport).\n"
                  + " Expected:\n" + _infoDiseaseReports + "\n"
                  + " Found:\n" + _existingDiseaseReports);
        }
        final HashMap<String, TableInfo.Column> _columnsDiseasesKnowledge = new HashMap<String, TableInfo.Column>(9);
        _columnsDiseasesKnowledge.put("diseaseId", new TableInfo.Column("diseaseId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseasesKnowledge.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseasesKnowledge.put("affectedCrops", new TableInfo.Column("affectedCrops", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseasesKnowledge.put("symptoms", new TableInfo.Column("symptoms", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseasesKnowledge.put("cause", new TableInfo.Column("cause", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseasesKnowledge.put("naturalTreatment", new TableInfo.Column("naturalTreatment", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseasesKnowledge.put("chemicalTreatment", new TableInfo.Column("chemicalTreatment", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseasesKnowledge.put("prevention", new TableInfo.Column("prevention", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDiseasesKnowledge.put("imageResId", new TableInfo.Column("imageResId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDiseasesKnowledge = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDiseasesKnowledge = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDiseasesKnowledge = new TableInfo("diseases_knowledge", _columnsDiseasesKnowledge, _foreignKeysDiseasesKnowledge, _indicesDiseasesKnowledge);
        final TableInfo _existingDiseasesKnowledge = TableInfo.read(_db, "diseases_knowledge");
        if (! _infoDiseasesKnowledge.equals(_existingDiseasesKnowledge)) {
          return new RoomOpenHelper.ValidationResult(false, "diseases_knowledge(com.agrriguard.data.model.DiseaseKnowledge).\n"
                  + " Expected:\n" + _infoDiseasesKnowledge + "\n"
                  + " Found:\n" + _existingDiseasesKnowledge);
        }
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(4);
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("password", new TableInfo.Column("password", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("cropType", new TableInfo.Column("cropType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(_db, "users");
        if (! _infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.agrriguard.data.model.User).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "7e268139d6f770da5c12f5d66f49ae87", "a9deb2d81e87bce4dc6481dfe038f367");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "disease_reports","diseases_knowledge","users");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `disease_reports`");
      _db.execSQL("DELETE FROM `diseases_knowledge`");
      _db.execSQL("DELETE FROM `users`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(DiseaseDao.class, DiseaseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public DiseaseDao diseaseDao() {
    if (_diseaseDao != null) {
      return _diseaseDao;
    } else {
      synchronized(this) {
        if(_diseaseDao == null) {
          _diseaseDao = new DiseaseDao_Impl(this);
        }
        return _diseaseDao;
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }
}
