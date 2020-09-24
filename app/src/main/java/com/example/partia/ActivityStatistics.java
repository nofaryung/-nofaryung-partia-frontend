package com.example.partia;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.partia.model.DrinksStatistic;
import com.example.partia.model.Event;
import com.example.partia.model.FoodStatistic;
import com.example.partia.model.UserEventstList;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityStatistics extends AppCompatActivity{
    private PieChart chart ;
    int screen = 1;
    private float[] percentageFood;
    private float[] percentageFood2;
    private float[] percentageDrinks;
    private String[] foodNames;
    Event currEvent;
    String userSessionEmail;
    Button next_btn;
    Button gotoEquipList_btn;
    private String[] drinksNames;
    int KOSHER = 0;
    int VEGAN = 1;
    int VEGERARIAN = 2;

    int ARAK_CHASER = 0;
    int ARAK_GLASS = 1;
    int BEER_GLASS = 2;
    int CAMPARI_CHASER = 3;
    int CAMPARI_GLASS = 4;
    int GIN_CHASER = 5;
    int GIN_GLASS = 6;
    int RED_WINE_GLASS = 7;
    int TEQUILA_CHASER = 8;
    int TEQUILA_GLASS = 9;
    int VODKA_CHASER = 10;
    int VODKA_GLASS = 11;
    int WHISKEY_CHASER = 12;
    int WHISKEY_GLASS = 13;
    int WHITE_WINE_GLASS = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        chart = findViewById(R.id.pieChart);
        next_btn = findViewById(R.id.btn_NextStatistic);
        gotoEquipList_btn = findViewById(R.id.btn_goToEquipList);
        chart.setRotationEnabled(false);
        chart.setHoleRadius(25);
        Parcelable parcelable = getIntent().getParcelableExtra("EXTRA_EVENT");
        currEvent = Parcels.unwrap(parcelable);
        userSessionEmail = getIntent().getStringExtra("EXTRA_USER_SESSION_EMAIL");
        percentageFood = new float[3];
        percentageDrinks = new float[15];
        drinksNames =  new String[]{"Arak_chaser" , "Arak_glass", "Beer","Campari chaser","Campari glass","Gin chaser","Gin glass",
                "Red whine","Tequila chaser","Tequila glass","Vodka chaser","Vodka glass",
                "Whiskey chaser","Whiskey glass","WHITE_WINE_GLASS"};
        foodNames =  new String[]{"Kosher" , "Vegan", "Vegetarian"};
        getServerFoodStatistics();
        getServerDrinksStatistics();
    }

    private void getServerDrinksStatistics() {
//        Call<DrinksStatistic> drinksStatisticCallResponseCall = APIClient.getAPIInterface().doGetDrinksStatistics(currEvent.pin_code);
        Call<DrinksStatistic> drinksStatisticCallResponseCall = APIClient.getAPIInterface().doGetDrinksStatistics(currEvent.getPin_code());
        drinksStatisticCallResponseCall.enqueue(new Callback<DrinksStatistic>() {
            @Override
            public void onResponse(Call<DrinksStatistic> call, Response<DrinksStatistic> response) {
                if(response.code() == 200){
                    percentageDrinks[ARAK_CHASER] = response.body().arak_chaser;
                    percentageDrinks[ARAK_GLASS] = response.body().arak_glass;
                    percentageDrinks[BEER_GLASS] = response.body().beer;
                    percentageDrinks[CAMPARI_CHASER] = response.body().campari_chaser;
                    percentageDrinks[CAMPARI_GLASS] = response.body().campari_glass;
                    percentageDrinks[GIN_CHASER] = response.body().gin_chaser;
                    percentageDrinks[GIN_GLASS] = response.body().gin_glass;
                    percentageDrinks[RED_WINE_GLASS] = response.body().red_whine;
                    percentageDrinks[TEQUILA_CHASER] = response.body().tequila_chaser;
                    percentageDrinks[TEQUILA_GLASS] = response.body().tequila_glass;
                    percentageDrinks[VODKA_CHASER] = response.body().vodka_chaser;
                    percentageDrinks[VODKA_GLASS] = response.body().vodka_glass;
                    percentageDrinks[WHISKEY_CHASER] = response.body().whiskey_chaser;
                    percentageDrinks[WHISKEY_GLASS] = response.body().whiskey_glass;
                    percentageDrinks[WHITE_WINE_GLASS] = response.body().white_wine;
                }
            }

            @Override
            public void onFailure(Call<DrinksStatistic> call, Throwable t) {

            }
        });
    }

    private void getServerFoodStatistics() {
 //       Call<FoodStatistic> foodResponseCall = APIClient.getAPIInterface().doGetFoodStatistics(currEvent.pin_code);
        Call<FoodStatistic> foodResponseCall = APIClient.getAPIInterface().doGetFoodStatistics(currEvent.getPin_code());
        foodResponseCall.enqueue(new Callback<FoodStatistic>() {
            @Override
            public void onResponse(Call<FoodStatistic> call, Response<FoodStatistic> response) {
                if(response.code() == 200){
                    percentageFood[KOSHER] = response.body().kosher;
                    percentageFood[VEGAN] = response.body().vegan;
                    percentageFood[VEGERARIAN] = response.body().vegetarian;
                    showStatistics();
                }
            }

            @Override
            public void onFailure(Call<FoodStatistic> call, Throwable t) {

            }
        });

    }


    private void showStatistics() {
        if(screen == 1) {
            chart.setContentDescription("Food preference statistics");
            addDataSet(percentageFood, foodNames,"food preference");
           // addDataSet(percentageFood2, foodNames,"food preference");
        }
        else if ( screen == 2 ) {
            chart.setContentDescription("Beverages preference statistics");
            addDataSet(percentageDrinks, drinksNames,"Beverages preference");
        }
        screen++;
    }



    private void addDataSet(float[] percent, String[] name, String label) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        //ArrayList<String> entriesName = new ArrayList<>();

        for(int i = 0 ; i < percent.length ; i++){
            pieEntries.add(new PieEntry(percent[i],name[i]));
        }

//        for(int i = 0 ; i < name.length ; i++){
//            entriesName.add(name[i]);
//        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries,label);
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);
        //pieDataSet.setDrawValues(false);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.GRAY);
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);
        colors.add(Color.BLACK);
        colors.add(Color.DKGRAY);

        pieDataSet.setColors(colors);

        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);

        PieData pieData = new PieData(pieDataSet);
        chart.getDescription().setEnabled(false);
        chart.setData(pieData);
        chart.setDrawSliceText(false);
        chart.invalidate();
    }

    public void next_statistic_btn_clicked(View view) {
        if(screen == 2){
            showStatistics();
            next_btn.setText("Re-Generate equipment list");
            gotoEquipList_btn.setVisibility(View.VISIBLE);
        }
        else {
            //regenrate eqip list
            Call<String> response= APIClient.getAPIInterface().getEquipmentList(currEvent.pin_code,false);
            response.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(ActivityStatistics.this,"Equipment list regenerated SUCCESSFULLY",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
    }

    public void goToEqipList_btn_clicked(View view) {
        Intent intent = new Intent(ActivityStatistics.this, ActivityEquipmentList.class);
        Parcelable parcelable = Parcels.wrap(currEvent);
        intent.putExtra("EXTRA_EVENT",parcelable);
        intent.putExtra("EXTRA_USER_SESSION_EMAIL", userSessionEmail);
        startActivity(intent);
    }
}