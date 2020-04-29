package com.example.partia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

import kotlinx.android.synthetic.main.activity_statistics.*
import kotlinx.android.synthetic.main.activity_statistics.view.*

class ActivityStatistics : AppCompatActivity() {
    private lateinit var chart : PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        chart = findViewById(R.id.pieChart)
        chart.setUsePercentValues(true)
        val arrayList = ArrayList<PieEntry>()
        arrayList.add(PieEntry(40f,"Vegan"))
        arrayList.add(PieEntry(35f,"Vegetarian"))
        arrayList.add(PieEntry(25f,"Kosher"))

        val pieDataSet = PieDataSet(arrayList,"Meal Type")
        //pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS)
        val pieData = PieData(pieDataSet)
        chart.data = pieData

    }
}
