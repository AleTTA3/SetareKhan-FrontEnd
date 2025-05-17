package com.example.setarekhan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;

    private final String LOGIN_URL = "https://setarekhan-backend-api.onrender.com/kaka/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "لطفاً تمام فیلدها را پر کنید", Toast.LENGTH_SHORT).show();
                return;
            }

            sendLoginRequest(username, password);
        });
    }

    private void sendLoginRequest(String username, String password) {
        try {
            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("userName", username);
                jsonBody.put("password", password);
            } catch (JSONException e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    LOGIN_URL,
                    jsonBody,
                    response -> {
                        Toast.makeText(this, "ورود موفق بود", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, BookListScreen.class));
                        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.apply();
                        finish();
                    },
                    error -> {
                        if (error.networkResponse != null) {
                            int statusCode = error.networkResponse.statusCode;
                            if (statusCode == 404) {
                                Toast.makeText(this, "نام کاربری یا رمز اشتباه است", Toast.LENGTH_SHORT).show();
                            } else if (statusCode == 500) {
                                Toast.makeText(this, "خطا در سرور یا پایگاه داده", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "خطای ناشناخته: " + statusCode, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "عدم ارتباط با سرور", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };

            Volley.newRequestQueue(this).add(request);

        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
            Toast.makeText(this, "خطا در ساخت درخواست ورود", Toast.LENGTH_SHORT).show();
        }
    }
}
