package com.finalproject.pebble.ui

import androidx.lifecycle.ViewModel
import com.finalproject.pebble.data.Sample
import com.finalproject.pebble.data.SampleDataSource

class DetailViewModel : ViewModel() {
    fun getSample(id: Int): Sample = SampleDataSource().getSample(id) ?: Sample()
}