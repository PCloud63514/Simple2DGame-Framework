package com.pcloud63514.simple2dgame_framework.core.views

import android.content.Context
import android.graphics.Bitmap
import com.pcloud63514.simple2dgame_framework.CreatureInfo
import com.pcloud63514.simple2dgame_framework.data.Meta
import com.pcloud63514.simple2dgame_framework.core.gl10.GL10View
import com.pcloud63514.simple2dgame_framework.core.utils.AnimationController
import com.pcloud63514.simple2dgame_framework.data.anim.AnimationCondition

class CreatureView constructor(
    val creatureInfo:CreatureInfo,
    animationController:AnimationController,
    context:Context,
    startStateFlag:Any,
    bitmap:Bitmap,
    meta:Meta
):TPView (
    animationController, context, startStateFlag, bitmap, meta
) {
    class CreatureViewBuilder(
        private val creatureInfo:CreatureInfo,
        private val animationController:AnimationController,
        private val context:Context,
        private val bitmap:Bitmap,
        private val meta:Meta):IGameObjectViewBuilder {

        fun addCondition(startStateFlag:Any, endStateFlag:Any, propName:String, condition:Function1<Any, Boolean>): CreatureViewBuilder {
            animationController.addCondition(startStateFlag, endStateFlag, propName, condition)
            return this
        }

        override fun build(startStateFlag: Any): GL10View {
            return CreatureView(
                creatureInfo = creatureInfo,
                animationController = animationController,
                context = context,
                startStateFlag = startStateFlag,
                bitmap = bitmap,
                meta = meta
            )
        }
    }


}
// TODO CreatureInfo 내의 변수가 변경되면 AnimationController 의 CurrentState 가 변경될 수 있어야함.
// TODO AnimationController 의 CurrentState 가 변경되면 GL10View 의 Render UI 가 변경되어야함.




// TODO Builder 에서 아무 것도 안하고 build 하면 TPView 를 반환
// TODO builder 에서 info 를 추가하면 CreatureView 반환



// 그러네 audio 는 현재 State 가 저장된 TPView 와 밀접한 관련이 있다.
// TPView 에서 audio 가 추가되도록 해야한다. 이건 반환 타입이 바뀌면 안될일.
