package com.mousa.muhammad.muhammadmyfinalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CouponActivity extends AppCompatActivity {

    private TextView tvMonthCoupon;
    private ImageView ivMonthCoupon;
    private Button btnDownloadCoupon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvMonthCoupon=(TextView) findViewById(R.id .tvMonthCoupon) ;
        btnDownloadCoupon=(Button)findViewById(R.id.btnDownloadCoupon);
        ivMonthCoupon=(ImageView)findViewById(R.id.ivMonthCoupon);



    }
}
