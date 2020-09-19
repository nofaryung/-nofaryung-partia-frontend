package com.example.partia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
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
    String userSessionEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        userSessionEmail = getIntent().getStringExtra("EXTRA_USER_SESSION_EMAIL");
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextEventDesctiprion = findViewById(R.id.editTextEventDescription);
        editTextEventName = findViewById(R.id.editTextEventName);
    }

        //Places.initialize(getApplicationContext(), "AIzaSyCYd9DNtP8fAnic_H5XwgCef7dmqj_7vB0");
//        editTextAddress.setFocusable(false);
//        editTextAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
//                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(ActivityCreateEvent.this);
//                startActivityForResult(intent,100);
//            }
//        });
        //apiInterface = APIClient.getClient().create(APIInterface.class);
        //will send event with some of the values init to query for planner


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 100 && resultCode == RESULT_OK) {
//            Place place = Autocomplete.getPlaceFromIntent(data);
//            editTextAddress.setText(place.getAddress());
//        }
//        else if( resultCode == AutocompleteActivity.RESULT_ERROR) {
//            Status status = Autocomplete.getStatusFromIntent(data);
//            Toast.makeText(getApplicationContext(), status.getStatusMessage(),Toast.LENGTH_SHORT).show();
//        }
//     }

    public void startQueryForPlanner(android.view.View view){
        Event event = new Event();
        event.setInfo(editTextEventDesctiprion.getText().toString());
        event.setName(editTextEventName.getText().toString());
        event.setLocation(editTextAddress.getText().toString());
        event.setOwner(userSessionEmail);
        Parcelable parcelable = Parcels.wrap(event);
        Intent intent = new Intent(this, ActivityQueryForPlanner.class);
        intent.putExtra("EXTRA_USER_SESSION_EMAIL", userSessionEmail);
        intent.putExtra("EXTRA_EVENT",parcelable);
        this.startActivity(intent);
    }
}
