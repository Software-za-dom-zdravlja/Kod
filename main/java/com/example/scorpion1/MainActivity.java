package com.example.scorpion1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button logoutB;
    Button dodatneInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logoutB=(Button)findViewById(R.id.logoutB);
        dodatneInfo=(Button)findViewById(R.id.dodatneInfo);
        dodatneInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DodatneInformacije.class));
                finish();
            }
        });
        logoutB.setOnClickListener(new View.OnClickListener() {
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
    }
}
