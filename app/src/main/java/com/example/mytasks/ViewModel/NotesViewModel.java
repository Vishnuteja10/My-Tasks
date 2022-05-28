package com.example.mytasks.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mytasks.Model.Notes;
import com.example.mytasks.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;

    public NotesViewModel(@NonNull  Application application) {
        super( application );
         repository = new NotesRepository( application );
         getAllNotes = repository.getallNotes;


    }

    void insertNotes(Notes notes){
        repository.insertNotes( notes );
    }

    void deleteNote(int id){
        repository.deleteNotes( id );
    }

    void updateNote(Notes notes){
        repository.updateNotes( notes );
    }

}
