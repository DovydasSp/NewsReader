package speckauskas.dovydas.newsreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import speckauskas.dovydas.newsreader.model.DataSource
import speckauskas.dovydas.newsreader.model.NewsPostRecyclerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var newsPostAdapter: NewsPostRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        newsPostAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        recycler_view.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            newsPostAdapter = NewsPostRecyclerAdapter()
            adapter = newsPostAdapter
        }
    }
}
