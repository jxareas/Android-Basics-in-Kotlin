package com.example.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApi
import com.example.amphibians.network.AmphibianApiService
import kotlinx.coroutines.launch

enum class AmphibianApiStatus {LOADING, ERROR, DONE}

class AmphibianViewModel : ViewModel() {

    // TODO: Create properties to represent MutableLiveData and LiveData for the API status
    private val _status  = MutableLiveData<AmphibianApiStatus>()
    val status : LiveData<AmphibianApiStatus> get() = _status

    // TODO: Create properties to represent MutableLiveData and LiveData for a list of amphibian objects
    private val _amphibians : MutableLiveData<List<Amphibian>> = MutableLiveData<List<Amphibian>>()
    val amphibians : LiveData<List<Amphibian>> get() = _amphibians


    // TODO: Create properties to represent MutableLiveData and LiveData for a single amphibian object.
    //  This will be used to display the details of an amphibian when a list item is clicked
    private val _amphibian : MutableLiveData<Amphibian> = MutableLiveData<Amphibian>()
    val amphibian : LiveData<Amphibian> get() = _amphibian

    // TODO: Create a function that gets a list of amphibians from the api service and sets the
    //  status via a Coroutine

    fun getAmphibianList() = viewModelScope.launch {
        _status.value = AmphibianApiStatus.LOADING
       try  {
           _amphibians.value = AmphibianApi.retrofitService.getAmphibians()
           _status.value = AmphibianApiStatus.DONE
       } catch (e : Exception) {
           _status.value = AmphibianApiStatus.ERROR
           _amphibians.value = listOf()
           e.printStackTrace()
       }
    }

    fun onAmphibianClicked(amphibian: Amphibian) : Unit {
        _amphibian.value = amphibian
    }
}

