package com.orangetheory.book.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orangetheory.book.repository.Repository
import com.orangetheory.book.repository.entities.BookItemInfo
import com.orangetheory.book.repository.entities.DataResult
import kotlinx.coroutines.launch

class BookViewModel(private val repository: Repository): ViewModel() {
    private val _bookItemsLiveData = MutableLiveData<List<BookItemInfo>>()
    var loadCount = 0
    private var _query = ""

    val bookItemsLiveData: LiveData<List<BookItemInfo>>
        get() = _bookItemsLiveData

    fun fetchData(q: String) {
        _query = q
        viewModelScope.launch {
            when(val result = repository.searchBooks(_query, 0)) {
                is DataResult.Success -> {
                    _bookItemsLiveData.value = result.data
                }
                is DataResult.Failure -> {

                }
            }
        }
    }
}