package rbdd.highton_android.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_main.view.*
import rbdd.highton_android.Adapter.HotFragmentAdapter
import rbdd.highton_android.Adapter.TodayFragmentAdapter
import rbdd.highton_android.Connect.Connector
import rbdd.highton_android.Connect.Responce
import rbdd.highton_android.Model.ContentBannerModel
import rbdd.highton_android.R

/**
 * Created by root1 on 2017. 11. 5..
 */

@SuppressLint("ValidFragment")
class MainFragement(fm: FragmentManager): Fragment() {

    lateinit var fm : FragmentManager

    init {
        this.fm = fm
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_main, container, false)
        val hotAdapter = HotFragmentAdapter(fm)
        val todayAdapter = TodayFragmentAdapter(fm)

        Connector.api.getMainNews()
                .enqueue(object : Responce<JsonObject>{
                    override fun onCall(code: Int, body: JsonObject?) {
                        if (code == 200){
                            val hotData = body?.get("hot_issue")
                            val todayData = body?.get("today_trump")

                            val gson = Gson()

                            val hotArr = gson.fromJson(hotData, Array<ContentBannerModel>::class.java)
                            val todayArr = gson.fromJson(todayData, Array<ContentBannerModel>::class.java)

                            hotAdapter.bind(hotArr)
                            todayAdapter.bind(todayArr)

                            with(view){
                                hotViewPager.adapter = hotAdapter
                                todayViewPager.adapter = todayAdapter
                            }

                        }
                    }
                })

        return view
    }

}