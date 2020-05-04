package com.example.partia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ActivityBudget : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)
    }

    fun split_btn_clicked(view: View) {}
    fun back_btn_clicked(view: View) {}
}
