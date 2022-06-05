package com.example.mytasks.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytasks.MainActivity;
import com.example.mytasks.Model.Notes;
import com.example.mytasks.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity ;
    List<Notes> notes;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
    }

    @NonNull

    @Override
    public notesViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        return new notesViewHolder( LayoutInflater.from(mainActivity ).inflate( R.layout.item_notes,parent,false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull  NotesAdapter.notesViewHolder holder, int position) {

        Notes note = notes.get(position);

       if( note.notesPriority.equals("1")){
            holder.notesPriority.setBackgroundResource( R.drawable.green_shape );
        }else if( note.notesPriority.equals("2")){
            holder.notesPriority.setBackgroundResource( R.drawable.yellow_shape );
        }else if( note.notesPriority.equals("3")){
            holder.notesPriority.setBackgroundResource( R.drawable.red_shape );
        }


        holder.title.setText( note.notesTitle );
        holder.subtitle.setText( note.notesSubtitle );
        holder.notesDate.setText( note.date ); // might be error

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

   static class notesViewHolder extends RecyclerView.ViewHolder{

        TextView title,subtitle,notesDate;
        View notesPriority;

        public notesViewHolder(@NonNull  View itemView) {
            super( itemView );

            title = itemView.findViewById( R.id.notesTitle );
            subtitle = itemView.findViewById( R.id.notesSubtitle );
            notesDate = itemView.findViewById( R.id.notesDate );
            notesPriority = itemView.findViewById( R.id.notesPriority );
        }
    }

}
