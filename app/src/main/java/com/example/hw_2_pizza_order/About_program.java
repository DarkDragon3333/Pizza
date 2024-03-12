package com.example.hw_2_pizza_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.hw_2_pizza_order.databinding.AboutProgramBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About_program extends AppCompatActivity implements Navigation{

    AboutProgramBinding aboutProgramBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutProgramBinding = AboutProgramBinding.inflate(getLayoutInflater());
        setContentView(aboutProgramBinding.getRoot());
    }
    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.about); // Замените на соответствующий элемент вашей активити
        navigation_function();
    }

    @Override
    public void navigation_function() {
        aboutProgramBinding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent intent;
            switch (item.getItemId()){
                case R.id.Menu:
                    intent = new Intent(About_program.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.basket:
                    intent = new Intent(About_program.this, Basket.class);
                    startActivity(intent);
                    break;
                case R.id.about:
                    intent = new Intent(About_program.this, About_program.class);
                    startActivity(intent);
                    break;
            }
            return true;
        });
    }
}
