package com.example.hw_2_pizza_order;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Objects;

public class DataBasePizzaManager extends SQLiteOpenHelper {

    private static String DB_PATH; // полный путь к базе данных
    private static String DB_NAME = "pizza.db";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "users"; // название таблицы в бд
    private Context myContext;
    public DataBasePizzaManager(Context context) {
        super(context, DB_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }

    void create_db(){
        File file = new File(DB_PATH);
        if (!file.exists()) {
            //получаем локальную бд как поток
            try(InputStream myInput = myContext.getAssets().open(DB_NAME);
                // Открываем пустую бд
                OutputStream myOutput = Files.newOutputStream(Paths.get(DB_PATH))) {

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
            }
            catch(IOException ex){
                Log.d("DatabaseHelper", Objects.requireNonNull(ex.getMessage()));
            }
        }
    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
