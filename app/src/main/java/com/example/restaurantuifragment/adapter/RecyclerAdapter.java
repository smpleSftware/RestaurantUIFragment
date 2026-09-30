package com.example.restaurantuifragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantuifragment.R;
import com.example.restaurantuifragment.databinding.RecyclerRowBinding;
import com.example.restaurantuifragment.model.RestaurantModel;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    ArrayList<RestaurantModel> restaurantModels;

    public RecyclerAdapter(ArrayList<RestaurantModel> restaurantModels) {
        this.restaurantModels = restaurantModels;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecyclerHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        holder.binding.restaurantName.setText(restaurantModels.get(position).restaurantName);
        holder.binding.restaurantCommentCount.setText(restaurantModels.get(position).restaurantCommentCount.toString());
        holder.binding.restaurantLogo.setImageResource(restaurantModels.get(position).restaurantImage);

        Integer[] starList = {R.id.restaurantStar1,R.id.restaurantStar2,R.id.restaurantStar3,R.id.restaurantStar4,R.id.restaurantStar5 };
        for (int i=0; i<restaurantModels.get(position).restaurantStar; i++){
            holder.itemView.findViewById(starList[i]).setVisibility(View.VISIBLE);
        }

        if (restaurantModels.get(position).restaurantRecommended){
            holder.binding.restaurantRecommended.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return restaurantModels.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder{

        RecyclerRowBinding binding;

        public RecyclerHolder(@NonNull RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
