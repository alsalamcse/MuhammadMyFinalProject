package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
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
    private Button btnLogIN;


    FirebaseAuth auth;//to establish sign in sign up
    FirebaseUser user;//user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edId=(EditText)findViewById(R.id .etId) ;
        etPassWord=(EditText)findViewById(R.id .edPassWord) ;

        btnLogIN=(Button)findViewById(R.id .btnLogIn) ;




//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // كود الانتقال إلى الشاشة الأخرى
//                Intent i=new Intent(getApplicationContext(), SignUpActivity.class);
//                startActivity(i);
//            }
//        });
        btnLogIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();
            }
        });






    }

    private void dataHandler()
    {
        String id=edId.getText().toString();
        String passw=etPassWord.getText().toString();
        signIn(id,passw);


    }

    private void signIn(String id,String passw) {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(id,passw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignInActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                   // Intent intent=new Intent(SignInActivity.this,MainTabsActivity.class);
                  //  startActivity(intent);
                    //  finish();
                }
                else
                {
                    Toast.makeText(SignInActivity.this, "signIn failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }


}
