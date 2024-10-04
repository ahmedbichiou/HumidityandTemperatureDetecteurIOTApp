package com.example.iot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    GraphView graph,graphT;


    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> seriesT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
       graph  = (GraphView) findViewById(R.id.graph);
        graphT = (GraphView) findViewById(R.id.graphT);

        graph.setVisibility(View.VISIBLE);
        graphT.setVisibility(View.VISIBLE);

        try {
            series = new LineGraphSeries<>(new DataPoint[] {
            });
            graph.addSeries(series);
        } catch (IllegalArgumentException f) {
            Toast.makeText(MainActivity.this, f.getMessage(), Toast.LENGTH_LONG).show();
        }
        try {
            seriesT = new LineGraphSeries<>(new DataPoint[] {
            });
            graphT.addSeries(seriesT);
        } catch (IllegalArgumentException f) {
            Toast.makeText(MainActivity.this, f.getMessage(), Toast.LENGTH_LONG).show();
        }

    }




    public void show(View v) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Sensors").document("data");

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.i("info", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.i("info", "message="+snapshot.getData());
                    String readable=snapshot.getData().toString();

//-------------------------------------------------substring
                    int virgule =readable.indexOf(',');
                    int acolade=readable.indexOf('}');
                    String temperatureALLSTRING=readable.substring(1,virgule);
                    String humidityALLSTRING=readable.substring(virgule+1,acolade);

                    //substing for temperature
                    int egalTEMP =temperatureALLSTRING.indexOf('=');
                    int pointTEMP=temperatureALLSTRING.indexOf('.');
                    String temperaturevalueSTR=temperatureALLSTRING.substring(egalTEMP+1,pointTEMP);
                    //substring for humidity
                    int egalHUM =humidityALLSTRING.indexOf('=');
                    int pointHUM=humidityALLSTRING.indexOf('.');
                    String humidityvalueSTR=humidityALLSTRING.substring(egalHUM+1,pointHUM);

                    int humidityvalue=Integer.valueOf(humidityvalueSTR);
                    int temperaturevalue=Integer.valueOf(temperaturevalueSTR);
//-------------------------------------------------substring


//----------------------------------------------retrive time BLOCK
                    SimpleDateFormat sdf = new SimpleDateFormat("HH.mmss");
                    Date now = new Date();
                    String strDate = sdf.format(now);

//----------------------------------------------retrive time BLOCK
                    series.appendData(new DataPoint(Double.valueOf(strDate),humidityvalue), false, 40); //add data to humidity
                    seriesT.appendData(new DataPoint(Double.valueOf(strDate),temperaturevalue), false, 40); //add data to temp

                } else {
                    Log.i("info", "Current data: null");
                }
            }
        });


        // [END listen_document]
    }








    }



