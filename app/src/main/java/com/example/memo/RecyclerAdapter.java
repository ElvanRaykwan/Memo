package com.example.memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*
a class that handles recycler view, a new version of ListView. Format the notes into presentable
sections
*/

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NoteHolder> {

    private ArrayList<Note> notes;
    private Context context;
    private NoteEventListener noteEvent;

    // data is passed into the constructor
    RecyclerAdapter(Context context, ArrayList<Note> data) {
        this.context = context;
        this.notes = data;
    }

    // inflates the layout for the row from xml
    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //uses memo_layout.xml as a template, each row is structured by this xml
        View view = LayoutInflater.from(context).inflate(R.layout.memo_layout,parent,false);
        return new NoteHolder(view);
    }

    // binds the data to the TextView as well as Date in each row
    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        final Note note = getNote(position); //get the note object
        if(note != null){
            holder.noteContent.setText(note.getNoteContent());
            holder.noteDate.setText(MemoRandomUtilities.dateFormatting(note.getNoteDate()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    noteEvent.onNoteClick(note);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    noteEvent.onNoteLongClick(note);
                    return false;
                }
            });
        }

    }

    // total number of rows. returns row size
    @Override
    public int getItemCount() {
        return notes.size();
    }




    // stores and recycles views as they are scrolled off screen
    public class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView noteContent;
        TextView noteDate;

        NoteHolder(View itemView) {
            super(itemView);
            noteContent=itemView.findViewById(R.id.memo_content);
            noteDate=itemView.findViewById(R.id.memo_date);
        }

        @Override
        public void onClick(View view) {
        }
    }

    //getter and setter
    private Note getNote(int pos){
        return notes.get(pos);
    }

    public void setListener(NoteEventListener event){
        this.noteEvent = event;
    }
}