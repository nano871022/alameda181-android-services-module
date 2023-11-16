package co.com.japl.services.implement

import android.content.Context
import androidx.annotation.RequiresApi
import androidx.core.text.isDigitsOnly
import co.com.japl.connect.gdrive.GDrive
import co.com.japl.interfaces.dtos.CarouselDTO
import co.com.japl.interfaces.services.ICarouselHome
import co.com.japl.model.BuildConfig
import java.util.Collections
import java.util.concurrent.atomic.AtomicInteger

class CarouselDriveImpl(val context:Context): ICarouselHome {
    @RequiresApi(34)
    override fun getCarouselHome(): List<CarouselDTO> {
        if(BuildConfig.CAROUSEL_HOME_DRIVE.toBoolean()) {
            val counter = AtomicInteger(1)
            val orderAtomic = AtomicInteger(1)
            return GDrive().connectToFolder(context).map {
                var order = getOrder(it.name)
                if (order == -1) {
                    order = orderAtomic.getAndIncrement()
                }
                CarouselDTO(
                    counter.getAndIncrement(),
                    it.name,
                    it.url,
                    counter.getAndIncrement(),
                    order,
                    true
                )
            }.sortedBy { it.order }
        }
        return Collections.emptyList()
    }

    private fun getOrder(name:String):Int{
        val last = name.substring(name.length-2)
        if(last.isDigitsOnly()){
            return last.toInt()
        }
        return -1
    }
}