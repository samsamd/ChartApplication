package com.example.chartapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    private RadarChart chart;
    private String themeChart;
    Button btnDevelopper;
    Button btnCooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);
        chart = findViewById(R.id.radarchart);

        themeChart = "Developper";

        btnDevelopper = findViewById(R.id.btnDevelopper);
        btnDevelopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeChart = "Developper";
                setData(themeChart);
                createChart();
                chart.animateXY(2000, 2000, Easing.EaseInOutQuad);

            }
        });

        btnCooking = findViewById(R.id.btnCooking);
        btnCooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeChart = "Cooking";
                setData(themeChart);
                createChart();
                chart.animateXY(2000, 2000, Easing.EaseInOutQuad);

            }
        });


    }

    /**
     * Méthode permettant de définir les données du graphique
     * @param themeChart
     */
    private void setData(String themeChart) {
        setTitle("Skills Radar");

        //On définit les labels des différents axes du radar


        ArrayList<RadarEntry> entries1 = new ArrayList<>();
        ArrayList<RadarEntry> entries2 = new ArrayList<>();
        if(themeChart.equals("Developper")) {
            entries1.add(new RadarEntry(2));
            entries1.add(new RadarEntry(3));
            entries1.add(new RadarEntry(4));
            entries1.add(new RadarEntry(3));

            entries2.add(new RadarEntry(2));
            entries2.add(new RadarEntry(2));
            entries2.add(new RadarEntry(4));
            entries2.add(new RadarEntry(3));
            entries2.add(new RadarEntry(4));
        } else {
            entries1.add(new RadarEntry(2));
            entries1.add(new RadarEntry(4));
            entries1.add(new RadarEntry(3));
            entries1.add(new RadarEntry(2));


            entries2.add(new RadarEntry(4));
            entries2.add(new RadarEntry(1));
            entries2.add(new RadarEntry(3));
            entries2.add(new RadarEntry(3));

        }
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

        RadarData data = new RadarData(datas);
        data.setDrawValues(false);

        chart.setData(data);
    }

    /**
     * Méthode permettant de définir les paramètres générals du graphique
     */
    private void createChart() {
        chart.getDescription().setEnabled(false);
        XAxis xAxis = chart.getXAxis();
        if(themeChart.equals("Developper")) {
            xAxis.setValueFormatter(new ValueFormatter() {
                private final String[] skills = new String[]{"Java", "C++", "Python", "Cobol", "SQL"};

                @Override
                public String getFormattedValue(float value) {
                    return skills[(int) value % skills.length];
                }
            });
        } else {
            xAxis.setValueFormatter(new ValueFormatter() {
                private final String[] skills = new String[]{"Tartes", "Crepes", "Macarons", "Gateaux"};

                @Override
                public String getFormattedValue(float value) {
                    return skills[(int) value % skills.length];
                }
            });
        }

        YAxis yAxis = chart.getYAxis();
        yAxis.setLabelCount(5,true);
        yAxis.setDrawTopYLabelEntry(false);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(5);
    }
}