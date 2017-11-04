package rbdd.highton_android.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_content_main_banner.view.*
import rbdd.highton_android.R

/**
 * Created by root1 on 2017. 11. 5..
 */
class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    override fun onBindViewHolder(holder: MainViewHolder?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_content_main_banner, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }
}

class MainViewHolder(view: View): RecyclerView.ViewHolder(view){
    lateinit var view: View

    init {
        this.view = view
    }

    public fun bind(title: String, content: String, from: String, like: Int, unLick: Int){
        with(view){
            titleText.text =
        }
    }


}

