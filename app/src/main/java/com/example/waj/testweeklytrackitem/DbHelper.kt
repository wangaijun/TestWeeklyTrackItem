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
        sql += "localOperType text,"
        sql += "PRIMARY KEY (id)"
        sql += ")"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        const val TABLE_NAME = "trackitem"
        const val LOCAL_OPER_TYPE_ADD = "localOperTypeAdd" //在本地新增的数据
        const val LOCAL_OPER_TYPE_MODIFY = "localOperTypeModify" //编辑来自服务端的数据，未上传至服务器，保存到了本地
    }
}
