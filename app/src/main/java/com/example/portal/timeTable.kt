package com.example.portal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView


class timeTable : AppCompatActivity() {
    lateinit var listview:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table)

        listview=findViewById(R.id.list_view)

        var days=resources.getStringArray(R.array.days)
        var arrayAdapter=ArrayAdapter(this,android.R.layout.activity_list_item,days)
        listview.adapter=arrayAdapter


    }
}