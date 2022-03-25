package com.socialmediagamer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialmediagamer.data.PublicacionesDAO
import com.socialmediagamer.model.Publicacion
import com.socialmediagamer.repository.PublicacionesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PublicacionViewModel : ViewModel() {

    val getAllData: LiveData<List<Publicacion>>
    private val repository: PublicacionesRepository = PublicacionesRepository(PublicacionesDAO())

    init{
        getAllData = repository.getAllData
    }

    fun addPublicacion(publicacion: Publicacion){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPublicacion(publicacion)
        }
    }

    fun updatePublicacion(publicacion: Publicacion){
        viewModelScope.launch(Dispatchers.IO){
            repository.updatePublicacion(publicacion)
        }
    }

    fun deletePublicacion(publicacion: Publicacion){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletePublicacion(publicacion)
        }
    }
}