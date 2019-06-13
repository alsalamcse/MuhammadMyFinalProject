package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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