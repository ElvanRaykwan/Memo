package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Date;

public class NoteEditorActivity extends AppCompatActivity {
    private EditText inputContent; //to store content that the user entered
    private DatabaseAccess dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        inputContent=findViewById(R.id.editable_content);
        dao = MemoDatabase.getInstance(this).notesDao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_edit_note, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.save_action)
            whenSave();
        return  super.onOptionsItemSelected(item);
    }

        private void whenSave(){
        String content = inputContent.getText().toString();
        if (!content.isEmpty()){ //if content is not empty then
            long date = new Date().getTime(); //get current date when save
            Note note = new Note(content, date); //append the new note to screen
            dao.insertNote(note); //insert note to database

            finish(); //end this activity and return to main
        }

        }
}
