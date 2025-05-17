package com.example.setarekhan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.BookViewHolder> {

    private final Context context;
    private List<Book> bookList;
    private List<Book> fullBookList;

    public BookRecyclerAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
        this.fullBookList = new ArrayList<>(bookList);
    }
    public void setFullBookList(List<Book> fullList) {
        this.fullBookList = new ArrayList<>(fullList);
    }


    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public BookViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.book_image);
            title = itemView.findViewById(R.id.book_title);
        }
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.title.setText(book.getTitle());

        String imageName = book.getImagePath();

        if (imageName != null && !imageName.isEmpty()) {
            imageName = imageName.replace(".jpg", "").replace(".png", "").toLowerCase();

            @SuppressLint("DiscouragedApi") int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

            if (resId != 0) {
                try {
                    holder.imageView.setImageResource(resId);
                } catch (Resources.NotFoundException e) {
                    holder.imageView.setImageResource(R.drawable.default_image);
                }
            } else {
                holder.imageView.setImageResource(R.drawable.default_image);
            }
        } else {
            holder.imageView.setImageResource(R.drawable.default_image);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookDetailsScreen.class);
            intent.putExtra("book", book);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void filter(String query) {
        List<Book> filtered = new ArrayList<>();
        for (Book book : fullBookList) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(book);
            }
        }
        filterList(filtered);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(List<Book> filteredList) {
        this.bookList = filteredList;
        notifyDataSetChanged();
    }
}
