package com.its.mobile.finball.ui.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


fun EditText.addTextChangedListener(
        beforeChanged: (s: CharSequence, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> },
        onChanged: (s: CharSequence, start: Int, before: Int, count: Int) -> Unit = { _, _, _, _ -> },
        afterChanged: (s: Editable?) -> Unit = { _ -> }
) {

    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) =
                beforeChanged.invoke(s, start, count, after)


        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) =
                onChanged.invoke(s, start, before, count)

        override fun afterTextChanged(s: Editable?) =
                afterChanged.invoke(s)

    })

}