package speckauskas.dovydas.newsreader.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.ParseException
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
)  : Serializable{
    fun changedDate():String{
        val mParsedDate: Date
        var mOutputDateString: String = ""
        val mInputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val mOutputDateFormat = SimpleDateFormat("yyyy MMMM dd HH:mm", Locale.getDefault())
        try{
            mParsedDate = mInputDateFormat.parse(date)
            mOutputDateString = mOutputDateFormat.format(mParsedDate)
        } catch(e: ParseException){
            return ""
        }
        return mOutputDateString
    }
}

data class NewsPostsModelList(
    @Expose
    @SerializedName("articles")
    val results : ArrayList<NewsPostModel>
)