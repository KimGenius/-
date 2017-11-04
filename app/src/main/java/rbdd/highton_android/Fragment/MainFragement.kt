package rbdd.highton_android.Fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.view.*
import rbdd.highton_android.Adapter.MainAdapter
import rbdd.highton_android.R

/**
 * Created by root1 on 2017. 11. 5..
 */
class MainFragement: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        with(view){
            recyclerView.layoutManager = LinearLayoutManager(container.context)
            recyclerView.adapter = MainAdapter()
        }

        return view
    }
}