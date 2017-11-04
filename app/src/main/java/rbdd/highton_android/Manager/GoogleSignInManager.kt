package rbdd.highton_android.Manager

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.Api
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import rbdd.highton_android.Util.BaseActivity

/**
 * Created by root1 on 2017. 11. 4..
 */
class GoogleSignInManager(activity: BaseActivity) {
    init {
        val gson = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        val googleApiClient = GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, object:GoogleApiClient.OnConnectionFailedListener{
                    override fun onConnectionFailed(p0: ConnectionResult) {
                        activity.showToast("연결에 실패하였습니다.")
                        //TODO 다국어 지원하기
                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API)
    }
}