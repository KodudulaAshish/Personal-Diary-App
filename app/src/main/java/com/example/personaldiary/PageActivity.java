package com.example.personaldiary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.icu.text.DateFormatSymbols;
import android.os.Build;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.personaldiary.model.Day;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PageActivity extends AppCompatActivity{
    EditText  dateid,diary;
    ImageButton imageButton;
    Day day;
    boolean alreadywritten = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        imageButton=findViewById(R.id.imageButton2);
        dateid=findViewById(R.id.dateid);
        diary=findViewById(R.id.diary);

        day = new Day();
        try {
            day = (Day) getIntent().getSerializableExtra("oldday");
            dateid.setText(day.getDay());
            day.setDay(day.getContent());
            alreadywritten = true;
        }catch (Exception book){
            book.printStackTrace();
        }
        day = (Day) getIntent().getSerializableExtra("oldday");
        imageButton.setOnClickListener(new View.OnClickListener() {



            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String date=dateid.getText().toString();
                String diaryText=diary.getText().toString();

                if (diaryText.isEmpty()){
                    Toast.makeText(PageActivity.this,"There are no changes 😁",Toast.LENGTH_SHORT).show();
                    return ;
                }
                if (!alreadywritten) {
                    day = new Day();
                }


                    day.setContent(diaryText);
                    day.setDay(date);

                    Intent intent=new Intent();
                    intent.putExtra("Day",day);
                    setResult(Activity.RESULT_OK,intent);
                    finish();

                }
            });
        };
    }
