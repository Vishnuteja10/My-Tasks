package my.project.mytasks.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import my.project.mytasks.Activity.UpdateNote;
import my.project.mytasks.MainActivity;
import my.project.mytasks.Model.Notes;
import my.project.mytasks.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity ;
    List<Notes> notes;
    List<Notes> allNotesitem;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        allNotesitem = new ArrayList<>(notes);
    }

    public void searchNotes(List<Notes> filtersName){
        this.notes = filtersName;
        notifyDataSetChanged();
    }

    @NonNull

    @Override
    public notesViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        return new notesViewHolder( LayoutInflater.from(mainActivity ).inflate( R.layout.item_notes,parent,false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull  NotesAdapter.notesViewHolder holder, int position) {

        Notes note = notes.get(position);

        switch (note.notesPriority) {
            case "1":
                holder.notesPriority.setBackgroundResource( R.drawable.green_shape );
                break;
            case "2":
                holder.notesPriority.setBackgroundResource( R.drawable.yellow_shape );
                break;
            case "3":
                holder.notesPriority.setBackgroundResource( R.drawable.red_shape );
                break;
        }


        holder.title.setText( note.notesTitle );
        holder.notes.setText( note.notes );

        holder.notesDate.setText( note.date ); // might be error

        holder.itemView.setOnClickListener( v -> {

            Intent intent = new Intent(mainActivity, UpdateNote.class );
            intent.putExtra( "id",note.id );
            intent.putExtra( "title",note.notesTitle );
            intent.putExtra( "notes",note.notes );
            intent.putExtra( "priority",note.notesPriority );
            mainActivity.startActivity( intent );
        } );

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

   static class notesViewHolder extends RecyclerView.ViewHolder{

        TextView title,notesDate,notes;
        View notesPriority;

        public notesViewHolder(@NonNull  View itemView) {
            super( itemView );

            title = itemView.findViewById( R.id.notesTitle );
            notes = itemView.findViewById( R.id.notes );
            notesDate = itemView.findViewById( R.id.notesDate );
            notesPriority = itemView.findViewById( R.id.notesPriority );
        }
    }

}
