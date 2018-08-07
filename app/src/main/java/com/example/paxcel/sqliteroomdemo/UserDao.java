package com.example.paxcel.sqliteroomdemo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();


    @Query("SELECT first_name FROM user")
    List<String> getAllUsersName();

    @Query("SELECT * FROM user where first_name LIKE  :firstName AND last_name LIKE :lastName")
    User findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from user")
    int getCountUsers();

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

}
