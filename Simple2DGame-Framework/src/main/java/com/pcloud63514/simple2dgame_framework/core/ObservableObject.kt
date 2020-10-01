package com.pcloud63514.simple2dgame_framework.core

import android.content.Context
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

open class ObservableObject(context: Context) {
    private val changeSupport = PropertyChangeSupport(context)
    protected val observer = {
            prop: KProperty<*>, oldValue: Any, newValue: Any ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }

    fun addPropertyChangeListener(propName:String, listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(propName, listener)
    }

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(propName:String, listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(propName, listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}