package com.example.assignment3;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class Adaptor extends RecyclerView.Adapter<Adaptor.MyViewHolder> {


    private List<Person> mydata;
    private myClickDetector myClickDetector;
    Bitmap mIcon_val;
    public static AppDatabase dbb;
    public Adaptor( AppDatabase _dbb, myClickDetector myClickd){

        dbb=_dbb;
        mydata=dbb.userDao().getAll();
        this.myClickDetector=myClickd;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater infalte=LayoutInflater.from(parent.getContext());
        View view= infalte.inflate(R.layout.eachobj,parent,false);
        return new MyViewHolder(view,myClickDetector);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        mydata=home.db.userDao().getAll();
        String title= mydata.get(position).firstname;
        String phone=mydata.get(position).number;
        holder.name.setText(title);
        holder.phone.setText(phone);
        if(mydata.get(position).photo!= null)
            holder.img.setImageURI(Uri.parse(mydata.get(position).photo));
        else
            holder.img.setImageResource(R.drawable.ic_launcher);
       // holder.img.setImageResource(mydata.get(position).photo);

    }
    @Override
    public int getItemCount() {

        mydata=home.db.userDao().getAll();
        return mydata.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView name;
        TextView phone;
        myClickDetector myClick;
        public MyViewHolder(@NonNull View itemView, myClickDetector myClick) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.picture);
            name=(TextView) itemView.findViewById(R.id.name);
            phone=(TextView) itemView.findViewById(R.id.phone);
            itemView.setOnClickListener(this);
            this.myClick=myClick;

        }

        @Override
        public void onClick(View view) {
                this.myClick.onMyClick(mydata.get(getAdapterPosition()));
        }
    }

    public interface myClickDetector{
        void onMyClick(Person p);
    }

}