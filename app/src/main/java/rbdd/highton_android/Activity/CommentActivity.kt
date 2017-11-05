package rbdd.highton_android.Activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_coment.*
import rbdd.highton_android.Adapter.CommentAdapter
import rbdd.highton_android.Connect.Connector
import rbdd.highton_android.Connect.Responce
import rbdd.highton_android.Model.CommnetModel
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity

/**
 * Created by root1 on 2017. 11. 5..
 */
class CommentActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coment)

        var id = intent.getStringExtra("id")

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CommentAdapter()
        recyclerView.adapter = adapter

        backBtn.setOnClickListener {
            finish()
        }

        commentCountText.text = "0"

        getComment(id, adapter)

        commentButton.setOnClickListener {
            if (commentEdit.text.isEmpty()){
                showToast("댓글을 입력하세요")
            }else{
                Connector.api.addComment(getCookie(), id, commentEdit.text.toString())
                        .enqueue(object : Responce<Void>{
                            override fun onCall(code: Int, body: Void?) {
                                getComment(id, adapter)
                            }
                        })
            }
        }

    }

    fun getComment(id: String, adapter: CommentAdapter){
        Connector.api.getComment(getCookie(), id)
                .enqueue(object : Responce<Array<CommnetModel>>{
                    override fun onCall(code: Int, body: Array<CommnetModel>?) {
                        if(code == 200){
                            commentCountText.text = "${body?.size}"
                            adapter.bind(body!!)
                        }
                    }
                })
    }

}