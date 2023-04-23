package com.teo.captchaskipper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

public class Web_Wiewer extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_wiewer);

        WebView webView = findViewById(R.id.web_view);

        // Récupération de la variable responseBody
        String responseBody = getIntent().getStringExtra("RESPONSE_BODY");
        webView.loadUrl(responseBody);


    }
}