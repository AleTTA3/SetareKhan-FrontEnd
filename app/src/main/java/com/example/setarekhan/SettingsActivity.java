
package com.example.setarekhan;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchDarkMode;
    private Button buttonSave;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchDarkMode = findViewById(R.id.switchDarkMode);
        buttonSave = findViewById(R.id.buttonSave);
        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);

        boolean isDarkMode = sharedPreferences.getBoolean("darkMode", false);
        switchDarkMode.setChecked(isDarkMode);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("darkMode", switchDarkMode.isChecked());
                editor.apply();
            }
        });
    }
}
