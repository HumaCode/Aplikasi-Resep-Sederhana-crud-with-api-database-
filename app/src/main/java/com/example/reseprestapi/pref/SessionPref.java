package com.example.reseprestapi.pref;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.reseprestapi.login.LoginActivity;
import com.example.reseprestapi.MainActivity;


/**
 * Created by Hairul on 6/19/2017.
 */

public class SessionPref {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

//    buat variabel yang berisi data yg akan di buat sesion
    private static final String PREF_NAME = "Sesi";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    public SessionPref(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //    data yg dipakai untuk session
    public void createLoginSession(String email){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

// cek login, jika belom login, akan di tendang dengan keras ke halaman login.
    public void checkLoginMain(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

//    cek login, jika sudah login boleh masuk ke mainactivity
    public void checkLogin(){
        if(this.isLoggedIn()){
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public String getID(){
        String name = pref.getString(KEY_ID, null);
        return name;
    }

    public String getName(){
        String name = pref.getString(KEY_NAME, null);
        return name;
    }

    public String getEmail(){
        String email = pref.getString(KEY_EMAIL, null);
        return email;
    }

//    function logout, ketika logout data yang tersimpan di shared preference / session akan di hapus / clear
//    kemudian di lempar juga ke login activity
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
