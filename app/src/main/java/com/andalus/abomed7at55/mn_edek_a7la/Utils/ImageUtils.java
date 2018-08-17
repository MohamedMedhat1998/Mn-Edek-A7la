package com.andalus.abomed7at55.mn_edek_a7la.Utils;

import android.widget.ImageView;

import com.andalus.abomed7at55.mn_edek_a7la.Objects.FoodCategory;
import com.andalus.abomed7at55.mn_edek_a7la.R;

public class ImageUtils {

    private ImageUtils(){

    }

    public static int getPlaceHolderId(String category){
        int id = 0;
        if(category.contains(FoodCategory.MEAT_TAG)){
            id = R.drawable.chicken_hot_icon;
        } else if(category.contains(FoodCategory.FISH_TAG)){
            id = R.drawable.fish_icon;
        }else if(category.contains(FoodCategory.SWEET_TAG)){
            id = R.drawable.cake_icon;
        }else if(category.contains(FoodCategory.DRINK_TAG)){
            id = R.drawable.juice_icon;
        }else if(category.contains(FoodCategory.STARCHES_TAG)){
            id = R.drawable.rice_icon;
        }else if(category.contains(FoodCategory.APPETIZER_TAG)){
            id = R.drawable.snack_icon;
        }else if(category.contains(FoodCategory.IDEA_TAG)){
            id = R.drawable.ligh_bulb_icon;
        }else if(category.contains(FoodCategory.DIET_TAG)){
            id = R.drawable.diet_icon;
        }else if(category.contains(FoodCategory.BAKERY_TAG)){
            id = R.drawable.bread_icon;
        }else if(category.contains(FoodCategory.MILK_TAG)){
            id = R.drawable.milk_bottle_icon;
        }
        //TODO if you added a category, add its placeholder icon here
        return id;
    }
}
