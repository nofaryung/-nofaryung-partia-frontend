package com.example.partia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partia.model.Event;
import com.example.partia.model.allParticipants;

import org.parceler.Parcels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityExistEvent extends AppCompatActivity {
     Event currEvent;
     TextView textView_EventName;
     TextView textView_eventDescription;
     String userSessionEmail;
     Button btnStatistics;
     ListView listView_ParticipantsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exist_event);
        Parcelable parcelable = getIntent().getParcelableExtra("EXTRA_EVENT");
        currEvent = Parcels.unwrap(parcelable);
        userSessionEmail = getIntent().getStringExtra("EXTRA_USER_SESSION_EMAIL");
        textView_EventName = findViewById(R.id.textView_EventName);
        textView_eventDescription = findViewById(R.id.textView_eventDescription);
        listView_ParticipantsList = findViewById(R.id.listView_ParticipantsList);
        btnStatistics = findViewById(R.id.button_statistics);
        if(!userSessionEmail.equals(currEvent.getOwner())) {
            btnStatistics.setVisibility(View.GONE);
        }
        fetchInfo();

    }

    private void fetchInfo() {
        textView_EventName.setText(currEvent.getName());
        textView_eventDescription.setText(currEvent.getInfo());
        textView_eventDescription.setEnabled(false);
        fetchParticipants();
    }

    private void fetchParticipants() {
        Call<allParticipants> userEventsResponseCall = APIClient.getAPIInterface().doGetParticipants(currEvent.getPin_code());
        userEventsResponseCall.enqueue(new Callback<allParticipants>() {
            @Override
            public void onResponse(Call<allParticipants> call, Response<allParticipants> response) {
                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(ActivityExistEvent.this,android.R.layout.simple_list_item_1,response.body().getparticipants());
                listView_ParticipantsList.setAdapter(itemsAdapter);
            }

            @Override
            public void onFailure(Call<allParticipants> call, Throwable t) {

            }
        });
    }


    public void equipmentList_btn_clicked(View view) {
        Intent intent = new Intent(ActivityExistEvent.this, ActivityExistEvent.class);
        intent.putExtra("EXTRA_PINCODE",currEvent.getPin_code());
        intent.putExtra("EXTRA_USER_SESSION_EMAIL", userSessionEmail);
        startActivity(intent);
    }

    public void budget_btn_clicked(View view) {
        Parcelable parcelable = Parcels.wrap(currEvent);
        Intent intent = new Intent(ActivityExistEvent.this, ActivityBudget.class);
        intent.putExtra("EXTRA_EVENT",parcelable);
        intent.putExtra("EXTRA_USER_SESSION_EMAIL",userSessionEmail);
        startActivity(intent);
    }

    public void statistics_btn_clicked(View view) {
        Parcelable parcelable = Parcels.wrap(currEvent);
        Intent intent = new Intent(ActivityExistEvent.this, ActivityStatistics.class);
        intent.putExtra("EXTRA_EVENT",parcelable);
        intent.putExtra("EXTRA_USER_SESSION_EMAIL", userSessionEmail);
        startActivity(intent);
    }
}
