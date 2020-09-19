package com.example.partia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partia.model.Event;

import org.parceler.Parcels;

public class ActivityExistEvent extends AppCompatActivity {
     Event currEvent;
     TextView textView_EventName;
     TextView textView_eventDescription;
     String userSessionEmail;
     Button btnStatistics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exist_event);
        Parcelable parcelable = getIntent().getParcelableExtra("EXTRA_EVENT");
        currEvent = Parcels.unwrap(parcelable);
        userSessionEmail = getIntent().getStringExtra("EXTRA_USER_SESSION_EMAIL");
        textView_EventName = findViewById(R.id.textView_EventName);
        textView_eventDescription = findViewById(R.id.textView_eventDescription);
        btnStatistics = findViewById(R.id.button_statistics);
        if(userSessionEmail != currEvent.getOwner()) {
            btnStatistics.setVisibility(View.GONE);
        }
        fetchInfo();

    }

    private void fetchInfo() {
        textView_EventName.setText(currEvent.getName());
        textView_eventDescription.setText(currEvent.getInfo());
        textView_eventDescription.setEnabled(false);
    }


    public void equipmentList_btn_clicked(View view) {
        Intent intent = new Intent(ActivityExistEvent.this, ActivityExistEvent.class);
        intent.putExtra("EXTRA_PINCODE",currEvent.getPin_code());
        intent.putExtra("EXTRA_USER_SESSION_EMAIL", userSessionEmail);
        startActivity(intent);
    }

    public void budget_btn_clicked(View view) {
        Intent intent = new Intent(ActivityExistEvent.this, ActivityBudget.class);
        intent.putExtra("EXTRA_PINCODE",currEvent.getPin_code());
        intent.putExtra("EXTRA_USER_SESSION_EMAIL", userSessionEmail);
        startActivity(intent);
    }

    public void statistics_btn_clicked(View view) {
        Intent intent = new Intent(ActivityExistEvent.this, ActivityStatistics.class);
        intent.putExtra("EXTRA_PINCODE",currEvent.getPin_code());
        intent.putExtra("EXTRA_USER_SESSION_EMAIL", userSessionEmail);
        startActivity(intent);
    }
}
