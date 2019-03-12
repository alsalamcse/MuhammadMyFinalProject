package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity {

    private Button btnAddWorker,btnSchedule,btnMyWorkers,btnMyProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

//        onBackPressed();

        btnAddWorker = (Button) findViewById(R.id.btnAddWorker);
        btnMyProfile = (Button) findViewById(R.id.btnMyProfile);
        btnSchedule = (Button) findViewById(R.id.btnSchedule);
        btnMyWorkers = (Button) findViewById(R.id.btnMyWorkers);

        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this, PortfolioActivity.class);
                startActivity(intent);
            }
        });

        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        btnAddWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this, PortfolioActivity.class);
                startActivity(intent);
            }
        });

        btnMyWorkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this, MyWorkersActivity.class);
                startActivity(intent);
            }
        });


    }
    ////function to the button Back in phone asked you if you sure to Exit
    public void onBackPressed() {
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(ManagerActivity.this);
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
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
