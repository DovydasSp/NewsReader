package speckauskas.dovydas.newsreader.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import speckauskas.dovydas.newsreader.BuildConfig
import speckauskas.dovydas.newsreader.MainActivity
import speckauskas.dovydas.newsreader.model.*
import speckauskas.dovydas.newsreader.contract.ContractInterface.INewsPostListPresenter
import speckauskas.dovydas.newsreader.contract.ContractInterface.INewsPostRowView
import speckauskas.dovydas.newsreader.model.RetrofitAPI.ApiFactory

class NewsPostListPresenter constructor(
    mainActivity_: MainActivity
): INewsPostListPresenter {
    private var items = ArrayList<NewsPostModel>()

    private var mainActivity = mainActivity_

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
        //TODO("add news details")
        System.out.println("RecyclerView item pressed: {$adapterPosition}")
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