package rbdd.highton_android.Activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.facebook.CallbackManager
import kotlinx.android.synthetic.main.activity_main.*
import rbdd.highton_android.Fragment.ListFragement
import rbdd.highton_android.Fragment.MainFragement
import rbdd.highton_android.Manager.GoogleSignInManager
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity
import rbdd.highton_android.Util.GlideUtil


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item) {

        }
        return true
    }

    lateinit var googleSignIn: GoogleSignInManager
    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        GlideUtil.setGliding(this, R.drawable.ic_nav_drawer, navigationBarImg)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, topBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        GlideUtil.setGliding(this, R.drawable.ic_home_on, homeBtn)
        GlideUtil.setGliding(this, R.drawable.ic_list_off, listBtn)

        mainPager.adapter = MainPagerAdapter(supportFragmentManager)
        homeBtn.setOnClickListener {
            GlideUtil.setGliding(this, R.drawable.ic_list_off, listBtn)
            GlideUtil.setGliding(this, R.drawable.ic_home_on, homeBtn)
            mainPager.currentItem = 0
        }
        listBtn.setOnClickListener {
            GlideUtil.setGliding(this, R.drawable.ic_list_on, listBtn)
            GlideUtil.setGliding(this, R.drawable.ic_home_off, homeBtn)
            mainPager.currentItem = 1
        }
    }

    class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        lateinit var fm: FragmentManager

        init {
            this.fm = fm
        }

        override fun getItem(position: Int): Fragment? {
            return when(position){
                0 -> MainFragement(fm)
                1 -> ListFragement()
                else -> null
            }
        }

        override fun getCount(): Int {
            return 2
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
