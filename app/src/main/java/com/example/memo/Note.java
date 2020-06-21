package com.example.memo;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//a class that represents a Note as an object
//@Entity makes notes an entity in database
@Entity(tableName = "notes")
public class Note {
    //the attributes of a note
    @PrimaryKey(autoGenerate = true) //automatically generate ID as primary key
    private int id;
    @ColumnInfo(name="content") //assign column name
    private  String noteContent; //the content of the note (what user wrote)
    @ColumnInfo(name="date")
    private  long noteDate; //the date of the note's creation

    //default constructor
    public Note(){

    }

    //constructor to assign note attributes
    public Note(String noteContent, long noteDate){
        this.noteContent = noteContent;
        this.noteDate = noteDate;
    }

    //getters and setters

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

}//end of class
