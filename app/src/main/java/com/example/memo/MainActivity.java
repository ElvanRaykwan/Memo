package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editText1;
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

        adapter = new RecyclerAdapter(this,notes);
        recyclerView.setAdapter(adapter);
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



    public void save(String fileName) {
        try {
            OutputStreamWriter saveData =
                    new OutputStreamWriter(openFileOutput(fileName, 0));
            saveData.write(editText1.getText().toString());
            saveData.close();
            Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show();
        }
        catch (Throwable t) {
            Toast.makeText(this, "Could not save file, Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkFile(String fileName){
        File file = getBaseContext().getFileStreamPath(fileName);
        return file.exists();
    }

    public String load(String fileName) {
        String fileContent = "";
        if (checkFile(fileName)) {
            try {
                InputStream loadData = openFileInput(fileName);
                if ( loadData != null) {
                    InputStreamReader readData = new InputStreamReader( loadData );
                    BufferedReader buffer = new BufferedReader(readData);
                    String tempStr;
                    StringBuilder string = new StringBuilder();
                    while ((tempStr = buffer.readLine()) != null) {
                        string.append(tempStr + "\n");
                    }
                    loadData .close();
                    fileContent = string.toString();
                }
            }
            catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
                Toast.makeText(this, "Could not load file, Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return fileContent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    //    @Override
//    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
//   }
}


