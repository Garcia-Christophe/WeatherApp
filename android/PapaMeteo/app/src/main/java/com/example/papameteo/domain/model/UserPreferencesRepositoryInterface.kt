package com.example.papameteo.domain.model

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepositoryInterface {
    val favsCities : Flow<List<FavCity>>

    suspend fun addOrRemoveFavCity(favCity : FavCity)

    suspend fun isInFavs(favCity : FavCity): Flow<Boolean>
}