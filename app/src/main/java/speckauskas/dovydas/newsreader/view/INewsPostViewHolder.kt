package speckauskas.dovydas.newsreader.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_news_post_list_item.view.*
import speckauskas.dovydas.newsreader.R
import speckauskas.dovydas.newsreader.contract.ContractInterface.INewsPostRowView
import speckauskas.dovydas.newsreader.presenter.NewsPostListPresenter
import java.text.SimpleDateFormat
import java.util.*

class INewsPostViewHolder constructor(
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
        val mParsedDate: Date
        val mOutputDateString: String
        val mInputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val mOutputDateFormat = SimpleDateFormat("yyyy MMMM dd HH:mm", Locale.getDefault())
        mParsedDate = mInputDateFormat.parse(date)
        mOutputDateString = mOutputDateFormat.format(mParsedDate)

        newsPostDate.setText(mOutputDateString)
    }

    override fun addOnClickListener(presenter: NewsPostListPresenter){
        itemView.setOnClickListener { presenter.onItemClickedAtPosition(adapterPosition) }
    }
}