package com.mousa.muhammad.muhammadmyfinalproject;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.mousa.muhammad.muhammadmyfinalproject.R.id.btnMyProfile;
import static com.mousa.muhammad.muhammadmyfinalproject.R.id.col1row2;

public class ScheduleActivity extends AppCompatActivity {

    private TextView tvSchedule, tvMonth ,col1row1 , col2row1,col3row1;
    private Button btnMyProfile, btnAddWorker;
    private Spinner spnMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        col1row1 = (TextView) findViewById(R.id.col1row1);
        col2row1 = (TextView) findViewById(R.id.col2row1);
        col3row1 = (TextView) findViewById(R.id.col3row1);
        tvSchedule = (TextView) findViewById(R.id.tvSchedule);
        tvMonth = (TextView) findViewById(R.id.tvMonth);

        btnAddWorker = (Button) findViewById(R.id.btnAddWorker);
        btnMyProfile = (Button) findViewById(R.id.btnMyProfile);

//        spnMonth=(Spinner)findViewById(R.id.spnMonth);



        col1row1.setText(getDate());
        col2row1.setText(getDateTime());


        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleActivity.this, PortfolioActivity.class);
                startActivity(intent);
            }
        });

    }
    //Following function getDateTime() returns datetime.
    private String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    ////function to the button Back in phone asked you if you sure to Exit
    public void onBackPressed(){
        final AlertDialog.Builder builder= new AlertDialog.Builder(ScheduleActivity.this);
        builder.setMessage("Really Exit?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                builder.setCancelable(false);
            }
        });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }
}
