package com.rasika.planets.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rasika.planets.data.datasource.PlanetPagingSource
import com.rasika.planets.model.Planet
import com.rasika.planets.network.APIService
import com.rasika.planets.network.SafeApiCall
import com.rasika.planets.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlanetRepository @Inject  constructor(private val apiService: APIService):SafeApiCall(){
    fun getPlanets(searchingString: String):Flow<PagingData<Planet>>{
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                    PlanetPagingSource(apiService,searchingString)
            }
        ).flow
    }



}