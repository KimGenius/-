package rbdd.highton_android.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
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
class MainContentBannerFragment: Fragment() {

    lateinit var itemView: View
    lateinit var data: ContentBannerModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.view_content_main_banner, container, false)

        with(view){
            Log.e("xxx", data.toString())
            titleText.text = data.title
            contentText.text = data.description
            likeCountText.text = "${data.like}"
            unLickCountText.text = "${data.unLike}"
        }

        view.setOnClickListener {
            val intent = Intent(container?.context, ContentNewsActivity::class.java)
            intent.putExtra("id", data.id)
            startActivity(intent)
        }

        return view
    }

    fun bind(data: ContentBannerModel){
        this.data = data
    }

}