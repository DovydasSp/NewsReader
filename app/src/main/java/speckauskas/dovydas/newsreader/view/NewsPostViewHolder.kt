package speckauskas.dovydas.newsreader.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_news_post_list_item.view.*
import speckauskas.dovydas.newsreader.R
import speckauskas.dovydas.newsreader.contract.ContractInterface.INewsPostRowView
import speckauskas.dovydas.newsreader.presenter.NewsPostListPresenter

//Populate news post layout item with data for recycler view
class NewsPostViewHolder constructor(
    itemView: View
): RecyclerView.ViewHolder(itemView), INewsPostRowView{

    val newsPostImage = itemView.news_post_image
    val newsPostTitle = itemView.news_post_title
    val newsPostDate = itemView.news_post_date

    override fun setPostImage(imageUrl: String) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(imageUrl)
            .into(newsPostImage)
    }

    override fun setPostTitle(title: String) {
        newsPostTitle.setText(title)
    }

    override fun setPostDate(date: String) {
        newsPostDate.setText(date)
    }

    override fun addOnClickListener(presenter: NewsPostListPresenter){
        itemView.setOnClickListener { presenter.onItemClickedAtPosition(adapterPosition) }
    }
}