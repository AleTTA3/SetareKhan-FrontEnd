package com.example.setarekhan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000; // مدت زمان به میلی‌ثانیه

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // همون اسپلش رو نشون می‌ده

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, BookListScreen.class);
            startActivity(intent);
            finish(); // این صفحه بسته بشه
        }, SPLASH_DELAY);
    }
}
