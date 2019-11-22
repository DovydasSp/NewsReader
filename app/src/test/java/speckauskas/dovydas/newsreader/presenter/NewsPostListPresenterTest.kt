package speckauskas.dovydas.newsreader.presenter

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import speckauskas.dovydas.newsreader.contract.ContractInterface
import speckauskas.dovydas.newsreader.model.NewsPostModel
import java.lang.Thread.sleep

class NewsPostListPresenterTest {
    lateinit var newsPostListPresenter:NewsPostListPresenter
    @Mock
    lateinit var newsPostListActivity:ContractInterface.IMainActivityView
    var testItems = ArrayList<NewsPostModel>()
    @Mock
    lateinit var rowView: ContractInterface.INewsPostRowView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        newsPostListPresenter = NewsPostListPresenter(newsPostListActivity)
        newsPostListPresenter.setData(testItems)
    }

    @After
    fun tearDown(){
        testItems = ArrayList<NewsPostModel>()
    }

    @Test
    fun setData() {
        val item = NewsPostModel("imageUrl", "title", "date","author","description","url")
        testItems.add(item)
        newsPostListPresenter.setData(testItems)
        assertEquals(newsPostListPresenter.items, testItems)
    }

    @Test
    fun setData2() {
        var items2 = ArrayList<NewsPostModel>()
        newsPostListPresenter.setData(testItems)
        assertEquals(newsPostListPresenter.items, items2)
    }

    @Test
    fun onBindRepositoryRowViewAtPosition() {
        val item = NewsPostModel("https://9to5google.com/wp-content/uploads/sites/4/2019/11/google_pixel_4_15.jpg?resize=1024,512", "\$100 Google Store credit for pre-ordering Pixel 4 now available - 9to5Google", "2019-11-22T08:30:00Z","author","description","url")
        testItems.add(item)
        newsPostListPresenter.setData(testItems)
        newsPostListPresenter.onBindRepositoryRowViewAtPosition(0, rowView)
        verify(rowView).setPostDate("2019 November 22 08:30")
        verify(rowView).setPostImage("https://9to5google.com/wp-content/uploads/sites/4/2019/11/google_pixel_4_15.jpg?resize=1024,512")
        verify(rowView).setPostTitle("\$100 Google Store credit for pre-ordering Pixel 4 now available - 9to5Google")
        verify(rowView).addOnClickListener(newsPostListPresenter)
    }

    @Test
    fun onBindRepositoryRowViewAtInvalidPosition() {
        val item = NewsPostModel("https://9to5google.com/wp-content/uploads/sites/4/2019/11/google_pixel_4_15.jpg?resize=1024,512", "\$100 Google Store credit for pre-ordering Pixel 4 now available - 9to5Google", "2019-11-22T08:30:00Z","author","description","url")
        testItems.add(item)
        newsPostListPresenter.setData(testItems)
        newsPostListPresenter.onBindRepositoryRowViewAtPosition(1, rowView)
        verifyNoInteractions(rowView)
    }

    @Test
    fun onBindRepositoryRowViewAtPositionNoImg() {
        val item = NewsPostModel("", "\$100 Google Store credit for pre-ordering Pixel 4 now available - 9to5Google", "2019-11-22T08:30:00Z","author","description","url")
        testItems.add(item)
        newsPostListPresenter.setData(testItems)
        newsPostListPresenter.onBindRepositoryRowViewAtPosition(0, rowView)
        verify(rowView).setPostDate("2019 November 22 08:30")
        verify(rowView).setPostTitle("\$100 Google Store credit for pre-ordering Pixel 4 now available - 9to5Google")
        verify(rowView).addOnClickListener(newsPostListPresenter)
        verifyNoMoreInteractions(rowView)
    }

    @Test
    fun onBindRepositoryRowViewAtPositionNoDate() {
        val item = NewsPostModel("https://9to5google.com/wp-content/uploads/sites/4/2019/11/google_pixel_4_15.jpg?resize=1024,512", "\$100 Google Store credit for pre-ordering Pixel 4 now available - 9to5Google", "","author","description","url")
        testItems.add(item)
        newsPostListPresenter.setData(testItems)
        newsPostListPresenter.onBindRepositoryRowViewAtPosition(0, rowView)
        verify(rowView).setPostDate("")
        verify(rowView).setPostImage("https://9to5google.com/wp-content/uploads/sites/4/2019/11/google_pixel_4_15.jpg?resize=1024,512")
        verify(rowView).setPostTitle("\$100 Google Store credit for pre-ordering Pixel 4 now available - 9to5Google")
        verify(rowView).addOnClickListener(newsPostListPresenter)
    }

    @Test
    fun getRepositoriesRowsCount0() {
        var count = newsPostListPresenter.getRepositoriesRowsCount()
        assertEquals(count, 0)
    }

    @Test
    fun getRepositoriesRowsCount4() {
        val item = NewsPostModel("imageUrl", "title", "date","author","description","url")
        testItems.add(item)
        testItems.add(item)
        testItems.add(item)
        testItems.add(item)
        var count = newsPostListPresenter.getRepositoriesRowsCount()
        assertEquals(count, 4)
    }

    @Test
    fun onItemClickedAtPosition() {
        val item = NewsPostModel("imageUrl", "title", "date","author","description","url")
        testItems.add(item)
        newsPostListPresenter.onItemClickedAtPosition(0)
        verify(newsPostListActivity).launchNewActivity(item)
    }

    @Test
    fun onItemClickedAtInvalidPosition() {
        val item = NewsPostModel("imageUrl", "title", "date","author","description","url")
        testItems.add(item)
        newsPostListPresenter.onItemClickedAtPosition(1)
        verifyNoInteractions(newsPostListActivity)
    }

    @Test
    fun getDataEmptyStrings() {
        var items2 = ArrayList<NewsPostModel>()
        newsPostListPresenter.getData("","")
        assertEquals(newsPostListPresenter.items, items2)
        verifyNoInteractions(newsPostListActivity)
    }

    @Test
    fun getDataEmptyString1() {
        var items2 = ArrayList<NewsPostModel>()
        newsPostListPresenter.getData("","technology")
        assertEquals(newsPostListPresenter.items, items2)
        verifyNoInteractions(newsPostListActivity)
    }

    @Test
    fun getDataEmptyString2() {
        var items2 = ArrayList<NewsPostModel>()
        newsPostListPresenter.getData("us","")
        assertEquals(newsPostListPresenter.items, items2)
        verifyNoInteractions(newsPostListActivity)
    }

    @Test
    fun getDataNonValidStrings() {
        var items2 = ArrayList<NewsPostModel>()
        newsPostListPresenter.getData("testCountry","testCategory")
        sleep(2000)
        assertEquals(newsPostListPresenter.items, items2)
        verifyNoInteractions(newsPostListActivity)
    }

    @Test
    fun getDataValidString() {
        var items2 = ArrayList<NewsPostModel>()
        newsPostListPresenter.getData("us","technology")
        sleep(2000)
        assertNotEquals(newsPostListPresenter.items, items2)
        verify(newsPostListActivity).refreshRecyclerView()
    }
}