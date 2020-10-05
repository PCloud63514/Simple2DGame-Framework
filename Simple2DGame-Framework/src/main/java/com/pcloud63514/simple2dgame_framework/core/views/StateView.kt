package com.pcloud63514.simple2dgame_framework.core.views

import android.content.Context
import com.pcloud63514.simple2dgame_framework.data.CreatureInfo
import java.beans.PropertyChangeListener

// None UI Interface
class StateView {
    private val creatureInfo: CreatureInfo
    constructor(context:Context) {
        this.creatureInfo = CreatureInfo(context)
    }

    constructor(context:Context, creatureInfo: CreatureInfo) {
        this.creatureInfo = creatureInfo
    }

    fun addPropertyChangeListener(propName:String, listener:PropertyChangeListener) {
        this.creatureInfo.addPropertyChangeListener(propName, listener)
    }

    fun removePropertyChangeListener(propName:String, listener:PropertyChangeListener) {
        this.creatureInfo.removePropertyChangeListener(propName, listener)
    }
}