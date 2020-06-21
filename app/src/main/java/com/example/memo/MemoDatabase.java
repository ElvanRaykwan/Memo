package com.example.memo;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Database;

//the database of teh application

@Database (entities = Note.class, version =1)
public abstract class MemoDatabase extends RoomDatabase {
    public abstract DatabaseAccess notesDao(); //used to gain access to the database

    private  static MemoDatabase instance;

    //generate instance in order to gain access to database
    public static MemoDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context, MemoDatabase.class, "DATABASE_NAME").
                    allowMainThreadQueries().build();

        }
        return instance;
    }
}
