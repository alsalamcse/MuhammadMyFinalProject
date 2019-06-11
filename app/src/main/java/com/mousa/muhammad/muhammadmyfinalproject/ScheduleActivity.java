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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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

public class ScheduleActivity extends AppCompatActivity{

    private TextView tvSchedule, tvMonth, col1row1, col2row1, col3row1, col4row1;
    private Spinner spnMonth;
    private TableLayout table;
    private TableRow row1;


    private TextView textView1,textView2,textView3,textView4;


    private static String[] MONTHS = new String[]
            {"Select Month", "January", "February", "March", "April", "May", "June", "July",
                    "August", "September", "October", "November", "December"};

    private static final String START_WORK = "START_WORK";
    private static final String END_WORK = "END_WORK";
    private static final String MONTH_SCHEDULE = "MONTH_SCHEDULE";

    FirebaseAuth auth;//to establish sign in sign up
    FirebaseUser user;//user
    private DatabaseReference databaseReference;
    public int monthNumber= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


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


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        SpinnerMonth();
        Intent intent = getIntent();
        String value  =  intent.getStringExtra("MAIN_ACTIVITY");
        intent.removeExtra("MAIN_ACTIVITY");
        if(value != null && value.equals(this.START_WORK)){

            //onClick(R.id.btnStartWork);
            updateStartEnd(this.START_WORK);
            //col1row1.setText(getDate());
        }else {
            if (value != null && value.equals(this.END_WORK)) {

                updateStartEnd(this.END_WORK);
                //col2row1.setText(getDateTime());
            }else{
                if(value != null && value.equals(this.MONTH_SCHEDULE)){

                    updateStartEnd(this.MONTH_SCHEDULE);
                }else{
                    updateStartEnd(this.MONTH_SCHEDULE);
                }
            }
        }
//        SpinnerMonth();
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
                Toast.makeText(ScheduleActivity.this, "Month : " + value , Toast.LENGTH_LONG).show();
                monthNumber=position;
                System.out.println("Spinner :  " +  monthNumber);
                showTable();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(ScheduleActivity.this,"Select Month",Toast.LENGTH_LONG).show();

            }
        });
    }


    public void updateStartEnd(String str) {

        if(str.equalsIgnoreCase(this.START_WORK)){

            String id = user.getUid();
            Date date = new Date();
            System.out.println("Date month : " +  date.getMonth());
            databaseReference.child("Users:").child(id).child("Months").child(""+(date.getMonth()+1)).child(getDate()).child("StartTime").setValue(getDateTime());
        }

        if(str.equalsIgnoreCase(this.END_WORK)){

            String id = user.getUid();
            Date date = new Date();
            System.out.println("Date month : " +  date.getMonth());
            System.out.println(" Date : " +  date );
            databaseReference.child("Users:").child(id).child("Months").child(""+(date.getMonth()+1)).child(getDate()).child("EndTime").setValue(getDateTime());
        }

        showTable();
    }


    public void showTable(){

        String id = user.getUid();
//        Date date = new Date();

//        table.removeView(row1);
//        table.removeViewAt(1);
        table.removeAllViews();

        System.out.println("Month Number : " + monthNumber);
        if(monthNumber==0){
            Toast.makeText(ScheduleActivity.this, "Month Error, Please choose month" , Toast.LENGTH_LONG).show();
            return;
        }
        DatabaseReference users = databaseReference.child("Users:").child(id).child("Months").child("" + monthNumber);
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot it : dataSnapshot.getChildren()) {
                    row1=new TableRow(ScheduleActivity.this);
                    col1row1=new TextView(ScheduleActivity.this);
                    col2row1=new TextView(ScheduleActivity.this);
                    col3row1=new TextView(ScheduleActivity.this);
                    col4row1=new TextView(ScheduleActivity.this);
                    System.out.println("Date under Month :  " + it.getKey());

                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                    Date startTime = null;
                    try {
                        if(dataSnapshot.child(it.getKey()).child("StartTime").getValue(String.class) != null){
                            startTime = format.parse(dataSnapshot.child(it.getKey()).child("StartTime").getValue(String.class));
                            System.out.println(" Start Time :  " +  format.format(startTime));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date endTime = null;
                    try {
                        if(dataSnapshot.child(it.getKey()).child("EndTime").getValue(String.class) !=null) {
                            endTime = format.parse(dataSnapshot.child(it.getKey()).child("EndTime").getValue(String.class));
                            System.out.println(" End Time :  " +  format.format(endTime));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    double res =0;
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

                    col1row1.setText(it.getKey());
                    if(startTime == null){
                        col2row1.setText("-");
                    }else {
                        col2row1.setText("" +  format.format(startTime));
                    }
                    if(endTime == null){
                        col3row1.setText("-");
                    }else{
                        col3row1.setText("" +  format.format(endTime));
                    }

                    col4row1.setText(""+res);

                    row1.addView(col1row1);
                    row1.addView(col2row1);
                    row1.addView(col3row1);
                    row1.addView(col4row1);

                    table.addView(row1);


                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        databaseReference.child("Users:").child(id).child("Months").child(""+(date.getMonth()+1));
//
//        databaseReference.child("Users:").child(id).child("Months").child(""+(date.getMonth()+1)).child(getDate()).child("EndTime").setValue(getDateTime());


    }


//    //function to add row in the table
//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()){
//            case R.id.textView1:
//                Toast.makeText(this,"Date",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.textView2:
//                Toast.makeText(this,"Start",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.textView3:
//                Toast.makeText(this,"End",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.textView4:
//                Toast.makeText(this,"Total Hours",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.btnStartWork:
//                row1=new TableRow(this);
//                col1row1=new TextView(this);
//                col2row1=new TextView(this);
//                col3row1=new TextView(this);
//                col4row1=new TextView(this);
//
//                col1row1.setText(getDate());
//                col2row1.setText(getDateTime());
//                col3row1.setText("16:00");
//                col4row1.setText("9:00");
//
//                row1.addView(col1row1);
//                row1.addView(col2row1);
//                row1.addView(col3row1);
//                row1.addView(col4row1);
//
//                table.addView(row1);
//
//                default:
//                    break;
//        }
//
//    }
}

    //function to the button Back in phone asked you if you sure to Exit

//
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
