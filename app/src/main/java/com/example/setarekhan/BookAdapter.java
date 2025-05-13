
package com.example.setarekhan;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Activity context, List<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }

        TextView title = convertView.findViewById(R.id.book_title);
        ImageView image = convertView.findViewById(R.id.book_image);

        title.setText(book.getTitle());
        image.setImageResource(book.getImageResId());

        return convertView;
    }
}
