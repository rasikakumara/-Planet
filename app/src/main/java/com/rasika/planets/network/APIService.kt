package com.rasika.planets.network

import com.rasika.planets.model.PlanetsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("planets/?page/")
    suspend fun getPlanetsList(@Query("page")page:Int): Response<PlanetsResponse>
}