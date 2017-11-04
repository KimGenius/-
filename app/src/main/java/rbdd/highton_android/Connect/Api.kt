package rbdd.highton_android.Connect

import com.google.gson.JsonObject
import rbdd.highton_android.Model.CommnetModel
import rbdd.highton_android.Model.ContentBannerModel
import rbdd.highton_android.Model.ContentNewsModel
import rbdd.highton_android.Model.Login
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by root1 on 2017. 11. 4..
 */
interface Api {

    @POST("/auth/basic")
    @FormUrlEncoded
    fun authBasic(@Field("id")id: String, @Field("pw")pw: String): Call<Login>

    @POST("/auth/facebook")
    @FormUrlEncoded
    fun authFacebook(@Field("token")token: String, @Field("name")name: String): Call<JsonObject>

    @POST("/auth/google")
    @FormUrlEncoded
    fun authGoogle(@Field("token")token: String, @Field("name")name: String, @Field("email")email: String): Call<JsonObject>

    @POST("/signup")
    @FormUrlEncoded
    fun signup(@Field("id")id: String, @Field("pw")pw: String, @Field("email")email: String, @Field("name")name: String): Call<Void>

    @GET("/news/main")
    fun getMainNews(): Call<JsonObject>

    @GET("/news/list")
    fun getListNews(@Query("page")page: Int): Call<Array<ContentBannerModel>>

    @GET("/news")
    fun getNews(@Query("id")id: String): Call<ContentNewsModel>

    @GET("/news/comment")
    fun getComment(@Header("Authorization")token: String, @Query("id")id: String): Call<Array<CommnetModel>>

    @POST("/news/comment")
    @FormUrlEncoded
    fun addComment(@Header("Authorization")token: String, @Field("id")id: String, @Field("content")content: String): Call<Void>

    @POST("/news/comment/like")
    @FormUrlEncoded
    fun addLike(@Header("Authorization")token: String, @Field("id")id: String): Call<Void>

    @DELETE("/news/comment/like")
    @FormUrlEncoded
    fun removeLike(@Header("Authorization")token: String, @Field("id")id: String): Call<Void>

    @DELETE("/news/like")
    @FormUrlEncoded
    fun removeNewsLike(@Header("Authorization")token: String, @Field("id")id: String) : Call<Void>

    @POST("/news/like")
    @FormUrlEncoded
    fun addNewsLike(@Header("Authorization")token: String, @Field("id")id: String) : Call<Void>
}