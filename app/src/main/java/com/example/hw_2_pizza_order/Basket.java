package com.example.hw_2_pizza_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.hw_2_pizza_order.databinding.BasketBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Basket extends AppCompatActivity implements Navigation{

    BasketBinding basketBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basketBinding = BasketBinding.inflate(getLayoutInflater());
        setContentView(basketBinding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.basket); // Замените на соответствующий элемент вашей активити
        navigation_function();
    }

    @Override
    public void navigation_function() {
        basketBinding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent;
            switch (item.getItemId()){
                case R.id.Menu:
                    intent = new Intent(Basket.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.basket:
                    intent = new Intent(Basket.this, Basket.class);
                    startActivity(intent);
                    break;
                case R.id.about:
                    intent = new Intent(Basket.this, About_program.class);
                    startActivity(intent);
                    break;
            }
            return true;
        });
    }
}
