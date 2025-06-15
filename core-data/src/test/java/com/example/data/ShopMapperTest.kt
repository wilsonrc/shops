package com.example.data

import com.example.data.model.dto.ShopDto
import com.example.data.model.mapper.ShopMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class ShopMapperTest {

    @Test
    fun dtoToEntity_mapsAllFields() {
        val dto = ShopDto(
            id = "42",
            name = "Test Shop",
            description = "Sake and snacks",
            picture = "https://example.com/logo.png",
            rating = 4.7,
            address = "123 Sample‑dori, Nagano",
            coordinates = listOf(36.1234, 138.5678),
            mapsUrl = "https://maps.example.com/?q=42",
            website = "https://example.com"
        )

        val entity = ShopMapper.dtoToEntity(dto)

        assertEquals(dto.id, entity.id)
        assertEquals(dto.name, entity.name)
        assertEquals(dto.description, entity.description)
        assertEquals(dto.picture, entity.picture)
        assertEquals(dto.rating, entity.rating, 0.0)
        assertEquals(dto.address, entity.address)
        assertEquals(dto.coordinates[0], entity.lat, 0.0)
        assertEquals(dto.coordinates[1], entity.lon, 0.0)
        assertEquals(dto.mapsUrl, entity.mapsUrl)
        assertEquals(dto.website, entity.website)
    }
}