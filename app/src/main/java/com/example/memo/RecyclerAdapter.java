package com.example.memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NoteHolder> {

    private ArrayList<Note> notes;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    RecyclerAdapter(Context context, ArrayList<Note> data) {
        this.context = context;
        this.notes = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.memo_layout,parent,false);
        return new NoteHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        Note note = getNote(position);
        if(note != null){
        holder.noteContent.setText(note.getContent());
        holder.noteDate.setText(MemoRandomUtilities.DateFormatting(note.getDate()));}
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return notes.size();
    }

    private Note getNote(int pos){
        return notes.get(pos);
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
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }



    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}