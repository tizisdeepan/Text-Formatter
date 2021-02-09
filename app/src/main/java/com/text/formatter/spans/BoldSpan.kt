package com.text.formatter.spans

import android.graphics.Typeface
import android.text.style.StyleSpan

class BoldSpan : StyleSpan(Typeface.BOLD){
     val k = 2
     companion object{
          const val type =1
     }
}