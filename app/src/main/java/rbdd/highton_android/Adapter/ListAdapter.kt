package rbdd.highton_android.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_content_main_banner.view.*
import rbdd.highton_android.Model.ContentBannerModel

/**
 * Created by root1 on 2017. 11. 5..
 */
class ListAdapter: RecyclerView.Adapter<ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ListViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    lateinit var view: View

    init {
        this.view = itemView
    }

    fun bind(data: ContentBannerModel){
        with(view){
            titleText.text = data.title
            contentText.text = data.description
            likeCountText.text = "${data.like}"
            unLickCountText.text = "${data.unLike}"
        }
    }


}