package com.example.waj.testweeklytrackitem

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener { batchInsert() }

        btnUpdate.setOnClickListener { update() }

        btnDel.setOnClickListener { delete() }

        btnLocalInsert.setOnClickListener { localInsert() }

        btnLocalModify.setOnClickListener { localModify() }

        btnLocalDataNum.setOnClickListener { displayLocalDataNum() }
    }

    private fun displayLocalDataNum() {
        val count = DAO.localDataNum()
        toast(count.toString())
    }

    private fun batchInsert() {
        val str = readFile("data.txt")

        val root = JsonParser().parse(str) as JsonObject
        val code = root["code"].asInt
        val msg = root["message"].asString
        if(code!=0){
            toast(msg)
        }else{
            DAO.batchInsert(root)
        }
    }

    private fun toast(msg: String?) {
        Toast.makeText(this, msg ?: "", Toast.LENGTH_LONG).show()
    }

    private fun readFile(fName:String): String {
        val istream = assets.open(fName)
        val bs = ByteArray(1024 * 1024)
        val len = istream.read(bs)
        val str = String(bs, 0, len)
        Log.i("waj", str)
        return str
    }

    private fun update(){
        val s = readFile("one.txt")
        DAO.update(s)
    }

    private fun delete(){
        DAO.delete("1579799726352226")
    }

    private fun localInsert(){
        val s = readFile("one.txt")
        val item = Gson().fromJson<WeeklyTrackItem>(s,WeeklyTrackItem::class.java)
        item.id = "${System.currentTimeMillis()}"
        DAO.insert(item)
    }

    private fun localModify(){
        val s = readFile("one.txt")
        val item = Gson().fromJson<WeeklyTrackItem>(s,WeeklyTrackItem::class.java)
        DAO.update(item)
    }
}
