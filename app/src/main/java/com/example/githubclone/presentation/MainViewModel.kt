package com.example.githubclone.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubclone.data.models.*
import com.example.githubclone.domain.repository.MainRepository
import com.example.githubclone.ui.paging.PageSource
import kotlinx.coroutines.flow.*

class MainViewModel(private val repo: MainRepository) : ViewModel() {

    val getSuccessFlow = MutableSharedFlow<String>()
    val getMessageFlow = MutableSharedFlow<String>()
    val getErrorFlow = MutableSharedFlow<Throwable>()

    val getUserPrInfoSuccessFlow = MutableSharedFlow<GetUserProfileInfoResponceData>()
    val getUserRepositoriesFlow = MutableSharedFlow<List<GetUserRepositories>>()
    val getSearchByUserFlow = MutableSharedFlow<List<ItemsData>>()
    val searchReposByRepoNameFlow = MutableSharedFlow<List<ItemsRepoData>>()

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
                    getSearchByUserFlow.emit(it.data)
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

    suspend fun getUserRepositories() {
        repo.getUserRepositories().onEach {
            when (it) {
                is ResultData.Success -> {
                    getUserRepositoriesFlow.emit(it.data)
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

    suspend fun searchRepoByRepoName(repoName: String) {
        repo.searchRepoByRepoName(repoName).onEach {
            when (it) {
                is ResultData.Success -> {
                    searchReposByRepoNameFlow.emit(it.data)
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