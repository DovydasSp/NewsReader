package speckauskas.dovydas.newsreader.model.RetrofitAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import speckauskas.dovydas.newsreader.model.*

interface ApiInterface {
    @GET("top-headlines")
    fun getNews(@Query("country") country:String, @Query("category") category:String,
                @Query("apiKey") apiKey:String, @Query("pageSize") size:Int ): Call<NewsPostsModelList>
}