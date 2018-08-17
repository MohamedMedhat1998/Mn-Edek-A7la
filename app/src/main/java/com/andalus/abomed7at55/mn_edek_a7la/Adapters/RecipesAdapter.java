package com.andalus.abomed7at55.mn_edek_a7la.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andalus.abomed7at55.mn_edek_a7la.Interfaces.OnRecipeClickListener;
import com.andalus.abomed7at55.mn_edek_a7la.Objects.FoodCategory;
import com.andalus.abomed7at55.mn_edek_a7la.Objects.Recipe;
import com.andalus.abomed7at55.mn_edek_a7la.R;
import com.andalus.abomed7at55.mn_edek_a7la.Utils.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeItemHolder>{

    private List<Recipe> mData;
    private Context mContext;
    private OnRecipeClickListener mOnRecipeClickListener;

    public RecipesAdapter(List<Recipe> data, OnRecipeClickListener onRecipeClickListener){
        mData = data;
        mOnRecipeClickListener = onRecipeClickListener;
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
                .apply(new RequestOptions().placeholder(ImageUtils.getPlaceHolderId(mData.get(position).getCategory())))
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnRecipeClickListener.onRecipeClicked(mData.get(getAdapterPosition()));
                }
            });
        }
    }
}
