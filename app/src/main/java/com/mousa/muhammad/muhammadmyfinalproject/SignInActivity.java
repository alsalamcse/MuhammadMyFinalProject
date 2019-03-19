package com.mousa.muhammad.muhammadmyfinalproject;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText edId, etPassWord;
    private Button btnLogIN, btnService;
    private TextView textView;

    private BroadcastReceiver broadcastReceiver;

//    FirebaseAuth auth;//to establish sign in sign up
//    FirebaseUser user;//user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edId = (EditText) findViewById(R.id.etId);
        etPassWord = (EditText) findViewById(R.id.edPassWord);
        textView=(TextView)findViewById(R.id.textView);
        btnService = (Button) findViewById(R.id.btnService);
        btnLogIN = (Button) findViewById(R.id.btnLogIn);


        btnLogIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {dataHandler();}
//                final AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
//                builder.setMessage("Are you sure?");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.setNegativeButton("No", null).setCancelable(false);
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
        });

        if(!runtime_permissions())
            enable_buttons();
    }
    private void enable_buttons() {

        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),GPS_Service.class);
                startService(i);
            }
        });
    }

    private boolean runtime_permissions() {

        if(Build.VERSION.SDK_INT >=23 &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED )
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION ,
                    Manifest.permission.ACCESS_COARSE_LOCATION},100);

            return true;
        }
        return false;
    }

    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[]grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode==100){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED &&
                    grantResults[1]==PackageManager.PERMISSION_GRANTED)
                enable_buttons();
            else
                runtime_permissions();
        }
    }

    protected void onResume(){
        super.onResume();
        if(broadcastReceiver==null)
            broadcastReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    textView.append("\n"+ intent.getExtras().get("coordinates"));
                }
            };
        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }

    protected void onDestroy(){
        super.onDestroy();
        if(broadcastReceiver==null)
            unregisterReceiver(broadcastReceiver);

    }


//function take the email and password that you insert and call the function SignIn
    private void dataHandler() {
        String id = edId.getText().toString();
        String passw = etPassWord.getText().toString();
        if(id.length() < 6){
            edId.setError("Invalid Email");
        }else {
            if (passw.length() < 6) {
                etPassWord.setError("Invalid Password");
            }else{
                signIn(id, passw);
            }
        }

    }

    //function to Sign in to app check email and password from the firebase if it Exists
    private void signIn(final String id, final String passw) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(id, passw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    Toast.makeText(SignInActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();

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

}









