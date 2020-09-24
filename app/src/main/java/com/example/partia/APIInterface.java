package com.example.partia;

import com.example.partia.model.DrinksStatistic;
import com.example.partia.model.EquipmentListItem;
import com.example.partia.model.Event;
import com.example.partia.model.FoodStatistic;
import com.example.partia.model.ItemAmount;
import com.example.partia.model.ItemDelete;
import com.example.partia.model.ItemInCharged;
import com.example.partia.model.ItemPrice;
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
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("/participant/check-unique?")
    Call<Event> doCheckValidPinCode(@Query("pin_code") Integer pin_code,@Query("userEmail") String userEmail );

    @POST("/participant")
    Call<Event>  sendParticipantQueryAnswers(@Body ParticipantAnswers answers );

    @GET("/cashier/split?")
    Call<ArrayList<String>> doGetParticipantsBalance(@Query("pin_code") Integer pin_code);

    @GET("/cashier/owes?")
       Call<String>doGetParticipantBalance(@Query("userEmail") String userEmail, @Query("pin_code") Integer pin_code);

    //Call<String>doGetParticipantBalance(@Query("pin_code") Integer pin_code);
    //   Call<String>doGetParticipantBalance(@Query("pin_code") Integer pin_code, @Query("userEmail") UserEmailHolder userEmail);

    @GET("/event/participants?")
    Call<allParticipants> doGetParticipants(@Query("pin_code") Integer pin_code);

    @GET("/equipment?")
    Call<String> getEquipmentList(@Query("pin_code") Integer pin_code,@Query("regenerate") boolean regenerate);

    @PUT("/equipment/Item/price")
    Call<String> PutItemPrice(@Body ItemPrice itemPrice);

    @PUT("/equipment/Item/amount")
    Call<String> PutItemAmount(@Body ItemAmount itemAmount);

    @PUT("/equipment/Item/in-charge")
    Call<String> PutItemInCharge(@Body ItemInCharged itemInCharged);

    @PUT("/equipment/Item/delete")
    Call<String> deleteItem(@Body ItemDelete itemDelete);

    @POST("/equipment/Item")
    Call<String> addItem(@Body ItemAmount itemAmount);

    @GET("/event/food_statistics?")
    Call<FoodStatistic> doGetFoodStatistics(@Query("pin_code") Integer pin_code);

    @GET("/event/beverage_statistics?")
    Call<DrinksStatistic> doGetDrinksStatistics(@Query("pin_code") Integer pin_code);

}
