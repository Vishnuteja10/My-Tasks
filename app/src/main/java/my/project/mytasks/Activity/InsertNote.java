package my.project.mytasks.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import my.project.mytasks.R;
import my.project.mytasks.databinding.ActivityInsertNoteBinding;

import java.util.Date;

import my.project.mytasks.Model.Notes;
import my.project.mytasks.ViewModel.NotesViewModel;

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
                notes = binding.notesData.getText().toString();
                //priority = priority;
                CreateNotes(title,notes);

            }
        } );
    }

    private void CreateNotes(String title,  String notes) {
        
        Date date = new Date();
        CharSequence sequence = android.text.format.DateFormat.format("MMMM d,yyyy",date.getTime());


        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notes = notes;
        notes1.notesPriority = priority;
        notes1.date = sequence.toString();
        notesViewModel.insertNotes( notes1 );

        Toast.makeText(this,"Notes created successfully",Toast.LENGTH_SHORT).show();

        finish();

    }
}