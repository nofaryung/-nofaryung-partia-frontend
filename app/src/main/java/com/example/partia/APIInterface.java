package com.example.partia;

import com.example.partia.model.Event;
import com.example.partia.model.LoginRequest;
import com.example.partia.model.LoginResponse;
import com.example.partia.model.UserEmailHolder;
import com.example.partia.model.UserEventstList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
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

    @POST("/login")
    Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);

    @POST("/signup")
    Call<LoginResponse> doSignUp(@Body LoginRequest loginRequest);

    @POST("/participant/events")
    Call<UserEventstList> doGetUserEvents(@Body UserEmailHolder userEmail);





}
