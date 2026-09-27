package com.example.myjavakt;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {
    private final NoteDao noteDao;
    private final LiveData<List<NoteEntity>> allNotes;
    private final ExecutorService executor= Executors.newSingleThreadExecutor();

    public NoteRepository(Context context) {
        NoteDatabase db = NoteDatabase.getDatabase(context);
        this.noteDao = db.noteDao();
        this.allNotes = noteDao.getAllNotes();
    }
    public LiveData<List<NoteEntity>> getAllNotes(){
        return  allNotes;
    }
    public void insert(NoteEntity note){
        executor.execute(()->noteDao.insert(note));
    }
    public void update(NoteEntity note){
        executor.execute(()->noteDao.update(note));
    }
    public void deleteNote(NoteEntity note){
        executor.execute(()->noteDao.delete(note));
    }


}
