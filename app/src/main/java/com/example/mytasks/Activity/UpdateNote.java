package com.example.mytasks.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mytasks.Model.Notes;
import com.example.mytasks.R;
import com.example.mytasks.ViewModel.NotesViewModel;
import com.example.mytasks.databinding.ActivityUpdateNoteBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNote extends AppCompatActivity {

    ActivityUpdateNoteBinding binding;
    String priority = "1";
    String sTitle, sNotes,sPriority;
    int iId;
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityUpdateNoteBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot());

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        iId = getIntent().getIntExtra( "id",0 );
        sTitle = getIntent().getStringExtra( "title" );
        sNotes = getIntent().getStringExtra( "notes" );
        sPriority = getIntent().getStringExtra( "priority" );

        binding.upTitle.setText( sTitle );
        binding.upNotesData.setText( sNotes );

     /*   if(sPriority.equals("1")){
            binding.greenPriority.setImageResource( R.drawable.ic_baseline_done_24 );
        }else if(sPriority.equals("2")){
            binding.yellowPriority.setImageResource( R.drawable.ic_baseline_done_24 );
        }else if(sPriority.equals("3")){
            binding.redPriority.setImageResource( R.drawable.ic_baseline_done_24 );
        } */

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

        binding.updateNotesbtn.setOnClickListener( v -> {

            String title = binding.upTitle.getText().toString();
            String notes = binding.upNotesData.getText().toString();
            //priority = priority;
            CreateNotes(title,notes);

        } );

    }

    private void CreateNotes(String title,  String notes) {

        Date date = new Date();
        CharSequence sequence = android.text.format.DateFormat.format("MMMM d,yyyy",date.getTime());

        Notes updateNotes = new Notes();

        notesViewModel.updateNote( updateNotes );
        updateNotes.id = iId;
        updateNotes.notesTitle = title;
        updateNotes.notes = notes;
        updateNotes.notesPriority = priority;
        updateNotes.date = sequence.toString();
        notesViewModel.updateNote( updateNotes );



        Toast.makeText(this,"Notes updated successfully",Toast.LENGTH_SHORT).show();
        finish();

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate( R.menu.delete_menu,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete){
            BottomSheetDialog sheetDialog = new BottomSheetDialog( UpdateNote.this,
                    R.style.BottomSheetStyle );

            View view = LayoutInflater.from( UpdateNote.this ).
                    inflate( R.layout.delete_sheet, (LinearLayout)findViewById( R.id.bottomSheet ));

            sheetDialog.setContentView( view );



            TextView yes , no;
            yes = view.findViewById( R.id.delete_yes );
            no = view.findViewById( R.id.delete_no );

            yes.setOnClickListener( v -> {
               notesViewModel.deleteNote( iId );
               finish();
            } );

            no.setOnClickListener( v -> {
                sheetDialog.dismiss();
            } );

            sheetDialog.show();

        }
        return true;
    }
}