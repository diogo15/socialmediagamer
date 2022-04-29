package com.socialmediagamer.repository

import com.socialmediagamer.data.PerfilDAO
import com.socialmediagamer.model.Perfil
import androidx.lifecycle.MutableLiveData

class PerfilRepository(private val perfilDao: PerfilDAO) {
    val getAllData: MutableLiveData<List<Perfil>> = perfilDao.getUserPerfil()

    suspend fun addPerfil(publicacion: Perfil) {
        perfilDao.savePerfil(publicacion)
    }

    suspend fun updatePerfil(publicacion: Perfil) {
        perfilDao.savePerfil(publicacion)
    }

    suspend fun deletePerfil(publicacion: Perfil) {
        perfilDao.savePerfil(publicacion)
    }
}