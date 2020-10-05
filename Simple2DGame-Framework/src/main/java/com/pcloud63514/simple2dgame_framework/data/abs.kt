package com.pcloud63514.simple2dgame_framework.data

open class State(protected val stateFlag:Any) {
    enum class Enum {
        Empty,
        Destroy
    }
}