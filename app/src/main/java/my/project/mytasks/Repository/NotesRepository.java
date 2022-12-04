package my.project.mytasks.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import my.project.mytasks.Dao.NotesDao;
import my.project.mytasks.Database.NotesDatabase;
import my.project.mytasks.Model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getallNotes;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;



    public NotesRepository(Application application){
        NotesDatabase database = NotesDatabase.getDatabaseInstance( application );
        notesDao = database.notesDao(); // we now got notesDao from database
        getallNotes = notesDao.getAllNotes(); // we got all notes from notesDao from database
        hightolow = notesDao.hightolow();
        lowtohigh = notesDao.lowtohigh();
    }

   public void insertNotes(Notes notes){
        notesDao.insertNotes( notes );
    }
    public void deleteNotes(int id){
        notesDao.deleteNotes( id );
    }
   public void updateNotes(Notes notes){
        notesDao.updateNotes( notes );
    }
}
