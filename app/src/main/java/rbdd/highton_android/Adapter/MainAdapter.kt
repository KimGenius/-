package rbdd.highton_android.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_content_main.view.*
import rbdd.highton_android.Fragment.MainContentBannerFragment
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity

/**
 * Created by root1 on 2017. 11. 5..
 */
class MainAdapter(activity: BaseActivity): RecyclerView.Adapter<MainViewHolder>() {

    lateinit var activity: BaseActivity

    init {
        this.activity = activity
    }

    override fun onBindViewHolder(holder: MainViewHolder?, position: Int) {
        holder?.bind("hello", "world")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_content_main, parent, false)
        return MainViewHolder(view, activity)
    }

    override fun getItemCount(): Int {
        return 10
    }
}

class MainViewHolder(view: View, context: BaseActivity): RecyclerView.ViewHolder(view){
    lateinit var view: View
    lateinit var activity: BaseActivity

    init {
        this.view = view
        activity = context
        with(view){
            viewPager.adapter = MainContentAdapter(activity.supportFragmentManager)
        }
    }

    fun bind(title: String, sub: String){
        with(view){
            titleText.text = title
            subText.text = sub
        }
    }

}

class MainContentAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return MainContentBannerFragment()
    }

    override fun getCount(): Int {
        return 10
    }
}

