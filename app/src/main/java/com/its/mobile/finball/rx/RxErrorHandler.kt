package com.its.mobile.finball.rx

import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.functions.Consumer

class RxErrorHandler : Consumer<Throwable> {
    override fun accept(t: Throwable?) {
        if (t != null) when (t) {
            is UndeliverableException -> {
            }
            is OnErrorNotImplementedException -> {
            }
            else -> throw t
        }


    }
}