package com.example.setarekhan;


import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class intent extends AppCompatActivity {
    Button btnOpenWebsite, btnOpenApp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_activity);

        btnOpenWebsite = findViewById(R.id.btnWebsite);
        btnOpenApp = findViewById(R.id.btnApp);


        btnOpenWebsite.setOnClickListener(v -> {
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
            startActivity(websiteIntent);
        });

        btnOpenApp.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/imansadeghi0"));
            startActivity(intent);

        });

    }
}
