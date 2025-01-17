package com.arzhang.borutoapp.data.remote

import com.arzhang.borutoapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoApi {

    @GET("/boruto/heroes")
    suspend fun getAllHeroes(
        @Query(value = "page") page: Int = 1
    ) : ApiResponse

    @GET("/boruto/heroes/search")
    suspend fun searchHeroes(
        @Query(value = "query") name: String
    ) : ApiResponse

}