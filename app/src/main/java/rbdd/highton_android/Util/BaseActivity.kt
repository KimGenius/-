package rbdd.highton_android.Util

import android.content.Intent
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
}