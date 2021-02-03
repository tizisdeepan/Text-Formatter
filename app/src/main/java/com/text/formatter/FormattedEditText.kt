package com.text.formatter

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.TextPaint
import android.text.style.StyleSpan
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText

class FormattedEditText : AppCompatEditText {
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context) : super(context)

    var hasSelection = false
    var start = 0
    var end = 0

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        Log.e("SELECTION", "$selStart:$selEnd")
        if (selStart == 0 && selEnd == 0) {
        } else if (selEnd != selStart) {
            hasSelection = true
            start = selStart
            end = selEnd
        } else {
            hasSelection = false
            start = 0
            end = 0
        }
    }

    fun setStyleForSelection(isBold: Boolean, isItalic: Boolean) {
        Log.e("STYLE -> $selectionStart:$selectionEnd", "BOLD:$isBold, Italic:$isItalic")
        if (isBold && isItalic) text?.setSpan(StyleSpan(Typeface.BOLD_ITALIC), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        else {
            when {
                isBold -> text?.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                isItalic -> text?.setSpan(StyleSpan(Typeface.ITALIC), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                else -> text?.setSpan(StyleSpan(Typeface.NORMAL), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            }
        }
        invalidate()
    }
}