package com.example.waj.testweeklytrackitem

import android.database.sqlite.SQLiteDatabase
import com.google.gson.Gson
import com.google.gson.JsonObject

object DAO{
    private const val ver = 1
    val db:SQLiteDatabase by lazy { DbHelper(Bim5dApplication.instance,ver).writableDatabase }

    fun localDataNum(): Int{
        val sql = "select count(localOperType) as count from ${DbHelper.TABLE_NAME} where localOperType is not null"
        val c = db.rawQuery(sql, arrayOf())
        c.moveToFirst()
        val count = c.getInt(c.getColumnIndex("count"))
        c.close()
        return count
    }

//    fun getLocalData():List<WeeklyTrackItem>{
//        val list = mutableListOf<WeeklyTrackItem>()
//    }

    fun getByTaskId(taskId:String):List<WeeklyTrackItem>{
        val list = mutableListOf<WeeklyTrackItem>()
        val sql = "select * from ${DbHelper.TABLE_NAME} where taskId = ?"
        val c = db.rawQuery(sql, arrayOf(taskId))
        while (c.moveToNext()){
            val body = c.getString(c.getColumnIndex("body"))
            val item = Gson().fromJson<WeeklyTrackItem>(body,WeeklyTrackItem::class.java)
            list.add(item)
        }
        c.close()
        return list
    }

    fun batchInsert(root: JsonObject) {
        db.beginTransaction()  //手动设置开始事务

        val data = root["data"] as JsonObject?
        data?.let {
            val jarr = data["items"].asJsonArray
            jarr?.let {
                val size = jarr.size()-1
                for (i in 0..size) {
                    val item = jarr[i].asJsonObject
                    insert(item.toString())
                }
            }
        }

        db.setTransactionSuccessful() //设置事务处理成功，不设置会自动回滚不提交。
    }

    //批量保存来自服务端的代码
    fun batchSave(root: JsonObject) {
        db.beginTransaction()

        val data = root["items"].asJsonObject
        data?.let {
            val jarr = data["items"].asJsonArray
            jarr?.let {
                val size = jarr.size()
                for (i in 0..size){
                    val item = jarr[i].asJsonObject
                    //根据操作类型执行操作
                }
            }
        }

        db.setTransactionSuccessful()
    }

    /**用于新增来自服务端的数据*/
    fun insert(item: String){
        val obj = Gson().fromJson<WeeklyTrackItem>(item,WeeklyTrackItem::class.java)
        val sql = "insert into ${DbHelper.TABLE_NAME} (id,taskId,body) values (?,?,?)"
        db.execSQL(sql, arrayOf(obj.id,obj.taskId,item))
    }
    /**用于保存来自服务器的编辑,可能会用服务端数据更新本地已修改数据，一个潜在的bug*/
    fun update(item:String){
        val sql = "update ${DbHelper.TABLE_NAME} set body=? where id=?"
        val id = Gson().fromJson<WeeklyTrackItem>(item, WeeklyTrackItem::class.java).id
        db.execSQL(sql, arrayOf(item, id))
    }
    fun delete(id:String){
        val sql = "delete from ${DbHelper.TABLE_NAME} where id = ?"
        db.execSQL(sql, arrayOf(id))
    }
    /**用于保存本地新增的数据*/
    fun insert(item: WeeklyTrackItem) {
        val sql = "insert into ${DbHelper.TABLE_NAME} (id,taskId,body,localOperType) values (?,?,?,?)"
        item.localOperType = DbHelper.LOCAL_OPER_TYPE_ADD
        db.execSQL(sql, arrayOf(item.id, item.taskId, Gson().toJson(item), item.localOperType))
    }

    /**
     * 用于保存本地编辑过的数据
     * 本地编辑的数据可能是来服务器，也可能是本地新建的
     * */
    fun update(item:WeeklyTrackItem){
        //取出原有的本地操作类型
        val sel = "select localOperType from ${DbHelper.TABLE_NAME} where id = ?"
        val c = db.rawQuery(sel, arrayOf(item.id))
        //设置新操作类型
        if (c.moveToFirst()){
            val oldLocalOperType = c.getString(c.getColumnIndex("localOperType"))
            c.close()
            val localOperType = when(oldLocalOperType){
                DbHelper.LOCAL_OPER_TYPE_ADD->DbHelper.LOCAL_OPER_TYPE_ADD
                DbHelper.LOCAL_OPER_TYPE_MODIFY->DbHelper.LOCAL_OPER_TYPE_MODIFY
                else->DbHelper.LOCAL_OPER_TYPE_MODIFY
            }
            val sql = "update ${DbHelper.TABLE_NAME} set body=?,localOperType=? where id=?"
            item.localOperType = localOperType
            db.execSQL(sql, arrayOf(Gson().toJson(item),item.localOperType,item.id))
        }
    }
}