package com.teo.captchaskipper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;




public class Recup_Lien extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recup_lien);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        EditText input_ip = findViewById(R.id.input_ip_adress);
        EditText input_port = findViewById(R.id.input_port);


        String lastIP = sharedPreferences.getString("lastIP", "");
        String lastPort = sharedPreferences.getString("lastPort", "");


        input_ip.setText(lastIP);
        input_port.setText(lastPort);

        Button getlink = findViewById(R.id.btn_ok);

        getlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                get_link();

            }});



    }

    private void get_link() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();



        EditText input_ip = findViewById(R.id.input_ip_adress);
        String ip_adress = input_ip.getText().toString();

        editor.putString("lastIP", ip_adress);
        editor.apply();



        EditText input_port = findViewById(R.id.input_port);
        String port = input_port.getText().toString();

        editor.putString("lastPort", port);
        editor.apply();


        String url = "http://" + ip_adress + ":" + port + "/get_url";



        new Thread(new Runnable() {

            String responseBody;

            @Override
            public void run() {

                Boolean aucuneErreur;


                Request getUrlRequest = new Request.Builder()
                        .url(url)
                        .build();
                try {
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(getUrlRequest).execute();
                    assert response.body() != null;
                    responseBody = response.body().string();
                    System.out.println(responseBody);
                    aucuneErreur = Boolean.TRUE;


                } catch (IOException e) {
                    Log.e("API", "Failed to make API request.", e);
                    Looper.prepare();
                    Toast.makeText(Recup_Lien.this, "Enable to connect to the server", Toast.LENGTH_LONG).show();

                    aucuneErreur = Boolean.FALSE;

                }
                if (aucuneErreur){

                    Looper.prepare();
                    Toast.makeText(Recup_Lien.this, "Connected to " + responseBody, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Recup_Lien.this, Web_Wiewer.class);
                    intent.putExtra("RESPONSE_BODY", responseBody);
                    intent.putExtra("UPLOAD_URL", "http://" + ip_adress + ":" + port + "/upload_url");
                    startActivity(intent);
                    finish();

                }
            }
        }).start();

    }
}