package com.example.app_register

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB(val context: Context, val factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, "app_db", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, email TEXT, psw TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUsers(user: User){
        val values = ContentValues()
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("psw", user.psw)

        val db = this.writableDatabase
        db.insert("users", null, values)

        db.close()
    }

    fun getUsers(login: String, psw: String): Boolean{
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM WHERE login = '$login' AND psw = '$psw'", null)
        return result.moveToFirst()
    }
}