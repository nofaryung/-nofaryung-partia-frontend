package com.example.partia;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.partia.model.Event;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    APIInterface apiInterface;
    String userSessionEmail;
    Integer pin_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userSessionEmail = getIntent().getStringExtra("EXTRA_USER_SESSION_EMAIL");
        ///Create Event

//        Event event = new Event("MY awesome event",userSessionEmail,"MULTIPLE_INCHARGES","Park Hyarkon","The best event ever woohoo","OUTDOORS","BIRTHDAY","2020-09-17T21:00","MULTIPLE_INCHARGES");
//        Call<Event> call1 =  APIClient.getAPIInterface().createEvent(event);
//        call1.enqueue(new Callback<Event>() {
//            @Override
//            public void onResponse(Call<Event> call, Response<Event> response) {
//            ////    Event event1 = response.body();
//
//            //    Toast.makeText(getApplicationContext(), event1.name + " " + event1.location + " " + event1.info + " " + event1.environment+ " " + event1.kind_of_event+ " " + event1.date+ " " + event1.meal_organization+ " " + event1.beverage_organization, Toast.LENGTH_SHORT).show();
//                Log.d("hello","there");
//                pin_code = response.body().getPin_code();
//            }
//
//            @Override
//            public void onFailure(Call<Event> call, Throwable t) {
//                call.cancel();
//            }
//        });

        ///Get Event by pin_code

//        Call<Event> call2 = apiInterface.doGetEvent(pin_code);
//        call2.enqueue(new Callback<Event>() {
//            @Override
//            public void onResponse(Call<Event> call, Response<Event> response) {
//
//                Event event = response.body();
//                Toast.makeText(getApplicationContext(), event.name + " \n" + event.info + " \n" + event.date + " \n" + event.kind_of_event + " \n" + event.environment + " \n" + event.pin_code + " \n", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Event> call, Throwable t) {
//                call.cancel();
//            }
//        });



    }



    public void myEvent_btn_clicked(android.view.View view){
        Intent intent = new Intent(this, ActivityMyEvents.class);
        intent.putExtra("EXTRA_USER_SESSION_EMAIL", userSessionEmail);
        this.startActivity(intent);
    }

    public void createNewEvent_btn_clicked(android.view.View view){
        Intent intent = new Intent(this, ActivityCreateEvent.class);
        intent.putExtra("EXTRA_USER_SESSION_EMAIL", userSessionEmail);
        this.startActivity(intent);
    }

}
