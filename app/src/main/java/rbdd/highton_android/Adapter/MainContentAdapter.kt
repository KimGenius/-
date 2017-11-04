package rbdd.highton_android.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import rbdd.highton_android.Fragment.MainContentBannerFragment

/**
 * Created by root1 on 2017. 11. 5..
 */
class MainContentAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return MainContentBannerFragment()
    }

    override fun getCount(): Int {
        return 4
    }
}