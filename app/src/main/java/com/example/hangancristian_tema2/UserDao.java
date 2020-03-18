package com.example.hangancristian_tema2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT COUNT(*) FROM user")
    int getSize();

    @Query("SELECT * FROM user WHERE id == :userId LIMIT 1")
    User findById(int userId);

    @Query("SELECT * FROM user WHERE name LIKE :name LIMIT 1")
    User findByName(String name);

    @Insert
    void insertAll(User... users);

    @Query("DELETE FROM user WHERE name LIKE :name")
    int delete(String name);
}
