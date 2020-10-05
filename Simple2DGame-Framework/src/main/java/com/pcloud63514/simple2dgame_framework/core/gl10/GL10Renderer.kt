package com.pcloud63514.simple2dgame_framework.core.gl10

import android.graphics.Bitmap
import android.opengl.GLSurfaceView
import com.pcloud63514.simple2dgame_framework.data.AnimationState
import com.pcloud63514.simple2dgame_framework.data.Frame
import com.pcloud63514.simple2dgame_framework.data.Meta
import com.pcloud63514.simple2dgame_framework.utils.FPSManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.beans.PropertyChangeListener
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GL10Renderer(private val bitmap: Bitmap, private val meta: Meta): GLSurfaceView.Renderer {
    private val fpsManager: FPSManager = FPSManager()
    private var gl10Shape:GL10Shape? = null
    private var frames:MutableList<Frame>? = null
    private var currentIndex:Int = 0

    private var oldX: Float = 0f
    private var oldY: Float = 0f

    val stateChangeListener: PropertyChangeListener = PropertyChangeListener {
        CoroutineScope(Main).launch {
            withContext(Main) {
                frames = (it.newValue as AnimationState).frames
                currentIndex = 0
                fpsManager.initialize()
            }
        }
    }

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        gl10Shape?.destroy()
        gl10Shape = GL10Shape(meta)
        gl10Shape!!.initialize(gl, bitmap)
        gl.glClearColor(0f, 0f, 0f, 0f)
        fpsManager.initialize()
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        gl.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10) {
        gl.apply{
            glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
            glMatrixMode(GL10.GL_TEXTURE)
        }

        frames?.let {
            val frame = it.get(currentIndex)
            val x = frame.frame.x
            val y = frame.frame.y
            val translateX = (x - oldX).toInt() / meta.size.w
            val translateY = (y - oldY).toInt() / meta.size.h

            gl10Shape?.draw(gl)
            gl.apply {
                if(fpsManager.isElapsed()) {
                    glTranslatef(translateX, translateY, 0f)
                    oldX = x
                    oldY = y
                    currentIndex = if(currentIndex < it.size.minus(1)) currentIndex.plus(1) else 0
                }
            }
        }
    }
}