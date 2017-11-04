package rbdd.highton_android.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_content_main_banner.view.*
import rbdd.highton_android.Model.ContentBannerModel
import rbdd.highton_android.R

/**
 * Created by root1 on 2017. 11. 5..
 */
class ListAdapter(data: Array<ContentBannerModel>): RecyclerView.Adapter<ListViewHolder>() {

    lateinit var data: Array<ContentBannerModel>

    init {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.view_content_main_banner, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder?, position: Int) {
        holder?.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
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