package com.example.hw_2_pizza_order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.hw_2_pizza_order.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity implements Navigation {
    SQLiteDatabase db;
    File dbFile;
    ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    DataBasePizzaManager databaseHelper;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbFile = getBaseContext().getDatabasePath("Pizza.bd");
        if (!dbFile.exists()) {
            try {
                InputStream inputStream = getBaseContext().getAssets().open("Pizza.db");
                OutputStream outputStream = Files.newOutputStream(dbFile.toPath());
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        RecyclerView recyclerView = findViewById(R.id.list);

        // Получаем доступ к базе данных
        db = SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);

        // Выполняем SQL-запрос
        Cursor cursor = db.rawQuery("SELECT * FROM Name_and_price", null);

        // Собираем данные из курсора и выводим в textView
        StringBuilder stringBuilder = new StringBuilder();
        /*while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            stringBuilder.append("Name: ").append(name).append(", Description: ").append(description).append("\n");
        }
        textView.setText(stringBuilder.toString());*/

        // Закрываем курсор и базу данных
        cursor.close();
        db.close();

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent, intent1;
            switch (item.getItemId()){
                case R.id.basket:
                    intent = new Intent(MainActivity.this, Basket.class);
                    startActivity(intent);
                    break;
                case R.id.about:
                    intent1 = new Intent(MainActivity.this, About_program.class);
                    startActivity(intent1);
                    break;
            }
            return true;
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Menu); // Замените на соответствующий элемент вашей активити
        navigation_function();
    }

    @Override
    public void navigation_function() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent;
            switch (item.getItemId()){
                case R.id.about:
                    intent = new Intent(MainActivity.this, About_program.class);
                    startActivity(intent);
                    break;
                case R.id.basket:
                    intent = new Intent(MainActivity.this, Basket.class);
                    startActivity(intent);
                    break;

            }
            return true;
        });
    }
}