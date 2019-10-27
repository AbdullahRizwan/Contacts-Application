package com.example.assignment3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person {
    @PrimaryKey
    public int id;

    @ColumnInfo(name="name")
    public String firstname;

    @ColumnInfo(name="email")
    public String email;

    @ColumnInfo(name="number")
    public String number;

    @ColumnInfo(name = "photo")
    public String photo;


}
