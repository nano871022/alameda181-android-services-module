package co.com.japl.interfaces.dtos

import android.graphics.drawable.ShapeDrawable

data class CarouselDTO(
    val id:Int,
    val name:String,
    val url:String,
    val order:Int,
    val drawable: Int,
    val active: Boolean
)
