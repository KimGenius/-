package rbdd.highton_android.Activity

import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_splash.*
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity
import rbdd.highton_android.Util.GlideUtil

/**
 * Created by young on 2017-11-05/오전 9:23
 * This Project is HighThon-Trump
 */
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        GlideUtil.setGliding(this@SplashActivity, R.drawable.splash, splashImg)

        Log.d("xxx", getCookie())

        Handler().postDelayed({
            if (getCookie().equals("JWT "))
                goNextActivity(LoginActivity::class.java, true)
            else
                goNextActivity(MainActivity::class.java, true)
        }, 1000)
    }
}