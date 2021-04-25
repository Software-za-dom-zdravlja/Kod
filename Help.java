package com.example.scorpion1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class Help extends AppCompatActivity {
    TextView pitanje1;
    TextView pitanje2;
    TextView pitanje3;
    TextView pitanje4;
    TextView odgovor1;
    TextView odgovor2;
    TextView odgovor3;
    TextView odgovor4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Objects.requireNonNull(getSupportActionBar()).hide();
        pitanje1=(TextView)findViewById(R.id.pitanje1);
        pitanje2=(TextView)findViewById(R.id.pitanje2);
        pitanje3=(TextView)findViewById(R.id.pitanje3);
        pitanje4=(TextView)findViewById(R.id.pitanje4);
        odgovor1=(TextView)findViewById(R.id.odgovor1);
        odgovor2=(TextView)findViewById(R.id.odgovor2);
        odgovor3=(TextView)findViewById(R.id.odgovor3);
        odgovor4=(TextView)findViewById(R.id.odgovor4);
        pitanje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odgovor1.setVisibility(View.VISIBLE);
            }
        });
        pitanje2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odgovor2.setVisibility(View.VISIBLE);
            }
        });
        pitanje3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odgovor3.setVisibility(View.VISIBLE);
            }
        });
        pitanje4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odgovor4.setVisibility(View.VISIBLE);
            }
        });
    }
}