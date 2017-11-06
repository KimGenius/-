package rbdd.highton_android.Manager

import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.facebook.login.widget.LoginButton
import rbdd.highton_android.Util.BaseActivity
import java.util.*


/**
 * Created by young on 2017-11-05/오전 1:24
 * This Project is HighThon-Trump
 */
class FacebookSignInManager {
    fun facebookSignIn(activity: BaseActivity, loginButton: LoginButton, callbackManager: CallbackManager) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email", "user_birthday", "user_friends"))
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"))
//        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
//            override fun onSuccess(result: LoginResult?) {
//                Log.d("facebook", result!!.accessToken.token)
//                Log.d("facebookL", Locale.getDefault().language)
//                val request = GraphRequest.newMeRequest(result.accessToken) { `object`, response ->
//                    Log.v("LoginActivity", response.toString())
//                    Connector.api.authFacebook(result.accessToken.token, `object`.getString("name"))
//                            .enqueue(object : Callback<Login> {
//                                override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
//                                    if (response!!.isSuccessful) {
//                                        activity.saveCookie(response!!.body()!!.get("access_token").toString())
//                                        Log.d("cookie ", response!!.body()!!.get("access_token").toString())
//                                    } else
//                                        activity.showToast("클라이언트 에러")
//                                }
//
//                                override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
//                                    activity.showToast("서버 연결 실패")
//                                    t!!.printStackTrace()
//                                }
//
//                            })
//                }
//                val parameters = Bundle()
//                parameters.putString("fields", "id,name,email,gender,birthday")
//                request.parameters = parameters
//                request.executeAsync()
//            }
//
//            override fun onError(error: FacebookException?) {
//                Log.d("facebookError", error.toString())
//            }
//
//            override fun onCancel() {
//
//            }
//        })
    }
}