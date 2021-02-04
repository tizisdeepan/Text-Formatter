package com.text.formatter.spans

import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.Spanned
import android.text.style.LeadingMarginSpan

class NumberSpan : LeadingMarginSpan {
    var number: Int

    constructor() {
        number = -1
    }

    constructor(number: Int) {
        this.number = number
    }

    override fun getLeadingMargin(first: Boolean): Int {
        return LEADING_MARGIN + 50
    }

    override fun drawLeadingMargin(c: Canvas, p: Paint, x: Int, dir: Int, top: Int, baseline: Int, bottom: Int, text: CharSequence, start: Int, end: Int, first: Boolean, l: Layout?) {
        if ((text as Spanned).getSpanStart(this) == start) {
            val style = p.style
            p.style = Paint.Style.FILL

            // Util.log("mNumber == " + mNumber);
            if (number != -1) {
                c.drawText("$number.", (x + dir + LEADING_MARGIN).toFloat(), baseline.toFloat(), p)
            } else {
                c.drawText("\u2022", (x + dir).toFloat(), baseline.toFloat(), p)
            }
            p.style = style
        }
    }

    companion object {
        const val LEADING_MARGIN = 30

        //  private static final int BULLET_RADIUS = 3;
        //  private static final int NUMBER_RADIUS = 10;
        //Gap should be about 1em
        const val STANDARD_GAP_WIDTH = 30
    }
}