package com.pcloud63514.simple2dgame_framework.data

data class Location(val x:Float, val y:Float, val w:Float, val h:Float)

data class Frame(val fileName:String, val frame: Location, val rotated:Boolean, val trimmed: Boolean, val spriteSourceSize: Location, var sourceSize:Location)
/**Meta 의 sourceSize 는 원래 존재하지 않음. 가능한 Frame 정보만을 사용해 Update 해야함.**/
data class Meta(val app:String, val version:String, val image:String, val format:String, val sourceSize:Location, val size:Location, val scale:Int, val smartUpdate:String)

data class SpriteSheetConfig(val meta:Meta, val frames:MutableMap<String, MutableList<Frame>>)