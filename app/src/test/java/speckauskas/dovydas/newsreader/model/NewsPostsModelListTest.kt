package speckauskas.dovydas.newsreader.model

import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class NewsPostsModelListTest {
    lateinit var results : ArrayList<NewsPostModel>
    @Before
    fun setUp() {
        results = ArrayList<NewsPostModel>()
    }

    @After
    fun tearDown() {
        results.clear()
    }

    @Test
    fun getResultsWithItems() {
        val result = NewsPostModel("", "", "","","","")
        results.add(result)
        results.add(result)
        results.add(result)
        results.add(result)
        val newsList = NewsPostsModelList(results)
        val count = newsList.results.count()

        assertEquals(4, count)
    }

    @Test
    fun getResultsWithItems2() {
        val result = NewsPostModel("", "", "","","","")
        results.add(result)
        results.add(result)
        results.add(result)
        results.add(result)
        val newsList = NewsPostsModelList(results)
        val nullOrEmpty = newsList.results.isNullOrEmpty()

        assertEquals(false, nullOrEmpty)
    }

    @Test
    fun getResultsWithoutItems() {
        val newsList = NewsPostsModelList(results)
        val count = newsList.results.count()

        assertEquals(0, count)
    }

    @Test
    fun getResultsWithoutItems2() {
        val newsList = NewsPostsModelList(results)
        val nullOrEmpty = newsList.results.isNullOrEmpty()

        assertEquals(true, nullOrEmpty)
    }

    @Test
    fun `getResultsDoesn'tContain`() {
        val result = NewsPostModel("", "", "","","","")
        val newsList = NewsPostsModelList(results)
        val contains = newsList.results.contains(result)

        assertEquals(false, contains)
    }

    @Test
    fun getResultsContains() {
        val result = NewsPostModel("", "", "","","","")
        results.add(result)
        val newsList = NewsPostsModelList(results)
        val contains = newsList.results.contains(result)

        assertEquals(true, contains)
    }
}