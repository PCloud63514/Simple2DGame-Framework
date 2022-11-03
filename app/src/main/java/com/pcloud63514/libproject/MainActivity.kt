package com.pcloud63514.libproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pcloud63514.simple2dgame_framework.core.GameObjectFactory
import pcloud.game.starter.ui.gles.app.TextureView
import pcloud.game.starter.ui.gles.app.TextureViewFactory
import java.util.*

class MainActivity : AppCompatActivity() {

//    private val gameObjectFactory:GameObjectFactory = GameObjectFactory(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textureView: TextureView = TextureViewFactory.create("Fairy", this)
        textureView.setMethod("idle")
//        val builder = gameObjectFactory.createCreatureViewBuilder("Hyena")

        setContentView(textureView)
        textureView.start()
    }
}