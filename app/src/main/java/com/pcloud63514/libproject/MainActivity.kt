package com.pcloud63514.libproject

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.pcloud63514.simple2dgame_framework.core.GameObjectFactory
import pcloud.game.starter.ui.gles.app.TextureView
import pcloud.game.starter.ui.gles.app.TextureViewFactory
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val gameObjectFactory:GameObjectFactory = GameObjectFactory(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        layout.setBackgroundColor(Color.BLACK)
        layout.orientation = LinearLayout.VERTICAL

        val textureView: TextureView = TextureViewFactory.create("Fairy", this)
        textureView.setMethod("vertigo")
        textureView.start()


        val size = textureView.getMethodNames().size

        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                val nextId = Random.nextInt(size)
                textureView.setMethod(textureView.getMethodNames()[nextId])
            }
        }

//        val builder = gameObjectFactory.createCreatureViewBuilder("Fairy")
        setContentView(textureView)
//        setContentView(builder!!.build("walking"))

        val timer = Timer("MyTimer");//create a new Timer

//        timer.scheduleAtFixedRate(timerTask, 10, 3000)
    }
}