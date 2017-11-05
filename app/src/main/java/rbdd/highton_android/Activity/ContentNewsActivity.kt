package rbdd.highton_android.Activity

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_content)

        goLink.bringToFront()
//        progress_bar.visibility = View.VISIBLE
        val id = intent.getStringExtra("id")
        Connector.api.getNews(id).enqueue(object : Responce<ContentNewsModel> {
            override fun onCall(code: Int, body: ContentNewsModel?) {
                if (code == 200) {
                    headerText.text = body?.title
                    contentNewsText.text = body?.content
                    dateText.text = body?.date
                    GlideUtil.setGliding(this@ContentNewsActivity, TrumpUtil.getRandomTrump(), img)

                    goLink.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)
                        val url = Uri.parse(body?.link)
                        intent.data = url
                        startActivity(intent)
                    }
                }
            }
        })

//        progress_bar.visibility = View.GONE

    }
}