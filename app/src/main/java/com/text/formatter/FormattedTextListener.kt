package com.text.formatter

interface FormattedTextListener {
    fun getCurrentSpans(isBold: Boolean, isItalic: Boolean, underlined: Boolean, striked: Boolean)
}