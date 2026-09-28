package com.example.myjavakt.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myjavakt.Dao.StudentDao;
import com.example.myjavakt.entity.Students;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {Students.class},
        version = 1
)
public abstract class StudentDB extends RoomDatabase {
    public abstract StudentDao studentDao();
    public static final ExecutorService writeExecutor = Executors.newSingleThreadExecutor();
    private static volatile StudentDB INSTANCE;
    public static StudentDB get(Context context){
        if(INSTANCE==null){
            synchronized (StudentDB.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(
                            context,
                            StudentDB.class,
                            "user"
                    ).createFromAsset("database/user.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
