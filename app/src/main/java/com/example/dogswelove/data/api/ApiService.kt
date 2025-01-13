package com.example.dogswelove.data.api

import com.example.dogswelove.model.Dog
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("1151549092634943488")
    fun getDogs(): Call<List<Dog>>
}