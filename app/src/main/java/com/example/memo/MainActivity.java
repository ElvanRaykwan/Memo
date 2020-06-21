package com.example.memo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.example.memo.NoteEditorActivity.NOTE_ID_KEY;

//the main activity of the application

public class MainActivity extends AppCompatActivity implements NoteEventListener {

    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Note> notes;
    private DatabaseAccess dao; //stands for data access object, a common term used

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set up the RecyclerView for a nice formatted notes
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adds a line between objects in recyclerview
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));

        //FAB for adding notes
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewNote(); //a method to add new note
            }
        });
        dao = MemoDatabase.getInstance(this).notesDao(); //link the database
        loadAllNotes(); //a method to load all notes
    } //end of onCreate

    //if activity was resumed (came back from NoteEditorActivity), then refresh  all notes (loadNotes)
    @Override
    protected void onResume() {
        super.onResume();
        loadAllNotes();
    }

    /*
    load all notes from database using getAllNote from DatabaseAccess interface and set up the
    recyclerview. Also acts as a refresh page method in MainActivity.
    */
    private void loadAllNotes() {
        this.notes = new ArrayList<Note>();
        List<Note> tempList = dao.getAllNotes();
        this.notes.addAll(tempList);

        this.adapter = new RecyclerAdapter(this,notes);
        this.adapter.setListener(this);
        this.recyclerView.setAdapter(adapter);
    }

    //add a new note by sending user to NoteEditorActivity where they can write and save notes
    private void addNewNote() {
        startActivity(new Intent(this, NoteEditorActivity.class)); //go to note editor activity
    }


    //when a note is clicked, go to NoteEditorActivity and also sent in the id of the clicked note
    @Override
    public void onNoteClick(Note note){
        //go to note editor
        Intent editPage = new Intent(this, NoteEditorActivity.class);
        editPage.putExtra(NOTE_ID_KEY,note.getId()); //give id to NoteEditor
        startActivity(editPage);
    }

    //when a note is long clicked (hold click), a delete note pop up will appear (uses AlertDialog)
    @Override
    public void onNoteLongClick(final Note note){
        new AlertDialog.Builder(this).setTitle("Do you wish to delete the Note?")
                //yes option for the confirmation (positive), to delete the note
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.deleteNoteData(note); //delete selected notes
                        loadAllNotes(); //refresh main page view after deleting
                    }
                })
                //no option for confirmation (negative), to cancel the delete, exit AlertDialog
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); //closes Alert Dialog
                    }
                }).create().show();
    }
}


