package com.text.formatter

import android.text.Editable
import android.text.Spannable
import android.text.style.StrikethroughSpan
import android.util.Log
import com.text.formatter.spans.*

const val TAG = "SpanClass"

object Span {


    fun <T> Editable.addSpan(enStart: Int, enEnd: Int, type: Class<T>) {
        this.setSpan(getStyle(type), enStart, enEnd, Spannable.SPAN_USER_SHIFT)
    }


    fun <T> getStyle(type: Class<T>): Any? {
        return when (type.simpleName) {
            BoldSpan::class.java.simpleName -> BoldSpan()
            ItalicSpan::class.java.simpleName -> ItalicSpan()
            UnderlineSpan::class.java.simpleName -> UnderlineSpan()
            StrikethroughSpan::class.java.simpleName -> StrikethroughSpan()
            NumberSpan::class.java.simpleName -> NumberSpan()
            CustomBulletSpan::class.java.simpleName -> CustomBulletSpan()
            else -> {
                null
            }
        }
    }


    inline fun <reified T> Editable.remove(start: Int, end: Int, type: Class<T>) {
        if (getStyle(type) == null) return
        val spans = this.getSpans(start, end, getStyle(type)!!::class.java)
        spans?.forEach {
            Log.d(TAG, "remove: $it")
            this.removeSpan(it)
        }
    }
}