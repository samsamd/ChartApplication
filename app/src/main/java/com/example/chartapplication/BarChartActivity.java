package com.example.chartapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity implements OnSeekBarChangeListener, OnChartValueSelectedListener {
    private BarChart chart;
    private TextView tv;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        setTitle("Populations zoo de la Flèche");

        chart = findViewById(R.id.barchart);
        chart.setOnChartValueSelectedListener(this);

        tv = findViewById(R.id.tv);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setProgress(5);

        chart.setDrawValueAboveBar(false);
        chart.getDescription().setEnabled(false);

        //Paramétrage de l'axe des abscisses
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//Affiche les valeurs de l'axe des abscisses en bas du graphique
        xAxis.setGranularity(1f);
        //Définitions des labels de l'axe des abscisses
        xAxis.setValueFormatter(new ValueFormatter() {
            private final String[] animaux = new String[]{"Éléphants", "Girafes", "Chouettes", "Lions", "Chimpanzé", "Tigres","Antilopes","Orang-outang","Manchots","Zèbres"};

            @Override
            public String getFormattedValue(float value) {
                return animaux[(int) value % animaux.length];
            }
        });

        //Paramétrage de l'axe des ordonnées
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);//Permet de commencer l'axe des ordonnées à 0
        leftAxis.setAxisMaximum(15f);
        chart.getAxisRight().setEnabled(false);//Empêche l'affichage de l'axe des ordonnées à droite du graphique


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tv.setText(String.valueOf(seekBar.getProgress()));

        ArrayList<BarEntry> animals = new ArrayList<>(); //Liste qui contient les donnés du graphique
        //Assignation de valeurs aléatoires aux données
        for (int i = 0; i< seekBar.getProgress(); i++) {
            int valueMale = (int) ((Math.random() * 6) +1);
            int valueFemale = (int) ((Math.random() * 6) +1);
            //On ajoute les données générées précédemment à la liste qui doit les contenir
            animals.add(new BarEntry(
                    i,
                    new float[]{valueMale,valueFemale} //permet la création de deux barres distinctes au sein d'une même barre
            ));
        }

        //Instanciation des barres du graphique
        BarDataSet set = new BarDataSet(animals, "Animaux du Zoo de la Fleche");
        set.setDrawIcons(false);
        set.setColors(getColors());
        set.setStackLabels(new String[]{"Mâle","Femelle"});

        //Création de la liste des données qui va permettre de restituer les barres du graphique visuellement
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);

        //Instanciation de l'ensemble des données qui seront restitués à l'écran
        BarData data = new BarData(dataSets);

        //Instanciation des données du graphique
        chart.setData(data);

        chart.setFitBars(true);
        chart.animateY(2000);
    }


    private int[] getColors() {

        // have as many colors as stack-values per entry
        int[] colors = new int[2];

        System.arraycopy(ColorTemplate.MATERIAL_COLORS, 0, colors, 0, 2);

        return colors;
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
