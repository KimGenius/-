package rbdd.highton_android.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.view.*
import rbdd.highton_android.Adapter.ListAdapter
import rbdd.highton_android.R

/**
 * Created by root1 on 2017. 11. 5..
 */


class ListFragement(): Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list, container, false)
        with(view){
            recyclerView.adapter = ListAdapter()
        }
        return view
    }

}