package speckauskas.dovydas.newsreader.model

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class NewsPostModelTest {

    lateinit var newsPost: NewsPostModel

    @Before
    fun setUp() {
        newsPost = NewsPostModel("", "", "","","", "")
    }

    @Test
    fun changedDateEmptyDate() {
        var date = newsPost.changedDate()
        assertEquals("",date)
    }

    @Test
    fun changedDateRandomDate() {
        newsPost.date = "random date string"
        var date = newsPost.changedDate()
        assertEquals("",date)
    }

    @Test
    fun changedDateNormalDate() {
        newsPost.date = "2019-11-22T08:30:00Z"
        var date = newsPost.changedDate()
        assertEquals("2019 November 22 08:30",date)
    }

    @Test
    fun getImageUrl() {
        var variable = newsPost.imageUrl
        assertEquals("",variable)
    }

    @Test
    fun getImageUrlIsEmpty() {
        var test = newsPost.imageUrl.isNullOrEmpty()
        assertEquals(true,test)
    }

    @Test
    fun setImageUrl() {
        newsPost.imageUrl = "A new test string"
        var variable = newsPost.imageUrl
        assertEquals("A new test string",variable)
    }

    @Test
    fun getTitle() {
        var variable = newsPost.title
        assertEquals("",variable)
    }

    @Test
    fun getTitleIsEmpty() {
        var test = newsPost.title.isNullOrEmpty()
        assertEquals(true,test)
    }

    @Test
    fun setTitle() {
        newsPost.title = "A new test string"
        var variable = newsPost.title
        assertEquals("A new test string",variable)
    }

    @Test
    fun getDate() {
        var variable = newsPost.date
        assertEquals("",variable)
    }

    @Test
    fun getDateIsEmpty() {
        var test = newsPost.date.isNullOrEmpty()
        assertEquals(true,test)
    }

    @Test
    fun setDate() {
        newsPost.date = "A new test string"
        var variable = newsPost.date
        assertEquals("A new test string",variable)
    }

    @Test
    fun getAuthor() {
        var variable = newsPost.author
        assertEquals("",variable)
    }

    @Test
    fun getAuthorIsEmpty() {
        var test = newsPost.author.isNullOrEmpty()
        assertEquals(true,test)
    }

    @Test
    fun setAuthor() {
        newsPost.author = "A new test string"
        var variable = newsPost.author
        assertEquals("A new test string",variable)
    }

    @Test
    fun getDescription() {
        var variable = newsPost.description
        assertEquals("",variable)
    }

    @Test
    fun getDescriptionIsEmpty() {
        var test = newsPost.description.isNullOrEmpty()
        assertEquals(true,test)
    }

    @Test
    fun setDescription() {
        newsPost.description = "A new test string"
        var variable = newsPost.description
        assertEquals("A new test string",variable)
    }

    @Test
    fun getUrl() {
        var variable = newsPost.url
        assertEquals("",variable)
    }

    @Test
    fun getUrlIsEmpty() {
        var test = newsPost.url.isNullOrEmpty()
        assertEquals(true,test)
    }

    @Test
    fun setUrl() {
        newsPost.url = "A new test string"
        var variable = newsPost.url
        assertEquals("A new test string",variable)
    }
}