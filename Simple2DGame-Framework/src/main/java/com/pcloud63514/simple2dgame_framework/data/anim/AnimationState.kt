package com.pcloud63514.simple2dgame_framework.data.anim

import com.pcloud63514.simple2dgame_framework.data.Frame

class AnimationState(
    stateFlag:Any,
    val frames:MutableList<Frame>) :State(stateFlag) {
    var mFocus:Int = 0
}