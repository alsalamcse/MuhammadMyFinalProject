package com.mousa.muhammad.muhammadmyfinalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyWorkersActivity extends AppCompatActivity {

    public static ListView list_view;
    private DatabaseReference databaseReference;
    FirebaseAuth auth;
    FirebaseUser user;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workers);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        list_view = (ListView)findViewById(R.id.listView);
        listView();
    }

        public void listView()
        {
            DatabaseReference users = databaseReference.child("Users:");
            users.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<String> list = new ArrayList<>();
                    for (DataSnapshot it : dataSnapshot.getChildren()) {
                        System.out.println("Id:  " + it.getKey());
                        list.add(dataSnapshot.child(it.getKey()).child("firstName").getValue(String.class));
                    }
                    String[] NAMES = new String[list.size()];
                    System.out.println("List size:  " + list.size());
                    for (int i = 0; i < list.size(); i++) {
                        NAMES[i] = list.get(i);
                        System.out.println(NAMES[i]);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MyWorkersActivity.this, R.layout.name_list, NAMES);
                    list_view.setAdapter(adapter);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String value = (String) list_view.getItemAtPosition(position);
                    Toast.makeText(MyWorkersActivity.this, "position :" + position, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
