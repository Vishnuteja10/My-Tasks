package com.example.mytasks;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query( "SELECT * FROM Notes_Database" )
     List<Notes> getallNotes();

    @Insert
    void insertNotes(Notes... notes);

    @Query( "DELETE FROM Notes_Database WHERE id=:id" ) // here id =:id means id points to id from delete notes (int id)
    void deleteNotes(int id);

    @Update
    void insertNotes(Notes notes);
}
