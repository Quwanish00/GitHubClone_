package com.example.github.data.models

import com.google.gson.annotations.SerializedName

data class GetAccessToken(
    @SerializedName("access_token")
    val access_token: String,

)
