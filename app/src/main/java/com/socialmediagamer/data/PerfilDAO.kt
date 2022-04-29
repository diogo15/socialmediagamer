package com.socialmediagamer.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.socialmediagamer.model.Perfil
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PerfilDAO {

    private val coleccion1 ="gamerAPP"
    private val coleccion2 ="myPerfil"
    private var codigoUsuario:String
    private var firestore: FirebaseFirestore

    init{
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "$usuario"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }
    
    fun getUserPerfil() : MutableLiveData<List<Perfil>>{
        val listaPerfiles = MutableLiveData<List<Perfil>>()

        //llamar a google
        firestore
            .collection(coleccion1)
            .document(codigoUsuario)
            .collection(coleccion2)
            .addSnapshotListener { snapshot, ex ->
                if (ex != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null){
                    val lista = ArrayList<Perfil>()
                    val perfiles = snapshot.documents
                    perfiles.forEach{
                        val perfil = it.toObject(Perfil::class.java)
                        if (perfil != null){
                            lista.add(perfil)
                        }
                    }
                    listaPerfiles.value = lista
                }
            }

        return listaPerfiles
    }

    suspend fun savePerfil(perfil: Perfil){
        val document:DocumentReference

        //Id the ID is empty is a NEW, otherwise is an Update
        if(perfil.id.isEmpty()){
            document = firestore.collection(coleccion1).document(codigoUsuario).collection(coleccion2).document()
            perfil.id = document.id
        }else{
            document = firestore.collection(coleccion1).document(codigoUsuario).collection(coleccion2).document(perfil.id)
        }

        val set = document.set(perfil)
        set.addOnSuccessListener  { Log.d("Perfil Modificado","Perfil Agregado") }
        set.addOnCanceledListener { Log.d("Perfil Modificado","Perfil Cancelado") }

    }

    suspend fun deletePerfil(perfil: Perfil){
        if(perfil.id.isNotEmpty()){
            firestore
                .collection(coleccion1)
                .document(codigoUsuario)
                .collection(coleccion2)
                .document(perfil.id)
                .delete()
                .addOnSuccessListener { Log.d("Perfil Modificado","Perfil Eliminado") }
        }
    }

}