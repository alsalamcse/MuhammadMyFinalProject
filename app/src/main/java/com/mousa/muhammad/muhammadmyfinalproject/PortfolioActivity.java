package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class PortfolioActivity extends AppCompatActivity {

    private EditText edtWorkerName,edtWorkerId,edtWorkerBirthday,edtVactionDays,edtWorkingDays ;
    private TextView tvWorkerPortfolio,tvWorkerName,tvWorkerId,tvWorkerBirthday,tvVactionDays,tvWorkingDays;
    private ImageView ivWorker;
    private Button btnDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_portfolio);

        edtWorkerName=(EditText)findViewById(R.id .edtWorkerName) ;
        edtWorkerId=(EditText)findViewById(R.id .edtWorkerId) ;
        edtWorkerBirthday=(EditText)findViewById(R.id .edtWorkerBirthday) ;
        edtVactionDays=(EditText)findViewById(R.id .edtVactionDays) ;
        edtWorkingDays=(EditText)findViewById(R.id .edtWorkingDays) ;

        tvWorkerPortfolio=(TextView)findViewById(R.id.tvWorkerPortfolio);
        tvWorkerName=(TextView)findViewById(R.id.tvWorkerName);
        tvWorkerId=(TextView)findViewById(R.id.tvWorkerId);
        tvVactionDays=(TextView)findViewById(R.id.tvVactionDays);
        tvWorkingDays=(TextView)findViewById(R.id.tvWorkingDays);
        tvWorkerBirthday=(TextView)findViewById(R.id.tvWorkerBirthday);

        ivWorker=(ImageView)findViewById(R.id.ivWorker);

        btnDetails=(Button)findViewById(R.id.btnDetails);

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PortfolioActivity.this,DetailsActivity .class);
                startActivity(intent);
            }
        });






    }
}
