package com.socialmediagamer.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.socialmediagamer.R
import com.socialmediagamer.databinding.PublicacionFilaBinding
import com.socialmediagamer.model.Publicacion

class PublicacionesAdapter: RecyclerView.Adapter<PublicacionesAdapter.PublicacionesViewHolder>() {

    private var listaPublicaciones = emptyList<Publicacion>()

    inner class PublicacionesViewHolder(private val itemBinding:PublicacionFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(publicacion: Publicacion){

            itemBinding.txtPostTitle.text = publicacion.titulo
            itemBinding.txtPostDesc.text = publicacion.descripcion

            if(publicacion.categoria=="Nintendo"){
                itemBinding.imageButton2.setImageResource(R.drawable.ic_nintendo)
                itemBinding.imageButton2.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#b50000"))
            }
            if(publicacion.categoria=="PC"){
                itemBinding.imageButton2.setImageResource(R.drawable.ic_pc)
                itemBinding.imageButton2.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3e3e3e"))
            }
            if(publicacion.categoria=="PS4"){
                itemBinding.imageButton2.setImageResource(R.drawable.ic_ps4)
                itemBinding.imageButton2.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1733ab"))
            }
            if(publicacion.categoria=="Xbox"){
                itemBinding.imageButton2.setImageResource(R.drawable.ic_xbox)
                itemBinding.imageButton2.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#31a22e"))
            }


            itemBinding.vistaFila.setOnClickListener{
                val action = PublicacionesViewHolder.actionPublicacionFragmentToUpdatePublicacionFragment(publicacion)
                itemView.findNavController().navigate(action)
            }


        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicacionesViewHolder {
        val itemBinding = PublicacionFilaBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return PublicacionesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PublicacionesViewHolder, position: Int) {
        val publicacion = listaPublicaciones[position]
        holder.bind(publicacion)
    }

    fun setData(publicacions : List<Publicacion>){
        this.listaPublicaciones = publicacions
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listaPublicaciones.size
    }
}