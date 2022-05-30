package com.rasika.planets.model


class PlanetsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Planet>
)