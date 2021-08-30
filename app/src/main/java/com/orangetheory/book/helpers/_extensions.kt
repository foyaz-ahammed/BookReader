package com.orangetheory.book.helpers

import android.content.Context
import android.widget.Toast

fun Context.showToast(msg: String) {
    val toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
    toast.show()
}