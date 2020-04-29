package com.example.partia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createNewEvent_btn_clicked(view: View){
        val intent = Intent(this, ActivityCreateEvent::class.java).apply {
        }
        startActivity(intent)

    }

    fun joinEvent_btn_clicked(view: View){
      val intent = Intent(this, ActivityJoinEvent::class.java).apply {
        }
        startActivity(intent)
    }
}
