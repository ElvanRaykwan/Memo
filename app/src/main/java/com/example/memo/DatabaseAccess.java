package com.example.memo;

import androidx.annotation.BinderThread;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DatabaseAccess {
    @Insert (onConflict=OnConflictStrategy.REPLACE) //If note exist, replace
    void insertNote(Note note);
    @Update
    void updateNote(Note note);
    @Delete
    void deleteNote(Note note);

    @Query("SELECT * FROM notes")
    List<Note> getNotes();

    @Query("SELECT * FROM notes WHERE id = :noteId")
    Note getNoteByID (int noteId);

    @Query("DELETE FROM notes WHERE id = :noteId")
    void deleteNoteById (int noteId);
}

