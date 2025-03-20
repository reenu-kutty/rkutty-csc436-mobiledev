package com.finalproject.pebble.data

import com.google.android.gms.maps.model.LatLng

data class Tag(
    val name: String,
    val color: String = "#FF5733"
)

data class Sample(
    val id: Int = 0,
    val tags: MutableList<Tag> = mutableListOf(),
    val desc: String? = "",
    val location: LatLng? = null,
    val imageId: Int = 0,
)