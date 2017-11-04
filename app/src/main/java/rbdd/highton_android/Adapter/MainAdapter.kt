package rbdd.highton_android.Adapter

import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_content_main.view.*
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity

/**
 * Created by root1 on 2017. 11. 5..
 */
class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    override fun onBindViewHolder(holder: MainViewHolder?, position: Int) {
        holder?.bind("hello", "world")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_content_main, parent, false)
        return MainViewHolder(view, parent.context as BaseActivity)
    }

    override fun getItemCount(): Int {
        return 4
    }

}

class MainViewHolder(view: View, context: BaseActivity): RecyclerView.ViewHolder(view){
    lateinit var view: View

    init {
        this.view = view
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = MainContentAdapter(context.supportFragmentManager)
    }

    fun bind(title: String, sub: String){
        with(view){
            titleText.text = title
            subText.text = sub
        }
    }

}

