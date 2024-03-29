package com.example.github.persentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.models.*
import com.example.github.domain.MainRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UserViewModel(private val repo:MainRepository):ViewModel() {


    val getUserProfileInfoFlow = MutableSharedFlow<GetUserProfileInfoData>()
    val getUserRepositoriesFlow = MutableSharedFlow<List<GetUserRepositoriesData>>()
    val getUserRepositoriesInProfileFlow = MutableSharedFlow<List<GetUserRepositoriesData>>()
    val getUserRepositoriesProfileFlow = MutableSharedFlow<List<GetUserRepositoriesData>>()

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
    suspend fun getUserRepositoriespProfile() {
        repo.getUserRepositories().onEach {
            when (it) {
                is ResultData.Success -> {
                    getUserRepositoriesProfileFlow.emit(it.data)
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
    suspend fun getUserRepositoriespInProfile() {
        repo.getUserRepositories().onEach {
            when (it) {
                is ResultData.Success -> {
                    getUserRepositoriesInProfileFlow.emit(it.data)
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


}