package com.its.mobile.finball.ui.utils

import android.support.v4.content.res.ResourcesCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.widget.TextView


fun TextView.setTextWithIcon(text: CharSequence, charToReplace: Char, iconResId: Int) =
        setTextWithIcon(text, charToReplace, iconResId, lineHeight, lineHeight)


fun TextView.setTextWithIcon(text: CharSequence, charToReplace: Char, iconResId: Int, size: Int) =
        setTextWithIcon(text, charToReplace, iconResId, size, size)


fun TextView.setTextWithIcon(text: CharSequence, charToReplace: Char, iconResId: Int, height: Int, width: Int) {
    val string = SpannableString(text)
    val index = string.indexOf(charToReplace)

    ResourcesCompat.getDrawable(resources, iconResId, null)
            ?.let {
                it.setBounds(0, 0, height, width)
                string.setSpan(ImageSpan(it), index, index + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

    this.text = string
}


fun TextView.setTextWithColor(textResId: Int, charToReplace: String, color: Int) =
        setTextWithColor(resources.getString(textResId), charToReplace, color)


fun TextView.setTextWithColor(text: String, strToReplace: String, color: Int) {
    val string = SpannableString(text.replace(strToReplace, ""))

    text.split(' ')
            .asSequence()
            .filter { it.contains(strToReplace) }
            .map { it.replace(strToReplace, "") }
            .map {
                val start = string.indexOf(it)
                val end = start + it.length
                string.setSpan(ForegroundColorSpan(color), start, end, 0)
            }
            .toList()

    this.text = string
}


fun String.colorString(strToReplace: String, color: Int): SpannableString {
    val string = SpannableString(this.replace(strToReplace, ""))

    this.split(' ')
            .asSequence()
            .filter { it.contains(strToReplace) }
            .map { it.replace(strToReplace, "") }
            .map {
                val start = string.indexOf(it)
                val end = start + it.length
                string.setSpan(ForegroundColorSpan(color), start, end, 0)
            }
            .toList()

    return string

}

