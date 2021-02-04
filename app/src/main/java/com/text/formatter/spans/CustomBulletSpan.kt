package com.text.formatter.spans

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Layout
import android.text.Spanned
import android.text.style.LeadingMarginSpan


class CustomBulletSpan : LeadingMarginSpan {

    override fun getLeadingMargin(first: Boolean): Int = LEADING_MARGIN

    override fun drawLeadingMargin(c: Canvas, p: Paint, x: Int, dir: Int, top: Int, baseline: Int, bottom: Int, text: CharSequence, start: Int, end: Int, first: Boolean, l: Layout?) {
        if ((text as Spanned).getSpanStart(this) == start) {
            val style: Paint.Style = p.style
            p.color = Color.parseColor("#ffffff")
            p.style = Paint.Style.FILL
            c.drawText("\u2022", (x + dir + LEADING_MARGIN).toFloat(), baseline.toFloat(), p)
            p.style = style
        }
    }

    companion object {
        const val LEADING_MARGIN = 30
        // Gap should be about 1em
        const val STANDARD_GAP_WIDTH = 30
    }
}