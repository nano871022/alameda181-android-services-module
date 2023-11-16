package co.com.japl.interfaces.services

import co.com.japl.interfaces.dtos.CarouselDTO

interface ICarouselHome {
    fun getCarouselHome():List<CarouselDTO>
}