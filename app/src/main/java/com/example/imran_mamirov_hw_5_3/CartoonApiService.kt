package com.example.imran_mamirov_hw_5_3

import com.example.imran_mamirov_hw_5_3.models.BaseReponse
import retrofit2.Call
import retrofit2.http.GET

interface CartoonApiService {

    @GET("character")
    fun fetchCharacters() : Call<BaseReponse>
}