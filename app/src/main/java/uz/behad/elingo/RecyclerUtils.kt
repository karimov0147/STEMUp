package uz.behad.elingo

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import uz.behad.elingo.data.models.LessonModel

object RecyclerUtils {


    var userProgress = 0
    var matrix = arrayListOf(
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        4, 3, 2, 1, 2, 3, 4, 3, 2, 1,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        4, 3, 2, 1, 2, 3, 4, 3, 2, 1,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        4, 3, 2, 1, 2, 3, 4, 3, 2, 1,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        4, 3, 2, 1, 2, 3, 4, 3, 2, 1,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        4, 3, 2, 1, 2, 3, 4, 3, 2, 1,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        4, 3, 2, 1, 2, 3, 4, 3, 2, 1,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        4, 3, 2, 1, 2, 3, 4, 3, 2, 1,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        4, 3, 2, 1, 2, 3, 4, 3, 2, 1,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        4, 3, 2, 1, 2, 3, 4, 3, 2, 1,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        4, 3, 2, 1, 2, 3, 4, 3, 2, 1,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        4, 3, 2, 1, 2, 3, 4, 3, 2, 1,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
        5,
        4, 3, 2, 1, 2, 3, 4, 3, 2, 1,
        5,
        1, 2, 3, 4, 3, 2, 1, 2, 3, 4,
    )

    var lessonId = 0

    var data = ArrayList<LessonModel>()

    fun View.gone() = run { visibility = View.GONE }

    fun View.visible() = run { visibility = View.VISIBLE }

    fun View.invisible() = run { visibility = View.INVISIBLE }

    fun String.toColoredSpannable(query: String): Spannable {
        val span = SpannableString(this)
        if (query.isEmpty())
            return span

        var start = this.indexOf(query, ignoreCase = true)
        var end = start + query.length


        span.setSpan(
            ForegroundColorSpan(Color.parseColor("#12D18E")),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return span
    }


}