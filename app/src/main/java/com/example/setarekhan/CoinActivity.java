package com.example.setarekhan;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class CoinActivity extends AppCompatActivity {

    TextView txtBitcoin, txtEthereum, txtDoge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        txtBitcoin = findViewById(R.id.txtBitcoin);
        txtEthereum = findViewById(R.id.txtEthereum);
        txtDoge = findViewById(R.id.txtDoge);

        fetchCryptoPrices();
    }

    private void fetchCryptoPrices() {
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,dogecoin&vs_currencies=usd";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        double bitcoin = jsonObject.getJSONObject("bitcoin").getDouble("usd");
                        double ethereum = jsonObject.getJSONObject("ethereum").getDouble("usd");
                        double doge = jsonObject.getJSONObject("dogecoin").getDouble("usd");

                        txtBitcoin.setText("Bitcoin: $" + bitcoin);
                        txtEthereum.setText("Ethereum: $" + ethereum);
                        txtDoge.setText("Dogecoin: $" + doge);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    txtBitcoin.setText("خطا در دریافت قیمت‌ها!");
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
