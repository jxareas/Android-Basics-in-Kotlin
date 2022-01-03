package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class MarsPhoto
    constructor(
        @Json(name = "id")
        val id : String,

        @Json(name = "img_src")
        val imageSource : String
    )

//data class MarsPhoto(
//    val id : String, val imageSource : String
//)