package com.its.mobile.finball.ui.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat

fun Context.getFontCompat(resId: Int) = ResourcesCompat.getFont(this, resId)

fun Context.getDrawableCompat(resId: Int) = ContextCompat.getDrawable(this, resId)

fun Context.getColorCompat(resId: Int) = ContextCompat.getColor(this, resId)

fun Context.getDimen(resId: Int) = resources.getDimension(resId)

fun Context.getDP(dp: Int) = resources.displayMetrics.density * dp
