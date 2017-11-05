package rbdd.highton_android.Connect

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by root1 on 2017. 11. 4..
 */
object Connector {

    lateinit var api: Api
    val baseUrl = "http://52.79.134.200:3002/"


    init {
        val httpLogger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(httpLogger).readTimeout(30, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

        api = retrofit.create(Api::class.java)
    }

}