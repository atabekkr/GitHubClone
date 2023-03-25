package com.example.githubclone.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubclone.data.models.ItemsData
import com.example.githubclone.data.models.ItemsRepoData
import com.example.githubclone.data.models.SearchRepoByRepoNameResponceData
import com.example.githubclone.retrofit.GitHubApi
import retrofit2.HttpException
import retrofit2.http.HTTP
import retrofit2.http.Query

class PageSource(
    private val api: GitHubApi,
    private val query: String
) : PagingSource<Int, ItemsRepoData>() {
    override fun getRefreshKey(state: PagingState<Int, ItemsRepoData>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): PagingSource.LoadResult<Int, ItemsRepoData> {
        if (query.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        val page = params.key ?: 1
        val pageSize = params.loadSize.coerceAtLeast(2)

        val responce = api.searchRepoByRepoName(query)
        if (responce.isSuccessful) {
            val articles = checkNotNull(responce.body()).items
            val nextKey = if(articles.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
                return LoadResult.Page(articles, nextKey, prevKey)
        } else {
            return LoadResult.Error(HttpException(responce))
        }
    }
}