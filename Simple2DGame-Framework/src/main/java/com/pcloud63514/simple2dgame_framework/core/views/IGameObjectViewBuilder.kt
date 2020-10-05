package com.pcloud63514.simple2dgame_framework.core.views

import com.pcloud63514.simple2dgame_framework.core.gl10.GL10View

interface IGameObjectViewBuilder {
    fun build(startStateFlag:Any) : GL10View
}