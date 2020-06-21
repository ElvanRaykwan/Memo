package com.example.memo;

public interface NoteEventListener {

    void onNoteClick(Note note); //for single quick clicks, do ...


    void onNoteLongClick(Note note); //for long mouse clicks (hold), do ...


}
