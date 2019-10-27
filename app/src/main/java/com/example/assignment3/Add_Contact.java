package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Add_Contact extends AppCompatActivity {

    private TextView _name,_email,_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__contact);
        _name=findViewById(R.id.name);
        _no=findViewById(R.id.number);
        _email=findViewById(R.id.email);
    }

    public void Save(View view) {

        String name= _name.getText().toString();
        String number= _no.getText().toString();
        String email= _email.getText().toString();


        Person person = new Person();
        person.id = home.db.userDao().GetSize()+1;
        person.firstname = name;
        person.number = number;
        person.email = email;
        person.photo=null;

        try {
            home.db.userDao().insertAll(person);
            notifyAll();
        } catch (Exception e) {

        }
        notify();
        notify();
        finish();
    }
}
