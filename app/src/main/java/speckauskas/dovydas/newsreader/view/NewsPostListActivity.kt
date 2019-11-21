package speckauskas.dovydas.newsreader.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_news_post_list.*
import speckauskas.dovydas.newsreader.R
import speckauskas.dovydas.newsreader.presenter.NewsPostListPresenter
import speckauskas.dovydas.newsreader.contract.ContractInterface.IMainActivityView
import speckauskas.dovydas.newsreader.model.NewsPostModel
import java.io.Serializable

class NewsPostListActivity : AppCompatActivity(), IMainActivityView {

    private lateinit var newsPostAdapter: NewsPostRecyclerAdapter
    private lateinit var mDrawerLayout: DrawerLayout
    private var country = "us"
    private var category = "general"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_post_list)

        initView()
    }

    override fun initView(){
        var presenter = NewsPostListPresenter(this)
        parseData(presenter)
        //Add pull to refresh listener
        pullToRefresh.setOnRefreshListener {
            parseData(presenter)
        }
        initToolbar(presenter)
        initRecyclerView(presenter)
    }

    override fun initToolbar(presenter: NewsPostListPresenter){
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        mDrawerLayout = findViewById(R.id.drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Handle navigation view item clicks here.
            when (menuItem.itemId) {
                R.id.nav_business -> {
                    category = "business"
                }
                R.id.nav_entertainment -> {
                    category = "entertainment"
                }
                R.id.nav_technology -> {
                    category = "technology"
                }
                R.id.nav_sports -> {
                    category = "sports"
                }
                R.id.nav_science -> {
                    category = "science"
                }
                R.id.nav_health -> {
                    category = "health"
                }
            }
            parseData(presenter)

            true
        }
    }

    override fun initRecyclerView(presenter: NewsPostListPresenter){
        recycler_view.apply{
            layoutManager = LinearLayoutManager(this@NewsPostListActivity)
            newsPostAdapter = NewsPostRecyclerAdapter(presenter)
            adapter = newsPostAdapter
        }
    }

    //Get data from api call to presenter
    override fun parseData(presenter: NewsPostListPresenter){
        pullToRefresh.isRefreshing = true
        presenter.getData(country, category)
    }

    //Notify recycler view adapter about changed data
    override fun refreshRecyclerView(){
        newsPostAdapter.notifyDataSetChanged()
        pullToRefresh.isRefreshing = false
    }

    //Open detailed news activity and pass news post
    override fun launchNewActivity(newsPost: NewsPostModel) {
        val intent = Intent(this, NewsDetailsActivity::class.java)
        intent.putExtra("newsPost", newsPost as Serializable)
        startActivity(intent)
    }

    //appbar - toolbar button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
