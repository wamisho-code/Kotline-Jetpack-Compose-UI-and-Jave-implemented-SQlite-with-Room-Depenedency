package com.example.myjavakt.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import com.example.myjavakt.database.StudentDB

class StudentViewModel(app: Application): AndroidViewModel(app) {
    private val dao= StudentDB.get(app).studentDao()
    val students = dao.getStudents()
}