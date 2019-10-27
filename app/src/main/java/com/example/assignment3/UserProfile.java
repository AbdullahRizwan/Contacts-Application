package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    ImageView imagee;
    TextView namee,emaill,numberr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Intent i=getIntent();
        imagee=findViewById(R.id.image);
        namee=findViewById(R.id.name);
        emaill=findViewById(R.id.email);
        numberr=findViewById(R.id.phone);
        if(i.getStringExtra("name")!=null){
            namee.setText(i.getStringExtra("name"));
        }

        if(i.getStringExtra("phone")!=null){
            numberr.setText(i.getStringExtra("phone"));
        }
        if(i.getStringExtra("email")!="")
            emaill.setText(i.getStringExtra("email"));
        else
            emaill.setText("Dummy Email");
        if(i.getStringExtra("photo")!=null){
            imagee.setImageURI(Uri.parse(i.getStringExtra("photo")));
        }
        else{
            imagee.setImageResource(R.mipmap.ic_launcher);
        }

    }

    public void back(View view) {
        finish();
    }
}
