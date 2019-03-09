package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private EditText edtTotalHours,edtPricetoHour,edtVactionDays2,edtWorkingDays2,edtMonthlySalary ;
    private TextView tvDetailsMonth,tvTotalHours,tvPricetoHour,tvVactionDays2,tvWorkingDays2,tvMonthlySalary;
    private Button btnMonthCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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


        btnMonthCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, CouponActivity.class);
                startActivity(intent);
            }
        });


    }
}
