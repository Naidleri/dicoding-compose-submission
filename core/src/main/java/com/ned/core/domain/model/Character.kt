package com.ned.core.domain.model

data class Character (
    var id: Int,
    var name: String,
    var image: String?,
    var films: List<String>?,
    var tvShows: List<String>?,
    var shortFilms: List<String>?,
    var videoGames: List<String>?,
    var createdAt: String?,
    var updatedAt: String?,
    var url: String?,
    var sourceUrl: String?,
    var parkAttractions: List<String>?,
    var allies: List<Any>?,
    var enemies: List<Any>,
    var __v: Int?,
    var favorite: Boolean = false
)