package com.example.scorpion1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import java.util.Calendar;
import java.util.Objects;

public class Kalendar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalendar);
        Objects.requireNonNull(getSupportActionBar()).hide();
        SimpleCalendar simpleCalendar = findViewById(R.id.square_day);
        Calendar calendar = Calendar.getInstance();

        int month = calendar.get(Calendar.MONTH);

        int year = calendar.get(Calendar.YEAR);


        simpleCalendar.setUserCurrentMonthYear(month,year);
        simpleCalendar.setCallBack(new SimpleCalendar.DayClickListener() {
            @Override
            public void onDayClick(View view) {

            }
        });



    }

}