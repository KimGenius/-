package rbdd.highton_android.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by root1 on 2017. 11. 5..
 */
data class ContentBannerModel(@SerializedName("description")val description: String,
                              @SerializedName("title")val title: String,
                              @SerializedName("like_count")val like: Int,
                              @SerializedName("unlick_count")val unLike: Int,
                              @SerializedName("id")val id: String)