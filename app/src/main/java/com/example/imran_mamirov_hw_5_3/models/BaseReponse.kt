package com.example.imran_mamirov_hw_5_3.models

import com.google.gson.annotations.SerializedName

data class BaseReponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("character")
    val characters: List<Character>
)