# Android 2D Game Engine

Android 내에서 다른 게임 엔진 및 라이브러리를 사용하지 않은 게임 엔진

TexturePacker 를 사용해 생긴 Image Atras 와 정보를 전달해 손쉽게 게임 캐릭터를 호출할 수 있습니다.



[필수 사항]

- TexturePacker
- OpenGLES 1.0



[조건]

- 디렉토리 구조 상 2d 이미지와 json은 asset 의 한 폴더 내에 함께 존재해야한다.  ex) assets/Demon/data.png   ...data.json
- TexturePacker 를 이용해 이미지를 생성할 때 grid 구조를 사용해야한다.
- ~~json file 의 meta 정보에 spriteSourceSIze 를 포함해야한다.~~



자동화 기능

- TexturePacker 를 사용한 결과물인 json 정보에 캐릭터 행동 태그를 기반으로 State 구분.
- 현재 State 에 따라 자동으로 캐릭터의 View Update
- State 변경 조건을 설정 시 자동으로 State 변경 (개발 중)



[SampleCode]

```kotlin
package com.pcloud.libraryproject
import kotlin.concurrent.timer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pcloud.a2dgameengine.core.GameObjectFactory
import java.util.*

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
```

