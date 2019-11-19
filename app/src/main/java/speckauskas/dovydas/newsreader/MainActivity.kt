package speckauskas.dovydas.newsreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import speckauskas.dovydas.newsreader.model.NewsPostRecyclerAdapter
import speckauskas.dovydas.newsreader.presenter.NewsPostListPresenter
import speckauskas.dovydas.newsreader.contract.ContractInterface.IMainActivityView

class MainActivity : AppCompatActivity(), IMainActivityView {

    private lateinit var newsPostAdapter: NewsPostRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            layoutManager = LinearLayoutManager(this@MainActivity)
            newsPostAdapter = NewsPostRecyclerAdapter(presenter)
            adapter = newsPostAdapter
        }
    }

    override fun refreshRecyclerView(){
        newsPostAdapter.notifyDataSetChanged()
        pullToRefresh.isRefreshing = false
    }
}
