package com.example.hw_2_pizza_order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import com.example.hw_2_pizza_order.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Navigation {
    ArrayList<Pizza> pizzas;
    ArrayList<String> id, name, recipe,
            eighteen_price, twenty_four_price, thirty_price,
            eighteen_weight, twenty_four_weight, thirty_weight;
    ArrayList<Integer> images;
    ActivityMainBinding binding;
    DataBasePizzaManager dataBasePizzaManager;
    PizzaAdapter pizzaAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataBasePizzaManager = new DataBasePizzaManager(MainActivity.this);
        recyclerView = binding.listOfPizza;

        InsertData();

        id = new ArrayList<>();
        name = new ArrayList<>();
        recipe = new ArrayList<>();
        eighteen_price = new ArrayList<>();
        twenty_four_price = new ArrayList<>();
        thirty_price = new ArrayList<>();
        eighteen_weight = new ArrayList<>();
        twenty_four_weight = new ArrayList<>();
        thirty_weight = new ArrayList<>();
        images = new ArrayList<>();
        pizzas = new ArrayList<>();

        DisplayData();

        pizzaAdapter = new PizzaAdapter(MainActivity.this, pizzas); //Отправляем массив пиц в адаптер

        recyclerView.setAdapter(pizzaAdapter); //Связываем адаптер с шаблоном 1-ой строки
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    //Получаем данные из курсора и вставляем в массивы
    void DisplayData(){
        Cursor cursor = dataBasePizzaManager.ReadData();
        if (cursor.getCount() == 0){}
        else { //Берём строковые данные из БД
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                recipe.add(cursor.getString(2));
                eighteen_price.add(cursor.getString(3));
                twenty_four_price.add(cursor.getString(4));
                thirty_price.add(cursor.getString(5));
                eighteen_weight.add(cursor.getString(6));
                twenty_four_weight.add(cursor.getString(7));
                thirty_weight.add(cursor.getString(8));
            }
            //Вставляем фотки в массив фоток

            images.add(0, R.drawable.id_1);

            //Добавляем данные в объект пицца
            for(int i = 0; i < 1; i++){
                pizzas.add(new Pizza(
                        id.get(i),
                        name.get(i),
                        recipe.get(i),
                        images.get(i),
                        eighteen_price.get(i),
                        twenty_four_price.get(i),
                        thirty_price.get(i),
                        eighteen_weight.get(i),
                        twenty_four_weight.get(i),
                        thirty_weight.get(i)
                    )
                );
            }

        }


    }

    //Вставляем даные в базу данных
    void InsertData(){
        dataBasePizzaManager.InsertData(
                1, "BBQ", "сыр",
                "200", "299", "345",
                "400", "600", "900"
        );
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