package com.example.setarekhan;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class  WeatherScreen  extends AppCompatActivity {

    private TextView tvCity, tvDescription, tvTemperature, tvHumidity, tvWind;

    private static final String BASE_URL = "https://api.openweathermap.org/";
    private static final String API_KEY = "80fdd4e89bcb38c5558f485d38a1cf55";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tvCity = findViewById(R.id.tvCity);
        tvDescription = findViewById(R.id.tvDescription);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvWind = findViewById(R.id.tvWind);

        fetchWeather("Tehran");
    }

    private void fetchWeather(String cityName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi api = retrofit.create(WeatherApi.class);

        Call<WeatherResponse> call = api.getWeather(cityName, API_KEY, "metric", "fa");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateUI(response.body());
                } else {
                    Toast.makeText( WeatherScreen.this, "خطا در دریافت داده‌ها", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText( WeatherScreen.this, "خطا: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateUI(WeatherResponse weather) {
        tvCity.setText(weather.name);
        if (weather.weather != null && weather.weather.length > 0) {
            tvDescription.setText(weather.weather[0].description);
        }
        tvTemperature.setText(String.format("دما: %.1f °C", weather.main.temp));
        tvHumidity.setText("رطوبت: " + weather.main.humidity + "%");
        tvWind.setText("باد: " + weather.wind.speed + " متر/ثانیه");
    }

    interface WeatherApi {
        @GET("data/2.5/weather")
        Call<WeatherResponse> getWeather(
                @Query("q") String city,
                @Query("appid") String apiKey,
                @Query("units") String units,
                @Query("lang") String lang
        );
    }

    // مدل داده برای پاسخ JSON
    public static class WeatherResponse {
        public String name;

        public Weather[] weather;

        public Main main;

        public Wind wind;

        public static class Weather {
            public String description;
        }

        public static class Main {
            public float temp;
            public int humidity;
        }

        public static class Wind {
            public float speed;
        }
    }
}
