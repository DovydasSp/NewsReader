package speckauskas.dovydas.newsreader.presenter

import speckauskas.dovydas.newsreader.model.NewsPostModel
import speckauskas.dovydas.newsreader.contract.ContractInterface.INewsPostListPresenter
import speckauskas.dovydas.newsreader.contract.ContractInterface.INewsPostRowView
class NewsPostListPresenter: INewsPostListPresenter {
    private var items: List<NewsPostModel> = ArrayList()

    override fun addDataSet(items_: ArrayList<NewsPostModel>){
        items = items_
    }

    override fun onBindRepositoryRowViewAtPosition(position: Int, rowViewI: INewsPostRowView) {
        val repo = items.get(position)
        rowViewI.setPostDate(repo.date)
        rowViewI.setPostImage(repo.imageUrl)
        rowViewI.setPostTitle(repo.title)
        rowViewI.addOnClickListener(this)
    }

    override fun getRepositoriesRowsCount(): Int {
        return items.size
    }

    override fun onItemClickedAtPosition(adapterPosition: Int) {
        System.out.println("RecyclerView item pressed: {$adapterPosition}")
    }
}