package com.example.memo;

//an interface for note events, enable main activity to have these events
public interface NoteEventListener {

    void onNoteClick(Note note); //for single quick clicks, do ...

    void onNoteLongClick(Note note); //for long mouse clicks (hold), do ...

}
