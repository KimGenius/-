package rbdd.highton_android.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookActivity
import com.facebook.FacebookSdk
import kotlinx.android.synthetic.main.activity_login.*
import rbdd.highton_android.Manager.FacebookSignInManager
import rbdd.highton_android.Manager.GoogleSignInManager
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity

/**
 * Created by root1 on 2017. 11. 5..
 */
class LoginActivity: BaseActivity() {
    lateinit var faceCall : CallbackManager
    private lateinit var googleSignIn : GoogleSignInManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setBtn()

    }
    private fun setBtn() {
        val facebookSignin = FacebookSignInManager()
        faceCall = CallbackManager.Factory.create()
        facebookSignin.facebookSignIn(this@LoginActivity,facebookBtn,faceCall)
        googleBtn.setOnClickListener {
            googleSignIn = GoogleSignInManager(this@LoginActivity)
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
                faceCall.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}