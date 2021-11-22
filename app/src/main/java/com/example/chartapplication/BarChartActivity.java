package com.example.chartapplication;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart chart = findViewById(R.id.barchart);

        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        ArrayList<BarEntry> datas = new ArrayList<>();


        //Ajout des des données pour le graphe en baton
        datas.add(new BarEntry(1, 945f));
        datas.add(new BarEntry(2, 1040f));
        datas.add(new BarEntry(3, 1133f));
        datas.add(new BarEntry(4, 1240f));
        datas.add(new BarEntry(5, 1369f));
        datas.add(new BarEntry(6, 1487f));
        datas.add(new BarEntry(7, 1501f));
        datas.add(new BarEntry(8, 1645f));
        datas.add(new BarEntry(9, 1578f));
        datas.add(new BarEntry(10, 1695f));

        BarDataSet bardataset = new BarDataSet(datas, "datas");
        chart.animateY(2000); 

        BarData data = new BarData(bardataset);
        data.setBarWidth(1f);
        bardataset.setColors(ColorTemplate.JOYFUL_COLORS);
        chart.setData(data);
    }
}
