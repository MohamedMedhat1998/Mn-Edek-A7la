package com.andalus.abomed7at55.mn_edek_a7la.Adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.andalus.abomed7at55.mn_edek_a7la.Data.AppDatabase;
import com.andalus.abomed7at55.mn_edek_a7la.Interfaces.OnRecipeClickListener;
import com.andalus.abomed7at55.mn_edek_a7la.Objects.FavoriteRecipe;
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

    private static final int LOADER_ID = 10;
    private static final String ADAPTER_POSITION = "adapter_position";

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
        @BindView(R.id.ib_favorite_recipe_item)
        ImageButton ibFavoriteRecipeItem;

        RecipeItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnRecipeClickListener.onRecipeClicked(mData.get(getAdapterPosition()));
                }
            });

            ibFavoriteRecipeItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new FavoriteRecipeAsyncTask(ibFavoriteRecipeItem).execute(mData.get(getAdapterPosition()));
                }
            });
        }
    }

    class FavoriteRecipeAsyncTask extends AsyncTask<Recipe,Integer,Boolean>{

        private ImageButton mImageButton;

        public FavoriteRecipeAsyncTask(ImageButton imageButton){
            mImageButton = imageButton;
        }

        private boolean isFavoriteNow;
        @Override
        protected Boolean doInBackground(Recipe... recipes) {
            Recipe recipe = recipes[0];
            FavoriteRecipe favoriteRecipe = new FavoriteRecipe(recipe.getId(),recipe.getTitle(),recipe.getIngredients(),recipe.getSteps(),recipe.getCategory(),recipe.getPhotoLink(),recipe.getVideoLink());
            if(AppDatabase.getInstance(mContext).getFavoriteRecipeDao().getFavoriteRecipeById(recipes[0].getId()) == null){
                //NOT FOUND IN THE DATABASE , TODO add to the database
                AppDatabase.getInstance(mContext).getFavoriteRecipeDao().insertFavoriteRecipe(favoriteRecipe);
                isFavoriteNow = true;
            }else{
                //FOUND IN THE DATABASE , TODO remove from the database
                AppDatabase.getInstance(mContext).getFavoriteRecipeDao().deleteFavoriteRecipe(favoriteRecipe);
                isFavoriteNow = false;
            }
            return isFavoriteNow;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            //TODO change the icon of the ibFavorite
            if(aBoolean){
                mImageButton.setImageResource(android.R.drawable.star_big_on);
            }else{
                mImageButton.setImageResource(android.R.drawable.star_big_off);
            }
        }
    }
}
