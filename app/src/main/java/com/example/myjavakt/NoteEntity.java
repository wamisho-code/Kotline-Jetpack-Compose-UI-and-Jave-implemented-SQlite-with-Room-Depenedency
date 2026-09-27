package com.example.myjavakt;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String content;
    public NoteEntity(String title,String content){
        this.title=title;
        this.content= content;
    }

}
