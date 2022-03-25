package com.socialmediagamer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.socialmediagamer.R
import com.socialmediagamer.databinding.FragmentNuevaPublicacionBinding
import com.socialmediagamer.model.Publicacion
import com.socialmediagamer.ui.home.PublicacionViewModel

class nueva_publicacion : Fragment() {

    private lateinit var publicacionViewModel: PublicacionViewModel
    private var _binding: FragmentNuevaPublicacionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        publicacionViewModel = ViewModelProvider(this)[PublicacionViewModel::class.java]
        _binding = FragmentNuevaPublicacionBinding.inflate(inflater, container, false)

        binding.btnNewPublicacion.setOnClickListener{
            insertarPublicacion()
        }

        return binding.root;
    }

    fun insertarPublicacion(){
        val titulo = binding.txtEditTitle.text.toString()
        val desc = binding.txtEditDesc.text.toString()
        val url = binding.txtEditImg.text.toString()

        val publicacion = Publicacion("", titulo, desc, url)
        publicacionViewModel.addPublicacion(publicacion)

        Toast.makeText(requireContext(),"Guardado!", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_nueva_publicacion_to_navigation_home)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}