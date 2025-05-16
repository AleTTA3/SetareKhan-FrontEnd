package com.example.setarekhan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView textViewUsername = findViewById(R.id.textViewUsername);
        ImageView profileImage = findViewById(R.id.profileImage);
        Button buttonLogout = findViewById(R.id.buttonLogout);
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);



        if (username != null && !username.isEmpty()) {
            textViewUsername.setText("نام کاربری: " + username);
        } else {
            textViewUsername.setText("نام کاربری: ناشناس");
        }


        profileImage.setImageResource(R.drawable.avatar); // آواتار پیش‌فرض (در پوشه drawable)

        buttonLogout.setOnClickListener(v -> {
            // حذف اطلاعات ذخیره‌شده
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("username"); // یا editor.clear() برای پاک کردن همه چیز
            editor.apply();

            // بازگشت به صفحه کتاب‌ها
            Intent intent = new Intent(ProfileActivity.this, BookListScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

    }
}
