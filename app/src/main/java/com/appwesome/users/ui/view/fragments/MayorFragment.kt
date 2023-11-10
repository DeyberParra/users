package com.appwesome.users.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.appwesome.users.R
import com.appwesome.users.core.mappers.toMayorModel
import com.appwesome.users.core.patterns.MayorSingleton
import com.appwesome.users.data.model.MayorModel
import com.appwesome.users.databinding.FragmentMayorBinding
import com.appwesome.users.ui.view.adapters.MayorAdapter
import com.appwesome.users.ui.viewmodel.MayorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MayorFragment : Fragment() {

    private lateinit var binding : FragmentMayorBinding
    private val mayorViewModel: MayorViewModel by viewModels()
    private lateinit var adapter: MayorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMayorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        updateData()
    }

    private fun initView(){

        adapter = MayorAdapter(
            selectListener = { mayorViewModel.setMayorSelected(it)},
            deleteListener = { deleteMayor(it)},
            updateListener = { updateMayorDialog(it)}
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.btnAddMayor.setOnClickListener{
          addMayorDialog()
        }
    }
    private fun updateData(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mayorViewModel.getAllMayor().observe(viewLifecycleOwner){ mayorListEntity->
                    val mayorList = mayorListEntity.map {
                        it.toMayorModel()
                    }
                    adapter.sentData(mayorList)
                }
            }
        }
    }

    private fun addMayorDialog(){
        findNavController().navigate(R.id.addMayorDialog)
    }

    private fun updateMayorDialog(mayor: MayorModel){
        MayorSingleton.setMayorToUpdate(mayor)
        findNavController().navigate(R.id.addMayorDialog)
    }

    private fun deleteMayor(mayor: MayorModel){
        mayorViewModel.deleteMayor(mayor)
    }
}