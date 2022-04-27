package com.socialmediagamer.repository

import com.socialmediagamer.data.PublicacionesDAO
import com.socialmediagamer.model.Publicacion
import androidx.lifecycle.MutableLiveData

class PublicacionesRepository(private val publicacionesDao: PublicacionesDAO) {
    val getAllData: MutableLiveData<List<Publicacion>> = publicacionesDao.getPublicaciones()

    val getMyData: MutableLiveData<List<Publicacion>> = publicacionesDao.getPublicacionesByUser()

    suspend fun addPublicacion(publicacion: Publicacion) {
        publicacionesDao.savePublicacion(publicacion)
    }

    suspend fun updatePublicacion(publicacion: Publicacion) {
        publicacionesDao.savePublicacion(publicacion)
    }

    suspend fun deletePublicacion(publicacion: Publicacion) {
        publicacionesDao.savePublicacion(publicacion)
    }
}