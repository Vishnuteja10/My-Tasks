package com.example.mytasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mytasks.Activity.InsertNote;
import com.example.mytasks.Adapter.NotesAdapter;
import com.example.mytasks.Model.Notes;
import com.example.mytasks.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesbtn;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    NotesAdapter adapter;

    TextView nofilter,high_low,low_high;
    List<Notes> filternotesallList;

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
            loadData(0);
            high_low.setBackgroundResource( R.drawable.filter_shape);
            low_high.setBackgroundResource( R.drawable.filter_shape );
            nofilter.setBackgroundResource( R.drawable.filter_selected_shape );
        } );
        high_low.setOnClickListener( v -> {
            loadData(1);
            high_low.setBackgroundResource( R.drawable.filter_selected_shape  );
            low_high.setBackgroundResource( R.drawable.filter_shape );
            nofilter.setBackgroundResource( R.drawable.filter_shape );
        } );
        low_high.setOnClickListener( v -> {
            loadData(2);
            high_low.setBackgroundResource( R.drawable.filter_shape );
            low_high.setBackgroundResource( R.drawable.filter_selected_shape  );
            nofilter.setBackgroundResource( R.drawable.filter_shape );
        } );

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        newNotesbtn.setOnClickListener( v -> {
            startActivity( new Intent(MainActivity.this, InsertNote.class) );
        } );

        notesViewModel.getAllNotes.observe( this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                filternotesallList = notes;
            }
        } );

    }

    private void loadData(int i) {

        if(i == 0){
            notesViewModel.getAllNotes.observe( this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesallList = notes;
                }
            } );
        }else if(i == 1){
            notesViewModel.hightolow.observe( this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesallList = notes;
                }
            } );
        }else{
            notesViewModel.lowtohigh.observe( this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesallList = notes;
                }
            } );
        }

    }

    public void setAdapter(List<Notes> notes){
        notesRecycler.setLayoutManager( new LinearLayoutManager( this ) );
        adapter = new NotesAdapter(MainActivity.this,notes);
        notesRecycler.setAdapter( adapter );
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate( R.menu.search_note,menu );

        MenuItem menuItem = menu.findItem( R.id.search_bar );
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint( "Search your notes here.." );
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesFilter(newText);
                return false;
            }
        } );

        return super.onCreateOptionsMenu( menu );
    }

    private void notesFilter(String newtext){
        ArrayList<Notes> filternames = new ArrayList<>();

        for(Notes notes : this.filternotesallList){

            if(notes.notesTitle.contains( newtext ) || notes.notes.contains( newtext )){
                filternames.add(notes);
            }
        }
        this.adapter.searchNotes( filternames );
        
    }
}