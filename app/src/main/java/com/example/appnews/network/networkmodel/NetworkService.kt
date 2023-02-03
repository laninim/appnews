package com.example.appnews.network.networkmodel

import com.example.appnews.network.NewsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private var instance : NewsService? = null





    fun loadInstance () : NewsService {

        return if(instance != null){
            instance!!
        }else{
            val retrofit = Retrofit.Builder().baseUrl(" https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).build()
            instance = retrofit.create(NewsService::class.java)
            return instance!!
        }
    }

}