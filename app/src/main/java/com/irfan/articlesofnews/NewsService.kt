package com.irfan.articlesofnews

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
   //const val baseUrl="https://newsapi.org/"
 //  const  val API_KEY ="57c4dfefe32b4b46b631fa8e9f700248"

// "https://newsapi.org/v2/everything?q=Apple&from=2023-02-18&sortBy=popularity&apiKey=57c4dfefe32b4b46b631fa8e9f700248"

interface NewsInterface{
/*instead of declaring individually base url and api key I have passed the full url to the get annotation of
  NewsInterface
  */
       @GET("https://newsapi.org/v2/top-headlines?apiKey=57c4dfefe32b4b46b631fa8e9f700248")

        fun getHeadlines(

            @Query(value = "country") country: String,
            @Query(value = "page") page: Int,
            @Query(value="category") category: String
            ):Call<News1>
}



//"https://newsapi.org/v2/top-headlines?apiKey=57c4dfefe32b4b46b631fa8e9f700248&country=in&page=1&category=science"

object NewsService{
  val newsInstance: NewsInterface


    init{

   val retrofit=Retrofit.Builder()
       .baseUrl("https://newsapi.org/")
       .addConverterFactory(GsonConverterFactory.create())
       .build()
        newsInstance=retrofit.create(NewsInterface::class.java)
    }


}


interface SendLink{
    fun SendTheLinkOfUrl(url:String?)
}