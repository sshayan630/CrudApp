package com.example.shayan.hotchTest.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.shayan.hotchTest.dao.PersonDao;
import com.example.shayan.hotchTest.entity.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class PersonAppDatabase extends RoomDatabase {

    public abstract PersonDao personDao();

    private static volatile PersonAppDatabase INSTANCE;

    static PersonAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PersonAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PersonAppDatabase.class, "person_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
