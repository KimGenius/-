package rbdd.highton_android.Manager

import android.content.Intent
import android.util.Log
import android.view.View
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import rbdd.highton_android.Activity.MainActivity
import rbdd.highton_android.Connect.Connector
import rbdd.highton_android.Util.BaseActivity
import java.util.*
import com.facebook.GraphResponse
import org.json.JSONObject
import com.facebook.GraphRequest
import com.google.gson.JsonObject
import rbdd.highton_android.Model.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.Bundle


/**
 * Created by young on 2017-11-05/오전 1:24
 * This Project is HighThon-Trump
 */
class FacebookSignInManager {
    fun facebookSignIn(activity: BaseActivity, loginButton: LoginButton, callbackManager: CallbackManager) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email", "user_birthday", "user_friends"))
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"))
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.d("facebook", result!!.accessToken.token)
                Log.d("facebookL", Locale.getDefault().language)
                val request = GraphRequest.newMeRequest(result.accessToken) { `object`, response ->
                    Log.v("LoginActivity", response.toString())
                    Connector.api.authFacebook(result.accessToken.token, `object`.getString("name"))
                            .enqueue(object : Callback<JsonObject> {
                                override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                                    if (response!!.isSuccessful) {
                                        activity.saveCookie(response!!.body()!!.get("access_token").toString())
                                        Log.d("cookie ", response!!.body()!!.get("access_token").toString())
                                    } else
                                        activity.showToast("클라이언트 에러")
                                }

                                override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                                    activity.showToast("서버 연결 실패")
                                    t!!.printStackTrace()
                                }

                            })
                }
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email,gender,birthday")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onError(error: FacebookException?) {
                Log.d("facebookError", error.toString())
            }

            override fun onCancel() {

            }
        })
    }
}