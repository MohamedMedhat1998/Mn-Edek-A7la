package com.andalus.abomed7at55.mn_edek_a7la.Utils;

import com.andalus.abomed7at55.mn_edek_a7la.Objects.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private static final String RECIPES_KEY = "recipes";

    private JsonParser(){

    }

    public static List<Recipe> parseRecipes(String jsonString) throws JSONException {
        List<Recipe> result = new ArrayList<>();

        JSONObject rootObject = new JSONObject(jsonString);
        JSONArray recipesArray = rootObject.getJSONArray(RECIPES_KEY);
        JSONObject recipeObject;

        Recipe tempRecipe;
        int id;
        String title,ingredients,steps,category,photoLink,videoLink;

        int sz = recipesArray.length();
        for (int i = 0 ; i < sz ; i++){
            recipeObject = recipesArray.getJSONObject(i);
            id = Integer.parseInt(recipeObject.getString(Recipe.COLUMN_ID));
            title = recipeObject.getString(Recipe.COLUMN_TITLE);
            ingredients = recipeObject.getString(Recipe.COLUMN_INGREDIENTS);
            steps = recipeObject.getString(Recipe.COLUMN_STEPS);
            category = recipeObject.getString(Recipe.COLUMN_CATEGORY);
            photoLink = recipeObject.getString(Recipe.COLUMN_PHOTO_LINK);
            videoLink = recipeObject.getString(Recipe.COLUMN_VIDEO_LINK);

            tempRecipe = new Recipe(id,title,ingredients,steps,category,photoLink,videoLink);
            result.add(tempRecipe);
        }

        return result;
    }
}
