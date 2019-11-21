package speckauskas.dovydas.newsreader.contract

import speckauskas.dovydas.newsreader.model.NewsPostModel
import speckauskas.dovydas.newsreader.presenter.NewsPostListPresenter

interface ContractInterface {

    interface IMainActivityView {
        fun initView()
        fun initRecyclerView(presenter: NewsPostListPresenter)
        fun refreshRecyclerView()
        fun launchNewActivity(newsPost: NewsPostModel)
        fun parseData(presenter: NewsPostListPresenter, country:String, category:String)
    }

    interface INewsPostListPresenter {
        fun setData(items_: ArrayList<NewsPostModel>)
        fun onBindRepositoryRowViewAtPosition(position: Int, rowView: INewsPostRowView)
        fun getRepositoriesRowsCount(): Int
        fun onItemClickedAtPosition(adapterPosition: Int)
        fun getData(country:String, category:String)
    }

    interface INewsPostRowView {
        fun setPostImage(imageUrl: String)
        fun setPostTitle(title: String)
        fun setPostDate(date: String)
        fun addOnClickListener(presenter: NewsPostListPresenter)
    }
}