package co.com.japl.services.implement

import android.content.Context
import android.util.Log
import co.com.japl.interfaces.dtos.CarouselDTO
import co.com.japl.interfaces.dtos.CarouselResponse
import co.com.japl.interfaces.services.ICarouselHome
import co.com.japl.model.BuildConfig
import co.com.japl.model.R
import com.google.gson.Gson
import java.net.URL
import java.util.Collections
import java.util.concurrent.CompletableFuture

class CarouselImpl(val context:Context) : ICarouselHome{
    override fun getCarouselHome(): List<CarouselDTO> {
        if(BuildConfig.CAROUSEL_HOME_JSON.toBoolean()){
            try {
                val task = CompletableFuture.supplyAsync {
                    val url = URL(context.resources.getString(R.string.url_carousel))
                    val payload = url.readText()
                    Gson().fromJson(payload, CarouselResponse::class.java)
                }
                return (task?.get()?.carousel?.filter { value->value.active }?.sortedBy { value->value.order } ?: Collections.emptyList()).also {
                    Log.i(javaClass.name, "<<<=== Finish:GetCarouselHome Size: ${it.size}")
                }
            }catch(error:Exception){
                Log.e("CarouselImpl#GetCarouselHome","$error")
            }
        }
        return Collections.emptyList()
    }
}