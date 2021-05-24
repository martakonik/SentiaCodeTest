package com.martakonik.sentiacodetest.data

import com.squareup.moshi.Json

data class Agent(
    @field:Json(name = "first_name")
    val firstName: String,

    @field:Json(name = "last_name")
    val lastName: String,

    @field:Json(name = "company_name")
    val companyName: String? = null,

    val avatar: Avatar,
    val dob: String? = null
)