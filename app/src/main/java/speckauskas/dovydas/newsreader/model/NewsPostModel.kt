package speckauskas.dovydas.newsreader.model

import java.util.*

data class NewsPostModel(
    var imageUrl : String,
    var title : String,
    var date : String,
    var author : String,
    var description : String
) {

}