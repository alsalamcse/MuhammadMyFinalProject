package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfileActivity extends AppCompatActivity {

    private TextView tvFirstName, tvWorkerId, tvWorkerBirthday, tvLastName, tvStartedWork;
    private TextView tvFirstName2, tvWorkerId2, tvWorkerBirthday2, tvLastName2, tvStartedWork2;
    private Button btnDetails;
    private DatabaseReference databaseReference;
    FirebaseAuth auth;//to establish sign in sign up
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);
        tvWorkerId = (TextView) findViewById(R.id.tvWorkerId);
        tvWorkerBirthday = (TextView) findViewById(R.id.tvWorkerBirthday);
        tvStartedWork = (TextView) findViewById(R.id.tvStartedWork);

        tvFirstName2 = (TextView) findViewById(R.id.tvFirstName2);
        tvLastName2 = (TextView) findViewById(R.id.tvLastName2);
        tvWorkerId2 = (TextView) findViewById(R.id.tvWorkerId2);
        tvWorkerBirthday2 = (TextView) findViewById(R.id.tvWorkerBirthday2);
        tvStartedWork2 = (TextView) findViewById(R.id.tvStartedWork2);

        final String id = user.getUid();
        DatabaseReference fname = databaseReference.child("Users:").child(id);

        fname.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                tvFirstName2.setText( dataSnapshot.child("firstName").getValue(String.class));
                tvLastName2.setText(dataSnapshot.child("lastName").getValue(String.class));
                tvWorkerId2.setText(dataSnapshot.child("workerId").getValue(String.class));
                tvWorkerBirthday2.setText(dataSnapshot.child("birthday").getValue(String.class));
                tvStartedWork2.setText(dataSnapshot.child("dateStarted").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        tvFirstName2.setText( databaseReference.child("Users:").child(id).child("firstName").);
//        tvLastName2.setText( databaseReference.child("Users:").child(id).child("firstName").getParent().toString());

//        Toast.makeText(MyProfileActivity.this,  databaseReference.child("Users:").child(id).child("firstName").getKey(), Toast.LENGTH_SHORT).show();

        btnDetails=(Button)findViewById(R.id.btnDetails);

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

    }
}
