package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SignInActivity extends AppCompatActivity {

    private EditText edId, etPassWord;
    private Button btnLogIN, btnLogOut;

    FirebaseAuth auth;//to establish sign in sign up
    FirebaseUser user;//user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

//        init();

        edId = (EditText) findViewById(R.id.etId);
        etPassWord = (EditText) findViewById(R.id.edPassWord);

        btnLogIN = (Button) findViewById(R.id.btnLogIn);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);

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

                final AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //add the time to the table


                        SignInActivity.super.onBackPressed();
                    }
                });
                builder.setNegativeButton("No", null).setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();

            }
        });


        btnLogIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataHandler();
                    }
                });
                builder.setNegativeButton("No", null).setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }

    private void dataHandler() {
        String id = edId.getText().toString();
        String passw = etPassWord.getText().toString();
        signIn(id, passw);
    }

    //
    private void signIn(final String id, final String passw) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(id, passw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Intent intent = new Intent(SignInActivity.this, ScheduleActivity.class);

                    if(id.equals("m@gmail.com")) {
                        Toast.makeText(SignInActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                        intent = new Intent(SignInActivity.this, ManagerActivity.class);
                    }
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignInActivity.this, "signIn failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }

    ////function to the button Back in phone asked you if you sure to Exit
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
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
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}


//    public void init() {
//        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
//        TableRow tbrow0 = new TableRow(this);
//        TextView tv0 = new TextView(this);
//        tv0.setText(" Sl.No ");
//        tv0.setTextColor(Color.WHITE);
//        tbrow0.addView(tv0);
//        TextView tv1 = new TextView(this);
//        tv1.setText(" Product ");
//        tv1.setTextColor(Color.WHITE);
//        tbrow0.addView(tv1);
//        TextView tv2 = new TextView(this);
//        tv2.setText(" Unit Price ");
//        tv2.setTextColor(Color.WHITE);
//        tbrow0.addView(tv2);
//        TextView tv3 = new TextView(this);
//        tv3.setText(" Stock Remaining ");
//        tv3.setTextColor(Color.WHITE);
//        tbrow0.addView(tv3);
//        stk.addView(tbrow0);
//        for (int i = 0; i < 25; i++) {
//            TableRow tbrow = new TableRow(this);
//            TextView t1v = new TextView(this);
//            t1v.setText("" + i);
//            t1v.setTextColor(Color.WHITE);
//            t1v.setGravity(Gravity.CENTER);
//            tbrow.addView(t1v);
//            TextView t2v = new TextView(this);
//            t2v.setText("Product " + i);
//            t2v.setTextColor(Color.WHITE);
//            t2v.setGravity(Gravity.CENTER);
//            tbrow.addView(t2v);
//            TextView t3v = new TextView(this);
//            t3v.setText("Rs." + i);
//            t3v.setTextColor(Color.WHITE);
//            t3v.setGravity(Gravity.CENTER);
//            tbrow.addView(t3v);
//            TextView t4v = new TextView(this);
//            t4v.setText("" + i * 15 / 32 * 10);
//            t4v.setTextColor(Color.WHITE);
//            t4v.setGravity(Gravity.CENTER);
//            tbrow.addView(t4v);
//            stk.addView(tbrow);
//        }
//    }








