package rbdd.highton_android.Util

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by root1 on 2017. 11. 4..
 */
public open class BaseActivity: AppCompatActivity() {

    public val GOOGLESIGNINCODE = 900
    public val FACEBOOKSIGNCODE = 64206

    public fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    public fun goNextActivity(clazz: Class<*>, finish: Boolean){
        val intent = Intent(this, clazz)
        startActivity(intent)

        if (finish){
            finish()
        }
    }

    public fun getPref(): SharedPreferences{
        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        return pref
    }

    public fun saveCookie(cookie: String){
        val editor = getPref().edit()
        editor.remove("cookie")
        editor.putString("cookie", cookie)
        editor.commit()
    }

    public fun getCookie(): String{
        return getPref().getString("cookie", "")
    }
}