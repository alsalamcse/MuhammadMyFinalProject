package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSchedule, tvMonth, col1row1, col2row1, col3row1, col4row1;
    private Spinner spnMonth;
    private TableLayout table;
    private TableRow row1;

    private TextView textView1,textView2,textView3,textView4;
    private Button btngo;

    private static String[] MONTHS = new String[]
            {"Select Month", "January", "February", "March", "April", "May", "June", "July",
                    "August", "September", "October", "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        btngo=(Button)findViewById(R.id.btngo);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);

        col1row1 = (TextView) findViewById(R.id.col1row1);
        col2row1 = (TextView) findViewById(R.id.col2row1);
        col3row1 = (TextView) findViewById(R.id.col3row1);
        col4row1 = (TextView)findViewById(R.id.col4row1);

        tvSchedule = (TextView) findViewById(R.id.tvSchedule);
        tvMonth = (TextView) findViewById(R.id.tvMonth);

        table=(TableLayout)findViewById(R.id.table);
        table.setColumnStretchable(0,true);
        table.setColumnStretchable(1,true);
        table.setColumnStretchable(2,true);
        table.setColumnStretchable(3,true);
        table.setColumnStretchable(4,true);

        spnMonth = (Spinner) findViewById(R.id.spnMonth);

        Intent intent = getIntent();
        String value  =  intent.getStringExtra("MAIN_ACTIVITY");
        if(value != null && value.equals("START_WORK")){
            col1row1.setText(getDate());
        }
        if(value != null && value.equals("END_WORK")){
            col2row1.setText(getDateTime());
        }
        SpinnerMonth();
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

    //Following function getDate() returns date.
    private String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    //Following function getDateTime() returns datetime.
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    //function to spinner show name month in Toast when you select the month from the spinner
    public void SpinnerMonth() {
        SpinnerAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, MONTHS);
        spnMonth.setAdapter(adapter);
        spnMonth.setSelection(0);
        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                String value = (String) spnMonth.getItemAtPosition(position);
                //Toast.makeText(ScheduleActivity.this, "Month : " + value , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(ScheduleActivity.this,"Select Month",Toast.LENGTH_LONG).show();

            }
        });
    }

    //function to add row in the table
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.textView1:
                Toast.makeText(this,"Date",Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView2:
                Toast.makeText(this,"Start",Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView3:
                Toast.makeText(this,"End",Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView4:
                Toast.makeText(this,"Total Hours",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btngo:
                row1=new TableRow(this);
                col1row1=new TextView(this);
                col2row1=new TextView(this);
                col3row1=new TextView(this);
                col4row1=new TextView(this);

                col1row1.setText(getDate());
                col2row1.setText(getDateTime());
                col3row1.setText("16:00");
                col4row1.setText("9:00");

                row1.addView(col1row1);
                row1.addView(col2row1);
                row1.addView(col3row1);
                row1.addView(col4row1);

                table.addView(row1);

                default:
                    break;
        }

    }
}

//    ////function to the button Back in phone asked you if you sure to Exit
//    public void onBackPressed(){
//        final AlertDialog.Builder builder= new AlertDialog.Builder(ScheduleActivity.this);
//        builder.setMessage("Really Exit?");
//        builder.setCancelable(true);
//        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                finish();
//            }
//        });
//        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                builder.setCancelable(false);
//            }
//        });
//        AlertDialog alertDialog= builder.create();
//        alertDialog.show();
//    }
