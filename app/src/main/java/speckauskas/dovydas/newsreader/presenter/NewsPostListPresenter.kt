package speckauskas.dovydas.newsreader.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import speckauskas.dovydas.newsreader.BuildConfig
import speckauskas.dovydas.newsreader.contract.ContractInterface
import speckauskas.dovydas.newsreader.model.*
import speckauskas.dovydas.newsreader.contract.ContractInterface.INewsPostListPresenter
import speckauskas.dovydas.newsreader.contract.ContractInterface.INewsPostRowView
import speckauskas.dovydas.newsreader.model.RetrofitAPI.ApiFactory

class NewsPostListPresenter constructor(
    newsPostListActivity_: ContractInterface.IMainActivityView
): INewsPostListPresenter {
    var items = ArrayList<NewsPostModel>()

    private var mainActivity = newsPostListActivity_

    //Save parsed data set
    override fun setData(items_: ArrayList<NewsPostModel>){
        items = ArrayList()
        items = items_
    }

    override fun onBindRepositoryRowViewAtPosition(position: Int, rowView: INewsPostRowView) {
        if(position < getRepositoriesRowsCount()) {
            val repo = items[position]
            rowView.setPostDate(repo.changedDate())
            if (!repo.imageUrl.isNullOrEmpty())
                rowView.setPostImage(repo.imageUrl)
            rowView.setPostTitle(repo.title)
            rowView.addOnClickListener(this)
        }
    }

    override fun getRepositoriesRowsCount(): Int {
        return items.size
    }

    override fun onItemClickedAtPosition(adapterPosition: Int) {
        if(adapterPosition < getRepositoriesRowsCount()) {
            var newsPost = items[adapterPosition]
            mainActivity.launchNewActivity(newsPost)
        }
    }

    //Parse data from API
    override fun getData(country:String, category:String) {
        if (!country.isNullOrBlank() && !category.isNullOrBlank()) {
            val call: Call<NewsPostsModelList> =
                ApiFactory.getClient.getNews(country, category, BuildConfig.API_KEY, 100)
            call.enqueue(object : Callback<NewsPostsModelList> {

                override fun onResponse(call: Call<NewsPostsModelList>?, response: Response<NewsPostsModelList>?) {
                    if(!response!!.body()!!.results.isNullOrEmpty()) {
                        setData(response!!.body()!!.results)
                        mainActivity.refreshRecyclerView()
                    }
                }

                override fun onFailure(call: Call<NewsPostsModelList>?, t: Throwable?) {
                    System.out.println(t.toString())
                }
            })
        }
    }
}