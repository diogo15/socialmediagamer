package com.socialmediagamer.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.socialmediagamer.model.Publicacion
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PublicacionesDAO {

    private val coleccion1 ="gamerAPP"
    private val coleccion2 ="myPosts"
    private var codigoUsuario:String
    private var firestore: FirebaseFirestore

    init{
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "$usuario"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getPublicaciones() : MutableLiveData<List<Publicacion>>{
        val listaPublicaciones = MutableLiveData<List<Publicacion>>()

        //llamar a google
        firestore.collectionGroup(coleccion2)
            .addSnapshotListener { snapshot, ex ->
                if (ex != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null){
                    val lista = ArrayList<Publicacion>()
                    val publicaciones = snapshot.documents
                    publicaciones.forEach{

                        val publicacion = it.toObject(Publicacion::class.java)
                        if (publicacion != null){
                            lista.add(publicacion)
                        }

                    }
                    listaPublicaciones.value = lista
                }
            }

        return listaPublicaciones
    }

    fun getPublicacionesByUser() : MutableLiveData<List<Publicacion>>{
        val listaPublicaciones = MutableLiveData<List<Publicacion>>()

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
                    val lista = ArrayList<Publicacion>()
                    val publicaciones = snapshot.documents
                    publicaciones.forEach{
                        val publicacion = it.toObject(Publicacion::class.java)
                        if (publicacion != null){
                            lista.add(publicacion)
                        }
                    }
                    listaPublicaciones.value = lista
                }
            }

        return listaPublicaciones
    }


    fun getPublicacionesByCategory() : MutableLiveData<List<Publicacion>>{
        val listaPublicaciones = MutableLiveData<List<Publicacion>>()

        //llamar a google
        firestore.collectionGroup(coleccion2).whereEqualTo("category", "ps4")
            .addSnapshotListener { snapshot, ex ->
                if (ex != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null){
                    val lista = ArrayList<Publicacion>()
                    val publicaciones = snapshot.documents
                    publicaciones.forEach{

                        val publicacion = it.toObject(Publicacion::class.java)
                        if (publicacion != null){
                            lista.add(publicacion)
                        }

                    }
                    listaPublicaciones.value = lista
                }
            }

        return listaPublicaciones
    }

    suspend fun savePublicacion(publicacion: Publicacion){
        val document:DocumentReference

        //Id the ID is empty is a NEW, otherwise is an Update
        if(publicacion.id.isEmpty()){
            document = firestore.collection(coleccion1).document(codigoUsuario).collection(coleccion2).document()
            publicacion.id = document.id
        }else{
            document = firestore.collection(coleccion1).document(codigoUsuario).collection(coleccion2).document(publicacion.id)
        }

        val set = document.set(publicacion)
        set.addOnSuccessListener  { Log.d("PublicacionModificada","Publicacion Agregada") }
        set.addOnCanceledListener { Log.d("PublicacionModificada","Publicacion Cancelada") }

    }

    suspend fun deletePublicacion(publicacion: Publicacion){
        if(publicacion.id.isNotEmpty()){
            firestore
                .collection(coleccion1)
                .document(codigoUsuario)
                .collection(coleccion2)
                .document(publicacion.id)
                .delete()
                .addOnSuccessListener { Log.d("PublicacionModificada","Publicacion Eliminada") }
        }
    }

}