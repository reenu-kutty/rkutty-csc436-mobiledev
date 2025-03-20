package com.finalproject.pebble.ui

import androidx.lifecycle.ViewModel
import com.finalproject.pebble.data.SampleDataSource

class ListViewModel : ViewModel() {
    val sampleList = SampleDataSource().loadSamples()
}