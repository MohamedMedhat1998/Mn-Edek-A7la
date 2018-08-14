package com.andalus.abomed7at55.mn_edek_a7la.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andalus.abomed7at55.mn_edek_a7la.Objects.FoodCategory;
import com.andalus.abomed7at55.mn_edek_a7la.Objects.Recipe;
import com.andalus.abomed7at55.mn_edek_a7la.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeItemHolder>{

    private List<Recipe> mData;
    private Context mContext;

    public RecipesAdapter(List<Recipe> data){
        mData = data;
    }

    @NonNull
    @Override
    public RecipeItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recipe_item,parent,false);
        return new RecipeItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeItemHolder holder, int position) {
        holder.tvRecipeItemTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext)
                .load(mData.get(position).getPhotoLink())
                .apply(new RequestOptions().placeholder(getPlaceHolderId(position)))
                .into(holder.ivRecipeItemMainImage);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class RecipeItemHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_recipe_item_title)
        TextView tvRecipeItemTitle;
        @BindView(R.id.iv_recipe_item_main_image)
        ImageView ivRecipeItemMainImage;

        RecipeItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private int getPlaceHolderId(int position){
        int id = 0;
        switch (mData.get(position).getCategory()){
            case FoodCategory.MEAT_TAG:
                id = R.drawable.chicken_hot_icon;
                break;
            case FoodCategory.FISH_TAG:
                id = R.drawable.fish_icon;
                break;
            case FoodCategory.SWEET_TAG:
                id = R.drawable.cake_icon;
                break;
            case FoodCategory.DRINK_TAG:
                id = R.drawable.juice_icon;
                break;
            case FoodCategory.STARCHES_TAG:
                id = R.drawable.rice_icon;
                break;
            case FoodCategory.APPETIZER_TAG:
                id = R.drawable.snack_icon;
                break;
            case FoodCategory.IDEA_TAG:
                id = R.drawable.ligh_bulb_icon;
                break;
            case FoodCategory.DIET_TAG:
                id = R.drawable.diet_icon;
                break;
            case FoodCategory.BAKERY_TAG:
                id = R.drawable.bread_icon;
                break;
            case FoodCategory.MILK_TAG:
                id = R.drawable.milk_bottle_icon;
                break;
                //TODO if you added a category, add its placeholder icon here
        }
        return id;
    }
}
