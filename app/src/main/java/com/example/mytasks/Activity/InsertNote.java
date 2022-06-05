package com.example.mytasks.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mytasks.Model.Notes;
import com.example.mytasks.R;
import com.example.mytasks.ViewModel.NotesViewModel;
import com.example.mytasks.databinding.ActivityInsertNoteBinding;

import java.text.DateFormat;
import java.util.Date;

import static androidx.lifecycle.ViewModelProviders.*;

public class InsertNote extends AppCompatActivity {

    ActivityInsertNoteBinding binding;
    String title,subtitle,notes;
    NotesViewModel notesViewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding=ActivityInsertNoteBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot());

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        binding.greenPriority.setOnClickListener( v -> {
            binding.greenPriority.setImageResource( R.drawable.ic_baseline_done_24 );
            binding.yellowPriority.setImageResource( 0 );
            binding.redPriority.setImageResource( 0 );

             priority ="1";
        } );

        binding.yellowPriority.setOnClickListener( v -> {

            binding.yellowPriority.setImageResource( R.drawable.ic_baseline_done_24 );
            binding.greenPriority.setImageResource( 0 );
            binding.redPriority.setImageResource( 0 );


            priority = "2";
        } );

        binding.redPriority.setOnClickListener( v -> {

            binding.redPriority.setImageResource( R.drawable.ic_baseline_done_24 );
            binding.yellowPriority.setImageResource( 0 );
            binding.greenPriority.setImageResource( 0 );

            priority ="3";
        } );



        binding.doneNotesbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = binding.title.getText().toString();
                subtitle = binding.subtitle.getText().toString();
                notes = binding.notesData.getText().toString();
                //priority = priority;
                CreateNotes(title,subtitle,notes);

            }
        } );
    }

    private void CreateNotes(String title, String subtitle, String notes) {
        
        Date date = new Date();
        CharSequence sequence = android.text.format.DateFormat.format("MMMM d,yyyy",date.getTime());


        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notes = notes;
        notes1.notesPriority = priority;
        notes1.date = sequence.toString();
        notesViewModel.insertNotes( notes1 );

        Toast.makeText(this,"Notes created successfully",Toast.LENGTH_SHORT).show();

        finish();

    }
}