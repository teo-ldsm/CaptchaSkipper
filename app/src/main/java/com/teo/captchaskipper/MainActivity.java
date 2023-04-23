package com.teo.captchaskipper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.start_btn);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(MainActivity.this, "Bonjour monsieur", Toast.LENGTH_LONG).show();
                Intent otherActivity = new Intent(getApplicationContext(), Recup_Lien.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }
}