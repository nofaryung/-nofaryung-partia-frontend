package com.example.partia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class ActivityQueryForParticipent : AppCompatActivity() {
    private lateinit var textViewPageCount : TextView
    private lateinit var textViewQuery : TextView
    private lateinit var rg : RadioGroup
    private lateinit var rb1 : RadioButton
    private lateinit var rb2 : RadioButton
    private lateinit var rb3 : RadioButton
    private lateinit var btnNext : Button
    private var  totalNumOfPages = 3
    private var currPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_for_participent)

        textViewPageCount = findViewById(R.id.textView_pageCount)
        textViewQuery = findViewById(R.id.textView_Query)
        rg = findViewById(R.id.rg)
        rb1= findViewById(R.id.radio_button_op1)
        rb2= findViewById(R.id.radio_button_op2)
        rb3= findViewById(R.id.radio_button_op3)
        btnNext= findViewById(R.id.button_next)
    }


    fun next_btn_clicked(view: View){
        rg.clearCheck()
        if(currPage < totalNumOfPages) {
            currPage++
            if(currPage == 2){
                textViewQuery.text = "Choose your glass"
                rb1.text = "Vodka"
                rb2.text = "Gin"
                rb3.text = "Wine"
            }
            if(currPage == 3){
                textViewQuery.text = "Are you allergic to..."
                rb1.text = "Peanuts"
                rb2.text = "Dairy"
                rb3.text = "Something else"
                btnNext.text = "Finish"
            }
        }
        //TODO
        //textViewPageCount.setText( currPage + "/" + totalNumOfPages)
        else if(currPage == totalNumOfPages){
            //TODO
            //finish Query for participents - now what does the user see

        }
    }
}
