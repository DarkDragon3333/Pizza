package com.example.hw_2_pizza_order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hw_2_pizza_order.entities.ConstantsStore;
import com.example.hw_2_pizza_order.entities.Order;
import com.example.hw_2_pizza_order.entities.PizzaRecipe;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    SQLiteDatabase db;
    File dbFile;
    ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    DataBasePizzaManager databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            stringBuilder.append("Name: ").append(name).append(", Description: ").append(description).append("\n");
        }
        textView.setText(stringBuilder.toString());

        // Закрываем курсор и базу данных
        cursor.close();
        db.close();

    }


}