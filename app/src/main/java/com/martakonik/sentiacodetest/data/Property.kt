package com.martakonik.sentiacodetest.data

import com.squareup.moshi.Json

data class GetPropertyResponse(
    val data: List<Property>
)

data class Property(
    val id: String,

    @field:Json(name = "auction_date")
    val auctionDate: String,

    @field:Json(name = "available_from")
    val availableFrom: String,

    val bedrooms: Long,
    val bathrooms: Long,
    val carspaces: Long,

    @field:Json(name = "date_first_listed")
    val dateFirstListed: String,

    @field:Json(name = "date_updated")
    val dateUpdated: String,

    val description: String,

    @field:Json(name = "display_price")
    val displayPrice: String,

    val currency: String,
    val location: Location,
    val owner: Agent,

    @field:Json(name = "property_images")
    val propertyImages: List<PropertyImage>,

    val agent: Agent,

    @field:Json(name = "property_type")
    val propertyType: String,

    @field:Json(name = "sale_type")
    val saleType: String
)
