package com.example.partia;

import com.example.partia.model.Event;
import com.example.partia.model.LoginRequest;
import com.example.partia.model.LoginResponse;
import com.example.partia.model.ParticipantAnswers;
import com.example.partia.model.UserEmailHolder;
import com.example.partia.model.UserEventstList;
import com.example.partia.model.allParticipants;

import java.util.ArrayList;
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

    @GET("/event?")
    Call<Event> doCheckValidPinCode(@Query("pin_code") Integer pin_code,@Query("userEmail") UserEmailHolder userEmail );

    @POST("/participant")
    Call<Event>  sendParticipantQueryAnswers(@Body ParticipantAnswers answers );

    @GET("/cashier/split?")
    Call<ArrayList<String>> doGetParticipantsBalance(@Query("pin_code") Integer pin_code);

    @GET("/cashier/owes?")
    Call<String>doGetParticipantBalance(@Query("pin_code") Integer pin_code, @Query("userEmail") UserEmailHolder userEmail);

    @GET("/event/participants?")
    Call<allParticipants> doGetParticipants(@Query("pin_code") Integer pin_code);

}
