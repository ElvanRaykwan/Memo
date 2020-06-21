package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteEventListener {

    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Note> notes;
    private DatabaseAccess dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set up the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        //FAB
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNewNote();
                Snackbar.make(view, "Added a new note", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        dao = MemoDatabase.getInstance(this).notesDao();
        loadNotes();


    }

    private void loadNotes() {
        this.notes = new ArrayList<Note>();
        List<Note> tempList = dao.getNotes();
        this.notes.addAll(tempList);

        this.adapter = new RecyclerAdapter(this,notes);
        this.adapter.setListener(this);
        this.recyclerView.setAdapter(adapter);
    }

    private void onAddNewNote() {
        startActivity(new Intent(this, NoteEditorActivity.class)); //go to note editor activity
        if (notes!=null){
            notes.add(new Note("test",new Date().getTime()));
        }
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    @Override
    public void onNoteClick(Note note){
        Toast.makeText(this, + note.getId()+" Selected", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNoteLongClick(Note note){}

}


