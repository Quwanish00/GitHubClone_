package com.example.github.data.remote

import com.example.github.data.models.*
import retrofit2.Response
import retrofit2.http.*

interface GitHubApi {

    @GET("/user")
    suspend fun getUserProfileInfo(): Response<GetUserProfileInfoData>

    @GET("/user/repos")
    suspend fun getUserRepositories(): Response<List<GetUserRepositoriesData>>


    @GET("/search/users?q")
    suspend fun searchUsersByUsername(@Query("q") login: String): Response<GenerateData<GetSearchUsersByUsername>>

    @GET("/search/repositories?q")
    suspend fun searchRepositoriesByRepositoryName(@Query("q") name: String): Response<GenerateData<GetRepositoriesByNameData>>

    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("code") code: String): Response<GetAccessToken>
}
//@FormUrlEncoded {{BASE_URL}}