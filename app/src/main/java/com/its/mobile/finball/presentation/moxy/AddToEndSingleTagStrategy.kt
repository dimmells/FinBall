package com.its.mobile.finball.presentation.moxy

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.ViewCommand
import com.arellomobile.mvp.viewstate.strategy.StateStrategy

class AddToEndSingleTagStrategy : StateStrategy {

    override fun <View : MvpView?> beforeApply(currentState: MutableList<ViewCommand<View>>?, incomingCommand: ViewCommand<View>?) {

        if(currentState != null && incomingCommand != null) {
            val iterator = currentState.iterator()
            val incomingTag = incomingCommand.tag
            while (iterator.hasNext()) {
                val entryTag = iterator.next().tag
                if (!entryTag.isEmpty() && !incomingTag.isEmpty() && entryTag == incomingTag) {
                    iterator.remove()
                }
            }
            currentState.add(incomingCommand)
        }

    }

    override fun <View : MvpView?> afterApply(currentState: MutableList<ViewCommand<View>>?, incomingCommand: ViewCommand<View>?) {
        //not used
    }
}