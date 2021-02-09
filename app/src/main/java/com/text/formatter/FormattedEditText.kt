package com.text.formatter

import android.content.Context
import android.text.*
import android.text.style.BulletSpan
import android.text.style.StrikethroughSpan
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import com.text.formatter.Span.addSpan
import com.text.formatter.Span.remove
import com.text.formatter.spans.*


class FormattedEditText : AppCompatEditText {
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )


    var isBoldSpan = false
    var isItalicSpan = false
    var isUnderline = false
    var isStrike = false
    var isBullet = false
    var isNumber = false

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context) : super(context)

    var start = 0
    var end = 0
    var listener: FormattedTextListener? = null

    var enStart = 0
    var enEnd = 0

    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                enStart = start
                enEnd = start + count
                if (enStart != enEnd) {
                    appSpan(enStart, enEnd)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun appSpan(start: Int, end: Int) {
        if (isBoldSpan)
            text?.addSpan(start, end, BoldSpan::class.java)
        if (isItalicSpan)
            text?.addSpan(start, end, ItalicSpan::class.java)
        if (isUnderline)
            text?.addSpan(start, end, UnderlineSpan::class.java)
        if (isStrike)
            text?.addSpan(start, end, StrikethroughSpan::class.java)
        if (isBullet)
            text?.addSpan(start, end, CustomBulletSpan::class.java)
        if (isNumber)
            text?.addSpan(start, end, NumberSpan::class.java)
    }

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
            Log.e("SPAN", "$it")
        }
        listener?.getCurrentSpans(spans?.any { it is BoldSpan } ?: false,
            spans?.any { it is ItalicSpan } ?: false,
            spans?.any { it is UnderlineSpan } ?: false,
            spans?.any { it is StrikethroughSpan } ?: false,
            spans?.any { it is BulletSpan } ?: false,
            spans?.any { it is NumberSpan } ?: false)
    }

    fun setStyleForSelection(
        isBold: Boolean,
        isItalic: Boolean,
        isUnderline: Boolean,
        isStrike: Boolean,
        isBullet: Boolean,
        isNumber: Boolean
    ) {
        Log.e("STYLE -> $selectionStart:$selectionEnd", "BOLD:$isBold, Italic:$isItalic")

        if (start != end) {
            if (isBold)
                text?.addSpan(start, end, BoldSpan::class.java)
            else
                text?.remove(start, end, BoldSpan::class.java)
            if (isItalic)
                text?.addSpan(start, end, ItalicSpan::class.java)
            else
                text?.remove(start, end, ItalicSpan::class.java)
            if (isUnderline)
                text?.addSpan(start, end, UnderlineSpan::class.java)
            else
                text?.remove(start, end, UnderlineSpan::class.java)
            if (isStrike)
                text?.addSpan(start, end, StrikethroughSpan::class.java)
            else
                text?.remove(start, end, StrikethroughSpan::class.java)
            if (isBullet)
                text?.addSpan(start, end, CustomBulletSpan::class.java)
            else
                text?.remove(start, end, CustomBulletSpan::class.java)
            if (isNumber)
                text?.addSpan(start, end, NumberSpan::class.java)
            else
                text?.remove(start, end, NumberSpan::class.java)

            invalidate()
        }
    }


}