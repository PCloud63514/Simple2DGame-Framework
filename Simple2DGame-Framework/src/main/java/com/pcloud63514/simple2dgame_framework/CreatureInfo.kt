package com.pcloud63514.simple2dgame_framework

import android.content.Context
import com.pcloud63514.simple2dgame_framework.core.ObservableObject
import kotlin.properties.Delegates

class CreatureInfo(context: Context): ObservableObject(context) {
    var speed:Int  by Delegates.observable(0, observer)
    var hp:Int by Delegates.observable(100, observer)
    var isGround:Boolean by Delegates.observable(true, observer)

}