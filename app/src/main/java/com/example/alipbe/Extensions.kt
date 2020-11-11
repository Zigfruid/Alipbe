package com.example.alipbe

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun Int.dpToSp(context: Context): Int =
    (dpToPx(this.toFloat(), context) / context.resources.displayMetrics.scaledDensity).toInt()

fun Int.dpToPx(dp: Float, context: Context): Int =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    )
        .toInt()
