package com.example.assignment3;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceConfig {
    private SharedPreferences sp;
    private Context context;


    public SharedPreferenceConfig(Context c){
        context = c;
        sp=context.getSharedPreferences(context.getResources().getString(R.string.login_preference),Context.MODE_PRIVATE);

    }

    public void WriteLoginStatus(boolean value){
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status),value);
        editor.commit();
    }

    public boolean ReadLoginStatus(){
        boolean status=false;
        status= sp.getBoolean(context.getResources().getString(R.string.login_status),false);
        return status;
    }
}
