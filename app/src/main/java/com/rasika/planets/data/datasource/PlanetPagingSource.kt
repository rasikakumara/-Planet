package com.rasika.planets.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rasika.planets.model.Planet
import com.rasika.planets.network.APIService
import com.rasika.planets.utils.Constants
import java.lang.Exception

class PlanetPagingSource(private val apiService: APIService,private val searchingString:String):PagingSource<Int,Planet>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Planet> {
        val position = params.key  ?: Constants.FIRST_PAGE_INDEX
        return try {
            val response = apiService.getPlanetsList(position)
            val planets = response.body()?.results
            val filteredData = planets?.filter { it.name.contains(searchingString,true) }
            val nextKey = position +1
            val prevKey = if (position ==1)null else position - 1

            LoadResult.Page(data = filteredData!!,prevKey =prevKey,nextKey = nextKey)
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Planet>): Int? {
        return state.anchorPosition
    }
}