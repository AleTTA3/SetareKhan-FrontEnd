package com.example.setarekhan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextUsername, editTextPassword, editTextConfirmPassword;

    private final String SIGNUP_URL = "https://setarekhan-backend-api.onrender.com/kaka/signup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // اتصال ویوها
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        // رویداد دکمه
        buttonSignUp.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String confirmPassword = editTextConfirmPassword.getText().toString().trim();

            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "لطفاً همه فیلدها را پر کنید", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "رمز عبور و تکرار آن یکسان نیستند", Toast.LENGTH_SHORT).show();
                return;
            }

            sendSignUpRequest(email, username, password, confirmPassword);
        });
    }

    private void sendSignUpRequest(String email, String username, String password, String confirmPassword) {
        try {
            // ساختن Body به صورت JSON
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", email);
            jsonBody.put("userName", username);
            jsonBody.put("password", password);
            jsonBody.put("repassword", confirmPassword);

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    SIGNUP_URL,
                    jsonBody,
                    response -> {
                        try {
                            if (response.has("message")) {
                                Toast.makeText(this, response.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "ثبت‌نام موفقیت‌آمیز بود", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this, LoginActivity.class));
                                finish();
                            }
                        } catch (Exception e) {
                            Log.e("SignUp", "خطا در پاسخ سرور: " + e.getMessage());
                            Toast.makeText(this, "خطای سرور", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> {
                        Log.e("SignUp", "خطا در اتصال: " + error.toString());
                        if (error.networkResponse != null) {
                            int statusCode = error.networkResponse.statusCode;
                            if (statusCode == 400) {
                                Toast.makeText(this, "ایمیل قبلاً ثبت شده", Toast.LENGTH_SHORT).show();
                            } else if (statusCode == 401) {
                                Toast.makeText(this, "نام کاربری قبلاً استفاده شده", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "خطای نامشخص", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "عدم ارتباط با سرور", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");  // مهم!
                    return headers;
                }
            };

            Volley.newRequestQueue(this).add(request);
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
            Toast.makeText(this, "خطا در ساخت درخواست", Toast.LENGTH_SHORT).show();
        }
    }

}
