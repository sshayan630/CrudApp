package com.example.shayan.hotchTest.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.shayan.hotchTest.entity.Person;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert
    void insert(Person person);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);

    @Query("DELETE FROM persons")
    void deleteAll();

    @Query("SELECT * from persons ORDER BY person_name ASC")
    LiveData<List<Person>> getAllPersons();
}
