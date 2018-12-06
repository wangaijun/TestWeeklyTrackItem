package com.example.waj.testweeklytrackitem

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context, version: Int) : SQLiteOpenHelper(context, "weeklytrack", null, version) {

    override fun onCreate(db: SQLiteDatabase) {
        var sql = "create table $TABLE_NAME ("
        sql += "id text,"
        sql += "taskId text,"
        sql += "body text,"
        sql += "PRIMARY KEY (id)"
        sql += ")"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        const val TABLE_NAME = "trackitem"
    }
}
