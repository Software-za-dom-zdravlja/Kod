package com.example.scorpion1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstScreen extends AppCompatActivity implements View.OnClickListener {
Button button;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        getSupportActionBar().hide();
        button=(Button)findViewById(R.id.logbutton);
        button.setOnClickListener(this);
        button1=(Button)findViewById(R.id.regbutton);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logbutton:
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
                break;
            case R.id.regbutton:
                Intent intent1 = new Intent(getApplicationContext(), Register.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}