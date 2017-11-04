package rbdd.highton_android.Activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_navigation.*
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity

/**
 * Created by root1 on 2017. 11. 4..
 */
class NavigationActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        setSupportActionBar(tool_bar)
        val actionBar = supportActionBar
        actionBar?.setHomeAsUpIndicator(R.mipmap.ic_launcher)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener({
            item ->
            item.setCheckable(true)
            drawerLayout.closeDrawers()
            true
        })

    }
}