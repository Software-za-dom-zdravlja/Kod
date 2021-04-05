package com.example.scorpion1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class Termini extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button vrijemeButton;
    Button datumButton,timeB;
    private static final String FILE_NAME = "user-id.txt";
    TimePicker timeP;
    String datum,fileReaderId;
    EditText dodatneInfo;
    Button finishButton;
    TextView tt;
    int hour;
    int minute;
    TextView nazadText,datumT,vrijemeT,text,text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termini);
        getSupportActionBar().hide();
        vrijemeButton = (Button) findViewById(R.id.vrijemeB);
        datumButton = (Button) findViewById(R.id.datumB);
        dodatneInfo = (EditText) findViewById(R.id.dodatneInfo);
        tt=(TextView)findViewById(R.id.tt);
        finishButton = (Button) findViewById(R.id.finishB);
        nazadText = (TextView) findViewById(R.id.nazad);
        timeB = (Button) findViewById(R.id.timeButton);
        timeP = (TimePicker) findViewById(R.id.timePicker);
        text = (TextView) findViewById(R.id.text);
        text2 = (TextView) findViewById(R.id.text2);
        vrijemeT = (TextView) findViewById(R.id.vrijemeT);
        datumT = (TextView) findViewById(R.id.datumT);
        timeP.setIs24HourView(true);
        vrijemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vrijemeButton.setVisibility(View.GONE);
                datumButton.setVisibility(View.GONE);
                text.setVisibility(View.GONE);
                dodatneInfo.setVisibility(View.GONE);
                text2.setVisibility(View.GONE);
                datumT.setVisibility(View.GONE);
                finishButton.setVisibility(View.GONE);
                nazadText.setVisibility(View.GONE);
                timeB.setVisibility(View.VISIBLE);
                timeP.setVisibility(View.VISIBLE);
            }
        });
        timeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 hour = timeP.getCurrentHour();
                 minute = timeP.getCurrentMinute();
                if (hour > 8 && hour < 20) {
                    String timeMessage = hour + ":" + minute;
                    vrijemeT.setText(timeMessage);
                    vrijemeButton.setVisibility(View.VISIBLE);
                    datumButton.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                    datumT.setVisibility(View.VISIBLE);
                    text2.setVisibility(View.VISIBLE);
                    dodatneInfo.setVisibility(View.VISIBLE);
                    finishButton.setVisibility(View.VISIBLE);
                    nazadText.setVisibility(View.VISIBLE);
                    timeB.setVisibility(View.GONE);
                    timeP.setVisibility(View.GONE);
                } else {
                    Toast.makeText(Termini.this, "Nije ispravno vrijeme", Toast.LENGTH_LONG).show();
                }

            }
        });
        nazadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        datumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis = null;
                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String textU;
                    while ((textU = br.readLine()) != null) {
                        sb.append(textU);
                    }
                     fileReaderId=sb.toString();
                    // text2.setText(fileReaderUsername);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
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
                String vrijeme=datum+" "+hour+":"+minute+":00";
                String dodatneInformacije=dodatneInfo.getText().toString();
                Handler handler=new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        String [] field=new String[4];
                        field[0]="patient_id";
                        field[1]="doctor_id";
                        field[2]="vrijeme";
                        field[3]="dodatneInfo";
                        String[] data=new String[4];
                        data[0]="31";
                        data[1]="2";
                        data[2]=vrijeme;
                        data[3]=dodatneInformacije;
                        PutData putData=new PutData("http://192.168.1.6/LoginRegister/termin.php", "POST", field, data);
                        if(putData.startPut()){
                            if(putData.onComplete()){
                                String result=putData.getResult();
                                if(result.equals("Success")){
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                });
            }
        });

    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String date = month + "/" + dayOfMonth + "/" + year;
        datum = year + "-" + month + "-" + dayOfMonth;
        TextView textView = (TextView) findViewById(R.id.datumT);
        textView.setText(date);
    }

}