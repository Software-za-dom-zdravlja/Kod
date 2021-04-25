package com.example.scorpion1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Doctor extends AppCompatActivity {
    ListView listView;
    ArrayList<String> lista;
    TextView textView;
    TextView textView2;
    String name,datum;
    StringTokenizer stringTokenizer=null;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        getSupportActionBar().hide();
        textView=(TextView)findViewById(R.id.tekst);
        textView2=(TextView)findViewById(R.id.naslov);
        listView = (ListView) findViewById(R.id.listView);
        getJSON("http://192.168.1.7/LoginRegister/getdata.php");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Doctor.this);
                builder.setCancelable(true);
                builder.setTitle("Potvrda");
                stringTokenizer=new StringTokenizer(lista.get(position),",");
                name=stringTokenizer.nextToken();
                datum=stringTokenizer.nextToken();
                builder.setMessage("Da li želite da odobrite termin koji je odabrao korisnik "+ name+". Datum i vrijeme: "+datum+".");
                builder.setPositiveButton("Potvrdi",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        String[] field = new String[3];
                                        field[0] = "status";
                                        field[1] = "patientName";
                                        field[2] = "vrijeme";
                                        String[] data = new String[3];
                                        data[0] = "3";
                                        data[1] = name;
                                        data[2] = datum;
                                        PutData putData = new PutData("http://192.168.1.7/LoginRegister/status.php", "POST", field, data);
                                        if (putData.startPut()) {
                                            if (putData.onComplete()) {
                                                String result = putData.getResult();
                                                if (result.equals("Success")) {
                                                  //  Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();


                                                } else {
                                                   // Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                                }
                                                //Log.i("PutData", result);
                                            }
                                        }
                                    }


                                });
                                lista.remove(position);
                                arrayAdapter.notifyDataSetChanged();

                            }
                        });
                builder.setNegativeButton("Odbij", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                String[] field = new String[3];
                                field[0] = "status";
                                field[1] = "patientName";
                                field[2] = "vrijeme";
                                String[] data = new String[3];
                                data[0] = "2";
                                data[1] = name;
                                data[2] = datum;
                                PutData putData = new PutData("http://192.168.1.7/LoginRegister/status.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Success")) {
                                           // Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();


                                        } else {
                                          //  Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        }
                                        //Log.i("PutData", result);
                                    }
                                }
                            }


                        });
                        lista.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });
    }
    private void getJSON(final String urlWebService) {

        @SuppressLint("StaticFieldLeak")
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
              //  Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] niz1 = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj1 = jsonArray.getJSONObject(i);

            niz1[i] = obj1.getString("vrijeme1");


        }
        JSONArray jsonArray1 = new JSONArray(json);
        String[] niz2 = new String[jsonArray1.length()];
        for (int j = 0; j < jsonArray1.length(); j++) {
            JSONObject obj2 = jsonArray1.getJSONObject(j);

            niz2[j] = obj2.getString("vrijeme2");


        }
        JSONArray jsonArray2 = new JSONArray(json);
        String[] niz3 = new String[jsonArray2.length()];
        for (int e = 0; e < jsonArray2.length(); e++) {
            JSONObject obj3 = jsonArray2.getJSONObject(e);

            niz3[e] = obj3.getString("vrijeme3");


        }
        JSONArray jsonArray3 = new JSONArray(json);
        String[] niz4 = new String[jsonArray3.length()];
        for (int f = 0; f < jsonArray3.length(); f++) {
            JSONObject obj4 = jsonArray3.getJSONObject(f);

            niz4[f] = obj4.getString("ime");

        }

        JSONArray jsonArray4 = new JSONArray(json);
        String[] niz5 = new String[jsonArray4.length()];
        for (int e = 0; e < jsonArray4.length(); e++) {
            JSONObject obj5 = jsonArray4.getJSONObject(e);

            niz5[e] = obj5.getString("ime2");


        }

        JSONArray jsonArray5 = new JSONArray(json);
        String[] niz6 = new String[jsonArray5.length()];
        for (int e = 0; e < jsonArray5.length(); e++) {
            JSONObject obj6 = jsonArray5.getJSONObject(e);

            niz6[e] = obj6.getString("ime3");

        }

        lista=new ArrayList<>();
        String a=niz4[0]+", "+niz1[0];
        String b=niz5[0]+", "+niz2[0];
        String c=niz6[0]+", "+niz3[0];

        if(niz4[0].equals("Nema termina")){
            textView2.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
        }else if(niz5[0].equals("Nema termina")){
            lista.add(a);
        }else if(niz6[0].equals("Nema termina")){
            lista.add(a);
            lista.add(b);
        }else{
            lista.add(a);
            lista.add(b);
            lista.add(c);
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(arrayAdapter);
    }
}