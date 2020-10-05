package com.pcloud63514.simple2dgame_framework.data.anim

import java.beans.PropertyChangeListener

data class AnimationCondition(val propName:String, val condition:Function1<Any, Boolean>) {
    val propertyChangeListener = PropertyChangeListener {event -> isActive = condition.invoke(event.newValue) }
    var isActive:Boolean = false
        private set
}