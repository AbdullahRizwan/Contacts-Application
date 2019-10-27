package com.example.assignment3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM Person")
    List<Person> getAll();

    @Query("SELECT * FROM Person WHERE id IN (:userIds)")
    List<Person> loadAllByIds(int[] userIds);

    @Query("SELECT COUNT(id) FROM Person ")
    int GetSize();

    @Query("SELECT * FROM Person WHERE name LIKE :first LIMIT 1")
    Person findByName(String first);

    @Insert
    void insertAll(Person user);

    @Delete
    void delete(Person user);
}
