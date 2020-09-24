package com.example.partia;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.gms.common.api.Status;
//import com.google.android.libraries.places.api.Places;
//import com.google.android.libraries.places.api.model.Place;
//import com.google.android.libraries.places.widget.Autocomplete;
//import com.google.android.libraries.places.widget.AutocompleteActivity;
//import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import com.example.partia.model.Event;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import javax.net.ssl.SSLEngineResult;


public class ActivityCreateEvent extends AppCompatActivity {
    private EditText editTextEventName;
    private EditText editTextAddress;
    private EditText editTextEventDesctiprion;
    private  EditText editTextDate;
    String userSessionEmail;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        userSessionEmail = getIntent().getStringExtra("EXTRA_USER_SESSION_EMAIL");
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextEventDesctiprion = findViewById(R.id.editTextEventDescription);
        editTextEventName = findViewById(R.id.editTextEventName);
        editTextDate = findViewById(R.id.editTextDate);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityCreateEvent.this,
                        R.style.Theme_AppCompat_Light_Dialog_MinWidth,
                        dateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                editTextDate.setText(date);
            }
        };
    }


    public void startQueryForPlanner(android.view.View view){
        Event event = new Event();
        event.setInfo(editTextEventDesctiprion.getText().toString());
        event.setName(editTextEventName.getText().toString());
        event.setLocation(editTextAddress.getText().toString());
        event.setDate(editTextDate.getText().toString());
        event.setOwner(userSessionEmail);
        Parcelable parcelable = Parcels.wrap(event);
        Intent intent = new Intent(this, ActivityQueryForPlanner.class);
        intent.putExtra("EXTRA_USER_SESSION_EMAIL", userSessionEmail);
        intent.putExtra("EXTRA_EVENT",parcelable);
        Toast.makeText(ActivityCreateEvent.this,"There are some question you need to answer so we can help you create your dream event ",Toast.LENGTH_LONG).show();
        this.startActivity(intent);
    }
}
