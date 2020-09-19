package com.example.partia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partia.model.Event;
import com.example.partia.model.ParticipantAnswers;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public final class ActivityQueryForParticipent extends AppCompatActivity {
    private TextView textViewPageCount;
    private TextView textViewQuery;
    private RadioGroup rg;
    private List<RadioButton> radioButtonList = new ArrayList<>(9);
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button btnNext;
    private int totalNumOfPages = 4;
    private int currPage = 1;
    private Event currEvent;
    private String userSessionEmail;
    ParticipantAnswers answers ;

    @SuppressLint("ResourceType")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_query_for_participent);
        Parcelable parcelable = getIntent().getParcelableExtra("EXTRA_EVENT");
        currEvent = Parcels.unwrap(parcelable);
        userSessionEmail = getIntent().getStringExtra("EXTRA_USER_SESSION_EMAIL");
        textViewPageCount = findViewById(R.id.textView_pageCount);
        textViewQuery = findViewById(R.id.textView_Query);
        rg = findViewById(R.id.rg);
        initRadioButtonList();
        btnNext= findViewById(R.id.button_next);
        answers = new ParticipantAnswers(userSessionEmail, currEvent.getPin_code());
    }

    private void initRadioButtonList() {
        radioButtonList.add(0,findViewById(R.id.radio_button_op1));
        radioButtonList.add(1,findViewById(R.id.radio_button_op2));
        radioButtonList.add(2,findViewById(R.id.radio_button_op3));
        radioButtonList.add(3,findViewById(R.id.radio_button_op4));
        radioButtonList.add(4,findViewById(R.id.radio_button_op5));
        radioButtonList.add(5,findViewById(R.id.radio_button_op6));
        radioButtonList.add(6,findViewById(R.id.radio_button_op7));
        radioButtonList.add(7,findViewById(R.id.radio_button_op8));
        radioButtonList.add(8,findViewById(R.id.radio_button_op9));
        radioButtonVisibility(false , new int[]{1,2,3,4,5,6,7,8,9});

        changeQuery();

    }

    private void changeQuery() {
        rg.clearCheck();
        if (currPage <= totalNumOfPages) {
            if (currPage == 1 ) {
                textViewQuery.setText("Are u a....");
                radioButtonList.get(0).setText("VEGETARIAN");
                radioButtonList.get(1).setText("VEGAN");
                radioButtonList.get(2).setText("KOSHER");
                radioButtonList.get(3).setText("NONE");
                radioButtonVisibility(true , new int[]{1,2,3,4});
            }
            else if (currPage == 2) {
                collectAnswers( currPage-1 );
                textViewQuery.setText("Are you allergic to...");
                radioButtonList.get(0).setText("PEANUTS");
                radioButtonList.get(1).setText("LACTOSE FREE");
                radioButtonList.get(2).setText("GLUTEN FREE");
                radioButtonList.get(3).setText("NONE");
                //radioButtonList.get(3).setVisibility(View.GONE);
                radioButtonVisibility(false , new int[]{4});
            } else if (currPage == 3) {
                collectAnswers( currPage-1 );
                textViewQuery.setText("Choose your glass");
                radioButtonList.get(0).setText("VODKA");
                radioButtonList.get(1).setText("GIN");
                radioButtonList.get(2).setText("ARAK");
                radioButtonList.get(3).setText("CAMPARI");
                radioButtonList.get(4).setText("WHISKEY");
                radioButtonList.get(5).setText("TEQUILA");
                radioButtonList.get(6).setText("BEAR");
                radioButtonList.get(7).setText("RED WINE");
                radioButtonList.get(8).setText("WHITE WINE");
                radioButtonVisibility(true, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
            } else if(currPage == 4) {
                collectAnswers( currPage-1 );
                textViewQuery.setText("Choose your chaser");
                radioButtonList.get(0).setText("VODKA");
                radioButtonList.get(1).setText("GIN");
                radioButtonList.get(2).setText("ARAK");
                radioButtonList.get(3).setText("CAMPARI");
                radioButtonList.get(4).setText("WHISKEY");
                radioButtonList.get(5).setText("TEQUILA");
                radioButtonVisibility(false , new int[]{7,8,9});
                btnNext.setText("Finish");
            }
            textViewPageCount.setText( currPage + "/" + totalNumOfPages);
            currPage++;
        } else {
            collectAnswers( currPage-1 );
            sendAnswersToserver();
        }
    }

    private void sendAnswersToserver() {
        Call<Event> eventResponseCall = APIClient.getAPIInterface().sendParticipantQueryAnswers(answers);
        eventResponseCall.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if(response.code() == 200 ){
                    Parcelable parcelable = Parcels.wrap(response.body());
                    Intent intent = new Intent(ActivityQueryForParticipent.this, ActivityExistEvent.class);
                    intent.putExtra("EXTRA_EVENT",parcelable);
                    intent.putExtra("EXTRA_USER_SESSION_EMAIL",userSessionEmail);
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


    public void next_btn_clicked(android.view.View view) {
        changeQuery();
    }

    private void collectAnswers(int question) {
        int selectedId = rg.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        String answer = radioButton.getText().toString();
        answer.replaceAll(" ", "_");
        if( question == 1 ){
            answers.mealPreference = answer;
        }
        if( question == 2 ){
            answers.allergies = radioButton.getText().toString();
        }
        if( question == 3 ){
            answer = answer + "_GLASS";
            answers.glassPreference = radioButton.getText().toString();
        }
        if( question == 4 ){
            answer = answer + "_CHASER";
            answers.chaserPreference = radioButton.getText().toString();
        }

    }

    private void radioButtonVisibility(boolean b, int[] ints) {
        int i;
        for( i = 0 ; i < ints.length ; i++ ) {
            radioButtonList.get(ints[i] - 1).setVisibility(b ? View.VISIBLE : View.GONE);
        }
    }
}
