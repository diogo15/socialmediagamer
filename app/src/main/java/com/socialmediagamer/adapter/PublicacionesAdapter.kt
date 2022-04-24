package com.socialmediagamer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
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
                itemBinding.imageButton2.setBackgroundColor("#A81414")
            }
            if(publicacion.categoria=="PC"){
                itemBinding.imageButton2.setImageResource(R.drawable.icon_nintendo)
                itemBinding.imageButton2.setBackgroundColor("#CCCCCC")
            }
            if(publicacion.categoria=="PS4"){
                itemBinding.imageButton2.setImageResource(R.drawable.ic_ps4)
                itemBinding.imageButton2.setBackgroundColor("#000080")
            }
            if(publicacion.categoria=="Xbox"){
                itemBinding.imageButton2.setImageResource(R.drawable.ic_nintendo)
                itemBinding.imageButton2.setBackgroundColor("#008000")
            }
            /*
            itemBinding.vistaFila.setOnClickListener{
                val action = PublicacionesViewHolder.actionPublicacionFragmentToUpdatePublicacionFragment(publicacion)
                itemView.findNavController().navigate(action)
            }
            */

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