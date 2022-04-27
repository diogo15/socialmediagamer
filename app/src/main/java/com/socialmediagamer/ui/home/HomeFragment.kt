package com.socialmediagamer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.socialmediagamer.R
import com.socialmediagamer.adapter.PublicacionesAdapter
import com.socialmediagamer.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var publicacionViewModel: PublicacionViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        publicacionViewModel = ViewModelProvider(this)[PublicacionViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnAddPost.setOnClickListener {
            //findNavController().navigate(R.id.action_navigation_home_to_nueva_publicacion)
            val action = R.id.navigation_nueva_publicacion
            this.findNavController().navigate(action)
        }

        //levantar el reciclador desde la clase adapter
        val publicacionesAdapter= PublicacionesAdapter()
        val reciclador = binding.latestPosts

        reciclador.adapter = publicacionesAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())
        publicacionViewModel = ViewModelProvider(this)[PublicacionViewModel::class.java]

        publicacionViewModel.getAllData.observe(viewLifecycleOwner){ publicaciones ->
            publicacionesAdapter.setData(publicaciones)
        }

        //add Divider
        reciclador.addItemDecoration( DividerItemDecoration( context, DividerItemDecoration.VERTICAL ) )

        return binding.root;
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}