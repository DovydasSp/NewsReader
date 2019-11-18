package speckauskas.dovydas.newsreader.model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_news_post_list_item.view.*
import speckauskas.dovydas.newsreader.R

class NewsPostRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: List<NewsPostModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsPostViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.layout_news_post_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is NewsPostViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(newsPostList : List<NewsPostModel>){
        items = newsPostList
    }

    class NewsPostViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val newsPostImage = itemView.news_post_image
        val newsPostTitle = itemView.news_post_title
        val newsPostDate = itemView.news_post_date

        fun bind(newsPostModel: NewsPostModel){

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(newsPostModel.imageUrl)
                .into(newsPostImage)

            newsPostTitle.setText(newsPostModel.title)
            newsPostDate.setText(newsPostModel.date)
        }
    }


}