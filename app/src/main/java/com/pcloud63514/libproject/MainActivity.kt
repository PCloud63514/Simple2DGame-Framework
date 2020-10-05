package com.pcloud63514.libproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pcloud63514.simple2dgame_framework.core.GameObjectFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private val gameObjectFactory:GameObjectFactory = GameObjectFactory(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = gameObjectFactory.createCreatureViewBuilder("Hyena")

        setContentView(builder!!.build("Move"))
    }
}