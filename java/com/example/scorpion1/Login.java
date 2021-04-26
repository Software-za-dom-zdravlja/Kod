package com.example.scorpion1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.scorpion1.PutData;


public class Login extends AppCompatActivity implements View.OnClickListener {
    Button loginBtn;
    EditText nameL, passwordL,kodET;
    CheckBox remember;
    CheckBox kodCB;
    TextView text;
    TextView kodT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        loginBtn = (Button) findViewById(R.id.loginB);
        nameL = (EditText) findViewById(R.id.name);
        nameL.setBackgroundColor(Color.WHITE);
        kodET = (EditText) findViewById(R.id.kodETxt);
        kodET.setBackgroundColor(Color.WHITE);
        passwordL = (EditText) findViewById(R.id.password);
        passwordL.setBackgroundColor(Color.WHITE);
        remember = (CheckBox) findViewById(R.id.remember_me);
        kodCB = (CheckBox) findViewById(R.id.kodCB);
        text=(TextView)findViewById(R.id.textViewR);
        kodT=(TextView)findViewById(R.id.kodTxt);
        text.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox=preferences.getString("remember","");
        if(checkbox.equals("true")){
            Intent intent=new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        }else if(checkbox.equals("false")){
            Toast.makeText(Login.this,"Prijavite se",Toast.LENGTH_LONG).show();
        }
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(Login.this,"Checked",Toast.LENGTH_LONG).show();
                }else if(!buttonView.isChecked()){
                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(Login.this,"Unchecked",Toast.LENGTH_LONG).show();
                }
            }
        });
        kodCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(kodCB.isChecked()){
                    kodT.setVisibility(View.VISIBLE);
                    kodET.setVisibility(View.VISIBLE);
                }else if(!kodCB.isChecked()){
                    kodT.setVisibility(View.GONE);
                    kodET.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginB:
                String username = nameL.getText().toString();
                String password = passwordL.getText().toString();
                String kod=kodET.getText().toString();
                if(!kodCB.isChecked()){
                if (!username.isEmpty() && !password.isEmpty()) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";

                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;

                            PutData putData = new PutData("http://192.168.1.7/LoginRegister/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Login Success")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                    }
                                    //Log.i("PutData", result);
                                }
                            }

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Unesite sve podatke", Toast.LENGTH_LONG).show();
                }


        }else if(kodCB.isChecked()){
                    if (!username.isEmpty() && !password.isEmpty() && !kod.isEmpty()) {

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                String[] field = new String[3];
                                field[0] = "username";
                                field[1] = "password";
                                field[2] = "kod";

                                String[] data = new String[3];
                                data[0] = username;
                                data[1] = password;
                                data[2] = kod;

                                PutData putData = new PutData("http://192.168.1.7/LoginRegister/loginDoktor.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Login Success")) {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), Doctor.class);
                                            startActivity(intent);
                                            finish();

                                        } else {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        }
                                        //Log.i("PutData", result);
                                    }
                                }

                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Unesite sve podatke", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.textViewR:
                startActivity(new Intent(getApplicationContext(), Register.class));
                break;

        }
    }
}