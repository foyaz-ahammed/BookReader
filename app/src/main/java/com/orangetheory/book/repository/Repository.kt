package com.orangetheory.book.repository

import android.util.Log
import com.orangetheory.book.helpers.ITEM_COUNT_PER_PAGE
import com.orangetheory.book.repository.api.BookService
import com.orangetheory.book.repository.entities.BookItemInfo
import com.orangetheory.book.repository.entities.DataResult

class Repository(private val apiService: BookService) {
    companion object {
        const val TAG = "Repository"
    }

    suspend fun searchBooks(query: String, startIndex: Int, maxResults: Int = ITEM_COUNT_PER_PAGE): DataResult<List<BookItemInfo>> =
        try {
            val result = apiService.searchBooks(query, startIndex, maxResults)
            DataResult.Success(result.bookItems.map(::BookItemInfo))
        } catch (e: Exception) {
            Log.w(TAG, e.toString())
            DataResult.Failure(e)
        }
}