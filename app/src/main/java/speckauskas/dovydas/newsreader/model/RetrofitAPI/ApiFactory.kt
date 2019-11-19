package speckauskas.dovydas.newsreader.model.RetrofitAPI

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    val getClient: ApiInterface
        get(){
            val gson = GsonBuilder().create()

            val apiClient = OkHttpClient().newBuilder().build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .client(apiClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
}