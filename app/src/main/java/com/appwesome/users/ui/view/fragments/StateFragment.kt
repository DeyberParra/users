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
import androidx.recyclerview.widget.LinearLayoutManager
import com.appwesome.users.core.mappers.toStateModel
import com.appwesome.users.data.model.StateModel
import com.appwesome.users.databinding.FragmentStateBinding
import com.appwesome.users.ui.view.adapters.StateAdapter
import com.appwesome.users.ui.view.dialogfragments.AddStateDialog
import com.appwesome.users.ui.view.dialogfragments.EditStateDialog
import com.appwesome.users.ui.viewmodel.StateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StateFragment : Fragment() {

    private lateinit var binding : FragmentStateBinding
    private val stateViewModel : StateViewModel by viewModels()
    private lateinit var adapter: StateAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        updateData()

    }

    private fun initView(){

        adapter = StateAdapter(
            selectListener = { stateViewModel.setStateSelected(it)},
            deleteListener = { deleteState(it)},
            updateListener = { updateStateDialog(it)}
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.btnAddState.setOnClickListener{
            addStateDialog()
        }
    }
    private fun updateData(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                stateViewModel.stateData().observe(viewLifecycleOwner){ stateListEntity->
                    val stateList = stateListEntity.map {
                        it.toStateModel()
                    }
                    adapter.sentData(stateList)
                }
            }
        }
    }

    private fun addStateDialog(){
        val dialog = AddStateDialog()
        dialog.show(childFragmentManager, "AddStateDialogFragment")
    }

    private fun updateStateDialog(state: StateModel){
        stateViewModel.setStateToUpdate(state)
        val dialog = EditStateDialog()
        dialog.show(childFragmentManager, "EditStateDialogFragment")
    }

    private fun deleteState(state:StateModel){
        stateViewModel.deleteState(state)
    }


}