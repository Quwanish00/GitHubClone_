package com.example.github.persentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.models.GetAccessToken
import com.example.github.data.models.GetUserProfileInfoData
import com.example.github.data.models.GetUserRepositoriesData
import com.example.github.data.models.ResultData
import com.example.github.data.remote.GitHubApi
import com.example.github.data.remote.RetrofitHelper
import com.example.github.domain.MainRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UserViewModel(application: Application):AndroidViewModel(application) {
    val repo = MainRepository(RetrofitHelper.getInstance().create(GitHubApi::class.java))

    val getUserProfileInfoFlow = MutableSharedFlow<GetUserProfileInfoData>()
    val getUserRepositoriesFlow = MutableSharedFlow<List<GetUserRepositoriesData>>()
    val getAccessTokenFlow = MutableSharedFlow<GetAccessToken>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()


    suspend fun getUserProfileInfo() {
        repo.getUserProfileInfo().onEach {
            when (it) {
                is ResultData.Success -> {
                    getUserProfileInfoFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getUserRepositories() {
        repo.getUserRepositories().onEach {
            when (it) {
                is ResultData.Success -> {
                    getUserRepositoriesFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getAccessToken(code: String) {
        repo.getAccessToken(code).onEach {
            when (it) {
                is ResultData.Success -> {
                    getAccessTokenFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
    suspend fun getUserRepositoriesByLanguage() {
        repo.getUserRepositories().onEach {
            when (it) {
                is ResultData.Success -> {
                    getUserRepositoriesFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}