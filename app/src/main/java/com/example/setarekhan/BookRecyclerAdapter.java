package com.example.setarekhan;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.BookViewHolder> {

    private Context context;
    private List<Book> bookList;

    public BookRecyclerAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
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

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

            int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

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
}
