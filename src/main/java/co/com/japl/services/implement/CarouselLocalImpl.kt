package co.com.japl.services.implement

import android.content.Context
import co.japl.android.homeconnect.model.models.CarouselDTO
import co.com.japl.interfaces.services.ICarouselHome
import co.com.japl.model.BuildConfig
import co.com.japl.model.R
import java.util.Collections

class CarouselLocalImpl (val context:Context): ICarouselHome {
    override fun getCarouselHome(): List<co.japl.android.homeconnect.model.models.CarouselDTO> {
        if(BuildConfig.CAROUSEL_HOME_LOCAL.toBoolean()){

            val resources = context.resources
            return R.drawable::class.java.fields.filter { it.name.startsWith("carousel") }
                .map{
                    co.japl.android.homeconnect.model.models.CarouselDTO(
                        drawable = resources.getIdentifier(
                            it.name,
                            "drawable",
                            context.packageName
                        ),
                        name = it.name,
                        description = "Local Image Saved",
                        id = it.name.replace("carousel", "").toInt(),
                        url = "",
                        order = it.name.replace("carousel", "").toInt(),
                        active = true
                    )
                }.sortedBy { value->value.order }
        }
        return Collections.emptyList()
    }
}