package com.example.imran_mamirov_hw_5_3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imran_mamirov_hw_5_3.models.BaseReponse
import com.example.imran_mamirov_hw_5_3.models.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

const val START_INDEX = 1

class CartoonPagingSource : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val current = params.key ?: START_INDEX
            val previousKey = if (current == START_INDEX) null else current.minus(1)
            val response = fetchCharacter()
            LoadResult.Page(
                data = response,
                prevKey = previousKey,
                nextKey = current.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private fun fetchCharacter(): List<Character> {
        var mResponse: List<Character> = emptyList()
        RetrofitService.api.fetchCharacters().enqueue(object : Callback<BaseReponse>{
            override fun onResponse(p0: Call<BaseReponse>, response: Response<BaseReponse>) {
                mResponse = response.body()?.characters ?: emptyList()
            }

            override fun onFailure(p0: Call<BaseReponse>, p1: Throwable) {
                mResponse = emptyList()
            }
        })
        return mResponse
    }
}