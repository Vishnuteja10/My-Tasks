package com.example.mytasks.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mytasks.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query( "SELECT * FROM Notes_Database" )
    LiveData<List<Notes>> getAllNotes();
    // List<Notes> getallNotes();

    @Query( "SELECT * FROM Notes_Database ORDER BY notes_priority DESC" )
    LiveData<List<Notes>> hightolow();

    @Query( "SELECT * FROM Notes_Database ORDER BY notes_priority ASC" )
    LiveData<List<Notes>> lowtohigh();

    @Insert
    void insertNotes(Notes... notes);

    @Query( "DELETE FROM Notes_Database WHERE id=:id" ) // here id =:id means id points to id from delete notes (int id)
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);
}
