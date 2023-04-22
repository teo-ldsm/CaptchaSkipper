package com.teo.captchaskipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class Recup_Lien extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recup_lien);

        Button getlink = (Button) findViewById(R.id.btn_ok);

        getlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                get_link();

                System.out.println();

        }});



    }

    private void get_link() {


        EditText simpleEditText = (EditText) findViewById(R.id.input_ip_adress);
        String ip_adress = simpleEditText.getText().toString();

        RequestQueue volleyQueue = Volley.newRequestQueue(Recup_Lien.this);
        String url = "http://" + ip_adress + ""; // TODO Mettre l'URL de l'API Flask

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

                Request.Method.GET,
                url,
                null,
                (Response.Listener<JSONObject>) response -> {

                    String captcha_url;
                    try {

                        captcha_url = response.getString("message");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                (Response.ErrorListener) error -> {
                    Toast.makeText(Recup_Lien.this, "Some error occurred! Cannot fetch captcha URL", Toast.LENGTH_LONG).show();
                    Log.e("MainActivity", "error: ${error.localizedMessage}");
                }
        );
        volleyQueue.add(jsonObjectRequest);

    }
}