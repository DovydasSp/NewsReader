package speckauskas.dovydas.newsreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import speckauskas.dovydas.newsreader.model.DataSource
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
        var presenter = NewsPostListPresenter()
        val data = DataSource.createDataSet()
        presenter.addDataSet(data)
        initRecyclerView(presenter)
        pullToRefresh.setOnRefreshListener {
            val data2 = DataSource.createDataSet2()
            presenter.addDataSet(data2)
            newsPostAdapter.notifyDataSetChanged()
            pullToRefresh.isRefreshing = false
            //TODO("refresh list")
        }
    }

    override fun initRecyclerView(presenter: NewsPostListPresenter){
        recycler_view.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            newsPostAdapter = NewsPostRecyclerAdapter(presenter)
            adapter = newsPostAdapter
        }
    }
}
