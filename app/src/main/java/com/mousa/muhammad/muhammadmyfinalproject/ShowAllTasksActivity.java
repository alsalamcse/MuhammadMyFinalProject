package com.mousa.muhammad.muhammadmyfinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ShowAllTasksActivity extends AppCompatActivity {

    private ListView lvTasks;
    private TaskAdapter taskAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_tasks);

        lvTasks=findViewById(R.id.);
    }
}
