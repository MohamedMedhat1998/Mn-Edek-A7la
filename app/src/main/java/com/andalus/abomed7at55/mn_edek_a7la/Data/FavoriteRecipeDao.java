package com.andalus.abomed7at55.mn_edek_a7la.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.andalus.abomed7at55.mn_edek_a7la.Objects.FavoriteRecipe;

import java.util.List;

@Dao
public interface FavoriteRecipeDao {

    @Query("SELECT * FROM FavoriteRecipe")
    List<FavoriteRecipe> getAll();

    @Query("SELECT * FROM FavoriteRecipe WHERE id = :id")
    FavoriteRecipe getFavoriteRecipeById(int id);

    @Insert
    void insertFavoriteRecipe(FavoriteRecipe... favoriteRecipes);

    @Delete
    void deleteFavoriteRecipe(FavoriteRecipe favoriteRecipe);
}
