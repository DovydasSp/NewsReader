package speckauskas.dovydas.newsreader.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class NewsPostModel(
    @Expose
    @SerializedName("urlToImage")
    var imageUrl : String,
    @Expose
    @SerializedName("title")
    var title : String,
    @Expose
    @SerializedName("publishedAt")
    var date : String,
    @Expose
    @SerializedName("author")
    var author : String,
    @Expose
    @SerializedName("description")
    var description : String,
    @Expose
    @SerializedName("url")
    var url : String
)  : Serializable

data class NewsPostsModelList(
    @Expose
    @SerializedName("articles")
    val results : ArrayList<NewsPostModel>
)