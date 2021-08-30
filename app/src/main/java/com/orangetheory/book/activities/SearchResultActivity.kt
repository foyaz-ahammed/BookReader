package com.orangetheory.book.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.orangetheory.book.viewmodels.BookViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultActivity: AppCompatActivity() {
    companion object {
        const val EXTRA_QUERY = "extra_query"
    }

    private val viewModel by viewModel<BookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.bookItemsLiveData.observe(this) {
            Log.w("SearchResultActivity", it.toString())
        }

        val query = intent.getStringExtra(EXTRA_QUERY)?:""
        viewModel.fetchData(query)
    }
}