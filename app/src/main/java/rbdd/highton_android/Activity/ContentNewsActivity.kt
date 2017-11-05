package rbdd.highton_android.Activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_show_content.*
import rbdd.highton_android.Connect.Connector
import rbdd.highton_android.Connect.Responce
import rbdd.highton_android.Model.ContentNewsModel
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity
import rbdd.highton_android.Util.GlideUtil
import rbdd.highton_android.Util.TrumpUtil

/**
 * Created by root1 on 2017. 11. 5..
 */
class ContentNewsActivity : BaseActivity() {

    var isLike = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_content)

        goLink.bringToFront()
        progress_bar.visibility = View.VISIBLE
        val id = intent.getStringExtra("id")
        Connector.api.getNews(id).enqueue(object : Responce<ContentNewsModel> {
            override fun onCall(code: Int, body: ContentNewsModel?) {
                if (code == 200) {
                    headerText.text = body?.title
                    contentNewsText.text = body?.content
                    dateText.text = body?.date
                    isLike = body?.liked!!
                    checkLike()
                    GlideUtil.setGliding(this@ContentNewsActivity, TrumpUtil.getRandomTrump(), img)

                    goLink.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)
                        val url = Uri.parse(body?.link)
                        intent.data = url
                        startActivity(intent)
                    }

                    likeButton.setOnClickListener {
                        if (isLike){
                            Connector.api.addLike(getCookie(), id)
                                    .enqueue(object : Responce<Void>{
                                        override fun onCall(code: Int, body: Void?) {

                                        }
                                    })
                        }else{
                            Connector.api.removeLike(getCookie(), id)
                                    .enqueue(object : Responce<Void>{
                                        override fun onCall(code: Int, body: Void?) {

                                        }
                                    })
                        }
                        isLike = !isLike
                        checkLike()
                    }
                }
            }
        })


        backBtn.setOnClickListener {
            finish()
        }


        progress_bar.visibility = View.GONE

    }

    fun checkLike(){
        if (isLike){
            likeButton.setBackgroundResource(R.drawable.back_content_button_liked)
            likeButton.setTextColor(resources.getColor(R.color.colorMain))
        }else{
            likeButton.setBackgroundResource(R.drawable.back_content_button_like)
            likeButton.setTextColor(Color.WHITE)
        }
    }
}