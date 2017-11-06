package rbdd.highton_android.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_comment.view.*
import rbdd.highton_android.Model.CommnetModel
import rbdd.highton_android.R

/**
 * Created by root1 on 2017. 11. 5..
 */

class CommentAdapter: RecyclerView.Adapter<CommentViewHolder>(){

    var data = emptyArray<CommnetModel>()

    fun bind(data: Array<CommnetModel>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder?, position: Int) {
        holder?.bind(data.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.view_comment, parent, false)
        return CommentViewHolder(view)
    }
}

class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    lateinit var view: View

    init {
        this.view = itemView
    }

    fun bind(data: CommnetModel){
        with(view){
            commentText.text = data.content
            commentWriterText.text = data.writer
        }
    }
}