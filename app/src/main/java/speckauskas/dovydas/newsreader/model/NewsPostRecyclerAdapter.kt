package speckauskas.dovydas.newsreader.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import speckauskas.dovydas.newsreader.R
import speckauskas.dovydas.newsreader.presenter.NewsPostListPresenter
import speckauskas.dovydas.newsreader.view.INewsPostViewHolder

class NewsPostRecyclerAdapter constructor(
    presenter_: NewsPostListPresenter
) : RecyclerView.Adapter<INewsPostViewHolder>(){

    private val presenter = presenter_

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): INewsPostViewHolder {

        return INewsPostViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.layout_news_post_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: INewsPostViewHolder, position: Int) {
        presenter.onBindRepositoryRowViewAtPosition(position, holder)
    }

    override fun getItemCount(): Int {
        return presenter.getRepositoriesRowsCount()
    }


}