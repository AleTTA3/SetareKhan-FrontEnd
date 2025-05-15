package com.example.setarekhan;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
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

        String imageName = book.getImagePath();

        if (imageName != null && !imageName.isEmpty()) {
            // حذف پسوند و حروف بزرگ
            imageName = imageName.replace(".jpg", "").replace(".png", "").toLowerCase();

            int resId = getContext().getResources().getIdentifier(
                    imageName,
                    "drawable",
                    getContext().getPackageName()
            );

            // چک دقیق و ایمن
            if (resId != 0) {
                try {
                    image.setImageResource(resId);
                } catch (Resources.NotFoundException e) {
                    Log.e("BookAdapter", "تصویر یافت نشد برای: " + imageName, e);
                    image.setImageResource(R.drawable.default_image);
                }
            } else {
                image.setImageResource(R.drawable.default_image);
            }
        } else {
            image.setImageResource(R.drawable.default_image);
        }

        return convertView;
    }


}
