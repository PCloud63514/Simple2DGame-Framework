package com.pcloud63514.libproject

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.pcloud63514.simple2dgame_framework.core.GameObjectFactory
import pcloud.game.starter.ui.gles.app.TextureView
import pcloud.game.starter.ui.gles.app.TextureViewFactory

class MainActivity : AppCompatActivity() {

    private val gameObjectFactory:GameObjectFactory = GameObjectFactory(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        layout.setBackgroundColor(Color.BLACK)
        layout.orientation = LinearLayout.VERTICAL


        val textureView: TextureView = TextureViewFactory.create("Fairy", this)
        textureView.setMethod("idle")
        textureView.start()

        val builder = gameObjectFactory.createCreatureViewBuilder("Fairy")

        setContentView(textureView)
//        setContentView(builder!!.build("idle"))
    }
}