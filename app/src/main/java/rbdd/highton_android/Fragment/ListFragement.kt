package rbdd.highton_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rbdd.highton_android.Adapter.ListAdapter
import rbdd.highton_android.Connect.Connector
import rbdd.highton_android.Connect.Responce
import rbdd.highton_android.Model.ContentBannerModel
import rbdd.highton_android.R

/**
 * Created by root1 on 2017. 11. 5..
 */


class ListFragement: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(container?.context)
        recyclerView.adapter = adapter

        Connector.api.getListNews(1).enqueue(object : Responce<Array<ContentBannerModel>>{
            override fun onCall(code: Int, body: Array<ContentBannerModel>?) {
                if(code == 200){
                    adapter.bind(body!!)
                    recyclerView.adapter.notifyDataSetChanged()
                }
            }
        })



        return view
    }

}