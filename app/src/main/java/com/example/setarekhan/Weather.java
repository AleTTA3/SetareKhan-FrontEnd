package com.example.setarekhan;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Weather extends AppCompatActivity {

    private static final String API_KEY = "bf2856fba59e46e6a76183207250205";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getWeatherData("Tehran");
    }

    private void getWeatherData(String cityName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);

        Call<WeatherResponse> call = weatherAPI.getWeather(API_KEY, cityName, "metric");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        Log.d("Weather", "Temp: " + weatherResponse.getCurrent().getTemp_c() + "°C");
                        Log.d("Weather", "Condition: " + weatherResponse.getCurrent().getCondition().getText());
                    }
                } else {
                    Log.e("Weather", "Response not successful: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("Weather", "Error: " + t.getMessage());
            }
        });
    }

    public interface WeatherAPI {
        @GET("current.json")
        Call<WeatherResponse> getWeather(
                @Query("key") String apiKey,
                @Query("q") String cityName,
                @Query("units") String units
        );
    }

    // مدل داده‌ها
    public static class WeatherResponse {
        private Current current;

        public Current getCurrent() {
            return current;
        }

        public static class Current {
            private double temp_c;
            private Condition condition;

            public double getTemp_c() {
                return temp_c;
            }

            public Condition getCondition() {
                return condition;
            }
        }

        public static class Condition {
            private String text;

            public String getText() {
                return text;
            }
        }
    }
}
