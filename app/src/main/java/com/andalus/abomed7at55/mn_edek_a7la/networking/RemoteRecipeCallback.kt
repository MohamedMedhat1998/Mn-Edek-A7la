package com.andalus.abomed7at55.mn_edek_a7la.networking

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe
import com.andalus.abomed7at55.mn_edek_a7la.model.RemoteRecipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRecipeCallback : Callback<List<RemoteRecipe>> {
    
    val result = MutableLiveData<List<Recipe>>()

    override fun onResponse(call: Call<List<RemoteRecipe>>, response: Response<List<RemoteRecipe>>) {
        val data = mutableListOf<Recipe>()
        if (response.body() != null)
            response.body()!!.forEach {
                data.add(it.toRecipe())
            }
        result.postValue(data)
    }

    override fun onFailure(call: Call<List<RemoteRecipe>>, t: Throwable) {
        Log.d("NETWORKING", "${t.message}")
    }
}