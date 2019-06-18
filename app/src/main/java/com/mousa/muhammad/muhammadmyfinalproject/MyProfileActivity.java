package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MyProfileActivity extends AppCompatActivity {
    private TextView tvFirstName, tvWorkerId, tvWorkerBirthday, tvLastName, tvStartedWork;
    private TextView tvFirstName2, tvWorkerId2, tvWorkerBirthday2, tvLastName2, tvStartedWork2;
    private ImageView ivWorkerPhoto;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
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
        storageReference = FirebaseStorage.getInstance().getReference();

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
        ivWorkerPhoto = (ImageView)findViewById(R.id.ivWorkerPhoto);

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

//                Uri uri=Uri.parse(dataSnapshot.child("WorkerPicture").getValue(String.class));
//                System.out.println(" The Uri : " + uri.toString());
//               ivWorkerPhoto.setImageURI(uri);
//                System.out.println(" storageReference download URL : " +  storageReference.child(dataSnapshot.child("WorkerPicture").getValue(String.class)).getDownloadUrl());
//                System.out.println(" database child : " + dataSnapshot.child("WorkerPicture").getValue(String.class));
//                Picasso.get().load(dataSnapshot.child("WorkerPicture").getValue(String.class)).into(ivWorkerPhoto);
               // Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/muhammadmyfinalproject.appspot.com/o/1560876798422.jpg?alt=media&token=da0689c1-ddbb-44bf-9c07-c91e8a781cc3").into(ivWorkerPhoto);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
