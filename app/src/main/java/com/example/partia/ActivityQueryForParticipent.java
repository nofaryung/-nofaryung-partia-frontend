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
        textViewPageCount = findViewById(R.id.textView_pageCountt);
        textViewQuery = findViewById(R.id.textView_Queryt);
        rg = findViewById(R.id.rgt);
        initQueryByPleenerDefinition();
        initRadioButtonList();
        btnNext= findViewById(R.id.button_nextt);
        answers = new ParticipantAnswers(userSessionEmail, currEvent.getPin_code());
    }

    private void initQueryByPleenerDefinition() {
    }

    private void initRadioButtonList() {
        radioButtonList.add(0,findViewById(R.id.radio_button_op1t));
        radioButtonList.add(1,findViewById(R.id.radio_button_op2t));
        radioButtonList.add(2,findViewById(R.id.radio_button_op3t));
        radioButtonList.add(3,findViewById(R.id.radio_button_op4t));
        radioButtonList.add(4,findViewById(R.id.radio_button_op5t));
        radioButtonList.add(5,findViewById(R.id.radio_button_op6t));
        radioButtonList.add(6,findViewById(R.id.radio_button_op7t));
        radioButtonList.add(7,findViewById(R.id.radio_button_op8t));
        radioButtonList.add(8,findViewById(R.id.radio_button_op9t));
        radioButtonVisibility(false , new int[]{1,2,3,4,5,6,7,8,9});

        changeQuery();

    }

    private void changeQuery() {
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
        rg.clearCheck();

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
        if (rg.getCheckedRadioButtonId() != -1) {
            changeQuery();
        }
    }

    private void collectAnswers(int question) {
        int selectedId = rg.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        String answer = radioButton.getText().toString();
        answer = answer.replace(" ", "_");
        if( question == 1 ){
            answers.mealPreference = answer;
        }
        if( question == 2 ){
            answers.allergies = answer;
        }
        if( question == 3 ){
            answer = answer + "_GLASS";
            answers.glassPreference = answer;
        }
        if( question == 4 ){
            answer = answer + "_CHASER";
            answers.chaserPreference = answer;
        }

    }

    private void radioButtonVisibility(boolean b, int[] ints) {
        int i;
        for( i = 0 ; i < ints.length ; i++ ) {
            radioButtonList.get(ints[i] - 1).setVisibility(b ? View.VISIBLE : View.GONE);
        }
    }
}
