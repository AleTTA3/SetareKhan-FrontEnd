package com.example.setarekhan;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Typeface;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class MenuBar extends AppCompatActivity {

    private void addCustomMenuItem(Menu menu, int id, String title) {
        MenuItem item = menu.add(Menu.NONE, id, Menu.NONE, title);

        TextView tv = new TextView(this);
        tv.setText(title);
        tv.setTextSize(18);
        tv.setPadding(20, 10, 20, 10);
        tv.setTextColor(getResources().getColor(android.R.color.white));

        Typeface typeface = ResourcesCompat.getFont(this, R.font.iranyekanwebextrabold);
        tv.setTypeface(typeface);

        item.setActionView(tv);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        addCustomMenuItem(menu, 1, "خانه");
        addCustomMenuItem(menu, 2, "ورود");
        addCustomMenuItem(menu, 3, "ثبت نام");
        addCustomMenuItem(menu, 4, "پروفایل");
        addCustomMenuItem(menu, 5, "ماشین حساب");
        addCustomMenuItem(menu, 6, "کلاس Intent");
        addCustomMenuItem(menu, 7, "قیمت رمز ارز های محبوب");
        addCustomMenuItem(menu, 8, "آب و هوای تهران");
        return true;
    }

    private void createMenuItemWithFont(Menu menu, int id, String title) {
        MenuItem item = menu.add(Menu.NONE, id, Menu.NONE, title);

        TextView tv = new TextView(this);
        tv.setText(title);
        tv.setPadding(20, 10, 20, 10);
        tv.setTextSize(16);
        tv.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        Typeface typeface = ResourcesCompat.getFont(this, R.font.iranyekanwebextrabold); // یا bnazanin
        tv.setTypeface(typeface);

        item.setActionView(tv);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(this, BookListScreen.class);
                startActivity(intent);
                Toast.makeText(this, "این خانه است", Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(this, "این ورود است", Toast.LENGTH_SHORT).show();
                return true;
            case 3:
                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                Toast.makeText(this, "این ثبت نام است", Toast.LENGTH_SHORT).show();
                return true;
            case 4:
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                Toast.makeText(this, "این پروفایل است", Toast.LENGTH_SHORT).show();
                return true;
            case 5:
                intent = new Intent(this, Calculater.class);
                startActivity(intent);
                Toast.makeText(this, "این ماشین حساب است", Toast.LENGTH_SHORT).show();
                return true;
            case 6:
                intent = new Intent(this, intent.class);
                startActivity(intent);
                Toast.makeText(this, "این کلاس intent است", Toast.LENGTH_SHORT).show();
                return true;
            case 7:
                intent = new Intent(this, CoinActivity.class);
                startActivity(intent);
                Toast.makeText(this, "صفحه رمز ارز ها است", Toast.LENGTH_SHORT).show();
                return true;
            case 8:
                intent = new Intent(this, WeatherScreen.class);
                startActivity(intent);
                Toast.makeText(this, "آب و هوای تهران...", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}