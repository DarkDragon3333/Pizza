package com.example.hw_2_pizza_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw_2_pizza_order.entities.ConstantsStore;
import com.example.hw_2_pizza_order.entities.Order;
import com.example.hw_2_pizza_order.entities.PizzaSize;
import com.example.hw_2_pizza_order.entities.PizzaTopping;
import com.example.hw_2_pizza_order.entities.ToppingCount;

public class PizzaSizeActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup rgPizzaSize;

    private ImageView ivMinusMeat;
    private ImageView ivPlusMeat;
    private ImageView ivMinusMushroom;
    private ImageView ivPlusMushroom;
    private ImageView ivMinusCheese;
    private ImageView ivPlusCheese;

    private TextView tvCountMeat;
    private TextView tvCountMushroom;
    private TextView tvCountCheese;

    private Button btnMakeOrder;

    private Order order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzasize);

        initView();
        setListener();
        initData();
    }



    private void initView() {
        rgPizzaSize = findViewById(R.id.rgPizzaSize);

        ivMinusMeat = findViewById(R.id.ivMinusMeat);
        ivPlusMeat = findViewById(R.id.ivPlusMeat);
        ivMinusMushroom = findViewById(R.id.ivMinusMushroom);
        ivPlusMushroom = findViewById(R.id.ivPlusMushroom);
        ivMinusCheese = findViewById(R.id.ivMinusCheese);
        ivPlusCheese = findViewById(R.id.ivPlusCheese);

        tvCountMeat = findViewById(R.id.tvCountMeat);
        tvCountMushroom = findViewById(R.id.tvCountMushroom);
        tvCountCheese = findViewById(R.id.tvCountCheese);

        btnMakeOrder = findViewById(R.id.btnMakeOrder);
    }



    private void setListener() {
        rgPizzaSize.setOnCheckedChangeListener(this::setPizzaSize);

        ivMinusMeat.setOnClickListener(this);
        ivPlusMeat.setOnClickListener(this);
        ivMinusMushroom.setOnClickListener(this);
        ivPlusMushroom.setOnClickListener(this);
        ivMinusCheese.setOnClickListener(this);
        ivPlusCheese.setOnClickListener(this);

        btnMakeOrder.setOnClickListener(this);
    }

    private void setPizzaSize(RadioGroup radioGroup, int id) {
        if(id == R.id.rBtnLarge){
            order.setPizzaSize(PizzaSize.LARGE);
        }else if(id == R.id.rBtnMedium){
            order.setPizzaSize(PizzaSize.MEDIUM);
        }else if(id == R.id.rBtnSmall){
            order.setPizzaSize(PizzaSize.SMALL);
        }
    }

    private void initData() {
        Intent intent = getIntent();

        order = (Order) intent.getSerializableExtra(ConstantsStore.KEY_ORDER);
        order.setPizzaSize(PizzaSize.SMALL);
    }

    @Override
    public void onClick(View view) {
        int btnId = view.getId();
        switch (btnId){
            case R.id.ivMinusMeat:
            case R.id.ivPlusMeat:
            case R.id.ivMinusMushroom:
            case R.id.ivPlusMushroom:
            case R.id.ivMinusCheese:
            case R.id.ivPlusCheese: {
                changeToppingsCount(btnId);
                break;
            }
            case R.id.btnMakeOrder:{
                showOrderActivity();
                break;
            }
        }
    }

    private void showOrderActivity() {
        Intent intent  = new Intent(this, OrderActivity.class);
        intent.putExtra(ConstantsStore.KEY_ORDER, order);
        startActivity(intent);
    }

    private void changeToppingsCount(int btnId) {
        switch (btnId){
            case R.id.ivMinusMeat:{
                changeToppingsCounter(tvCountMeat, false);
                break;
            }
            case R.id.ivPlusMeat:{
                changeToppingsCounter(tvCountMeat, true);
                break;
            }
            case R.id.ivMinusMushroom:{
                changeToppingsCounter(tvCountMushroom, false);
                break;
            }
            case R.id.ivPlusMushroom:{
                changeToppingsCounter(tvCountMushroom, true);
                break;
            }
            case R.id.ivMinusCheese:{
                changeToppingsCounter(tvCountCheese, false);
                break;
            }
            case R.id.ivPlusCheese:{
                changeToppingsCounter(tvCountCheese, true);
                break;
            }
        }

        changeOrderToppingsList();
    }

    private void changeOrderToppingsList() {
        order.getToppingCountList().clear();
        if(!tvCountMeat.getText().equals("0")){
            ToppingCount toppingCount = new ToppingCount(PizzaTopping.MEAT, Integer.parseInt(tvCountMeat.getText().toString()));
            order.addToppingCount(toppingCount);
        }
        if(!tvCountMushroom.getText().equals("0")){
            ToppingCount toppingCount = new ToppingCount(PizzaTopping.MUSHROOMS, Integer.parseInt(tvCountMushroom.getText().toString()));
            order.addToppingCount(toppingCount);
        }
        if(!tvCountCheese.getText().equals("0")){
            ToppingCount toppingCount = new ToppingCount(PizzaTopping.CHEESE, Integer.parseInt(tvCountCheese.getText().toString()));
            order.addToppingCount(toppingCount);
        }
    }

    private void changeToppingsCounter(TextView tvCounter, boolean isPlus) {
        int counter = Integer.parseInt(tvCounter.getText().toString());
        tvCounter.setText(isPlus ? (counter < 3 ? "" + (counter + 1) : "" + counter) : (counter > 0 ? "" + (counter - 1) : "" + counter));
    }
}
