package com.example.setarekhan;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookDetailsScreen extends AppCompatActivity {

    private ReviewAdapter reviewAdapter;
    private final List<Review> reviews = new ArrayList<>();

    private EditText editTextReview;
    private RatingBar ratingBar;

    private Book book;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_screen);

        TextView titleTextView = findViewById(R.id.book_title);
        TextView authorTextView = findViewById(R.id.book_author);
        TextView descriptionTextView = findViewById(R.id.book_description);
        ImageView bookImage = findViewById(R.id.book_image);
        RecyclerView reviewRecyclerView = findViewById(R.id.review_recycler);
        editTextReview = findViewById(R.id.editTextReview);
        ratingBar = findViewById(R.id.ratingBar);
        Button buttonSubmitReview = findViewById(R.id.buttonSubmitReview);

        reviewAdapter = new ReviewAdapter(reviews);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewRecyclerView.setAdapter(reviewAdapter);

        book = (Book) getIntent().getSerializableExtra("book");
        if (book != null) {
            titleTextView.setText(book.getTitle());
            authorTextView.setText(book.getAuthor());
            descriptionTextView.setText(book.getDescription());

            String imageName = book.getImagePath();
            if (imageName != null) {
                imageName = imageName.replace(".jpg", "").replace(".png", "").toLowerCase();
                @SuppressLint("DiscouragedApi") int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
                if (resId != 0) {
                    bookImage.setImageResource(resId);
                } else {
                    bookImage.setImageResource(R.drawable.default_image);
                }
            }

            fetchReviews(book.getId());

            buttonSubmitReview.setOnClickListener(v -> submitReview());
        } else {
            Log.e("BookDetailsScreen", "کتاب null است!");
        }
    }

    private void submitReview() {
        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String userName = prefs.getString("username", "کاربر ناشناس");

        String reviewText = editTextReview.getText().toString().trim();
        int rating = (int) ratingBar.getRating();

        if (reviewText.isEmpty()) {
            Toast.makeText(this, "لطفاً نظر خود را وارد کنید", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://setarekhan-backend-api.onrender.com/kaka/r";

        JSONObject body = new JSONObject();
        try {
            body.put("bookId", book.getId());
            body.put("userName", userName);
            body.put("review", reviewText);
            body.put("rating", rating);
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                response -> {
                    Toast.makeText(this, "نظر شما ثبت شد", Toast.LENGTH_SHORT).show();
                    editTextReview.setText("");
                    ratingBar.setRating(4.0f);
                    fetchReviews(book.getId());
                },
                error -> {
                    Toast.makeText(this, "خطا در ثبت نظر", Toast.LENGTH_SHORT).show();
                    Log.e("ReviewError", error.toString());
                });

        Volley.newRequestQueue(this).add(request);
    }

    private void fetchReviews(String bookId) {
        String url = "https://setarekhan-backend-api.onrender.com/kaka/book/" + bookId;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                this::parseReviewResponse,
                error -> Log.e("Volley", "خطا در دریافت نظرات: " + error.toString()));

        Volley.newRequestQueue(this).add(request);
    }

    @SuppressLint("NotifyDataSetChanged")
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
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
        reviewAdapter.notifyDataSetChanged();
    }
}
