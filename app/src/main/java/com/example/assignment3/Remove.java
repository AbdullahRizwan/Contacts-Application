package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Remove extends AppCompatActivity {

    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
    }

    public void RemoveContact(View view) {
        name=(EditText)findViewById(R.id.name);
        String fname=name.getText().toString();
        if(name!=null){
            if(home.db.userDao().findByName(fname)!=null)
                home.db.userDao().delete(home.db.userDao().findByName(fname));
            else
                Toast.makeText(this, "No such contact", Toast.LENGTH_SHORT).show();

        }
        else
            Toast.makeText(this, "No such contact", Toast.LENGTH_SHORT).show();
        notifyAll();
        finish();



    }
}
