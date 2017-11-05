package rbdd.highton_android.Manager

import android.content.Intent
import android.util.Log
import android.view.View
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import rbdd.highton_android.Activity.MainActivity
import rbdd.highton_android.Util.BaseActivity
import java.util.*

/**
 * Created by young on 2017-11-05/오전 1:24
 * This Project is HighThon-Trump
 */
class FacebookSignInManager {
    fun facebookSignIn(activity: BaseActivity, loginButton: LoginButton, callbackManager: CallbackManager) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile"))
        loginButton.setReadPermissions("email")
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.d("facebook", result!!.accessToken.token)
                Log.d("facebookL", Locale.getDefault().language)
                activity.saveCookie(result!!.accessToken.token)
                if (activity.getCookie() == "") {

                } else {
                    activity.startActivity(Intent(activity, MainActivity::class.java))
                    activity.finish()
                }
            }

            override fun onError(error: FacebookException?) {
                Log.d("facebookError", error.toString())
            }

            override fun onCancel() {

            }
        })
    }
}