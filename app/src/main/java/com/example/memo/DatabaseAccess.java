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

/*
An interface to do query actions such as insertm update, delete, and select from database
The Database stores Notes as objects (see Notes.java).
*/

@Dao
public interface DatabaseAccess {
    @Insert (onConflict=OnConflictStrategy.REPLACE) //If note exist, replace
    void insertNoteData(Note note); //insertNoteData is used to insert data into the database
    @Update
    void updateNoteData(Note note); //updateNoteData is used to update existing data in the database
    @Delete
    void deleteNoteData(Note note); //deleteNoteData is used to delete data from the database

    @Query("SELECT * FROM notes") //a query to select all notes from database, used in loadNotes()
    List<Note> getAllNotes();

    @Query("SELECT * FROM notes WHERE id = :noteId") //a query to select a specific note from database
    Note selectNoteById(int noteId);

    @Query("DELETE FROM notes WHERE id = :noteId") //a query to delete a specific note from database
    void deleteNoteById (int noteId);
}

