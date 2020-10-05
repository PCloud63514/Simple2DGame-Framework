package com.pcloud63514.simple2dgame_framework.core.utils

import android.content.Context
import com.pcloud63514.simple2dgame_framework.data.Frame
import com.pcloud63514.simple2dgame_framework.data.anim.AnimationState
import com.pcloud63514.simple2dgame_framework.data.anim.State
import com.pcloud63514.simple2dgame_framework.core.ObservableObject
import com.pcloud63514.simple2dgame_framework.data.anim.AnimationCondition
import java.beans.PropertyChangeListener
import kotlin.properties.Delegates

class AnimationController(context: Context, frames:MutableMap<String, MutableList<Frame>>): ObservableObject(context) {
    var currentState: State by Delegates.observable(State("Empty"), observer)
    private val states:MutableMap<Any, AnimationState> = mutableMapOf<Any, AnimationState>()

    init {
        frames.iterator().forEach {
            states[it.key] = createState(it.key, it.value)
        }

        addPropertyChangeListener("currentState", PropertyChangeListener {
            for((key, transition) in (it.oldValue as AnimationState).transitions) {
                for((key, condition) in transition.conditions) {
                    removePropertyChangeListener(key, condition.propertyChangeListener)
                }
            }

            for((key, transition) in (it.newValue as AnimationState).transitions) {
                for((key, condition) in transition.conditions) {
                    addPropertyChangeListener(key, condition.propertyChangeListener)
                }
            }
        })
    }

    // 동일한 propName 을 갖은 Condition 은 존재할 수 없다. 덮어쓰기 형태
    fun addCondition(startStateFlag:Any, endStateFlag:Any, propName:String, condition:Function1<Any, Boolean>) {
        val startState = getState(startStateFlag)
        val endState = getState(endStateFlag)

        val transition = startState.getTransitionOrAdd(endState, { animationState:AnimationState -> setCurrentState(animationState) })
        transition.addCondition(propName, condition)
    }

    fun setCurrentState(stateFlag:Any) {
        currentState = getState(stateFlag)
    }

    fun getStateSize():Int {
        return states.size
    }

    private fun getState(stateFlag:Any):AnimationState {
        return states[stateFlag]!!
    }

    private fun createState(stateFlag:Any, frames:MutableList<Frame>): AnimationState {
        return AnimationState(stateFlag, frames)
    }
}
