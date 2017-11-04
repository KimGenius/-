package rbdd.highton_android.Connect

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by root1 on 2017. 11. 5..
 */
interface Responce<T>: Callback<T> {

    override fun onResponse(call: Call<T>?, response: Response<T>) {
        val code = response.code()
        val body = response.body()
        onCall(code, body)
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        t?.printStackTrace()
    }

    fun onCall(code: Int, body: T?)
}