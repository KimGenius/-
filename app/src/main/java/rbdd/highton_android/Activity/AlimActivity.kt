package rbdd.highton_android.Activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_notification.*
import rbdd.highton_android.Adapter.NotificationAdapter
import rbdd.highton_android.Connect.Connector
import rbdd.highton_android.Model.activityLog
import rbdd.highton_android.Model.notification
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by young on 2017-11-05/오전 9:50
 * This Project is HighThon-Trump
 */
class AlimActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        backBtn.setOnClickListener { finish() }
        Connector.api.getNotification(getCookie()).enqueue(object : Callback<List<notification>> {
            override fun onFailure(call: Call<List<notification>>?, t: Throwable?) {
                showToast("서버 연결 실패")
            }

            override fun onResponse(call: Call<List<notification>>?, response: Response<List<notification>>?) {
                if (response!!.isSuccessful) {
                    response.body().run {
                        recyclerView.layoutManager = GridLayoutManager(this@AlimActivity, 1)
                        recyclerView.adapter = NotificationAdapter(this@AlimActivity, response.body() as List<notification>)
                    }
                } else {
                    showToast("클라이언트 오류")
                }
            }

        })
    }
}