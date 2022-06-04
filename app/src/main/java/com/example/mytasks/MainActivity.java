package com.example.mytasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mytasks.Activity.InsertNote;
import com.example.mytasks.Adapter.NotesAdapter;
import com.example.mytasks.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesbtn;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    NotesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        newNotesbtn = findViewById( R.id.newNotesbtn );
        notesRecycler = findViewById( R.id.notesRecycler );

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        newNotesbtn.setOnClickListener( v -> {
            startActivity( new Intent(MainActivity.this, InsertNote.class) );
        } );

        notesViewModel.getAllNotes.observe( this,notes -> {
               notesRecycler.setLayoutManager( new LinearLayoutManager( this ) );
               adapter = new NotesAdapter(MainActivity.this,notes);
               notesRecycler.setAdapter( adapter );
        } );

    }
}