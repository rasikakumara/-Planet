package com.rasika.planets.viewModel

import androidx.lifecycle.*
import com.rasika.planets.model.Planet
import com.rasika.planets.repository.PlanetRepository
import com.rasika.planets.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(private val planetRepository: PlanetRepository,savedStateHandle:SavedStateHandle):ViewModel() {
    private val myArguments =  savedStateHandle.get<Planet>("planetDetail")

    private val _details =MutableLiveData<Planet>()
    val detail:LiveData<Planet>
    get() = _details
    init {
        _details.value = myArguments!!
    }



}