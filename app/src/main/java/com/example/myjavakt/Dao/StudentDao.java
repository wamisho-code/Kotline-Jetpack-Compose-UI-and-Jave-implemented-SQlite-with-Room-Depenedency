package com.example.myjavakt.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myjavakt.entity.Students;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("select * from Students order by name")
    LiveData<List<Students>> getStudents();
    @Insert
    void insertStudent(Students student);
    @Query("Select * from Students where name like :sname order by name")
    LiveData<List<Students>> getStudentByName(String sname);
}
