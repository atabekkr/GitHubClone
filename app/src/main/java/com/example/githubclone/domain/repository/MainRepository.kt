package com.example.githubclone.domain.repository

import android.util.Log
import com.example.githubclone.data.local.LocalStorage
import com.example.githubclone.data.models.ResultData
import com.example.githubclone.retrofit.GitHubApi
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow

class MainRepository(private val api: GitHubApi) {

    suspend fun loginApi() = flow {
        Log.d("TTTT", LocalStorage().code)
        val responce =
            api.getAccessToken(
                "8f3cf5f09bd0c93a0528",
                "5447af3efb5afba3751aa6a0025e97affcf1a538",
                LocalStorage().code
            )

        Log.e("TTTT", responce.body()!!.accessToken)
        if (responce.isSuccessful) {
            emit(ResultData.Success(responce.body()!!.accessToken))
        } else {
            emit(ResultData.Message(responce.message()))
        }
    }

    suspend fun getUserProfileInfo() = flow {
        val responce =
            api.getUserProfileInfo()

        Log.d("atabekkr", LocalStorage().token)

        if (responce.isSuccessful) {
            emit(ResultData.Success(responce.body()!!))
        } else {
            emit(ResultData.Message(responce.message()))
        }
    }

    suspend fun searchUsersByUsername(userName: String) = flow {
        val responce =
            api.searchUsersByUsername(userName)

        if (responce.isSuccessful) {
            emit(ResultData.Success(responce.body()!!.items.last().login))
        } else {
            emit(ResultData.Message(responce.message()))
        }
    }
}