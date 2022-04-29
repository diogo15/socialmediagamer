package com.socialmediagamer.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.socialmediagamer.R
import com.socialmediagamer.adapter.PublicacionesPerfilAdapter
import com.socialmediagamer.databinding.FragmentDashboardBinding
import com.socialmediagamer.model.Perfil
import com.socialmediagamer.ui.home.PublicacionViewModel
import com.socialmediagamer.utiles.ImagenUtiles
import com.google.firebase.auth.ktx.auth
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var publicacionViewModel: PublicacionViewModel
    private lateinit var perfilViewModel: PerfilViewModel

    private lateinit var imagenUtiles: ImagenUtiles
    private lateinit var profileID: String
    private lateinit var tomarFotoActivity: ActivityResultLauncher<Intent>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var rutaImagen2 = ""

        perfilViewModel = ViewModelProvider(this)[PerfilViewModel::class.java]

        perfilViewModel.getAllData.observe(viewLifecycleOwner){ perfiles ->
            profileID = perfiles?.get(0)?.id.toString()
            rutaImagen2 = perfiles?.get(0)?.rutaImagen.toString()
            if(rutaImagen2 != ""){
                //load image
                Glide.with(root.context)
                    .load(rutaImagen2)
                    .into(binding.imageView);
            }
        }

        //levantar el reciclador desde la clase adapter
        val publicacionesAdapter= PublicacionesPerfilAdapter()
        val reciclador = binding.recicladorPropias

        reciclador.adapter = publicacionesAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())
        publicacionViewModel = ViewModelProvider(this)[PublicacionViewModel::class.java]

        publicacionViewModel.getMyData.observe(viewLifecycleOwner){ publicaciones ->
            publicacionesAdapter.setData(publicaciones)
        }

        //add Divider
        reciclador.addItemDecoration( DividerItemDecoration( context, DividerItemDecoration.VERTICAL ) )

        tomarFotoActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode == Activity.RESULT_OK){
                imagenUtiles.actualizaFoto()
                subeImagenNube()
            }
        }

        imagenUtiles = ImagenUtiles(
            requireContext(),
            binding.floatingActionButton2,
            binding.imageView,
            tomarFotoActivity
        )



        return root
    }

    private fun subeImagenNube(){
        var rutaImagen = ""
        val imagenFile = imagenUtiles.imagenFile
        if (imagenFile.exists() && imagenFile.isFile && imagenFile.canRead()) {
            val ruta = Uri.fromFile(imagenFile)
            val rutaNube = "gamerAPP/${imagenFile.name}"
            val referencia: StorageReference = Firebase.storage.reference.child(rutaNube)
            referencia.putFile(ruta)
                .addOnSuccessListener {
                    referencia.downloadUrl
                        .addOnSuccessListener {
                            rutaImagen = it.toString()
                            insertarPerfil(rutaImagen)
                        }
                }
                .addOnFailureListener{
                    //insertarPerfil(rutaImagen)
                }

        } else {
            //insertarPerfil(rutaImagen)
        }
    }

    fun insertarPerfil(rutaImagen:String){

        val perfil = Perfil(profileID, "", "", "","",rutaImagen)
        perfilViewModel.updatePerfil(perfil)

        Toast.makeText(requireContext(),"Imagen Agregada", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}