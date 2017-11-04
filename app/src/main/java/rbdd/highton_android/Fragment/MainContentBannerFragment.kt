package rbdd.highton_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_content_main_banner.view.*
import rbdd.highton_android.Model.ContentBannerModel
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity

/**
 * Created by root1 on 2017. 11. 5..
 */
class MainContentBannerFragment: Fragment() {

    var itemView:View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        itemView = inflater!!.inflate(R.layout.view_content_main_banner, container, false)
        itemView?.setOnClickListener {
            val activity = container!! as BaseActivity

        }

        return view
    }

    fun bind(data: ContentBannerModel){
        with(itemView!!){
            titleText.text = data.title
            contentText.text = data.description
            likeCountText.text = "${data.like}"
            unLickCountText.text = "${data.unLike}"
        }
    }

}