package speckauskas.dovydas.newsreader.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_news_details.*
import speckauskas.dovydas.newsreader.R
import speckauskas.dovydas.newsreader.model.NewsPostModel
import java.text.SimpleDateFormat
import java.util.*

class NewsDetailsActivity: AppCompatActivity(){
    private lateinit var newsPost:NewsPostModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        //Get information about post that user wants details about
        newsPost = intent.extras.get("newsPost") as NewsPostModel

        initView()
    }

    private fun initView(){
        //Populate views fields with information
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(newsPost.imageUrl)
            .into(image_details)

        title_details.setText(newsPost.title)
        description_details.setText(newsPost.description)
        author_details.setText(newsPost.author)
        date_details.setText(setPostDate(newsPost.date))

        //Set button press to open link to webpage
        button_details.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(newsPost.url)
            startActivity(openURL)
        }

        //Show toolbar
        setSupportActionBar(toolbar_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //Adds functionality to toolbar's back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //Converts date to better format
    private fun setPostDate(date: String) : String {
        val mParsedDate: Date
        val mOutputDateString: String
        val mInputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val mOutputDateFormat = SimpleDateFormat("yyyy MMMM dd HH:mm", Locale.getDefault())
        mParsedDate = mInputDateFormat.parse(date)
        mOutputDateString = mOutputDateFormat.format(mParsedDate)

        return mOutputDateString
    }
}