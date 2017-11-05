package rbdd.highton_android.Activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
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
        Log.d("item", item.itemId.toString())
        when (item.itemId) {
            2131230750-> {
                goNextActivity(AlimActivity::class.java, false)
            }
            2131230873 -> {
                goNextActivity(HistoryActivity::class.java, false)
            }
            2131230908 -> {

            }
            2131230876 -> {
                saveCookie("")
                goNextActivity(LoginActivity::class.java, true)
            }
            2131230939 -> {

            }
        }
        return true
    }

    lateinit var googleSignIn: GoogleSignInManager
    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        mainPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        GlideUtil.setGliding(this@MainActivity, R.drawable.ic_list_off, listBtn)
                        GlideUtil.setGliding(this@MainActivity, R.drawable.ic_home_on, homeBtn)
                    }
                    1 -> {
                        GlideUtil.setGliding(this@MainActivity, R.drawable.ic_list_on, listBtn)
                        GlideUtil.setGliding(this@MainActivity, R.drawable.ic_home_off, homeBtn)
                    }
                }
            }

        })

    }

    class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        lateinit var fm: FragmentManager

        init {
            this.fm = fm
        }

        override fun getItem(position: Int): Fragment? {
            return when (position) {
                0 -> MainFragement(fm)
                1 -> ListFragement()
                else -> null
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getItemPosition(`object`: Any?): Int {
            return PagerAdapter.POSITION_NONE
        }

    }
}
