package com.mousa.muhammad.muhammadmyfinalproject;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.mousa.muhammad.muhammadmyfinalproject.R.id.btnMyProfile;
import static com.mousa.muhammad.muhammadmyfinalproject.R.id.col1row2;

public class ScheduleActivity extends AppCompatActivity {

    private TextView tvSchedule, tvMonth ,col1row1 , col2row1,col3row1;
//    private Button btnMyProfile, btnAddWorker;
    private Spinner spnMonth;
    private static String[] MONTHS=new String[]
            {"Select Month","January","February","March","April","May","June","July",
                    "August","September","October","November","December" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        col1row1 = (TextView) findViewById(R.id.col1row1);
        col2row1 = (TextView) findViewById(R.id.col2row1);
        col3row1 = (TextView) findViewById(R.id.col3row1);
        tvSchedule = (TextView) findViewById(R.id.tvSchedule);
        tvMonth = (TextView) findViewById(R.id.tvMonth);

       spnMonth=(Spinner)findViewById(R.id.spnMonth);

        col1row1.setText(getDate());
        col2row1.setText(getDateTime());

        SpinnerMonth();

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

//function to spinner show name month in Toast when you select the month from the spinner
    public void SpinnerMonth()
    {
        SpinnerAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, MONTHS);
        spnMonth.setAdapter(adapter);
        spnMonth.setSelection(0);
        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id)
            {
                String value = (String) spnMonth.getItemAtPosition(position);
                //Toast.makeText(ScheduleActivity.this, "Month : " + value , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(ScheduleActivity.this,"Select Month",Toast.LENGTH_LONG).show();

            }
        });
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

    //function to add time in Table

//    public void init(){
//        TableLayout ll = (TableLayout) findViewById(R.id.displayLinear);
//
//
//        for (int i = 0; i <2; i++) {
//
//            TableRow row= new TableRow(this);
//            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//            row.setLayoutParams(lp);
//            checkBox = new CheckBox(this);
//            tv = new TextView(this);
//            addBtn = new ImageButton(this);
//            addBtn.setImageResource(R.drawable.add);
//            minusBtn = new ImageButton(this);
//            minusBtn.setImageResource(R.drawable.minus);
//            qty = new TextView(this);
//            checkBox.setText("hello");
//            qty.setText("10");
//            row.addView(checkBox);
//            row.addView(minusBtn);
//            row.addView(qty);
//            row.addView(addBtn);
//            ll.addView(row,i);
//        }
//    }


}
