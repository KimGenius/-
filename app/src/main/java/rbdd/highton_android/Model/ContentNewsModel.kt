package rbdd.highton_android.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by root1 on 2017. 11. 5..
 */
data class ContentNewsModel(@SerializedName("content")val content: String,
                            @SerializedName("title")val title: String,
                            @SerializedName("link")val link: String,
                            @SerializedName("liked")val liked: Boolean,
                            @SerializedName("pub_date")val date: String)