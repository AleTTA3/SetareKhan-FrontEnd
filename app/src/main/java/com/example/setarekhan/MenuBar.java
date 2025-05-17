package com.example.setarekhan;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_main, menu);

        // سرچ‌ویو
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("نام کتاب را وارد کنید");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (MenuBar.this instanceof BookListScreen) {
                    ((BookListScreen) MenuBar.this).performSearch(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (MenuBar.this instanceof BookListScreen) {
                    ((BookListScreen) MenuBar.this).performSearch(newText);
                }
                return true;
            }
        });

        // آیتم‌های منو
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



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case 1:
                intent = new Intent(this, BookListScreen.class);
                break;
            case 2:
                intent = new Intent(this, LoginActivity.class);
                break;
            case 3:
                intent = new Intent(this, SignUpActivity.class);
                break;
            case 4:
                intent = new Intent(this, ProfileActivity.class);
                break;
            case 5:
                intent = new Intent(this, Calculater.class);
                break;
            case 6:
                intent = new Intent(this, intent.class);
                break;
            case 7:
                intent = new Intent(this, CoinActivity.class);
                break;
            case 8:
                intent = new Intent(this, Weather.class);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        startActivity(intent);
        return true;
    }
}
