package rbdd.highton_android.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by root1 on 2017. 11. 5..
 */
data class CommnetModel(@SerializedName("content")val content: String,
                        @SerializedName("writer")val writer: String,
                        @SerializedName("like_count")val like: Int,
                        @SerializedName("liked")val liked: Boolean,
                        @SerializedName("id")val id: String)