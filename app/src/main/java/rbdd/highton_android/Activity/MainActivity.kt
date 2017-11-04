package rbdd.highton_android.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import com.facebook.CallbackManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_content_main.*
import rbdd.highton_android.Fragment.MainFragement
import rbdd.highton_android.Manager.GoogleSignInManager
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity
import rbdd.highton_android.Util.GlideUtil

class MainActivity : BaseActivity() {

    lateinit var googleSignIn: GoogleSignInManager
    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlideUtil.setGliding(this, R.drawable.ic_nav_drawer, navigationBarImg)
        GlideUtil.setGliding(this, R.drawable.ic_home_on, homeBtn)
        GlideUtil.setGliding(this, R.drawable.ic_list_off, listBtn)

        viewPager.adapter = MainPagerAdapter(supportFragmentManager)

    }

    class MainPagerAdapter(val fm: FragmentManager): FragmentStatePagerAdapter(fm){

        override fun getItem(position: Int): Fragment {
            return MainFragement()
        }

        override fun getCount(): Int {
            return 10
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("req", requestCode.toString())
        when (requestCode) {
            GOOGLESIGNINCODE -> {
                val account = googleSignIn.googleSignInResult(data!!)
                showToast("" + account?.displayName)
            }
            FACEBOOKSIGNCODE -> {
                super.onActivityResult(requestCode, resultCode, data)
                callbackManager.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}
