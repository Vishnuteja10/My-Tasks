package com.example.mytasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mytasks.Activity.InsertNote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        newNotesbtn = findViewById( R.id.newNotesbtn );

        newNotesbtn.setOnClickListener( v -> {
            startActivity( new Intent(MainActivity.this, InsertNote.class) );
        } );

    }
}