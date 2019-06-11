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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvDetailsMonth,tvTotalHours,tvPricetoHour,tvWorkingDays2,tvMonthlySalary
                        ,tvTotalHours2,tvPricetoHour2,tvMonthlySalary2,tvWorkingDays3,tvMonth;
    private Spinner spnMonth;

    private static String[] MONTHS1=new String[]
            {"Select Month","January","February","March","April","May","June","July",
                    "August","September","October","November","December" };

    FirebaseAuth auth;//to establish sign in sign up
    FirebaseUser user;//user
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        tvDetailsMonth=(TextView)findViewById(R.id.tvDetailsMonth);
        tvTotalHours=(TextView)findViewById(R.id.tvTotalHours);
        tvPricetoHour=(TextView)findViewById(R.id.tvPricetoHour);
        tvWorkingDays2=(TextView)findViewById(R.id.tvWorkingDays2);
        tvMonthlySalary=(TextView)findViewById(R.id.tvMonthlySalary);
        tvMonth=(TextView)findViewById(R.id.tvMonth);

        tvMonthlySalary2=(TextView)findViewById(R.id.tvMonthlySalary2);
        tvTotalHours2=(TextView)findViewById(R.id.tvTotalHours2);
        tvPricetoHour2=(TextView)findViewById(R.id.tvPricetoHour2);
        tvWorkingDays3=(TextView)findViewById(R.id.tvWorkingDays3);


        spnMonth=(Spinner)findViewById(R.id.spnMonth);

        showWorkerDetails();
        SpinnerMonth1();

    }

    public void showWorkerDetails(){
        final String id = user.getUid();

        DatabaseReference data = databaseReference.child("Users:").child(id);

        data.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                double price = Double.parseDouble(dataSnapshot.child("HourPrice").getValue().toString().trim());
                Date date = new Date();
                dataSnapshot =  dataSnapshot.child("Months").child(""+(date.getMonth()+1));
                double totalHours=0 , totalDays=0;
                for (DataSnapshot it : dataSnapshot.getChildren()) {
                    System.out.println("Day :  " + it.getKey());

                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                    Date startTime = null;
                    try {
                        if(it.child("StartTime").getValue(String.class) != null){
                            startTime = format.parse(it.child("StartTime").getValue(String.class));
                            System.out.println(" Start Time :  " +  format.format(startTime));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date endTime = null;
                    try {
                        if(it.child("EndTime").getValue(String.class) !=null) {
                            endTime = format.parse(it.child("EndTime").getValue(String.class));
                            System.out.println(" End Time :  " +  format.format(endTime));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    double res = 0;
                    if(startTime != null && endTime != null) {
                        double difference = (endTime.getTime() - startTime.getTime()) / 1000.0 / 60.0 / 60.0;
                        long roundDiff = (long) (difference * 100.0);
                        res = roundDiff / 100 + (roundDiff % 100) / 100.0;
                        System.out.println("difference : " + difference);
                        System.out.println("roundDiff : " + roundDiff);
                        System.out.println(" roundDiff/100 : " + roundDiff / 100);
                        System.out.println("(roundDiff%100)/100.0 : " + (roundDiff % 100) / 100.0);
                        System.out.println("Time difference : " + res);
                    }
                    totalHours+=res;
                    totalDays++;

                }

                System.out.println("Hour Price = " + price);
                long roundPrice = (long)(totalHours * price * 100.0);
                tvMonthlySalary2.setText( "  " + roundPrice/100.0);
                tvTotalHours2.setText("  " + totalHours);
                tvPricetoHour2.setText("  " + price);
                tvWorkingDays3.setText("  " + totalDays);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    //function to spinner show name month in Toast when you select the month from the spinner
    public void SpinnerMonth1() {
        SpinnerAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, MONTHS1);
        spnMonth.setAdapter(adapter);
        spnMonth.setSelection(0);
        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
              //  String value = (String) spnMonthD.getItemAtPosition(position);
             //   Toast.makeText(DetailsActivity.this, "Month : " + value, Toast.LENGTH_LONG).show();
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
