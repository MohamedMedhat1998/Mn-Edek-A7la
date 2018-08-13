package com.andalus.abomed7at55.mn_edek_a7la.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andalus.abomed7at55.mn_edek_a7la.Objects.FoodCategory;
import com.andalus.abomed7at55.mn_edek_a7la.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>{

    private List<FoodCategory> mData;

    public CategoriesAdapter(List<FoodCategory> data){
        mData = data;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_item,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.ivCategoryIcon.setImageResource(mData.get(position).getIconId());
        holder.tvCategoryTitle.setText(mData.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_category_icon)
        ImageView ivCategoryIcon;
        @BindView(R.id.tv_category_title)
        TextView tvCategoryTitle;

        CategoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
