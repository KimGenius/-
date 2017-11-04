package rbdd.highton_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rbdd.highton_android.R

/**
 * Created by root1 on 2017. 11. 5..
 */
class MainContentBannerFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.view_content_main_banner, container, false)
        return view
    }

}