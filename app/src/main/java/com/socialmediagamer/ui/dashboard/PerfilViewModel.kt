package com.socialmediagamer.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialmediagamer.data.PerfilDAO
import com.socialmediagamer.model.Perfil
import com.socialmediagamer.repository.PerfilRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PerfilViewModel : ViewModel() {

    val getAllData: LiveData<List<Perfil>>

    private val repository: PerfilRepository = PerfilRepository(PerfilDAO())

    init{
        getAllData = repository.getAllData
    }

    fun addPerfil(perfil: Perfil){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPerfil(perfil)
        }
    }

    fun updatePerfil(perfil: Perfil){
        viewModelScope.launch(Dispatchers.IO){
            repository.updatePerfil(perfil)
        }
    }

    fun deletePerfil(perfil: Perfil){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletePerfil(perfil)
        }
    }
}