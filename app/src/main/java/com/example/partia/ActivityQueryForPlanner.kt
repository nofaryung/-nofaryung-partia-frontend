package com.example.partia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class ActivityQueryForPlanner : AppCompatActivity() {

    private lateinit var textViewPageCount : TextView
    private lateinit var textViewQuery : TextView

    private lateinit var rg : RadioGroup
    private lateinit var rb1 : RadioButton
    private lateinit var rb2 : RadioButton
    private lateinit var rb3 : RadioButton
    private lateinit var rb4 : RadioButton
    private lateinit var btnNext : Button
    private var  totalNumOfPages = 4
    private var currPage = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_for_planner)

        textViewPageCount = findViewById(R.id.textView_pageCount)
        textViewQuery = findViewById(R.id.textView_Query)
        rg = findViewById(R.id.rg)
        rb1= findViewById(R.id.radio_button_op1)
        rb2= findViewById(R.id.radio_button_op2)
        rb3= findViewById(R.id.radio_button_op3)
        rb4= findViewById(R.id.radio_button_op4)
        btnNext= findViewById(R.id.button_next)
        rb4.visibility = View.GONE
    }


    fun next_btn_clicked(view: View){
        //TODO collect answears
        rg.clearCheck()
        if(currPage < totalNumOfPages) {
            currPage++
            if(currPage == 2){
                textViewQuery.text = "Choose environment"
                rb1.text = "Indoor"
                rb2.text = "Outdoor"
                rb3.visibility = View.GONE
                rb4.visibility = View.GONE
            }
            if(currPage == 3){
                textViewQuery.text = "Is there a meal?"
                rb1.text = "NO"
                rb2.text = "Yes, each one brings something"
                rb3.text = "Yes, but I'm on it"
                rb4.text = "Yes, from outsource"
                rb3.visibility = View.VISIBLE
                rb4.visibility = View.VISIBLE
            }
            if(currPage == 4){
                textViewQuery.text = "Is there alcohol?"
                //rb1.text = "NO"
                //rb2.text = "Yes, each one brings something"
                //rb3.text = "Yes, but I'm on it"
                //rb4.text = "Yes, from outsource"
                //rb3.visibility = View.VISIBLE
                //rb4.visibility = View.VISIBLE
            }
        }
        //TODO
        //textViewPageCount.setText( currPage + "/" + totalNumOfPages)
        else if(currPage == totalNumOfPages){
            //finish Query
            val intent = Intent(this, ActivityFinishCreatingEvent::class.java).apply {
            }
            startActivity(intent)

        }
    }

}
