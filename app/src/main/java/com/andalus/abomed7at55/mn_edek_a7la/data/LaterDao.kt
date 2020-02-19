package com.andalus.abomed7at55.mn_edek_a7la.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andalus.abomed7at55.mn_edek_a7la.model.LaterRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe

@Dao
interface LaterDao {

    @Insert
    suspend fun addToLater(list: List<LaterRecipe>)

    @Insert
    suspend fun addToLater(favoriteRecipe: LaterRecipe)

    @Query("DELETE FROM LaterRecipe WHERE id = :id")
    suspend fun deleteFromLater(id: Int)

    @Query("DELETE FROM LaterRecipe WHERE id in (:ids)")
    suspend fun deleteFromLater(ids: List<Int>)

    @Query("SELECT * FROM LaterRecipe")
    fun getAllLaters(): LiveData<List<LaterRecipe>>

    @Query("SELECT id FROM LaterRecipe")
    fun getLaterIds(): LiveData<List<Int>>

    @Query("SELECT id,title,category,photo_link FROM LaterRecipe")
    fun getLaterPreview(): LiveData<List<PreviewRecipe>>

}