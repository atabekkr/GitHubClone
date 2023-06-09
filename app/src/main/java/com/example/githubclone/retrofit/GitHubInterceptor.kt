package com.example.githubclone.retrofit

import com.example.githubclone.data.local.LocalStorage
import okhttp3.Interceptor
import okhttp3.Response

class GitHubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = LocalStorage().token
        val request =
            chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
        return chain.proceed(request)
    }
}