package com.example.partia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ActivityCreateEvent : AppCompatActivity() {



    fun startQueryForPlanner(view: View){
        val intent = Intent(this, ActivityQueryForPlanner::class.java).apply {
        }
        startActivity(intent)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)
    }
}
