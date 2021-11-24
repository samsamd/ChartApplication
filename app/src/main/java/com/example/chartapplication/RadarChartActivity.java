package com.example.chartapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

public class RadarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);
        RadarChart chart = findViewById(R.id.radarchart);

        setTitle("Skills Radar");

        chart.getDescription().setEnabled(false);


        XAxis xAxis = chart.getXAxis();

        //On définit les labels des différents axes du radar
        xAxis.setValueFormatter(new ValueFormatter() {
            private final String[] skills = new String[]{"Java", "C++", "Python", "Cobol", "SQL"};
            @Override
            public String getFormattedValue(float value) {
                return skills[(int) value % skills.length];
            }
        });

        YAxis yAxis = chart.getYAxis();
        yAxis.setLabelCount(5,true);
        yAxis.setDrawTopYLabelEntry(false);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(5);


        ArrayList<RadarEntry> entries1 = new ArrayList<>();
        ArrayList<RadarEntry> entries2 = new ArrayList<>();

        entries1.add(new RadarEntry(2));
        entries1.add(new RadarEntry(3));
        entries1.add(new RadarEntry(3));
        entries1.add(new RadarEntry(4));
        entries1.add(new RadarEntry(3));

        entries2.add(new RadarEntry(2));
        entries2.add(new RadarEntry(2));
        entries2.add(new RadarEntry(4));
        entries2.add(new RadarEntry(3));
        entries2.add(new RadarEntry(4));

        RadarDataSet set1 = new RadarDataSet(entries1, "Samuel skills");
        RadarDataSet set2 = new RadarDataSet(entries2, "Simon skills");

        set1.setColor(Color.rgb(250,100,100));
        set1.setFillColor(Color.rgb(250,100,100));
        set1.setDrawFilled(true);
        set1.setLineWidth(2f);
        set1.setFillAlpha(100);

        set2.setColor(Color.rgb(100,100,250));
        set2.setFillColor(Color.rgb(100,100,250));
        set2.setDrawFilled(true);
        set2.setLineWidth(2f);
        set2.setFillAlpha(100);

        ArrayList<IRadarDataSet> datas = new ArrayList<>();
        datas.add(set1);
        datas.add(set2);
        chart.animateXY(2000, 2000, Easing.EaseInOutQuad);
        RadarData data = new RadarData(datas);
        data.setDrawValues(false);

        chart.setData(data);

    }
}