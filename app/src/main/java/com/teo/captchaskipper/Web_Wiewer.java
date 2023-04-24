package com.teo.captchaskipper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Web_Wiewer extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_wiewer);

        webView = findViewById(R.id.web_view);
        Button done_btn = findViewById(R.id.btn_done);

        String url = getIntent().getStringExtra("RESPONSE_BODY");
        String upload_url = getIntent().getStringExtra("UPLOAD_URL");
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(url);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload_link(upload_url);

            }
        });

    }

    private void upload_link(String upload_url) {
        new Thread(new Runnable() {

            final WebView webView = findViewById(R.id.web_view);


            String responseBody;
            final String new_url = webView.getUrl();

            @Override
            public void run() {

                Boolean aucuneErreur;

                RequestBody requestBody = new FormBody.Builder()
                        .add("url", new_url)
                        .build();
                Request uploadRequest = new Request.Builder()
                        .url(upload_url)
                        .post(requestBody)
                        .build();
                try {
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(uploadRequest).execute();
                    assert response.body() != null;
                    String responseBody = response.body().string();
                    Log.i("API", "Réponse de l'API d'upload : " + responseBody);
                    aucuneErreur = Boolean.TRUE;

                } catch (IOException e) {
                    Log.e("API", "Failed to make API request.", e);
                    Toast.makeText(Web_Wiewer.this, "Enable to connect to the server", Toast.LENGTH_LONG).show();
                    aucuneErreur = Boolean.FALSE;

                }

                if (aucuneErreur) {

                    Looper.prepare();
                    Toast.makeText(Web_Wiewer.this, "URL successfully uploaded", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Web_Wiewer.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        }).start();
    }

        /*webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    String currentUrl = view.getUrl();
                    // Vérifier si l'URL a changé
                    if (!currentUrl.equals(url)) {

                        RequestBody requestBody = new FormBody.Builder()
                                .add("url", currentUrl)
                                .build();
                        Request uploadRequest = new Request.Builder()
                                .url(upload_url)
                                .post(requestBody)
                                .build();
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Response response = client.newCall(uploadRequest).execute();
                            assert response.body() != null;
                            String responseBody = response.body().string();
                            Log.i("API", "Réponse de l'API d'upload : " + responseBody);
                        } catch (IOException e) {
                            Log.e("API", "Failed to make API request.", e);
                        }

                    }
                }
            }
        });*/


}