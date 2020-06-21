package com.example.memo;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true) //automatically generate ID as primary key
    private int id;
    @ColumnInfo(name="content")
    private  String noteContent;
    @ColumnInfo(name="date")
    private  long noteDate;

    public Note(String noteContent, long noteDate){
        this.noteContent = noteContent;
        this.noteDate = noteDate;
    }

    public String getNoteContent(){
        return noteContent;
    }

    public void setNoteContent(String noteContent){
        this.noteContent = noteContent;
    }

    public long getNoteDate(){
        return noteDate;
    }

    public void setNoteDate(long noteDate){
        this.noteDate=noteDate;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}
