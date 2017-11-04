package rbdd.highton_android.Activity

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import rbdd.highton_android.Manager.GoogleSignInManager
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity

class MainActivity : BaseActivity() {

    lateinit var googleSignIn: GoogleSignInManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        googleSign.setOnClickListener {
            googleSignIn = GoogleSignInManager(MainActivity@this)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            GOOGLESIGNINCODE -> {
                val account = googleSignIn.googleSignInResult(data!!)
            }
        }
    }
}
