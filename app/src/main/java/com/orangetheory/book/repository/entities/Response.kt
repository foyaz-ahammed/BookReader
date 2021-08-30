package com.orangetheory.book.repository.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class Response {
    @JsonClass(generateAdapter = true)
    class   BookResultData(
        @Json(name = "items") val bookItems: List<BookItem>
    )

    @JsonClass(generateAdapter = true)
    class BookItem(
        @Json(name = "volumeInfo") val volumeInfo: VolumeInfo
    )

    @JsonClass(generateAdapter = true)
    class VolumeInfo(
        @Json(name = "imageLinks") val imageLinks: ImageLinks?,
        @Json(name = "title") val title: String,
        @Json(name = "publisher") val publisher: String,
        @Json(name = "publishedDate") val publishedDate: Int,
    )

    @JsonClass(generateAdapter = true)
    class ImageLinks(
        @Json(name = "smallThumbnail") val smallThumbnail: String
    )


}