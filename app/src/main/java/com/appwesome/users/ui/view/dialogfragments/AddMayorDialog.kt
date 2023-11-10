package com.appwesome.users.ui.view.dialogfragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.appwesome.users.R
import com.appwesome.users.core.extension.decorateStateSpinnerList
import com.appwesome.users.core.mappers.toStateModel
import com.appwesome.users.core.patterns.MayorSingleton
import com.appwesome.users.data.model.MayorModel
import com.appwesome.users.databinding.AddMayorDialogBinding
import com.appwesome.users.ui.view.adapters.SpinnerStateAdapter
import com.appwesome.users.ui.viewmodel.MayorViewModel
import com.appwesome.users.ui.viewmodel.StateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddMayorDialog : Fragment() {

    private lateinit var binding : AddMayorDialogBinding
    private val mayorViewModel : MayorViewModel by viewModels()
    private val stateViewModel : StateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddMayorDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(MayorSingleton.isUpdateMayor()){
            binding.title.text = (getString(R.string.update_mayor_title_fragment))
            binding.name.setText(MayorSingleton.getMayorToUpdate()!!.nameMayor)
        }
       fillSelectView()

        lifecycleScope.launch{
            binding.btnAddAcept.setOnClickListener{
                val optionSelected = stateViewModel.getStateSelected()
                optionSelected?.let { state->
                    if(state.id!! >  -1 && !binding.name.text.toString().isNullOrEmpty()){
                        val newMayor = MayorModel(binding.name.text.toString(), idState = state.id)

                        if(MayorSingleton.isUpdateMayor()){
                            val updateMayor = MayorSingleton.getMayorToUpdate()!!.copy(newMayor.nameMayor, state.id)
                            mayorViewModel.updateMayor(updateMayor)
                            MayorSingleton.setMayorToUpdate(null)
                        }else{
                            mayorViewModel.addMayor(newMayor)
                        }

                        findNavController().navigate(R.id.mayorFragment)
                    }else{
                        Toast.makeText(requireContext(), getString(R.string.empty_data), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.btnAddCancel.setOnClickListener{
            MayorSingleton.setMayorToUpdate(null)
            findNavController().navigate(R.id.mayorFragment)
        }
    }


    private fun fillSelectView(){
        lifecycleScope.launch {
            stateViewModel.stateData().observe(viewLifecycleOwner){
                val states = it.map { state->
                    state.toStateModel()
                }
                val decoratedStates = states.decorateStateSpinnerList(getString(R.string.select_state_mayor_fragment), -1)
                val adapter = SpinnerStateAdapter(requireContext(), states = decoratedStates )
                binding.spinner.adapter = adapter

                if(MayorSingleton.isUpdateMayor()){
                    val mayorToUpdate = MayorSingleton.getMayorToUpdate()
                    mayorToUpdate?.let { mayor ->
                        val selectedState = states.firstOrNull { it.id == mayor.idState }
                        val selectedIndex = decoratedStates.indexOf(selectedState)
                        binding.spinner.setSelection(selectedIndex)
                    }
                }
            }
        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                stateViewModel.stateData().observe(viewLifecycleOwner){
                    val states = it.map { state->
                        state.toStateModel()
                    }
                    val newStates = states.decorateStateSpinnerList(getString(R.string.select_state_mayor_fragment), -1)
                    stateViewModel.setStateSelected(newStates[position])
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MayorSingleton.setMayorToUpdate(null)
    }
}