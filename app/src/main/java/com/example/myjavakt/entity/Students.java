package com.example.myjavakt.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Students {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int age;
    public double gpa;
}
