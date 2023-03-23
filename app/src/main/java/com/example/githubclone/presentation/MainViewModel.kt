package com.example.githubclone.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclone.data.models.GetUserProfileInfoResponceData
import com.example.githubclone.data.models.ResultData
import com.example.githubclone.domain.repository.MainRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(private val repo: MainRepository) : ViewModel() {

    val getSuccessFlow = MutableSharedFlow<String>()
    val getMessageFlow = MutableSharedFlow<String>()
    val getErrorFlow = MutableSharedFlow<Throwable>()

    val getUserPrInfoSuccessFlow = MutableSharedFlow<GetUserProfileInfoResponceData>()

    suspend fun isSuccess() {
        repo.loginApi().onEach {
            when(it) {
                is ResultData.Success -> {
                    getSuccessFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    getMessageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    getErrorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getUserProfileInfo() {
        repo.getUserProfileInfo().onEach {
            when(it) {
                is ResultData.Success -> {
                    getUserPrInfoSuccessFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    getMessageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    getErrorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun searchUsersByUserName(userName: String) {
        repo.searchUsersByUsername(userName).onEach {
            when (it) {
                is ResultData.Success -> {
                    getSuccessFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    getMessageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    getErrorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}