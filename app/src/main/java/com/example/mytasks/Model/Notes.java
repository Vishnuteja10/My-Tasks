package com.example.mytasks.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Database")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes_title")
    String notesTitle;

    @ColumnInfo(name = "note_subtitle")
    String notesSubtitle;

    @ColumnInfo(name = "notes_date")
    String date;

    @ColumnInfo(name = "notes")
    String notes;

    @ColumnInfo(name = "notes_priority")
    String notesPriority;

}
