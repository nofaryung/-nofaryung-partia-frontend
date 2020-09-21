package com.example.partia;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class ActivityStatistics extends AppCompatActivity{
    private PieChart chart ;
    int screen = 1;
    private float[] percentage;
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        chart = findViewById(R.id.pieChart);
        chart.setRotationEnabled(false);
        chart.setHoleRadius(25);
        percentage = new float[]{25.3f,10.6f,32f};
        names =  new String[]{"or" , "nofar", "inbar"};
        showStatistics();
    }

    private void showStatistics() {
        if(screen == 1) {
            chart.setContentDescription("Food preference statistics");
            //addDataSet(getFoodStatistics());
            addDataSet(percentage,names,"food preference");
        }
        else if ( screen == 2 ) {
            chart.setContentDescription("Beverages preference statistics");
            addDataSet(percentage,names,"Beverages preference");
            // addDataSet(getBeveragesStatistics());
        }
    }

//    private Object getFoodStatistics() {
//
//    }
//
//    private Object getBeveragesStatistics() {
//
//    }

    private void addDataSet(float[] percent, String[] name, String label) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        ArrayList<String> entriesName = new ArrayList<>();

        for(int i = 0 ; i < percent.length ; i++){
            pieEntries.add(new PieEntry(percent[i],i));
        }

        for(int i = 0 ; i < name.length ; i++){
            entriesName.add(name[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries,label);
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

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
        chart.setData(pieData);
        chart.invalidate();
    }



}