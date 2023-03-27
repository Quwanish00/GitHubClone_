package com.example.github.data.models

import com.google.gson.annotations.SerializedName

data class GetAccessToken(
    @SerializedName("access_token")
    val token: String,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("token_type")
    val token_type: String
)
