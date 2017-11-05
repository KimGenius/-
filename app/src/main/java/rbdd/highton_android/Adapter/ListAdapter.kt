package rbdd.highton_android.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_content_main_banner.view.*
import rbdd.highton_android.Activity.ContentNewsActivity
import rbdd.highton_android.Model.ContentBannerModel
import rbdd.highton_android.R

/**
 * Created by root1 on 2017. 11. 5..
 */
class ListAdapter: RecyclerView.Adapter<ListViewHolder>() {

    var data = emptyArray<ContentBannerModel>()

    fun bind(data: Array<ContentBannerModel>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.view_content_main_banner, parent, false)
        return ListViewHolder(view, parent?.context!!)
    }

    override fun onBindViewHolder(holder: ListViewHolder?, position: Int) {
        holder?.bind(data.get(position))
    }

    override fun getItemCount(): Int{
        return data.size
    }
}

class ListViewHolder(view: View, context: Context): RecyclerView.ViewHolder(view){
    var view: View? = null
    var context: Context? = null

    init {
        this.view = view
        this.context = context
    }

    fun bind(data: ContentBannerModel){
        with(view!!){
            titleText.text = data.title
            contentText.text = data.description
            likeCountText.text = "${data.like}"
            unLickCountText.text = "${data.unLike}"
        }

        view?.setOnClickListener {
            val intent = Intent(context, ContentNewsActivity::class.java)
            intent.putExtra("id", data.id)
            context?.startActivity(intent)
        }
    }


}