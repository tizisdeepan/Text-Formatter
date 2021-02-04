package com.text.formatter

import android.content.Context
import android.text.Spannable
import android.text.style.BulletSpan
import android.text.style.StrikethroughSpan
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import com.text.formatter.spans.*


class FormattedEditText : AppCompatEditText {
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context) : super(context)

    var start = 0
    var end = 0
    var listener: FormattedTextListener? = null

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        Log.e("SELECTION", "$selStart:$selEnd")
        start = selStart
        end = selEnd
        if (start > 0) {
            getCurrentSpans()
        }
    }

    private fun getCurrentSpans() {
        val spans = text?.getSpans(start - 1, end, Any::class.java)
        spans?.forEach {
            Log.e("SPAN", it::class.java.canonicalName)
        }
        listener?.getCurrentSpans(spans?.any { it is BoldSpan } ?: false, spans?.any { it is ItalicSpan } ?: false, spans?.any { it is UnderlineSpan } ?: false, spans?.any { it is StrikethroughSpan } ?: false, spans?.any { it is BulletSpan } ?: false, spans?.any { it is NumberSpan } ?: false)
    }

    private val boldSpan = BoldSpan()
    private val italicSpan = ItalicSpan()
    private val underlineSpan = UnderlineSpan()
    private val strikeSpan = StrikethroughSpan()
    private val bulletSpan = CustomBulletSpan()
    private val numberSpan = NumberSpan()

    fun setStyleForSelection(isBold: Boolean, isItalic: Boolean, isUnderline: Boolean, isStrike: Boolean, isBullet: Boolean, isNumber: Boolean) {
        Log.e("STYLE -> $selectionStart:$selectionEnd", "BOLD:$isBold, Italic:$isItalic")

        if (start != end) {
            if (isBold) text?.setSpan(boldSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE) else removeSpan(boldSpan)
            if (isItalic) text?.setSpan(italicSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE) else removeSpan(italicSpan)
            if (isUnderline) text?.setSpan(underlineSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE) else removeSpan(underlineSpan)
            if (isStrike) text?.setSpan(strikeSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE) else removeSpan(strikeSpan)
            if (isBullet) text?.setSpan(bulletSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE) else removeSpan(bulletSpan)
            if (isNumber) text?.setSpan(numberSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE) else removeSpan(numberSpan)
            invalidate()
        }
    }

    private fun removeSpan(span: Any) {
        val spans = text?.getSpans(start, end, span::class.java)
        spans?.forEach {
            text?.removeSpan(it)
        }
    }
}