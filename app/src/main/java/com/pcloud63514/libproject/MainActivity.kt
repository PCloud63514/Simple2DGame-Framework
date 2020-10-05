package com.pcloud63514.libproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pcloud63514.simple2dgame_framework.CreatureInfo
import com.pcloud63514.simple2dgame_framework.core.GameObjectFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private val gameObjectFactory:GameObjectFactory = GameObjectFactory(this)
    private var size:Int = 0
    private var random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = gameObjectFactory.createGameObjectViewBuilder("Hyena")

        val creatureInfo = CreatureInfo(this)
        builder!!.mountCreatureInfo(creatureInfo)
            .addCondition("Idle", "Move", "speed", condition={speed:Any->(speed as Float) >= 0.1f})
            .addCondition("Move", "Idle", "speed", condition={speed:Any->(speed as Float) <= 0.0f})
            .addCondition("Idle", "Jump", "isGround", condition={isGround:Any-> (isGround as Boolean) == true })
            .addCondition("Move", "Jump", "isGround", condition={isGround:Any-> (isGround as Boolean) == true })
            .addCondition("Jump", "Idle", "isGround", condition={isGround:Any-> (isGround as Boolean) == false })
            .build("Idle")


        setContentView(builder!!.build("Attack"))

        //val builder = gameObjectFactory.createCreatureViewBuilder("Hyena")
        //builder?.let {
        //    val creatureView = it.build("Attack")
        //    size = creatureView.animationController.getStateSize()
        //    setContentView(creatureView)
//
        //    timer(period=5000, initialDelay = 5000) {
        //        creatureView.animationController.currentState = creatureView.animationController.getState(random.nextInt(size))
        //    }
        //}
    }
}

class Data:Observable() {
    var text = ""
        set(value) {
            field = value
            this.notifyObservers()
        }
    var text2 = ""
        set(value) {
            field = value
            this.notifyObservers()
        }
}

class Ob(observable:Observable):Observer {
    init {
        observable.addObserver(this)
    }

    override fun update(o: Observable?, arg: Any?) {
        println("update")
    }

}

fun main() {
    val data = Data()
    val ob = Ob(data)

    data.text = "hi1"
    data.text2 = "hi2"

    data.notifyObservers()
}


