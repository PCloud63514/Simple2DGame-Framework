package com.pcloud63514.libproject


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pcloud63514.simple2dgame_framework.core.GameObjectFactory
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private val gameObjectFactory:GameObjectFactory = GameObjectFactory(this)
    private var size:Int = 0
    private var random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = gameObjectFactory.createCreatureViewBuilder("Hyena")
        builder?.let {
            val creatureView = it.build()
            creatureView.animationController.currentState = creatureView.animationController.getState("Attack")[0]
            size = creatureView.animationController.getStateSize()
            setContentView(creatureView)

            timer(period=5000, initialDelay = 5000) {
                creatureView.animationController.currentState = creatureView.animationController.getState(random.nextInt(size))
            }
        }
    }
}