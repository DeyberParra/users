package com.appwesome.users.ui.view.dialogfragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.appwesome.users.R
import com.appwesome.users.databinding.EditStateDialogBinding
import com.appwesome.users.ui.viewmodel.StateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditStateDialog : DialogFragment() {

    private lateinit var binding : EditStateDialogBinding
    private val stateViewModel : StateViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditStateDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val state = stateViewModel.getStateToUpdate()
        state?.let{
            binding.name.setText(state.nameState)
            binding.btnEditAcept.setOnClickListener{
                val newState = state.copy(binding.name.text.toString())
                stateViewModel.updateState(newState)
                dismiss()
            }
        }
        binding.btnEditCancel.setOnClickListener{
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_Dialog_AlertDialog)
        return super.onCreateDialog(savedInstanceState)
    }
}
