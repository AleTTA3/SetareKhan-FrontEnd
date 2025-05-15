package com.example.setarekhan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookDetailsScreen extends AppCompatActivity {

    private TextView titleTextView, authorTextView, descriptionTextView;
    private ImageView bookImage;
    private RecyclerView reviewRecyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviews = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_screen);

        // دریافت رفرنس به ویوها
        titleTextView = findViewById(R.id.book_title);
        authorTextView = findViewById(R.id.book_author);
        descriptionTextView = findViewById(R.id.book_description);
        bookImage = findViewById(R.id.book_image);
        reviewRecyclerView = findViewById(R.id.review_recycler);

        // راه‌اندازی RecyclerView
        reviewAdapter = new ReviewAdapter(reviews);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewRecyclerView.setAdapter(reviewAdapter);

        // دریافت اطلاعات کتاب

        Book book = (Book) getIntent().getSerializableExtra("book");
        if (book != null) {
            String bookId = book.getId();
            titleTextView.setText(book.getTitle());
            authorTextView.setText(book.getAuthor());
            descriptionTextView.setText(book.getDescription());

            // اصلاح بخش نمایش عکس
            String imageName = book.getImagePath();
            if (imageName != null) {
                imageName = imageName.replace(".jpg", "").replace(".png", "").toLowerCase();
                int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
                if (resId != 0) {
                    bookImage.setImageResource(resId);
                } else {
                    bookImage.setImageResource(R.drawable.default_image);
                }
            } else {
                bookImage.setImageResource(R.drawable.default_image);
            }

            // فراخوانی نظرات از سرور
            fetchReviews(book.getId());
        }
        else {
            Log.e("BookDetailsScreen", "کتاب null است!");
        }
    }

    private void fetchReviews(String bookId) {
        Log.d("BookDetailsScreen", "bookId = " + bookId);
        String url = "https://setarekhan-backend-api.onrender.com/kaka/book/" + bookId;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    parseReviewResponse(response);
                },
                error -> {
                    Log.e("Volley", "خطا در دریافت نظرات: " + error.toString());
                });

        Volley.newRequestQueue(this).add(request);
    }

    private void parseReviewResponse(JSONArray response) {
        reviews.clear();
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject obj = response.getJSONObject(i);
                Review review = new Review(
                        obj.getString("userName"),
                        obj.getString("review"),
                        obj.getInt("rating")
                );
                reviews.add(review);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        reviewAdapter.notifyDataSetChanged();
        Log.d("REVIEW_SIZE", "Review count: " + reviews.size());
    }
}
