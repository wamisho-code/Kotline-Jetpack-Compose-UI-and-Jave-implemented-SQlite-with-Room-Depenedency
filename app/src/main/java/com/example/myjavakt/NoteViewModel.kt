package com.example.myjavakt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val repository= NoteRepository(application)
    val allNotes: LiveData<List<NoteEntity>> = repository.getAllNotes()
    fun insert(note: NoteEntity)= repository.insert(note)
    fun update(note: NoteEntity)=  repository.update(note)
    fun delete(note: NoteEntity)=  repository.deleteNote(note)
}