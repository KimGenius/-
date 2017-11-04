package rbdd.highton_android.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.view.*
import rbdd.highton_android.Adapter.MainAdapter
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity

/**
 * Created by root1 on 2017. 11. 5..
 */

@SuppressLint("ValidFragment")
class MainFragement(activity: BaseActivity): Fragment() {

    lateinit var activity: BaseActivity

    init {
        this.activity = activity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_main, container, false)
        with(view){
            recyclerView.layoutManager = LinearLayoutManager(container?.context)
            recyclerView.adapter = MainAdapter(activity)
        }

        return view
    }

}