package rbdd.highton_android.Activity

import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_signup.*
import rbdd.highton_android.Connect.Connector
import rbdd.highton_android.Connect.Responce
import rbdd.highton_android.R
import rbdd.highton_android.Util.BaseActivity

/**
 * Created by root1 on 2017. 11. 5..
 */
class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signupButton.setOnClickListener {
            if(isEmpty(idEdit) || isEmpty(passwordEdit) || isEmpty(nameEdit)){
                showToast("값을 다 입력하세요")
            }else{
                Connector.api.signup(getStr(idEdit), getStr(passwordEdit), "hello wolrd",getStr(nameEdit))
                        .enqueue(object : Responce<Void>{
                            override fun onCall(code: Int, body: Void?) {
                                when(code){
                                    201 -> {
                                        showToast("회원가입 성공했습니다.")
                                        finish()
                                    }
                                    204 -> {
                                        showToast("ID가 중복됩니다.")
                                        idEdit.setText("")
                                    }
                                }
                            }
                        })
            }
        }
    }

    private fun isEmpty(edit: EditText): Boolean{
        return edit.text.toString().isEmpty()
    }

    private fun getStr(edit: EditText): String{
        return edit.text.toString()
    }

}