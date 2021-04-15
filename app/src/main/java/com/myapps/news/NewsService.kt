package com.myapps.news

import retrofit2.Call
import retrofit2.http.GET

interface NewsService {
    @GET("search?page=2&q=debate&api-key=test")
    fun getNews() : Call<Base>
}