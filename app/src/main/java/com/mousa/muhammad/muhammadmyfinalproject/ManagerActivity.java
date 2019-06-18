package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ManagerActivity extends AppCompatActivity {

    private Button btnAddWorker,btnSchedule,btnMyWorkers,btnStart, btnEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        btnAddWorker = (Button) findViewById(R.id.btnAddWorker);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnSchedule = (Button) findViewById(R.id.btnSchedule);
        btnMyWorkers = (Button) findViewById(R.id.btnMyWorkers);
        btnEnd = (Button) findViewById(R.id.btnEnd);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ManagerActivity.this);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ManagerActivity.this,"Work Started",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ManagerActivity.this, ScheduleActivity.class);
                        intent.putExtra("MAIN_ACTIVITY","START_WORK");
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null).setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this, ScheduleActivity.class);
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
        btnAddWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this, AddWorkerActivity.class);
                startActivity(intent);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ManagerActivity.this);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(ManagerActivity.this,"Work Ended",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ManagerActivity.this, ScheduleActivity.class);
                        intent.putExtra("MAIN_ACTIVITY","END_WORK");
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null).setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }
    //Options to intent to all the activities- MyProflie, MyDetails,Coupon.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.MyProfile:
                Intent intent1=new Intent(this,MyProfileActivity.class);
                startActivity(intent1);
                return true;
            case R.id.Details:
                Intent intent2=new Intent(this,DetailsActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
