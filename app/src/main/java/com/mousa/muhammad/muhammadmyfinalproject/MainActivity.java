package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnStartWork, btnEndWork, btnMonthSchedule, btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartWork = (Button) findViewById(R.id.btnStartWork);
        btnEndWork = (Button) findViewById(R.id.btnEndWork);
        btnMonthSchedule = (Button) findViewById(R.id.btnMonthSchedule);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);


        btnMonthSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null).setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();

            }
        });


        btnStartWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this,"Work Started",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                        intent.putExtra("MAIN_ACTIVITY","START_WORK");
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null).setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();

            }
        });



        btnEndWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this,"Work Ended",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
