package rbdd.highton_android.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_action_log.*
import rbdd.highton_android.Connect.Connector
import rbdd.highton_android.Model.activityLog
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by young on 2017-11-05/오전 10:32
 * This Project is HighThon-Trump
 */
class HistoryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_log)
        backBtn.setOnClickListener {
            finish()
        }
        Log.d("cooke", getCookie())
        Connector.api.getActivityLog(getCookie()).enqueue(object : Callback<activityLog> {
            override fun onFailure(call: Call<activityLog>?, t: Throwable?) {
                showToast("서버 연결 실패")
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<activityLog>?, response: Response<activityLog>?) {
                if (response!!.isSuccessful) {
                    response.body().run {
                        this!!.run {
                            signupDay.text = after_signup.toString() + "일"
//                        signupRank.text =
                            contriScore.text = contribution_score.toString() + "점"
                            commentScore.text = comment_count.toString() + "개"
                            likeScore.text = received_like_count.toString() + "개"
                            commentOnedayScore.text = "평균 " + comment_avg.toString() + "개"
                            likeOnedayScore.text = "평균 " + received_like_avg.toString() + "개"
                        }
                    }
                } else {
                    showToast("클라이언트 오류")
                }
            }

        })
    }
}