package com.example.dogswelove.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dogswelove.data.api.ApiClient
import com.example.dogswelove.data.api.ApiService
import com.example.dogswelove.data.database.AppDatabase
import com.example.dogswelove.data.database.DogEntity
import com.example.dogswelove.model.Dog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getInstance(application)
    private val dogsLiveData = MutableLiveData<List<Dog>>()

    fun getDogs(): LiveData<List<Dog>> {
        CoroutineScope(Dispatchers.IO).launch {
            val cachedDogs = database.dogDao().getAllDogs()
            if (cachedDogs.isNotEmpty()) {
                dogsLiveData.postValue(cachedDogs.map {
                    Dog(it.dogName, it.description, it.age, it.image)
                })
            } else {
                val apiService = ApiClient.getClient().create(ApiService::class.java)
                apiService.getDogs().enqueue(object : Callback<List<Dog>> {
                    override fun onResponse(call: Call<List<Dog>>, response: Response<List<Dog>>) {
                        if (response.isSuccessful) {
                            val dogs = response.body() ?: emptyList()
                            CoroutineScope(Dispatchers.IO).launch {
                                database.dogDao().insertDogs(dogs.map {
                                    DogEntity(
                                        dogName = it.dogName,
                                        description = it.description,
                                        age = it.age,
                                        image = it.image
                                    )
                                })
                                dogsLiveData.postValue(dogs)
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<Dog>>, t: Throwable) {
                        // TODO: handle errors
                    }
                })
            }
        }
        return dogsLiveData
    }
}
