package rbdd.highton_android.Util

import rbdd.highton_android.R
import java.util.*


/**
 * Created by young on 2017-11-05/오전 10:04
 * This Project is HighThon-Trump
 */
object TrumpUtil {
    val trumps = listOf<Int>(R.drawable.trump_1, R.drawable.trump_3, R.drawable.trump_4, R.drawable.trump_5, R.drawable.trump_6, R.drawable.trump_7)
    fun getRandomTrump() : Int {
        return trumps[Random().nextInt(trumps.size)]
    }
}