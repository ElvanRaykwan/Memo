package com.example.memo;

public class Note {
    private  String noteContent;
    private  long noteDate;

    public Note(String nContent, long nDate){
        this.noteContent = nContent;
        this.noteDate = nDate;
    }

    public String getContent(){
        return noteContent;
    }

    public void setContent(String newContent){
        this.noteContent = newContent;
    }

    public long getDate(){
        return noteDate;
    }

    public void setDate(long newDate){
        this.noteDate=newDate;
    }
}
