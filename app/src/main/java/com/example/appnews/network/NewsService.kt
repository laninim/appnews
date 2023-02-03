package com.example.appnews.network

import com.example.appnews.network.networkmodel.NewsResponse
import retrofit2.http.*

interface NewsService {

    @Headers("X-Api-Key:Insert your api key")
    @GET("everything")
    suspend fun getArticleByKeyWord(@Query("q") searchkey : String)  : NewsResponse

    @Headers("X-Api-Key:Insert your api key")
    @GET("everything?")
    suspend fun getArticleByKeyWordAndPopularity(@Query("q") keyword : String, @Query("sortBy") relevancy : String) : NewsResponse


}