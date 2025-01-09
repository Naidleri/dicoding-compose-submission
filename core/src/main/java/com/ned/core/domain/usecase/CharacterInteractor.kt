package com.ned.core.domain.usecase

import androidx.paging.PagingData
import com.ned.core.domain.model.Character
import com.ned.core.domain.repository.ICharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterInteractor (private val characterRepository: ICharacterRepository):CharacterUseCase {
    override fun getCharacters(): Flow<PagingData<Character>> {
        return characterRepository.getCharacters()
    }

    override suspend fun getCharacterById(id: Int): Character {
        return characterRepository.getCharacterById(id)
    }

    override fun searchCharacterByName(name: String): Flow<List<Character>> {
        return characterRepository.searchCharacterByName(name)
    }

    override suspend fun insertFavoriteCharacters(characters: Character) {
        return characterRepository.insertFavoriteCharacters(characters)
    }

    override suspend fun getFavoriteCharacters(): List<Character> {
        return characterRepository.getFavoriteCharacters()
    }

    override suspend fun isFavoriteCharacter(id: Int): Boolean {
        return characterRepository.isFavoriteCharacter(id)
    }

    override suspend fun deleteFavoriteCharacter(id: Int) {
        return characterRepository.deleteFavoriteCharacter(id)
    }
}