package com.example.scorpion1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    CardView card6;
    CardView card1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        card1=(CardView)findViewById(R.id.card1);
       card6=(CardView)findViewById(R.id.card6);
       card6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SharedPreferences sharedPreferences=getSharedPreferences("checkbox",MODE_PRIVATE);
               SharedPreferences.Editor editor=sharedPreferences.edit();
               editor.putString("remember","false");
               editor.apply();
               startActivity(new Intent(getApplicationContext(),Login.class));
               finish();
           }
       });
       card1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getApplicationContext(),Termini.class);
               startActivity(intent);
           }
       });
    }
}