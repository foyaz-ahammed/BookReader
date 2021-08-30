package com.orangetheory.book.repository.entities

class BookItemInfo(val smallThumbnail: String, val title: String, val publisher: String, val publishDate: Int) {
    constructor(item: Response.BookItem): this(item.volumeInfo.imageLinks?.smallThumbnail?:"", item.volumeInfo.title, item.volumeInfo.publisher, item.volumeInfo.publishedDate)
}