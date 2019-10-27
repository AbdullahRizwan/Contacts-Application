package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;



public class home extends AppCompatActivity implements Adaptor.myClickDetector {
    int i;
    RecyclerView rv;
    private  List<Contacts> contacts= new ArrayList<>();
    public static AppDatabase db;
    private int CONTACT_PERMISSION_CODE=1;
    SharedPreferenceConfig preferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        preferenceConfig= new SharedPreferenceConfig(getApplicationContext());
        db = Room.databaseBuilder(this.getApplicationContext(), AppDatabase.class, "MyDb").allowMainThreadQueries().build();
        if(db!=null){
            RecyclerView rv =(RecyclerView) findViewById(R.id.rview);
            rv.setLayoutManager(new LinearLayoutManager(this));
            Adaptor adaptor= new Adaptor(db,this);
            rv.setAdapter(adaptor);
        }
    }

    public void Logout(View view) {
        preferenceConfig.WriteLoginStatus(false);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }


    public void contacts(View view) {
        if (ContextCompat.checkSelfPermission(home.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(home.this, "You have already granted this permission!",
                    Toast.LENGTH_SHORT).show();
        } else {

            requestStoragePermission();
        }
    }
        private void requestStoragePermission() {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                new AlertDialog.Builder(this)
                        .setTitle("Permission needed")
                        .setMessage("We require this permission to read your contacts so that we can use them")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(home.this,
                                        new String[] {Manifest.permission.READ_CONTACTS}, CONTACT_PERMISSION_CODE);
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.READ_CONTACTS}, CONTACT_PERMISSION_CODE);
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if (requestCode == CONTACT_PERMISSION_CODE)  {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                    process();
                    i++;

                } else {
                    Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
                }
            }
        }
    void process(){
            Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            String[] protection={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Email.DISPLAY_NAME};

            ContentResolver resolver= getContentResolver();
            Cursor cursor=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null, ContactsContract.Contacts.DISPLAY_NAME + " ASC ");
            contacts.clear();
        while(cursor.moveToNext()) {

                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String no = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String photo = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
                String email = " ";


                contacts.add(new Contacts(name, no, "", photo));
                i++;
            }




            db = Room.databaseBuilder(this.getApplicationContext(), AppDatabase.class, "MyDb").allowMainThreadQueries().build();
    //      db=Room.databaseBuilder(getApplicationContext(),Person.class);
            for (int j = 0; j < contacts.size(); j++) {
                Person person = new Person();
                person.id = j;
                person.firstname = contacts.get(j).name;
                person.number = contacts.get(j).phone;
                person.email = contacts.get(j).email;
                person.photo=contacts.get(j).photo;

                try {
                    db.userDao().insertAll(person);
                } catch (Exception e) {
                    Toast.makeText(this, "Already Exist", Toast.LENGTH_SHORT).show();
                }
            }


        rv =(RecyclerView) findViewById(R.id.rview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        Adaptor adaptor= new Adaptor(db,this);
        rv.setAdapter(adaptor);
    }

    public void AddContact(View view) {
        Intent i= new Intent(this,Add_Contact.class);
        startActivity(i);
    }

    public void remove(View view) {
        startActivity(new Intent(this,Remove.class));
    }



    public void showProfile(View view) {
        TextView t=findViewById(R.id.phone);
        Toast.makeText(this,t.getText().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMyClick(Person p) {

        Intent i=new Intent(this,UserProfile.class);
        i.putExtra("photo",p.photo);
        i.putExtra("name",p.firstname);
        i.putExtra("email",p.email);
        i.putExtra("phone",p.number);
        startActivity(i);


    }
}

