package com.example.data.model.mapper

import com.example.data.model.dto.ShopDto
import com.example.data.model.entity.ShopEntity
import com.example.domain.model.Shop

internal object ShopMapper {

    fun dtoToEntity(dto: ShopDto): ShopEntity = ShopEntity(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        picture = dto.picture,
        rating = dto.rating,
        address = dto.address,
        lat = dto.coordinates[0],
        lon = dto.coordinates[1],
        mapsUrl = dto.mapsUrl,
        website = dto.website
    )

    fun entityToDomain(entity: ShopEntity): Shop = Shop(
        id = entity.id,
        name = entity.name,
        description = entity.description,
        picture = entity.picture,
        rating = entity.rating,
        address = entity.address,
        latitude = entity.lat,
        longitude = entity.lon,
        mapsUrl = entity.mapsUrl,
        website = entity.website
    )
}