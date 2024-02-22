package com.example.hw_2_pizza_order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hw_2_pizza_order.entities.ConstantsStore;
import com.example.hw_2_pizza_order.entities.Order;
import com.example.hw_2_pizza_order.entities.PizzaRecipe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvCountPizza;

    private ImageButton imageButtonMinus;
    private ImageButton imageButtonPlus;

    private ImageButton ibPizza1;
    private ImageButton ibPizza2;
    private ImageButton ibPizza3;

    private ConstraintLayout clPizza1;
    private ConstraintLayout clPizza2;
    private ConstraintLayout clPizza3;

    private Button btnShowActivitySize;

    private Order order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
        initData();
    }

    private void initData() {
        order = new Order();
    }

    private void initView(){
        tvCountPizza = findViewById(R.id.tvCountPizza);
        imageButtonMinus=findViewById(R.id.ibMinus);
        imageButtonPlus=findViewById(R.id.ibPlus);

        ibPizza1=findViewById(R.id.ibPizza1);
        ibPizza2=findViewById(R.id.ibPizza2);
        ibPizza3=findViewById(R.id.ibPizza3);

        clPizza1=findViewById(R.id.clPizza1);
        clPizza2=findViewById(R.id.clPizza2);
        clPizza3=findViewById(R.id.clPizza3);

        btnShowActivitySize=findViewById(R.id.btnShowActivitySize);

    }

    private void setListener(){
        imageButtonPlus.setOnClickListener(this);
        imageButtonMinus.setOnClickListener(this);

        ibPizza1.setOnClickListener(this);
        ibPizza2.setOnClickListener(this);
        ibPizza3.setOnClickListener(this);

        clPizza1.setOnClickListener(this);
        clPizza2.setOnClickListener(this);
        clPizza3.setOnClickListener(this);

        btnShowActivitySize.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int btnId=view.getId();
        switch (btnId){
            case R.id.ibPlus:{
                changeCountPizza(true);
                break;
            }
            case R.id.ibMinus:{
                changeCountPizza(false);
                break;
            }
            case R.id.ibPizza1:
            case R.id.ibPizza2:
            case R.id.ibPizza3:
            case R.id.clPizza1:
            case R.id.clPizza2:
            case R.id.clPizza3: {
                choosePizzaRecipe(btnId);
                break;
            }
            case R.id.btnShowActivitySize:{
                showPizzaSizeActivity();
                break;
            }
        }
    }

    private void showPizzaSizeActivity() {
        if (tvCountPizza.getText().equals(tvCountPizzaStart())){
            showChoosePizzaRecipeMessage();
        }else {
            Intent intent = new Intent(this, PizzaSizeActivity.class);
            intent.putExtra(ConstantsStore.KEY_ORDER, order);
            startActivity(intent);
        }

    }


    private void changeCountPizza(boolean isPlus) {
        if(!tvCountPizza.getText().toString().equals(tvCountPizzaStart())){
            int countPizza = Integer.parseInt(tvCountPizza.getText().toString());
            countPizza = isPlus ? countPizza + 1 : countPizza > 1 ? countPizza - 1 : countPizza;
            order.setPizzaCount(countPizza);
            tvCountPizza.setText("" + countPizza);
        } else{
            showChoosePizzaRecipeMessage();
        }
    }

    private void showChoosePizzaRecipeMessage() {
        Resources resources = getResources();
        String message = resources.getString(R.string.messageChooseRecipe);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String tvCountPizzaStart() {
        Resources resources = getResources();
        return "-";
    }

   private void choosePizzaRecipe(int btn_id){
       Resources resources = getResources();
       Drawable drawableClChoseRecipe = resources.getDrawable(R.drawable.rectangle_rounded_chose_recipe);
       Drawable drawableClNotChoseRecipe = resources.getDrawable(R.drawable.rectangle_rounded);
       Drawable drawableIbChoseRecipe = resources.getDrawable(R.drawable.circle_ib_pizza_chose);
       Drawable drawableIbNotChoseRecipe = resources.getDrawable(R.drawable.circle_ib_pizza_notchose);

       switch (btn_id){
           case R.id.ibPizza1:
           case R.id.clPizza1:{
               clPizza1.setBackground(drawableClChoseRecipe);
               clPizza2.setBackground(drawableClNotChoseRecipe);
               clPizza3.setBackground(drawableClNotChoseRecipe);
               ibPizza1.setBackground(drawableIbChoseRecipe);
               ibPizza2.setBackground(drawableIbNotChoseRecipe);
               ibPizza3.setBackground(drawableIbNotChoseRecipe);
               order.setPizzaRecipe(PizzaRecipe.HAWAII_PIZZA);
               break;
           }
           case R.id.ibPizza2:
           case R.id.clPizza2:{
               clPizza1.setBackground(drawableClNotChoseRecipe);
               clPizza2.setBackground(drawableClChoseRecipe);
               clPizza3.setBackground(drawableClNotChoseRecipe);
               ibPizza1.setBackground(drawableIbNotChoseRecipe);
               ibPizza2.setBackground(drawableIbChoseRecipe);
               ibPizza3.setBackground(drawableIbNotChoseRecipe);
               order.setPizzaRecipe(PizzaRecipe.CARBONARA_PIZZA);
               break;
           }
           case R.id.ibPizza3:
           case R.id.clPizza3:{
               clPizza1.setBackground(drawableClNotChoseRecipe);
               clPizza2.setBackground(drawableClNotChoseRecipe);
               clPizza3.setBackground(drawableClChoseRecipe);
               ibPizza1.setBackground(drawableIbNotChoseRecipe);
               ibPizza2.setBackground(drawableIbNotChoseRecipe);
               ibPizza3.setBackground(drawableIbChoseRecipe);
               order.setPizzaRecipe(PizzaRecipe.HUNTERS_PIZZA);
               break;
           }
       }
       if(tvCountPizza.getText().equals(tvCountPizzaStart())){
           order.setPizzaCount(1);
           tvCountPizza.setText("1");
       }


   }
}