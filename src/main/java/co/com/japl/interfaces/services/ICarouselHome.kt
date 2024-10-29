package co.com.japl.interfaces.services

import co.japl.android.homeconnect.model.models.CarouselDTO

interface ICarouselHome {
    fun getCarouselHome():List<co.japl.android.homeconnect.model.models.CarouselDTO>
}