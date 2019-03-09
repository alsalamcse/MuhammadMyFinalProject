package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.mousa.muhammad.muhammadmyfinalproject.R.id.btnMyProfile;

public class ScheduleActivity extends AppCompatActivity {

    private TextView tvSchedule, tvMonth;
    private Button btnMyProfile, btnAddWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        tvSchedule = (TextView) findViewById(R.id.tvSchedule);
        tvMonth = (TextView) findViewById(R.id.tvMonth);

        btnAddWorker = (Button) findViewById(R.id.btnAddWorker);
        btnMyProfile = (Button) findViewById(R.id.btnMyProfile);

        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleActivity.this, PortfolioActivity.class);
                startActivity(intent);
            }
        });

    }
}
