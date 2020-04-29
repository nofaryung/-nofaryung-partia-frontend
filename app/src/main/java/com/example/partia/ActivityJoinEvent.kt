package com.example.partia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class ActivityJoinEvent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_event)
    }


    fun join_event_btn_clicked(view: View){

        val intent = Intent(this, ActivityQueryForParticipent::class.java).apply {
        }
        startActivity(intent)
    }
}
