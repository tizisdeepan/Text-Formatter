package com.text.formatter

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText


class FormattedEditText : AppCompatEditText {
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context) : super(context)

    var start = 0
    var end = 0

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        Log.e("SELECTION", "$selStart:$selEnd")
        start = selStart
        end = selEnd
    }

    private val boldSpan = StyleSpan(Typeface.BOLD)
    private val italicSpan = StyleSpan(Typeface.ITALIC)
    private val underlineSpan = UnderlineSpan()
    private val strikeSpan = StrikethroughSpan()

    fun setStyleForSelection(isBold: Boolean, isItalic: Boolean, isUnderline: Boolean, isStrike: Boolean) {
        Log.e("STYLE -> $selectionStart:$selectionEnd", "BOLD:$isBold, Italic:$isItalic")

        if (start != end) {
            if (isBold) text?.setSpan(boldSpan, start, end, Spannable.SPAN_COMPOSING) else text?.removeSpan(boldSpan)
            if (isItalic) text?.setSpan(italicSpan, start, end, Spannable.SPAN_COMPOSING) else text?.removeSpan(italicSpan)
            if (isUnderline) text?.setSpan(underlineSpan, start, end, Spannable.SPAN_COMPOSING) else text?.removeSpan(underlineSpan)
            if (isStrike) text?.setSpan(strikeSpan, start, end, Spannable.SPAN_COMPOSING) else text?.removeSpan(strikeSpan)
        }

        invalidate()
    }
}