package com.example.setarekhan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
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

    List<Book> books = new ArrayList<>();
    ListView listView;
    BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_screen);

        listView = findViewById(R.id.book_list_view);
        adapter = new BookAdapter(this, books);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(BookListScreen.this, BookDetailsScreen.class);
            intent.putExtra("book", books.get(position));  // کل کتاب رو می‌فرستیم
            startActivity(intent);
        });

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
