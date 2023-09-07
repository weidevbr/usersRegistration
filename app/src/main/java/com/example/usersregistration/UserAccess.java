package com.example.usersregistration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserAccess {

    private DBConnection dbConnection;
    private SQLiteDatabase banco;

    public UserAccess(Context context){
        dbConnection = new DBConnection(context);
        banco = dbConnection.getWritableDatabase();
    }

    public long input ( User user){
        ContentValues values = new ContentValues();
        values.put("nome", user.getNome());
        values.put("usuario", user.getUserName());
        values.put("senha", user.getPassWord());
        values.put("endereco", user.getAddress());
        values.put("email", user.getEmail());
        values.put("datanasc", user.getUserName());
        values.put("sexo", user.getSex());
        values.put("tipo", user.getIdType());
        values.put("idNum", user.getIdNumb());
        return banco.insert("user", null,values);
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        Cursor cursor = banco.query("user",new String[]{"_id","nome","usuario","senha","endereco","email"
                ,"datanasc","sexo","tipo","idNum"},null,
                null,null,null, "nome");
        while (cursor.moveToNext()) {
            User u = new User();
            u.setId(cursor.getInt(0));
            u.setNome(cursor.getString(1));
            u.setUserName(cursor.getString(2));
            u.setPassWord(cursor.getString(3));
            u.setAddress(cursor.getString(4));
            u.setEmail(cursor.getString(5));
            u.setDate(cursor.getString(6));
            u.setSex(cursor.getString(7));
            u.setIdType(cursor.getString(8));
            u.setIdNumb(cursor.getString(9));
            users.add(u);
        }
        return users;
    }


    public void deleteUser(String u){

        banco.delete("user","usuario = ?", new String[]{u});
    }

    public void editUser(User user) {

        ContentValues values = new ContentValues();
        values.put("nome", user.getNome());
        values.put("usuario", user.getUserName());
        values.put("senha", user.getPassWord());
        values.put("endereco", user.getAddress());
        values.put("email", user.getEmail());
        values.put("datanasc", user.getUserName());
        values.put("sexo", user.getSex());
        values.put("tipo", user.getIdType());
        values.put("idNum", user.getIdNumb());
        banco.update("user",values,"usuario = ?",
                new String[]{user.getUserName()});
    }
}
