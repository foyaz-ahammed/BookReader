package com.orangetheory.book.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orangetheory.book.R
import com.orangetheory.book.databinding.ActivityMainBinding
import com.orangetheory.book.helpers.showToast

/**
 * Activity for main screen
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val query = binding.editQuery.text.toString()
            if(query.isEmpty()) {
                showToast(getString(R.string.keyword_empty))
                return@setOnClickListener
            }

            //Show search result screen
            val intent = Intent(this, SearchResultActivity::class.java).apply {
                putExtra(SearchResultActivity.EXTRA_QUERY, query)
            }
            startActivity(intent)
        }
    }
}