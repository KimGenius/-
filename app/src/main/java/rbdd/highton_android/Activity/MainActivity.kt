package rbdd.highton_android.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*
import rbdd.highton_android.Manager.GoogleSignInManager
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity
import rbdd.highton_android.Util.GlideUtil
import java.util.*

class MainActivity : BaseActivity() {

    lateinit var googleSignIn: GoogleSignInManager
    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
<<<<<<< HEAD

        Log.e("xxx", "" + FirebaseInstanceId.getInstance().token)

        googleSign.setOnClickListener {
            googleSignIn = GoogleSignInManager(MainActivity@ this)
        }

        callbackManager = CallbackManager.Factory.create()
//        loginButton.setReadPermissions("email", "public_profile", "user_friends")
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
        loginButton.setReadPermissions("email")
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.d("facebook", result.toString())
                Log.d("facebook", result!!.accessToken.token)
            }

            override fun onError(error: FacebookException?) {
                Log.d("facebookError", error.toString())
            }

            override fun onCancel() {
                showToast("취소;")
            }
        })
=======
        GlideUtil.setGliding(this, R.drawable.ic_nav_drawer, navigationBarImg)
        GlideUtil.setGliding(this, R.drawable.ic_home_on, homeBtn)
        GlideUtil.setGliding(this, R.drawable.ic_list_off, listBtn)
//        googleSign.setOnClickListener {
//            googleSignIn = GoogleSignInManager(MainActivity@ this)
//        }
//
//        callbackManager = CallbackManager.Factory.create()
////        loginButton.setReadPermissions("email", "public_profile", "user_friends")
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
//        loginButton.setReadPermissions("email")
//        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
//            override fun onSuccess(result: LoginResult?) {
//                Log.d("facebook", result.toString())
//                Log.d("facebook", result!!.accessToken.token)
//            }
//
//            override fun onError(error: FacebookException?) {
//                Log.d("facebookError", error.toString())
//            }
//
//            override fun onCancel() {
//                showToast("취소;")
//            }
//        })
>>>>>>> 3e1e15d94b00c69687706eb7cb9732f5d5559834


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