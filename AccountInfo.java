package com.example.scorpion1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AccountInfo extends AppCompatActivity {
    final String FILE_NAME="user-info.txt";
TextView username;
TextView prezime;
TextView oldPassT;
TextView newPassT;
TextView ime;
TextView email;
TextView num;
TextView passwordChange;
TextView back;
EditText password;
EditText oldPassword;
Button potvrdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        getSupportActionBar().hide();
        username=(TextView)findViewById(R.id.usernameInfo);
        oldPassT=(TextView)findViewById(R.id.oldPasswordT);
        newPassT=(TextView)findViewById(R.id.newPasswordT);
        back=(TextView)findViewById(R.id.natrag);
        prezime=(TextView)findViewById(R.id.prezimeInfo);
        ime=(TextView)findViewById(R.id.imeInfo);
        email=(TextView)findViewById(R.id.emailInfo);
        num=(TextView)findViewById(R.id.numInfo);
        potvrdi=(Button)findViewById(R.id.potvrdiB);
        passwordChange=(TextView)findViewById(R.id.passwordChange);
        password=(EditText)findViewById(R.id.passwordACC);
        oldPassword=(EditText)findViewById(R.id.passwordOld);

        FileInputStream fis=null;
        ArrayList<String> array=new ArrayList<>();
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String textU;
            while ((textU = br.readLine()) != null) {
                array.add(textU);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
       String username2=array.get(0);
        String [] name2;
        name2=username2.split("_");
        String name,surname;
        name=name2[0];
        surname=name2[1];
        username.setText(array.get(0));
        email.setText(array.get(1));
        num.setText(array.get(2));
        ime.setText(name);
        prezime.setText(surname);
        potvrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setVisibility(View.INVISIBLE);
                oldPassword.setVisibility(View.GONE);
                oldPassT.setVisibility(View.INVISIBLE);
                newPassT.setVisibility(View.INVISIBLE);
                potvrdi.setVisibility(View.INVISIBLE);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        field[0] = "password";
                        field[1] = "username";
                        String[] data = new String[2];
                        data[0] = password.getText().toString();
                        data[1] = array.get(0);
                        PutData putData = new PutData("http://192.168.1.7/LoginRegister/updatePassword.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                if (result.equals("Success")) {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                    }
                });
                Toast.makeText(getApplicationContext(),"Passowrd uspješno promijenjen",Toast.LENGTH_LONG).show();
            }
        });
        passwordChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPassword.setVisibility(View.VISIBLE);
                potvrdi.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                oldPassT.setVisibility(View.VISIBLE);
                newPassT.setVisibility(View.VISIBLE);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}