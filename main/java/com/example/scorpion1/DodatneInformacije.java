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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Calendar;

public class DodatneInformacije extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
     TextView text;
     public String birth_date2;
     TextView text2;
     Button buttonDR;
     EditText doctorName;
     Button finishB;
     String doktor;
     TextView text3;
     TextView textNazad;
    private static final String TAG = "DodatneInformacije";
    private DatePickerDialog.OnDateSetListener birth_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodatne_informacije);
        getSupportActionBar().hide();
        text = (TextView) findViewById(R.id.textV);
        text2 = (TextView) findViewById(R.id.textW);
        text3=(TextView)findViewById(R.id.text3);
        textNazad=(TextView)findViewById(R.id.textViewRe);
        finishB = (Button) findViewById(R.id.finishB);
        buttonDR=(Button)findViewById(R.id.buttonDR);
        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DodatneInformacije.this,
                android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.imena));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        textNazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonDR.setOnClickListener(new View.OnClickListener() {
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
                 birth_date2 = year + "-" + month + "-" + day;
                String tekst = "Vaš datum rođenja je: ";
                text2.setText(tekst + date);
                text2.setVisibility(View.VISIBLE);
            }
        };
        finishB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String doktor = doctorName.getText().toString();
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        field[0] = "datum";
                        field[1] = "doktor";
                        String[] data = new String[2];
                        data[0] = birth_date2;
                        data[1] = doktor;
                        PutData putData = new PutData("http://192.168.1.5/LoginRegister/DodatneInformacije.php", "POST", field, data);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Odabrali ste doktora", Toast.LENGTH_LONG).show();
        String tekst2 = "Vaš doktor je: ";
        switch (position) {
            case 0:
                     doktor="1";
                text3.setText(tekst2+"Doktor 1");
                break;
            case 1:
                    doktor="2";
                text3.setText(tekst2+"Doktor 2");
                break;
            case 2:
                doktor="3";
                text3.setText(tekst2+"Doktor 3");
                break;
            case 3:
                doktor="4";
                text3.setText(tekst2+"Doktor 4");
                break;
        }
            text3.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "Morate odabrati doktora", Toast.LENGTH_LONG).show();
    }
}