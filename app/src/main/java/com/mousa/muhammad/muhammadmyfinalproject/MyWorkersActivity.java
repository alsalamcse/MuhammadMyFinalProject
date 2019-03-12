package com.mousa.muhammad.muhammadmyfinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyWorkersActivity extends AppCompatActivity {

    private static ListView list_view;
    private static String[] NAMES=new String[]
            {"Sohel","Awad","Muhammad","F","L" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workers);

        listView();
    }

    public void listView() {
        list_view=(ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.name_list,NAMES);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=(String)list_view.getItemAtPosition(position);
                Toast.makeText(MyWorkersActivity.this,"position :"+ position,Toast.LENGTH_LONG).show();
            }
        });

    }
}
