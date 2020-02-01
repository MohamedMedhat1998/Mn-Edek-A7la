package com.andalus.abomed7at55.mn_edek_a7la.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM Recipe WHERE category LIKE :category")
    List<Recipe> getRecipesByCategory(String category);

    @Query("SELECT * FROM Recipe WHERE title Like :searchKeyword OR ingredients LIKE :searchKeyword OR steps LIKE :searchKeyword")
    List<Recipe> getRecipesBySearchKeyword(String searchKeyword);

    @Query("SELECT * FROM Recipe WHERE id = :id")
    Recipe getRecipeById(int id);

    @Insert
    void insertRecipe(Recipe recipe);

}
