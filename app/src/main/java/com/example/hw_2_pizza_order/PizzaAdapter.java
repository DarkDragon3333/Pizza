package com.example.hw_2_pizza_order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder>{
    private final LayoutInflater inflater; //XML для данных
    private final List<Pizza> pizzas; //Массив с данными

    //Конструктор адаптера
    PizzaAdapter(Context context, List<Pizza> pizzas) {
        this.pizzas = pizzas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public PizzaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_of_pizzas, parent, false);
        return new ViewHolder(view);
    }

    @Override  //Получаем данные
    public void onBindViewHolder(PizzaAdapter.ViewHolder holder, int position) {
        Pizza pizza = pizzas.get(position);
        holder.flagView.setImageResource(pizza.getPicture());
        holder.nameView.setText(pizza.getName());
        holder.recipeView.setText(pizza.getRecipe());
        holder.button_with_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }
    //Получаем view, в которые будет вставлять данные
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView flagView;
        final TextView nameView, recipeView;
        final Button button_with_price;
        ViewHolder(View view){
            super(view);
            flagView = view.findViewById(R.id.pizza_Photo);
            nameView = view.findViewById(R.id.name_of_pizza);
            recipeView = view.findViewById(R.id.tvPizzaRecipe);
            button_with_price = view.findViewById((R.id.button_with_price));
        }
    }
}
