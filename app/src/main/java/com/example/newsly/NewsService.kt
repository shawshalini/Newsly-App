package com.example.newsly

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&apiKey=b14a84f541404a2b816dea909854e3bc
//https://newsapi.org/v2/everything?q=apple&from=2022-11-23&to=2022-11-23&sortBy=popularity&apiKey=b14a84f541404a2b816dea909854e3bc
const val BASE_URL="https://newsapi.org/"
const val API_KEY="b14a84f541404a2b816dea909854e3bc"

interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country:String,@Query("page")page:Int) :Call<News>
}
object NewsService{
    val newsInterface:NewsInterface

    init {
        val retrofit =Retrofit.Builder()
        .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInterface=retrofit.create(NewsInterface::class.java)
    }

}