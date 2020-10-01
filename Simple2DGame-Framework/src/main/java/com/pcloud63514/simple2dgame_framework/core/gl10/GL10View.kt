package com.pcloud63514.simple2dgame_framework.core.gl10

import android.content.Context
import android.graphics.Bitmap
import android.opengl.GLSurfaceView
import com.pcloud63514.simple2dgame_framework.data.Meta

open class GL10View(context: Context, bitmap: Bitmap, meta: Meta): GLSurfaceView(context) {
    protected val gl10Renderer:GL10Renderer = GL10Renderer(bitmap, meta)

    open fun initialize() {
        setRenderer(gl10Renderer)
        renderMode = RENDERMODE_CONTINUOUSLY
        isFocusable = true
    }
}