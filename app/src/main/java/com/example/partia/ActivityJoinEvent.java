package com.example.partia;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partia.model.Event;
import com.example.partia.model.LoginResponse;
import com.example.partia.model.UserEmailHolder;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.parceler.Parcels;

public final class ActivityJoinEvent extends AppCompatActivity {
    TextView textview_PINcode;
    Integer pinCode;
    String userSessionEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_event);
        textview_PINcode = findViewById(R.id.textview_PINcode);
        userSessionEmail = getIntent().getStringExtra("EXTRA_USER_SESSION_EMAIL");
    }


    public void join_event_btn_clicked(android.view.View view) throws InterruptedException {
        pinCode = Integer.parseInt(textview_PINcode.getText().toString());
        Call<Event> eventResponseCall = APIClient.getAPIInterface().doCheckValidPinCode(pinCode, userSessionEmail);
        TimeUnit.SECONDS.sleep(1);
        eventResponseCall.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if(response.code() == 200 ){
                    Parcelable parcelable = Parcels.wrap(response.body());
                    Intent intent = new Intent(ActivityJoinEvent.this, ActivityQueryForParticipent.class);
                    intent.putExtra("EXTRA_EVENT",parcelable);
                    intent.putExtra("EXTRA_USER_SESSION_EMAIL",userSessionEmail);
                    startActivity(intent);
                }
                if(response.code() == 400){
                    //invalid pin code
                    Toast.makeText(ActivityJoinEvent.this,"Invalid Pin Code!",Toast.LENGTH_LONG).show();
                }
                if(response.code() == 403) {
                    //user already exist
                    Toast.makeText(ActivityJoinEvent.this,"User Already Exist In Event!",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                call.cancel();
            }
        });

    }
}
