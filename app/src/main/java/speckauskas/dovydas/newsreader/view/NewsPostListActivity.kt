package speckauskas.dovydas.newsreader.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_news_post_list.*
import speckauskas.dovydas.newsreader.R
import speckauskas.dovydas.newsreader.model.NewsPostRecyclerAdapter
import speckauskas.dovydas.newsreader.presenter.NewsPostListPresenter
import speckauskas.dovydas.newsreader.contract.ContractInterface.IMainActivityView
import speckauskas.dovydas.newsreader.model.NewsPostModel
import java.io.Serializable

class NewsPostListActivity : AppCompatActivity(), IMainActivityView {

    private lateinit var newsPostAdapter: NewsPostRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_post_list)

        initView()
    }

    override fun initView(){
        var presenter = NewsPostListPresenter(this)
        presenter.getData("lt", "technology")
        initRecyclerView(presenter)
        pullToRefresh.setOnRefreshListener {
            presenter.getData("us", "technology")
        }
    }

    override fun initRecyclerView(presenter: NewsPostListPresenter){
        recycler_view.apply{
            layoutManager = LinearLayoutManager(this@NewsPostListActivity)
            newsPostAdapter = NewsPostRecyclerAdapter(presenter)
            adapter = newsPostAdapter
        }
    }

    override fun refreshRecyclerView(){
        newsPostAdapter.notifyDataSetChanged()
        pullToRefresh.isRefreshing = false
    }

    override fun launchNewActivity(newsPost: NewsPostModel) {
        val intent = Intent(this, NewsDetailsActivity::class.java)
        intent.putExtra("newsPost", newsPost as Serializable)
        startActivity(intent)
    }

}
