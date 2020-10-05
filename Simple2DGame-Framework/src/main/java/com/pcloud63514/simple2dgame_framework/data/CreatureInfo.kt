package com.pcloud63514.simple2dgame_framework.data

import android.content.Context
import com.pcloud63514.simple2dgame_framework.utils.ObservableObject
import kotlin.properties.Delegates

class CreatureInfo(context: Context): ObservableObject(context) {
    var currentState: State by Delegates.observable(State(State.Enum.Empty), observer)
}