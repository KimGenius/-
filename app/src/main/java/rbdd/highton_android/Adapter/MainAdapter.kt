package rbdd.highton_android.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.util.Log
import rbdd.highton_android.Fragment.MainContentBannerFragment
import rbdd.highton_android.Model.ContentBannerModel

/**
 * Created by root1 on 2017. 11. 5..
 */
class HotFragmentAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    var data = emptyArray<ContentBannerModel>()

    override fun getItem(position: Int): Fragment {
        Log.d("pos", "" + position)
        val fragment = MainContentBannerFragment()
        fragment.bind(data.get(position))
        return fragment
    }

    override fun getCount(): Int {
        return data.size
    }

    fun bind(data: Array<ContentBannerModel>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemPosition(Object: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }

}

class TodayFragmentAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    var data = emptyArray<ContentBannerModel>()

    override fun getItem(position: Int): Fragment {
        var fragment = MainContentBannerFragment()
        fragment.bind(data.get(position))
        return fragment
    }

    override fun getCount(): Int {
        return data.size
    }

    fun bind(data: Array<ContentBannerModel>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemPosition(Object: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }


}