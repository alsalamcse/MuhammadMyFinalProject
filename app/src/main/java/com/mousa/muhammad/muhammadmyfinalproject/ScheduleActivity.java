package com.mousa.muhammad.muhammadmyfinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScheduleActivity extends AppCompatActivity {

    private TextView tvSchedule, tvMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        tvSchedule=(TextView)findViewById(R.id.tvSchedule);
        tvMonth=(TextView)findViewById(R.id.tvMonth);
    }
}
