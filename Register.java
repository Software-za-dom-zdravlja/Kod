package com.example.scorpion1;

import android.content.Intent;
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
import androidx.appcompat.app.AppCompatActivity;
import com.example.scorpion1.PutData;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Register extends AppCompatActivity implements View.OnClickListener {
    Button loginBtn;
    EditText nameR,passwordR,numberR,emailR,surnameR;
    TextView accountR,emailT;
    String username;
    CheckBox registracijaD;
    private static final String FILE_NAME = "user-info.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        loginBtn=(Button)findViewById(R.id.loginBR);
        nameR=(EditText)findViewById(R.id.nameR);
        surnameR=(EditText)findViewById(R.id.surnameR);
        passwordR=(EditText)findViewById(R.id.passwordR);
        numberR=(EditText)findViewById(R.id.brojMob);
        emailR=(EditText)findViewById(R.id.emailR);
        accountR=(TextView)findViewById(R.id.textViewV);
        registracijaD=(CheckBox)findViewById(R.id.kodCB);
        emailT=(TextView)findViewById(R.id.emailT);
        loginBtn.setOnClickListener(this);
        accountR.setOnClickListener(this);
        registracijaD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(registracijaD.isChecked()){
                    emailR.setVisibility(View.GONE);
                    emailT.setVisibility(View.GONE);

                }else if(!registracijaD.isChecked()){
                    emailR.setVisibility(View.VISIBLE);
                    emailT.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.loginBR:
                String name = nameR.getText().toString();
                String surname = surnameR.getText().toString();
                String password = passwordR.getText().toString();
                String email = emailR.getText().toString();
                String kontakt_broj = numberR.getText().toString();
                 username=name+"_"+surname;

                    if(registracijaD.isChecked()){
                        if (!name.isEmpty() && !password.isEmpty() && !surname.isEmpty() && !kontakt_broj.isEmpty()) {

                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        String[] field = new String[5];
                                        field[0] = "name";
                                        field[1] = "surname";
                                        field[2] = "password";
                                        field[3] = "kontakt_broj";
                                        field[4] = "username";
                                        String[] data = new String[5];
                                        data[0] = name;
                                        data[1] = surname;
                                        data[2] = password;
                                        data[3] = kontakt_broj;
                                        data[4] = username;
                                        PutData putData = new PutData("http://192.168.1.6/LoginRegister/signupDoktor.php", "POST", field, data);
                                        if (putData.startPut()) {
                                            if (putData.onComplete()) {
                                                String result = putData.getResult();
                                                if (result.equals("Sign Up Success")) {
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

                        }
                        }else if(!registracijaD.isChecked()) {


                        if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty() && !kontakt_broj.isEmpty() && isValid(email)) {
                            if (isValid(email)) {
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        String[] field = new String[6];
                                        field[0] = "name";
                                        field[1] = "surname";
                                        field[2] = "password";
                                        field[3] = "kontakt_broj";
                                        field[4] = "email";
                                        field[5] = "username";
                                        String[] data = new String[6];
                                        data[0] = name;
                                        data[1] = surname;
                                        data[2] = password;
                                        data[3] = kontakt_broj;
                                        data[4] = email;
                                        data[5] = username;
                                        PutData putData = new PutData("http://192.168.1.6/LoginRegister/signup.php", "POST", field, data);
                                        if (putData.startPut()) {
                                            if (putData.onComplete()) {
                                                String result = putData.getResult();
                                                if (result.equals("Sign Up Success")) {
                                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(getApplicationContext(), DodatneInformacije.class);
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
                            }


                        } else {
                            Toast.makeText(Register.this, "Unesite sve potrebne podatke", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
                    case R.id.textViewV:
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        break;
                }
        FileOutputStream fos = null;
        String text=nameR.getText().toString()+"_"+surnameR.getText().toString();
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            //Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
               //     Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }}

    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }


}