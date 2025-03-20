package com.finalproject.pebble.data

import com.finalproject.pebble.R
import com.google.android.gms.maps.model.LatLng

/*

    val id: Int = 0,
    val tags: ArrayList<Tag> = ArrayList(),
    val name: String? = "",
    val desc: String? = "",
    val location: LatLng? = null,
    val imageName: String = "default.png",
 */

class SampleDataSource {
    private val sampleList = listOf(
        Sample(
            id = 1,
            tags = mutableListOf(Tag(name="jasper", color="red"), Tag(name="found", color="red"), Tag(name="untumbled", color="red"), Tag(name="red", color="red"), Tag(name="shiny", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock,
            desc = "this is a rock that i collected. i found it on the ground. it reminds me of another rock. this rock is hard. i like rocks."
        ),
        Sample(
            id = 2,
            tags = mutableListOf(Tag(name="agate", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock2
        ),
        Sample(
            id = 3,
            tags = mutableListOf(Tag(name="purchased", color="red"), Tag(name="glass", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock3
        ),
        Sample(
            id = 4,
            tags = mutableListOf(Tag(name="jade", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock4
        ),
        Sample(
            id = 5,
            tags = mutableListOf(Tag(name="agate", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock5
        ),
        Sample(
            id = 6,
            tags = mutableListOf(Tag(name="limestone", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock6
        ),
        Sample(
            id = 7,
            tags = mutableListOf(Tag(name="unknown", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock7
        ),
        Sample(
            id = 8,
            tags = mutableListOf(),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock
        ),
        Sample(
            id = 9,
            tags = mutableListOf(Tag(name="jasper", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock2
        ),
        Sample(
            id = 10,
            tags = mutableListOf(Tag(name="found", color="red"), Tag(name="chalcedony", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock3
        ),
        Sample(
            id = 11,
            tags = mutableListOf(Tag(name="quartz", color="red"), Tag(name="gift", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock5
        ),
        Sample(
            id = 12,
            tags = mutableListOf(Tag(name="brittle", color="red"), Tag(name="sedimentary", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock7
        ),
        Sample(
            id = 13,
            tags = mutableListOf(Tag(name="catch and release", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock6
        ),
        Sample(
            id = 14,
            tags = mutableListOf(Tag(name="jasper", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock4
        ),
        Sample(
            id = 15,
            tags = mutableListOf(Tag(name="jasper", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock2
        ),
        Sample(
            id = 16,
            tags = mutableListOf(Tag(name="jasper", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock
        ),
        Sample(
            id = 14,
            tags = mutableListOf(Tag(name="jasper", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock6
        ),
        Sample(
            id = 15,
            tags = mutableListOf(Tag(name="jasper", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock3
        ),
        Sample(
            id = 16,
            tags = mutableListOf(Tag(name="jasper", color="red")),
            location = LatLng(35.0000, -120.0000),
            imageId = R.drawable.rock4
        ),
    )
    private val tagList = mutableListOf("sedimentary", "metamorphic", "igneous", "found", "purchased", "fun shape", "gifted", "jade", "quartz", "agate", "jasper", "serpentinite", "chalcedony", "sandstone", "fossil", "unknown", "combo")

    fun getSample(id: Int): Sample? {
        return sampleList.find { it.id == id }
    }

    fun loadSamples() = sampleList

    fun loadTags() = tagList

}

