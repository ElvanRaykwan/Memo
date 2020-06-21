package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Date;

//an activity for the sole purpose of editing & saving note's content
public class NoteEditorActivity extends AppCompatActivity {
    private EditText inputContent; //store what user wrote
    private DatabaseAccess dao;
    //a temporary note, to help do action before determining ID of note when doing update
    private Note tempNote;
    //an extra String to send in putExtra()
    public  static final String NOTE_ID_KEY ="note_id";

    @Override //when activity is created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        inputContent=findViewById(R.id.editable_content);
        dao = MemoDatabase.getInstance(this).notesDao(); //links to database
        if(getIntent().getExtras()!=null){ //activated when an already existing note is clicked
            int id=getIntent().getExtras().getInt(NOTE_ID_KEY, 0); //get id from main
            tempNote = dao.selectNoteById(id); //uses query to get the right note by id
            inputContent.setText(tempNote.getNoteContent()); //fill up content from query
        }
    }

    //creates menu (have a save button on it)
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_edit_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //what happens when menu item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.save_action) //only have save, so if button == save, then do whenSave()
            whenSave();
        return  super.onOptionsItemSelected(item);
    }

    //The save function of the menu
    private void whenSave(){
        String content = inputContent.getText().toString(); //get the latest user content
        if (!content.isEmpty()) { //if content is not empty then
            long date = new Date().getTime(); //get current date when save.

            //if note exist in database update, else new
            if (tempNote == null) {  //meaning doesn't exist in database
                tempNote = new Note(content, date);
                dao.insertNoteData(tempNote); //insert new note to database
            } else { //else if not null because of passed id from main (line 27-30) of this class
                tempNote.setNoteContent(content);
                tempNote.setNoteDate(date);
                dao.updateNoteData(tempNote);//update note to database
            }
            finish(); //end this activity and return to main
        }
    }
}
