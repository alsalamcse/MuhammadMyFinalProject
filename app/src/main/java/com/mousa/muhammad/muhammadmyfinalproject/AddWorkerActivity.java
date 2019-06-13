package com.mousa.muhammad.muhammadmyfinalproject;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;


public class AddWorkerActivity extends AppCompatActivity {
    FirebaseAuth auth;//to establish sign in sign up
    FirebaseUser user;//user
        private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private EditText edtFirstName, edtWorkerId, edtWorkerBirthday, edtLastName, edtStartedWork,edtPriceToHour;
    private TextView tvWorkerPortfolio, tvFirstName, tvWorkerId, tvWorkerBirthday, tvLastName, tvStartedWork, tvPriceToHour;
    private ImageButton imageButton;
    private Button btnChooseProfilePicture, btnSave;
    private ProgressBar progressBar3;
    Uri uri;
   public static Task<Uri> uri_session;
   String uri_string;
    public static final int PICK_IMAGE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtWorkerId = (EditText) findViewById(R.id.edtWorkerId);
        edtWorkerBirthday = (EditText) findViewById(R.id.edtWorkerBirthday);
        edtStartedWork = (EditText) findViewById(R.id.edtStartedWork);
        edtPriceToHour = (EditText) findViewById(R.id.edtPriceToHour);
        tvWorkerPortfolio = (TextView) findViewById(R.id.tvWorkerPortfolio);
        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);
        tvWorkerId = (TextView) findViewById(R.id.tvWorkerId);
        tvWorkerBirthday = (TextView) findViewById(R.id.tvWorkerBirthday);
        tvStartedWork = (TextView) findViewById(R.id.tvStartedWork);
        tvPriceToHour = (TextView) findViewById(R.id.tvPriceToHour);
        imageButton=(ImageButton)findViewById(R.id.imageButton);
        progressBar3=(ProgressBar)findViewById(R.id.progressBar3);
        btnChooseProfilePicture = (Button) findViewById(R.id.btnChooseProfilePicture);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnChooseProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfilechoseer();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add the details to the profile worker and add the name to the listView
                //of workers in MyWorkers Activity
                uploadProfilePicture();
                //to upload picture to the sotrage in firebase
                dataHandler();
                // to create a new account for worker
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode== RESULT_OK && data != null && data.getData()!= null)
            uri=data.getData();
        imageButton.setImageURI(uri);
    }
    private void openfilechoseer(){
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE);
    }
    //to check if the manager filled the worker details and calls the function creatAcount to Create account to the worker by manager
    private void dataHandler() {
        boolean isok = true;//if all the fields filled well
        String FirstName = edtFirstName.getText().toString();
        String LastName = edtLastName.getText().toString();
        String WorkerId = edtWorkerId.getText().toString();
        String WorkerBirthday = edtWorkerBirthday.getText().toString();
        String StartDate = edtStartedWork.getText().toString();
        String HourPrice = edtPriceToHour.getText().toString();
        if (FirstName.length() < 3 ) {
            edtFirstName.setError("Wrong Name");
            isok = false;
        }
        try{
            Double price = Double.parseDouble(HourPrice.trim());
        }catch (Exception e){
            edtPriceToHour.setError("Wrong Hour price");
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

    //Create account to the worker by manager
    private void creatAcount(final String firstName,final String lastName)
    {
        System.out.println("Create Account");
        final String email = edtWorkerId.getText().toString() + "@mapp.com";
        auth.createUserWithEmailAndPassword(email, edtWorkerId.getText().toString())
                .addOnCompleteListener(AddWorkerActivity.this, new OnCompleteListener<AuthResult>() {
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
                            databaseReference.child("Users:").child(id).child("HourPrice").setValue(edtPriceToHour.getText().toString());

                            System.out.println( " uri_string   : " + uri_string);
                            if(uri_session != null) {
                                System.out.println("create Account , uri_session : " + uri_session.toString());
                                databaseReference.child("Users:").child(id).child("WorkerPicture").setValue(uri_session.toString());
                            }else{
                                System.out.println("create Account , uri_session is null");
                            }

                            Map<String,Object> map = new HashMap<>();
                            for(int i=1 ; i<13 ; i++){
                                map.put(""+i,"");
                            }
                            databaseReference.child("Users:").child(id).child("Months").updateChildren(map);


                            String toastLabel = "Authentication Successful." + "your email: " + email + "your password: " + edtWorkerId.getText().toString();
                            Toast.makeText(AddWorkerActivity.this,toastLabel , Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(AddWorkerActivity.this, "Authentication failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            task.getException().printStackTrace();
                        }
                    }
                });
    }

    private String getfileExtenion(Uri uri){
        ContentResolver contentResolver= getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void uploadProfilePicture(){

        if(uri != null){

            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()+"." +
            getfileExtenion(uri));
            fileReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


//                    uri_session = taskSnapshot.getUploadSessionUri();
                    uri_session = fileReference.getDownloadUrl();
                    uri_string = "" + taskSnapshot.getUploadSessionUri();

//                    System.out.println(" getUploadSessionUri : " + taskSnapshot.getUploadSessionUri());
//                    System.out.println(" getDownloadUrl : " + storageReference.getDownloadUrl());
//                    System.out.println(" getDownloadUrl : " + fileReference.getDownloadUrl());

                    if(uri_session != null) {
                        System.out.println("create Account , uri_session : " + uri_session.toString());
                        databaseReference.child("Users:").child(user.getUid()).child("WorkerPicture").setValue(uri_session.toString());

                    }else{
                        System.out.println("create Account , uri_session is null");
                    }


                    Handler handler= new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar3.setProgress(0);
                        }
                    },500);
                    Toast.makeText(AddWorkerActivity.this,"Upload Successful",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddWorkerActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress=(100.0* taskSnapshot.getBytesTransferred() /
                    taskSnapshot.getTotalByteCount());
                    progressBar3.setProgress((int) progress);
                }
            });
        }else {
            Toast.makeText(this,"No File Selected", Toast.LENGTH_SHORT).show();
        }
    }
}

