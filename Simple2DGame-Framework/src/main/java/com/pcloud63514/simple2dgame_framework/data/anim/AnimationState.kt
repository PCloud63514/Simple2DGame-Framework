package com.pcloud63514.simple2dgame_framework.data.anim

import com.pcloud63514.simple2dgame_framework.data.Frame

class AnimationState(stateFlag:Any, val frames:MutableList<Frame>) :State(stateFlag) {
    val transitions:MutableMap<AnimationState, AnimationTransition> = mutableMapOf<AnimationState, AnimationTransition>()

    fun getTransitionOrAdd(animationState:AnimationState, func: (AnimationState) -> Unit):AnimationTransition {
        if(transitions[animationState] == null) {
            transitions[animationState] = AnimationTransition(animationState, func)
        }
        return transitions[animationState]!!

    }
}