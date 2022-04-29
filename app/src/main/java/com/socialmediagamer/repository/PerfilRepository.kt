package com.socialmediagamer.repository

import com.socialmediagamer.data.PerfilDAO
import com.socialmediagamer.model.Perfil
import androidx.lifecycle.MutableLiveData

class PerfilRepository(private val perfilDao: PerfilDAO) {
    val getAllData: MutableLiveData<List<Perfil>> = perfilDao.getUserPerfil()

    suspend fun addPerfil(perfil: Perfil) {
        perfilDao.savePerfil(perfil)
    }

    suspend fun updatePerfil(perfil: Perfil) {
        perfilDao.savePerfil(perfil)
    }

    suspend fun deletePerfil(perfil: Perfil) {
        perfilDao.savePerfil(perfil)
    }
}