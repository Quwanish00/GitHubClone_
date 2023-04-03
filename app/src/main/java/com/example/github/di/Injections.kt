package com.example.github.di

import com.example.github.data.remote.GitHubApi
import com.example.github.domain.MainRepository
import com.example.github.persentation.SearchViewModel
import com.example.github.persentation.UserViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<MainRepository> {
        MainRepository(api = get())
    }

    fun retrofit(): Retrofit {
        val httpLogginInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )


        val client = OkHttpClient.Builder()
            .addInterceptor(httpLogginInterceptor)
            .build()

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com").client(client).build()
       return retrofit
    }
    single {
        retrofit()
    }

    single<GitHubApi> {
        provideApi(retrofit = get())
    }

}

val viewModelModule = module {
    viewModel<UserViewModel> {
        UserViewModel(repo = get())
    }

    viewModel <SearchViewModel> {
        SearchViewModel(repo = get())
    }
}

fun provideApi(retrofit: Retrofit): GitHubApi {
    return retrofit.create(GitHubApi::class.java)
}