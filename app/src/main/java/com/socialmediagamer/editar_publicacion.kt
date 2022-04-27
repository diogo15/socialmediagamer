package com.socialmediagamer

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.socialmediagamer.R
import com.socialmediagamer.databinding.FragmentNuevaPublicacionBinding
import com.socialmediagamer.databinding.FragmentEditarPublicacionBinding
import com.socialmediagamer.model.Publicacion
import com.socialmediagamer.ui.home.PublicacionViewModel

class editar_publicacion : Fragment() {

    private lateinit var publicacionViewModel: PublicacionViewModel
    private var _binding: FragmentNuevaPublicacionBinding? = null
    private val binding get() = _binding!!
    private var categoria = ""
    private val args by navArgs<editar_publicacionArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        publicacionViewModel = ViewModelProvider(this)[PublicacionViewModel::class.java]
        _binding = FragmentNuevaPublicacionBinding.inflate(inflater, container, false)

        binding.txtEditTitle.setText(args.publicacion.titulo)
        binding.txtEditDesc.setText(args.publicacion.descripcion)
        categoria = args.publicacion.categoria.toString()

        inicial(binding, categoria)

        binding.btnNewPublicacion.setOnClickListener{
            actualizarPublicacion()
        }
        binding.btNintendo.setOnClickListener{
            limpiar(binding);
            categoria = "Nintendo"
            binding.btNintendo.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#b50000"))
        }
        binding.btPc.setOnClickListener{
            limpiar(binding);
            categoria = "PC"
            binding.btPc.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3e3e3e"))
        }
        binding.btPs4.setOnClickListener{
            limpiar(binding);
            categoria = "PS4"
            binding.btPs4.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1733ab"))
        }
        binding.btXbox.setOnClickListener{
            limpiar(binding);
            categoria = "Xbox"
            binding.btXbox.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#31a22e"))
        }

        return binding.root;
    }

    fun actualizarPublicacion(){
        val titulo = binding.txtEditTitle.text.toString()
        val desc = binding.txtEditDesc.text.toString()
        val url = binding.txtEditImg.text.toString()

        val publicacion = Publicacion(args.publicacion.id, titulo, desc, url, categoria)
        publicacionViewModel.updatePublicacion(publicacion)

        Toast.makeText(requireContext(),"Actualizado!", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_navigation_editar_publicacion_to_navigation_dashboard)

    }

    fun limpiar(binding: FragmentNuevaPublicacionBinding){
        binding.btNintendo.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#AAAAAA"))
        binding.btPc.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#AAAAAA"))
        binding.btPs4.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#AAAAAA"))
        binding.btXbox.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#AAAAAA"))
    }

    fun inicial(binding: FragmentNuevaPublicacionBinding, cat:String){
        if (cat == "Nintendo")
        binding.btNintendo.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#b50000"))
        if (cat == "PC")
        binding.btPc.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3e3e3e"))
        if (cat == "PS4")
        binding.btPs4.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1733ab"))
        if (cat == "Xbox")
        binding.btXbox.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#31a22e"))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}