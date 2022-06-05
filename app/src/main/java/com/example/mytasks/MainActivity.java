package com.example.mytasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mytasks.Activity.InsertNote;
import com.example.mytasks.Adapter.NotesAdapter;
import com.example.mytasks.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesbtn;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    NotesAdapter adapter;

    TextView nofilter,high_low,low_high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        newNotesbtn = findViewById( R.id.newNotesbtn );
        notesRecycler = findViewById( R.id.notesRecycler );

        nofilter= findViewById( R.id.no_filter );
        high_low = findViewById( R.id.high_low );
        low_high = findViewById( R.id.low_high );

        nofilter.setBackgroundResource( R.drawable.filter_selected_shape );

        nofilter.setOnClickListener( v -> {
            high_low.setBackgroundResource( R.drawable.filter_shape);
            low_high.setBackgroundResource( R.drawable.filter_shape );
            nofilter.setBackgroundResource( R.drawable.filter_selected_shape );
        } );
        high_low.setOnClickListener( v -> {
            high_low.setBackgroundResource( R.drawable.filter_selected_shape  );
            low_high.setBackgroundResource( R.drawable.filter_shape );
            nofilter.setBackgroundResource( R.drawable.filter_shape );
        } );
        low_high.setOnClickListener( v -> {
            high_low.setBackgroundResource( R.drawable.filter_shape );
            low_high.setBackgroundResource( R.drawable.filter_selected_shape  );
            nofilter.setBackgroundResource( R.drawable.filter_shape );
        } );

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