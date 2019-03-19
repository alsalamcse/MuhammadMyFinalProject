package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    private EditText edtTotalHours,edtPricetoHour,edtVactionDays2,edtWorkingDays2,edtMonthlySalary ;
    private TextView tvDetailsMonth,tvTotalHours,tvPricetoHour,tvVactionDays2,tvWorkingDays2,tvMonthlySalary;
    private Button btnMonthCoupon;
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

        btnMonthCoupon=(Button)findViewById(R.id.btnMonthCoupon);

        spnMonthD=(Spinner)findViewById(R.id.spnMonth);


        btnMonthCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, CouponActivity.class);
                startActivity(intent);
            }
        });

        //SpinnerMonth1();


    }


    //function to spinner show name month in Toast when you select the month from the spinner
    public void SpinnerMonth1()
    {
        SpinnerAdapter Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, MONTHS1);
        spnMonthD.setAdapter(Adapter);
//        spnMonthD.setSelection(0);
        spnMonthD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id)
            {
                String value = (String) spnMonthD.getItemAtPosition(position);
                Toast.makeText(DetailsActivity.this, "Month : " + value , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(ScheduleActivity.this,"Select Month",Toast.LENGTH_LONG).show();

            }
        });
    }
}
