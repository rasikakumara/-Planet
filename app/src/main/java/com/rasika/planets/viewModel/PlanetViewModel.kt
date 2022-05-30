package com.rasika.planets.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rasika.planets.model.Planet
import com.rasika.planets.repository.PlanetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PlanetViewModel @Inject constructor(private val planetRepository: PlanetRepository):ViewModel() {

    fun getPlanets(searchString:String):Flow<PagingData<Planet>>{
        return planetRepository.getPlanets(searchString).cachedIn(viewModelScope)
    }

}