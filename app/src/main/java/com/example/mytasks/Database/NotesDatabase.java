package com.example.mytasks.Database;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mytasks.Model.Notes;
import com.example.mytasks.Dao.NotesDao;

@SuppressLint("RestrictedApi")
@Database( entities = {Notes.class},version = 2,exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;

    static Migration migration = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {




            database.execSQL(" CREATE TABLE t1 (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,notes_title TEXT,notes_date TEXT,notes TEXT,notes_priority TEXT) ");
            database.execSQL( "INSERT INTO t1 (id,notes_title,notes_date,notes,notes_priority) SELECT id,notes_title,notes_date,notes,notes_priority FROM 'Notes_Database'" );
            database.execSQL( "DROP TABLE 'Notes_Database'" );
            database.execSQL( "ALTER TABLE t1 RENAME TO 'Notes_Database'" );
        }
    };

    public static NotesDatabase getDatabaseInstance(Context context){
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class,"Notes_Database").allowMainThreadQueries().addMigrations( migration ).build();
        }
        return INSTANCE;
    }

}
