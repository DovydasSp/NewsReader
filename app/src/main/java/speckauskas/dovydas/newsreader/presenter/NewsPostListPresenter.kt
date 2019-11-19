package speckauskas.dovydas.newsreader.presenter

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import speckauskas.dovydas.newsreader.BuildConfig
import speckauskas.dovydas.newsreader.view.NewsPostListActivity
import speckauskas.dovydas.newsreader.model.*
import speckauskas.dovydas.newsreader.contract.ContractInterface.INewsPostListPresenter
import speckauskas.dovydas.newsreader.contract.ContractInterface.INewsPostRowView
import speckauskas.dovydas.newsreader.model.RetrofitAPI.ApiFactory

class NewsPostListPresenter constructor(
    newsPostListActivity_: NewsPostListActivity
): INewsPostListPresenter {
    private var items = ArrayList<NewsPostModel>()

    private var mainActivity = newsPostListActivity_

    override fun addDataSet(items_: ArrayList<NewsPostModel>){
        items = ArrayList()
        items = items_
    }

    override fun onBindRepositoryRowViewAtPosition(position: Int, rowView: INewsPostRowView) {
        val repo = items.get(position)
        rowView.setPostDate(repo.date)
        if(!repo.imageUrl.isNullOrEmpty())
            rowView.setPostImage(repo.imageUrl)
        rowView.setPostTitle(repo.title)
        rowView.addOnClickListener(this)
    }

    override fun getRepositoriesRowsCount(): Int {
        return items.size
    }

    override fun onItemClickedAtPosition(adapterPosition: Int) {
        System.out.println("RecyclerView item pressed: {$adapterPosition}")
        var newsPost = items[adapterPosition]
        mainActivity.launchNewActivity(newsPost)
    }

    override fun getData(country:String, category:String){
        val call: Call<NewsPostsModelList> = ApiFactory.getClient.getNews(country, category, BuildConfig.API_KEY, 100)
        call.enqueue(object : Callback<NewsPostsModelList> {

            override fun onResponse(call: Call<NewsPostsModelList>?, response: Response<NewsPostsModelList>?) {
                addDataSet(response!!.body()!!.results)
                mainActivity.refreshRecyclerView()
            }

            override fun onFailure(call: Call<NewsPostsModelList>?, t: Throwable?) {
                System.out.println(t.toString())
                mainActivity.refreshRecyclerView()
            }

        })
    }
}