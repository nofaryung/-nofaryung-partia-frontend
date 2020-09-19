package com.example.partia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.partia.model.Event;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.parceler.Parcels;

public final class ActivityQueryForPlanner extends AppCompatActivity {
    private TextView textViewPageCount;
    private TextView textViewQuery;
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button btnNext;
    private int totalNumOfPages = 4;
    private int currPage = 1;
    Event event;
    String userSessionEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parcelable parcelable = getIntent().getParcelableExtra("EXTRA_EVENT");
        event = Parcels.unwrap(parcelable);
        userSessionEmail = getIntent().getStringExtra("EXTRA_USER_SESSION_EMAIL");
        this.setContentView(R.layout.activity_query_for_planner);
        textViewPageCount = findViewById(R.id.textView_pageCount);
        textViewQuery = findViewById(R.id.textView_Query);
        rg =  findViewById(R.id.rg_planner);
        rb1 = findViewById(R.id.radio_button_op1p);
        rb2 = findViewById(R.id.radio_button_op2p);
        rb3 = findViewById(R.id.radio_button_op3p);
        rb4 = findViewById(R.id.radio_button_op4p);
        btnNext = findViewById(R.id.button_nextPlanner);
        changeQuery();
    }


    public void next_btn_inPlanner_clicked(android.view.View view) {
        changeQuery();
    }

    private void changeQuery() {
        if (currPage <= totalNumOfPages) {
            if (currPage == 1) {
                textViewQuery.setText("What's the occasion?");
                rb1.setText("Birthday party");
                rb2.setText("Bachelorette party");
                rb3.setText("gathering");
                rb4.setVisibility(View.GONE);
            } else if (currPage == 2) {
                collectAnswers(currPage - 1);
                textViewQuery.setText("Choose environment");
                rb1.setText("Indoor");
                rb2.setText("Outdoor");
                rb3.setVisibility(View.GONE);
            } else if (currPage == 3) {
                collectAnswers(currPage - 1);
                textViewQuery.setText("Is there a meal?");
                rb1.setText("NO");
                rb2.setText("Yes, each one brings something");
                rb3.setText("Yes, but I'm on it");
                rb4.setText("Yes, from outsource");
                rb3.setVisibility(View.VISIBLE);
                rb4.setVisibility(View.VISIBLE);
            } else if (currPage == 4) {
                collectAnswers(currPage - 1);
                textViewQuery.setText("Is there alcohol?");
                btnNext.setText("Finish");
            }
            textViewPageCount.setText(currPage + "/" + totalNumOfPages);
            currPage++;
        } else {
            collectAnswers(currPage - 1);
            sendAnswersToserver();
        }
        rg.clearCheck();
    }

    private void sendAnswersToserver() {
        Call<Event> eventResponseCall = APIClient.getAPIInterface().createEvent(event);
        eventResponseCall.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if(response.code() == 200 ){
                    Parcelable parcelable = Parcels.wrap(response.body());
                    Intent intent = new Intent(ActivityQueryForPlanner.this, ActivityExistEvent.class);
                    intent.putExtra("EXTRA_EVENT",parcelable);
                    intent.putExtra("EXTRA_USER_SESSION_EMAIL", userSessionEmail);
                    startActivity(intent);
                }
                if(response.code() == 400){
                    //Toast.makeText(ActivityJoinEvent.this,"Invalid Pin Code!",Toast.LENGTH_LONG).show();
                }
                if(response.code() == 403) {
                    // Toast.makeText(ActivityJoinEvent.this,"User Already Exist In Event!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {

            }
        });
    }

    private void collectAnswers(int question) {
        int selectedId = rg.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);

        if( question == 1 ){
            event.setKind_of_event(radioButton.getText().toString());
        }
        if( question == 2 ){
            event.setEnvironment(radioButton.getText().toString());
        }
        if( question == 3 ){
            event.setMeal_organization(radioButton.getText().toString());
        }
        if( question == 4 ){
            event.setBeverage_organization(radioButton.getText().toString());
        }

    }
}
