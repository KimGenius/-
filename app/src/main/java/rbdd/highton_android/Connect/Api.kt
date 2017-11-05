package rbdd.highton_android.Connect

import com.google.gson.JsonObject
import rbdd.highton_android.Model.*
import retrofit2.Call
import retrofit2.http.*
import java.util.*

/**
 * Created by root1 on 2017. 11. 4..
 */
interface Api {

    @POST("/auth/basic")
    @FormUrlEncoded
    fun authBasic(@Field("id") id: String, @Field("pw") pw: String): Call<Login>

    @POST("/auth/facebook")
    @FormUrlEncoded

    fun authFacebook(@Field("token") token: String, @Field("name") name: String): Call<Login>

    @POST("/auth/google")
    @FormUrlEncoded
    fun authGoogle(@Field("token") token: String, @Field("name") name: String, @Field("email") email: String): Call<Login>

    @POST("/signup")
    @FormUrlEncoded
    fun signup(@Field("id") id: String, @Field("pw") pw: String, @Field("email") email: String, @Field("name") name: String): Call<Void>

    @GET("/news/main")
    fun getMainNews(@Query("language") lang: String = Locale.getDefault().language): Call<JsonObject>

    @GET("/news/list")
    fun getListNews(@Query("page") page: Int, @Query("language") lang: String = Locale.getDefault().language): Call<Array<ContentBannerModel>>

    @GET("/news")
    fun getNews(@Query("id") id: String, @Query("language") lang: String = Locale.getDefault().language): Call<ContentNewsModel>

    @GET("/news/comment")
    fun getComment(@Header("Authorization") token: String, @Query("id") id: String): Call<Array<CommnetModel>>

    @POST("/news/comment")
    @FormUrlEncoded
    fun addComment(@Header("Authorization") token: String, @Field("id") id: String, @Field("content") content: String): Call<Void>

    @POST("/news/like")
    @FormUrlEncoded
    fun addLike(@Header("Authorization") token: String, @Field("id") id: String): Call<Void>

    @DELETE("/news/like")
    fun removeLike(@Header("Authorization") token: String, @Query("id") id: String): Call<Void>

    @DELETE("/news/like")
    @FormUrlEncoded
    fun removeNewsLike(@Header("Authorization") token: String, @Field("id") id: String): Call<Void>

    @POST("/news/like")
    @FormUrlEncoded
    fun addNewsLike(@Header("Authorization") token: String, @Field("id") id: String): Call<Void>

    @GET("/activity-log")
    fun getActivityLog(@Header("Authorization") token: String): Call<activityLog>

    @GET("/notification")
    fun getNotification(@Header("Authorization") token: String): Call<List<notification>>
}