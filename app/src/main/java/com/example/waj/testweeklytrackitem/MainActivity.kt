package com.example.waj.testweeklytrackitem

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            f()
        }
    }

    private fun f() {
        val istream = assets.open("data.txt")
        val bs = ByteArray(1024*1024)
        val len = istream.read(bs)
        val str = String(bs,0,len)
        Log.i("waj",str)

        val root = JsonParser().parse(str) as JsonObject
        val code = root["code"].asInt
        val msg = root["message"].asString
        if(code!=0){
            Toast.makeText(this,msg?:"",Toast.LENGTH_LONG).show()
        }else{
            DAO.batchInsert(root)
        }
    }
}
