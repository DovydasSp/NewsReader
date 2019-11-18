package speckauskas.dovydas.newsreader.contract

import speckauskas.dovydas.newsreader.model.NewsPostModel
import speckauskas.dovydas.newsreader.presenter.NewsPostListPresenter

interface ContractInterface {

    interface IMainActivityView {
        fun initView()
        fun initRecyclerView(presenter: NewsPostListPresenter)
    }

    interface INewsPostListPresenter {
        fun addDataSet(items_: ArrayList<NewsPostModel>)
        fun onBindRepositoryRowViewAtPosition(position: Int, rowViewI: INewsPostRowView)
        fun getRepositoriesRowsCount(): Int
        fun onItemClickedAtPosition(adapterPosition: Int)
    }

    interface INewsPostRowView {
        fun setPostImage(imageUrl: String)
        fun setPostTitle(title: String)
        fun setPostDate(date: String)
        fun addOnClickListener(presenter: NewsPostListPresenter)
    }
}