package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferenceConfig preferenceConfig;
    private EditText userName,userPassword;
    private CheckBox checkbox;
    RecyclerView r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferenceConfig= new SharedPreferenceConfig(getApplicationContext());
        userName= (EditText) findViewById(R.id.email);
        userPassword =(EditText) findViewById(R.id.Password);
        if(preferenceConfig.ReadLoginStatus()){
            startActivity(new Intent(this,home.class));
            finish();
        }
    }

    public void Login(View view) {
        String name= userName.getText().toString();
        String pass= userPassword.getText().toString();
        checkbox=(CheckBox) findViewById(R.id.checkBox);

        if(name.equals(getResources().getString(R.string.user_name)) && pass.equals(getResources().getString(R.string.user_password))){
            if(checkbox.isChecked())
                preferenceConfig.WriteLoginStatus(true);
            startActivity(new Intent(this,home.class));
            finish();
        }
        else{
            Toast.makeText(this,"login failed...Try again...",Toast.LENGTH_SHORT).show();
            userName.setText("");
            userPassword.setText("");
        }

    }
}
