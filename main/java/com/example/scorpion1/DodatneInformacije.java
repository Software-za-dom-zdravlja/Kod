package com.example.scorpion1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Calendar;

public class DodatneInformacije extends AppCompatActivity {
     TextView text;
     TextView text2;
     EditText doctorName;
     Button finishB;
    private static final String TAG = "DodatneInformacije";
    private DatePickerDialog.OnDateSetListener birth_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodatne_informacije);
        text = (TextView) findViewById(R.id.textV);
        text2 = (TextView) findViewById(R.id.textW);
        doctorName=(EditText)findViewById(R.id.doctorName);
        finishB = (Button) findViewById(R.id.finishB);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        DodatneInformacije.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        birth_date,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        birth_date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                String birth_date = year + "-" + month + "-" + day;
                String tekst = "Vaš datum rođenja je: ";
                text2.setText(tekst + date);
            }
        };
        finishB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doktor = doctorName.getText().toString();
                    
                    String datum="datum";
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        field[0] = "datum";
                        field[1] = "doktor";
                        String[] data = new String[2];
                        data[0] = datum;
                        data[1] = doktor;
                        PutData putData = new PutData("http://192.168.1.7/LoginRegister/DodatneInformacije.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                if (result.equals("Success")) {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                }
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        }
                    }
                });

        }});

    }
}