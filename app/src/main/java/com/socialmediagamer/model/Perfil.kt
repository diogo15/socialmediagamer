package com.socialmediagamer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Perfil(
    var id:String,
    val nombre: String?,
    val telefono: String?,
    val instagram: String?,
    val twitter: String?,
    val rutaImagen: String?
) : Parcelable {
    constructor():
            this("","","","","","")
}