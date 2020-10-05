package com.pcloud63514.simple2dgame_framework.data.anim

class AnimationTransition(val animationState:AnimationState, val func:(AnimationState) -> Unit) {
    val conditions:MutableMap<String, AnimationCondition> = mutableMapOf<String, AnimationCondition>()

    //Condition Change



    fun addCondition(propName:String, condition:Function1<Any, Boolean>) {
        conditions[propName] = AnimationCondition(propName, condition)
    }


    // TODO condition 조건들 검사 후에 전부 일치할 경우 CurrentState 를 보유한 State 로 변경한다.
    // TODO
    fun test() {
        func.invoke(animationState)
    }
}