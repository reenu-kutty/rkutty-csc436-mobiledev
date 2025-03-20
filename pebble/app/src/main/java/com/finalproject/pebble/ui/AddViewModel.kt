package com.finalproject.pebble.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.finalproject.pebble.data.SampleDataSource
import kotlinx.coroutines.launch

class AddViewModel : ViewModel() {
    val description = mutableStateOf("")
    val tags = SampleDataSource().loadTags()

    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)
    val successMessage = mutableStateOf<String?>(null)

    fun saveSample() {
        isLoading.value = true
        errorMessage.value = null
        successMessage.value = null

        try {
            // Simulate a successful save after 2 seconds
            kotlinx.coroutines.GlobalScope.launch {
                kotlinx.coroutines.delay(2000)
                successMessage.value = "Sample saved successfully!"
                isLoading.value = false
            }
        } catch (e: Exception) {
            isLoading.value = false
            errorMessage.value = "Failed to save sample: ${e.message}"
        }
    }
}