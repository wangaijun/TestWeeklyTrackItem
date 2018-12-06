package com.example.waj.testweeklytrackitem

import android.database.sqlite.SQLiteDatabase
import com.google.gson.Gson

object DAO{
    private const val ver = 1
    val db:SQLiteDatabase by lazy { DbHelper(Bim5dApplication.instance,ver).writableDatabase }
    /**用于保存本地新增的数据*/
    fun insert(item: WeeklyTrackItem) {
        val sql = "insert into ${DbHelper.TABLE_NAME} (id,taskId,body) values (?,?,?)"
        db.execSQL(sql, arrayOf(item.id, item.taskId, Gson().toJson(item)))
    }
    /**用于新增来自服务端的数据*/
    fun insert(item: String){
        val obj = Gson().fromJson<WeeklyTrackItem>(item,WeeklyTrackItem::class.java)
        val sql = "insert into ${DbHelper.TABLE_NAME} (id,taskId,body) values (?,?,?)"
        db.execSQL(sql, arrayOf(obj.id,obj.taskId,item,item))
    }

    fun delete(id:String){
        val sql = "delete from ${DbHelper.TABLE_NAME} where id = ?"
        db.execSQL(sql, arrayOf(id))
    }

    /**用于保存本地编辑过的数据*/
    fun update(item:WeeklyTrackItem){
        val sql = "update ${DbHelper.TABLE_NAME} set body=? where id=?"
        db.execSQL(sql, arrayOf(Gson().toJson(item),item.id))
    }
    /**用于保存来自服务器的编辑*/
    fun update(item:String){
        val sql = "update ${DbHelper.TABLE_NAME} set body=? where id=?"
        val id = Gson().fromJson<WeeklyTrackItem>(item, WeeklyTrackItem::class.java).id
        db.execSQL(sql, arrayOf(item, id))
    }


    fun getByTaskId(taskId:String):List<WeeklyTrackItem>{
        val list = mutableListOf<WeeklyTrackItem>()
        val sql = "select * from ${DbHelper.TABLE_NAME} where taskId = ?"
        val c = db.rawQuery(sql, arrayOf(taskId))
        while (c.moveToNext()){
            val body = c.getString(c.getColumnIndex("body"))
            val item = Gson().fromJson<WeeklyTrackItem>(body,WeeklyTrackItem::class.java)
            list.add(item)
        }
        return list
    }
}