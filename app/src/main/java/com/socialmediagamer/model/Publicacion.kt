package com.socialmediagamer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Publicacion(
    var id:String,
    val titulo: String?,
    val descripcion: String?,
    val imgUrl: String?,
    val categoria: String?
) : Parcelable {
    constructor():
            this("","","","","Nintendo")
}