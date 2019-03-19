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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    private EditText edtTotalHours,edtPricetoHour,edtVactionDays2,edtWorkingDays2,edtMonthlySalary ;
    private TextView tvDetailsMonth,tvTotalHours,tvPricetoHour,tvVactionDays2,tvWorkingDays2,tvMonthlySalary;
    private Spinner spnMonthD;

    private static String[] MONTHS1=new String[]
            {"Select Month","January","February","March","April","May","June","July",
                    "August","September","October","November","December" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtMonthlySalary=(EditText)findViewById(R.id .edtMonthlySalary) ;
        edtPricetoHour=(EditText)findViewById(R.id .edtPricetoHour) ;
        edtTotalHours=(EditText)findViewById(R.id .edtTotalHours) ;
        edtVactionDays2=(EditText)findViewById(R.id .edtVactionDays2) ;
        edtWorkingDays2=(EditText)findViewById(R.id .edtWorkingDays2) ;

        tvDetailsMonth=(TextView)findViewById(R.id.tvDetailsMonth);
        tvTotalHours=(TextView)findViewById(R.id.tvTotalHours);
        tvPricetoHour=(TextView)findViewById(R.id.tvPricetoHour);
        tvVactionDays2=(TextView)findViewById(R.id.tvVactionDays2);
        tvWorkingDays2=(TextView)findViewById(R.id.tvWorkingDays2);
        tvMonthlySalary=(TextView)findViewById(R.id.tvMonthlySalary);

        spnMonthD=(Spinner)findViewById(R.id.spnMonth);

    }


    //function to spinner show name month in Toast when you select the month from the spinner
    public void SpinnerMonth1() {
        SpinnerAdapter Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, MONTHS1);
        spnMonthD.setAdapter(Adapter);
//        spnMonthD.setSelection(0);
        spnMonthD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                String value = (String) spnMonthD.getItemAtPosition(position);
                Toast.makeText(DetailsActivity.this, "Month : " + value, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(ScheduleActivity.this,"Select Month",Toast.LENGTH_LONG).show();

            }
        });
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
