package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PortfolioActivity extends AppCompatActivity {



    FirebaseAuth auth;//to establish sign in sign up
    FirebaseUser user;//user
   // private DatabaseReference databaseReference;
   private DatabaseReference databaseReference;

    private EditText edtFirstName, edtWorkerId, edtWorkerBirthday, edtLastName, edtStartedWork;
    private TextView tvWorkerPortfolio, tvFirstName, tvWorkerId, tvWorkerBirthday, tvLastName, tvStartedWork;
    private ImageView ivWorker;
    private Button btnDetails, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();



        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtWorkerId = (EditText) findViewById(R.id.edtWorkerId);
        edtWorkerBirthday = (EditText) findViewById(R.id.edtWorkerBirthday);
        edtStartedWork = (EditText) findViewById(R.id.edtStartedWork);

        tvWorkerPortfolio = (TextView) findViewById(R.id.tvWorkerPortfolio);
        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);
        tvWorkerId = (TextView) findViewById(R.id.tvWorkerId);
        tvWorkerBirthday = (TextView) findViewById(R.id.tvWorkerBirthday);
        tvStartedWork = (TextView) findViewById(R.id.tvStartedWork);

        ivWorker = (ImageView) findViewById(R.id.ivWorker);

        btnDetails = (Button) findViewById(R.id.btnDetails);
        btnSave = (Button) findViewById(R.id.btnSave);


        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PortfolioActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add the details to the profile worker and add the name to the listView
                //of workers in MyWorkers Activity
                dataHandler();
            }
        });


    }


    private void dataHandler() {
        boolean isok = true;//if all the fields filled well
        String FirstName = edtFirstName.getText().toString();
        String LastName = edtLastName.getText().toString();
        String WorkerId = edtWorkerId.getText().toString();
        String WorkerBirthday = edtWorkerBirthday.getText().toString();
        String StartDate = edtStartedWork.getText().toString();
        if (FirstName.length() < 3 ) {
            edtFirstName.setError("Wrong Name");
            isok = false;
        }
        if (LastName.length() < 3) {
            edtLastName.setError("Wrong Name");
            isok = false;
        }
        if (WorkerId.length() < 8) {
            edtWorkerId.setError("Have to be at least 8 char");
            isok = false;
        }
        if (WorkerBirthday.length() < 5) {
            edtWorkerBirthday.setError("Have to be at least 5 char");
            isok = false;
        }
        if (StartDate.length() < 5) {
            edtStartedWork.setError("Have to be at least 5 char");
            isok = false;
        }
        if (isok) {
            creatAcount(FirstName, LastName);
        }
    }

    /**
     *create firebase user using FirstName, LastName,WorkerId,WorkerBirthday,StartDate
     * @param firstName
     * @param lastName
    // * @param WorkerId
    // * @param WorkerBirthday
    // * @param StartDate
     */
    private void creatAcount(final String firstName,final String lastName)
    {

        final String email = edtWorkerId.getText().toString() + "@mapp.com";
        auth.createUserWithEmailAndPassword(email, edtWorkerId.getText().toString())
                .addOnCompleteListener(PortfolioActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            final String id = user.getUid();
                            databaseReference.child("Users:").child(id).child("firstName").setValue(firstName);
                            databaseReference.child("Users:").child(id).child("lastName").setValue(lastName);
                            databaseReference.child("Users:").child(id).child("workerId").setValue(edtWorkerId.getText().toString());
                            databaseReference.child("Users:").child(id).child("birthday").setValue(edtWorkerBirthday.getText().toString());
                            databaseReference.child("Users:").child(id).child("dateStarted").setValue(edtStartedWork.getText().toString());

                            String toastLabel = "Authentication Successful." + "your email: " + email + "your password: " + edtWorkerId.getText().toString();
                            Toast.makeText(PortfolioActivity.this,toastLabel , Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(PortfolioActivity.this, "Authentication failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            task.getException().printStackTrace();
                        }
                    }
                });
    }

}

