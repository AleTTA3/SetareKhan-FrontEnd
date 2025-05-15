package com.example.setarekhan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookListScreen extends MenuBar {

    private List<Book> books = new ArrayList<>();
    private RecyclerView recyclerView;
    private BookRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_screen);

        recyclerView = findViewById(R.id.book_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // دو ستون
        adapter = new BookRecyclerAdapter(this, books);
        recyclerView.setAdapter(adapter);

        fetchBooks();
    }

    private void fetchBooks() {
        String url = "https://setarekhan-backend-api.onrender.com/kaka/";

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        books.clear();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            String id = obj.getString("_id");
                            String title = obj.getString("title");
                            String author = obj.getString("author");
                            String description = obj.getString("description");
                            String imagePath = obj.getString("imagePath"); // ✅ اضافه شد
                            List<String> reviews = Arrays.asList("نقدی ثبت نشده"); // یا بعداً از سرور بیاد

                            Book book = new Book(id, title, author, description, imagePath, reviews);
                            books.add(book);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        Log.e("BookListScreen", "JSON parsing error", e);
                    }
                },
                error -> Log.e("BookListScreen", "Volley error", error)
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
