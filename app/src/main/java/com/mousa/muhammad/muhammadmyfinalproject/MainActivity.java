package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
                Toast.makeText(MainActivity.this,"Month Schedule",Toast.LENGTH_LONG).show();
                intent.putExtra("MAIN_ACTIVITY","MONTH_SCHEDULE");
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
            case R.id.Coupon:
                Intent intent3=new Intent(this,CouponActivity.class);
                startActivity(intent3);
                return true;
                default:
                return super.onOptionsItemSelected(item);
        }
    }
}
