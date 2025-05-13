
package com.example.setarekhan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView textViewUsername = findViewById(R.id.textViewUsername);
        Button buttonLogout = findViewById(R.id.buttonLogout);

        String username = getIntent().getStringExtra("username");
        if (username != null) {
            textViewUsername.setText("نام کاربری: " + username);
        } else {
            textViewUsername.setText("نام کاربری: ناشناس");
        }

        buttonLogout.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
