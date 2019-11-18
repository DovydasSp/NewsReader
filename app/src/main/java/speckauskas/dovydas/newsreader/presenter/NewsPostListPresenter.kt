package speckauskas.dovydas.newsreader.presenter

import speckauskas.dovydas.newsreader.model.NewsPostModel
import speckauskas.dovydas.newsreader.contract.ContractInterface.INewsPostListPresenter
import speckauskas.dovydas.newsreader.contract.ContractInterface.INewsPostRowView
class NewsPostListPresenter: INewsPostListPresenter {
    private lateinit var items: List<NewsPostModel>

    override fun addDataSet(items_: ArrayList<NewsPostModel>){
        items = ArrayList()
        items = items_
    }

    override fun onBindRepositoryRowViewAtPosition(position: Int, rowView: INewsPostRowView) {
        val repo = items.get(position)
        rowView.setPostDate(repo.date)
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
}