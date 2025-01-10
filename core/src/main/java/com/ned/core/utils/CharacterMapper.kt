package com.ned.core.utils

import com.ned.core.data.local.entity.CharactersEntity
import com.ned.core.data.remote.response.DataItem
import com.ned.core.domain.model.Character

object CharacterMapper {
    fun DataItem.toDomain(): Character {
        return Character (
            id = id,
            name = name,
            image = imageUrl,
            films = films,
            tvShows = tvShows,
            shortFilms = shortFilms,
            videoGames = videoGames,
            createdAt = createdAt,
            updatedAt = updatedAt,
            url = url,
            sourceUrl = sourceUrl,
            parkAttractions = parkAttractions,
            allies = allies,
            enemies = enemies,
            __v = v
        )
    }

    fun CharactersEntity.toDomain(): Character {
        return Character(
            id = id,
            name = name,
            image = image,
            films = films,
            tvShows = tvShows,
            shortFilms = shortFilms,
            videoGames = videoGames,
            createdAt = createdAt,
            updatedAt = updatedAt,
            url = url,
            sourceUrl = sourceUrl,
            parkAttractions = parkAttraction,
            allies = allies,
            enemies = enemies,
            __v = v,
            favorite = favorite
        )
    }

    fun Character.toEntity(): CharactersEntity {
        return CharactersEntity(
            id = id,
            name = name,
            image = image,
            films = films,
            tvShows = tvShows,
            shortFilms = shortFilms,
            videoGames = videoGames,
            createdAt = createdAt,
            updatedAt = updatedAt,
            url = url,
            sourceUrl = sourceUrl,
            parkAttraction = parkAttractions,
            allies = allies,
            enemies = enemies,
            v = __v,
            favorite = true
        )
    }
}