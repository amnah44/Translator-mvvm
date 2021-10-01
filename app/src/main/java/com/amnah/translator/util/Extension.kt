package com.amnah.translator.util

import android.widget.Spinner

fun <T> Spinner.getSelectedIndex(item: T?): Int{
    for (index in 0 until adapter.count){
        if (adapter.getItem(index) == item){
            return index
        }
    }
    return -1
}