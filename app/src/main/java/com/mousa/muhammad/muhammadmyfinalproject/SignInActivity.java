package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private EditText edId, etPassWord;
    private Button btnLogIN, btnLogOut;


    FirebaseAuth auth;//to establish sign in sign up
    FirebaseUser user;//user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edId=(EditText)findViewById(R.id .etId) ;
        etPassWord=(EditText)findViewById(R.id .edPassWord) ;

        btnLogIN=(Button)findViewById(R.id .btnLogIn) ;
        btnLogOut=(Button)findViewById(R.id .btnLogOut) ;

//        btnLogOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // كود الانتقال إلى الشاشة الأخرى
//                Intent i=new Intent(getApplicationContext(), SignUpActivity.class);
//                startActivity(i);
//            }
//        });


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder= new AlertDialog.Builder(SignInActivity.this);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //add the time to the table



                        SignInActivity.super.onBackPressed();
                    }
                });
                builder.setNegativeButton("No",null).setCancelable(false) ;
                AlertDialog alert=builder.create();
                alert.show();

            }
        });



        btnLogIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder= new AlertDialog.Builder(SignInActivity.this);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataHandler();
                    }
                });
                builder.setNegativeButton("No",null).setCancelable(false) ;
                AlertDialog alert=builder.create();
                alert.show();

            }
        });
    }
    private void dataHandler()
    {
        String id=edId.getText().toString();
        String passw=etPassWord.getText().toString();
        signIn(id,passw);
    }
//
    private void signIn(String id,String passw) {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(id,passw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignInActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SignInActivity.this,ScheduleActivity.class);
                   startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(SignInActivity.this, "signIn failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }

    ////function to the button Back in phone asked you if you sure to Exit
    public void onBackPressed(){
        final AlertDialog.Builder builder= new AlertDialog.Builder(SignInActivity.this);
        builder.setMessage("Really Exit?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                builder.setCancelable(false);
            }
        });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }





}

