package rbdd.highton_android.Adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rbdd.highton_android.Model.notification
import rbdd.highton_android.R

/**
 * Created by young on 2017-11-05/오전 11:54
 * This Project is HighThon-Trump
 */
class NotificationAdapter(val activity: Activity, val gsonData: List<notification>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val view = LayoutInflater.from(activity).inflate(R.layout.view_alim, parent, false)
        viewHolder = GridViewHolder(view)
        return viewHolder
    }


    override fun getItemCount(): Int {
        return gsonData.size
    }

}
class GridViewHolder(v: View) : RecyclerView.ViewHolder(v)