package com.example.scorpion1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AccountInfo extends AppCompatActivity {
    final String FILE_NAME="user-info.txt";
    final String FILE_NAME2="user-id.txt";
TextView username;
TextView prezime;
TextView ime;
TextView email;
TextView num;
TextView passwordChange;
EditText password;
Button potvrdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        getSupportActionBar().hide();
        username=(TextView)findViewById(R.id.usernameInfo);
        prezime=(TextView)findViewById(R.id.prezimeInfo);
        ime=(TextView)findViewById(R.id.imeInfo);
        email=(TextView)findViewById(R.id.emailInfo);
        num=(TextView)findViewById(R.id.numInfo);
        potvrdi=(Button)findViewById(R.id.potvrdiB);
        passwordChange=(TextView)findViewById(R.id.passwordChange);
        password=(EditText)findViewById(R.id.passwordACC);
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
                password.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Passowrd uspješno promijenjen",Toast.LENGTH_LONG).show();
            }
        });
    }
}