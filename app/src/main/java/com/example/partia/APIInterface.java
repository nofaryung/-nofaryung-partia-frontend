package com.example.partia;

import com.example.partia.model.Event;
import com.example.partia.model.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    //34.242.211.28:5000/event
    @POST("/event")
    Call<Event> createEvent(@Body Event event);

    @GET("/event?")
    Call<Event> doGetEvent(@Query("pin_code") Integer pin_code);

    @GET("/login?")
    Call<LoginResponse> doLogin(@Query("email","password") Integer pin_code);


}
