package com.socialmediagamer.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.socialmediagamer.adapter.PublicacionesAdapter
import com.socialmediagamer.databinding.FragmentDashboardBinding
import com.socialmediagamer.adapter.PublicacionesPerfilAdapter
import com.socialmediagamer.ui.home.PublicacionViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var publicacionViewModel: PublicacionViewModel

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


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}