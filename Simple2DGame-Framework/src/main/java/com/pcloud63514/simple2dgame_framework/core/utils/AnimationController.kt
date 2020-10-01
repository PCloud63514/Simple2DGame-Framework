package com.pcloud63514.simple2dgame_framework.core.utils

import android.content.Context
import com.pcloud63514.simple2dgame_framework.data.Frame
import com.pcloud63514.simple2dgame_framework.data.anim.AnimationState
import com.pcloud63514.simple2dgame_framework.data.anim.State
import com.pcloud63514.simple2dgame_framework.core.ObservableObject
import kotlin.properties.Delegates

class AnimationController(context: Context, frames:MutableMap<String, MutableList<Frame>>): ObservableObject(context) {
    var currentState: State by Delegates.observable(State("Empty"), observer)
    private val states = mutableListOf<AnimationState>()

    init {
        frames.iterator().forEach {
            states.add(createState(it.key, it.value))
        }
    }

    fun getState(index:Int):AnimationState {
        return states[index]
    }

    fun getStateSize():Int {
        return states.size
    }

    fun getState(stateFlag:Any):List<AnimationState> {
        return states.filter{
            it.stateFlag == stateFlag
        }
    }

    private fun createState(stateFlag:Any, frames:MutableList<Frame>): AnimationState {
        return AnimationState(stateFlag, frames)
    }
}
