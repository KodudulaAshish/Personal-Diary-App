package com.example.personaldiary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.personaldiary.database.RoomDB;
import com.example.personaldiary.model.Day;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DayAdapter dayAdapter;
    List<Day> days = new ArrayList<>();
    RoomDB database;
    Button addADay;
    DayClickListener dayClickListener;
    private Object DayAdapter;
    dayViewholder dayViewholder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        addADay = findViewById(R.id.button2);

        database = RoomDB.getInstance(this);
        days = database.mainDAO().getAll();

        updateRecycler(recyclerView);

        addADay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PageActivity.class);
                startActivityForResult(intent, 1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1){
            if (resultCode==Activity.RESULT_OK) {
                Day newday;
                assert data != null;
                newday =(Day) data.getSerializableExtra("Day");
                database.mainDAO().insert(newday);
                days.clear();
                days.addAll(database.mainDAO().getAll());
                DayAdapter.notify();
            }
                else if(requestCode==2)
            {
                if(resultCode==Activity.RESULT_OK){
                    Day updatedday = (Day) data.getSerializableExtra("day");
                    database.mainDAO().update(updatedday.getID(), updatedday.getDay(), updatedday.getContent());
                    days.clear();
                    days.addAll(database.mainDAO().getAll());
                    DayAdapter.notify();
                }
            }
        }
    }

    private <recyclerView> void updateRecycler(recyclerView RecyclerView) {
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        dayAdapter= new DayAdapter(MainActivity.this,days);
        this.recyclerView.setAdapter(dayAdapter);
    }

    private final DayClickListener dayClickListener2 = new  DayClickListener() {
        @Override
        public void onClick(Day day) {
        Intent intent = new Intent(MainActivity.this,PageActivity.class);
                intent.putExtra("oldday",day);
                startActivityForResult(intent,2);
        }
    };
};



