package com.appwesome.users.ui.view.dialogfragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.appwesome.users.R
import com.appwesome.users.data.model.StateModel
import com.appwesome.users.databinding.AddStateDialogBinding
import com.appwesome.users.ui.viewmodel.StateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddStateDialog : DialogFragment() {

    private lateinit var binding : AddStateDialogBinding
    private val stateViewModel : StateViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddStateDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnAddAcept.setOnClickListener{
            if(!binding.name.text.toString().isNullOrEmpty()){
                val newState = StateModel(nameState = binding.name.text.toString())
                stateViewModel.addState(newState)
                dismiss()
            }else{
                Toast.makeText(requireContext(), getString(R.string.empty_data), Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnAddCancel.setOnClickListener{
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme_Dialog_AlertDialog)
        return super.onCreateDialog(savedInstanceState)
    }
}
