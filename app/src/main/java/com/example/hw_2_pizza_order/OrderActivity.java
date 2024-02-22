package com.example.hw_2_pizza_order;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw_2_pizza_order.entities.ConstantsStore;
import com.example.hw_2_pizza_order.entities.Order;
import com.example.hw_2_pizza_order.entities.PizzaRecipe;
import com.example.hw_2_pizza_order.entities.PizzaSize;
import com.example.hw_2_pizza_order.entities.PizzaTopping;
import com.example.hw_2_pizza_order.entities.ToppingCount;

public class OrderActivity extends AppCompatActivity {

    private TextView tvPizzaRecipe;
    private TextView tvPizzaSize;
    private TextView tvPizzaAmount;
    private TextView tvTopping;
    private TextView tvPrice;

    private EditText etPersonName;
    private EditText etPhone;

    private Button btnOrder;

    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initView();
        setListener();
        initData();
    }





    private void initView() {
        tvPizzaRecipe = findViewById(R.id.tvPizzaRecipe);
        tvPizzaSize = findViewById(R.id.tvPizzaSize);
        tvPizzaAmount = findViewById(R.id.tvPizzaAmount);
        tvTopping = findViewById(R.id.tvTopping);
        tvPrice = findViewById(R.id.tvPrice);

        etPersonName = findViewById(R.id.etPersonName);
        etPhone = findViewById(R.id.etPhone);

        btnOrder = findViewById(R.id.btnOrder);
    }

    private void setListener() {
        Resources resources = getResources();
        btnOrder.setOnClickListener(view -> {
            if (etPersonName.getText().toString().trim().length() == 0 || etPhone.getText().toString().trim().length() == 0) {
                String message = resources.getString(R.string.messageInputYourData);
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            } else {
                String message = resources.getString(R.string.messageOrder);
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra(ConstantsStore.KEY_ORDER);
        Resources resources = getResources();
        setTvPizzaRecipe(resources);
        setTvPizzaSize(resources);
        setTvPizzaAmount();
        setTvToppings(resources);
        setTvPrice(resources);
    }

    private void setTvPrice(Resources resources) {
        double priceToppings = 0;
        for (ToppingCount toppingCount : order.getToppingCountList()) {
            priceToppings += toppingCount.getPizzaTopping().getCost() * toppingCount.getCount();
        }

        double pizzaPriceWithToppings = order.getPizzaRecipe().getCost() + priceToppings;

        double price = (pizzaPriceWithToppings + (pizzaPriceWithToppings * order.getPizzaSize().getMargin() / 100)) * order.getPizzaCount();

        String newPrice = String.valueOf(price) + resources.getString(R.string.currencyType);
        tvPrice.setText(newPrice);
    }

    private void setTvToppings(Resources resources) {
        if (order.getToppingCountList().size() > 0) {
            for (ToppingCount toppingCount : order.getToppingCountList()) {
                String newTvToppingText = tvTopping.getText() +
                        (tvTopping.getText().length() > 0 ? "\n" : "") +
                        toppingSelection(resources, toppingCount);
                tvTopping.setText(newTvToppingText);
            }
        }
    }

    private String toppingSelection(Resources resources, ToppingCount toppingCount) {
        if (PizzaTopping.MEAT == toppingCount.getPizzaTopping()) {
            return resources.getString(R.string.tvMeat) + " - " + toppingCount.getCount();
        } else if (PizzaTopping.MUSHROOMS == toppingCount.getPizzaTopping()) {
            return resources.getString(R.string.tvMushroom) + " - " + toppingCount.getCount();
        }
        return resources.getString(R.string.tvCheese) + " - " + toppingCount.getCount();
    }

    private void setTvPizzaAmount() {
        tvPizzaAmount.setText(String.valueOf(order.getPizzaCount()));
    }

    private void setTvPizzaSize(Resources resources) {
        if (PizzaSize.LARGE == order.getPizzaSize()) {
            tvPizzaSize.setText(resources.getString(R.string.rBtnLarge));
        } else if (PizzaSize.MEDIUM == order.getPizzaSize()) {
            tvPizzaSize.setText(resources.getString(R.string.rBtnMedium));
        } else if (PizzaSize.SMALL == order.getPizzaSize()) {
            tvPizzaSize.setText(resources.getString(R.string.rBtnSmall));
        }
    }
    private void setTvPizzaRecipe(Resources resources) {
        if (PizzaRecipe.HAWAII_PIZZA == order.getPizzaRecipe()) {
            tvPizzaRecipe.setText(resources.getString(R.string.tvPizza1));
        } else if (PizzaRecipe.CARBONARA_PIZZA == order.getPizzaRecipe()) {
            tvPizzaRecipe.setText(resources.getString(R.string.tvPizza2));
        } else if (PizzaRecipe.HUNTERS_PIZZA == order.getPizzaRecipe()) {
            tvPizzaRecipe.setText(resources.getString(R.string.tvPizza3));
        }
    }

}
