package com.example.myjavakt;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(NoteEntity note);
    @Update
    void update(NoteEntity note);
    @Delete
    void delete(NoteEntity note);
    @Query("Select * from notes order by id desc")
    LiveData<List<NoteEntity>> getAllNotes();
}
