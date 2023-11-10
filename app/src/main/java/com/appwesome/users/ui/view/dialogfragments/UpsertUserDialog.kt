package com.appwesome.users.ui.view.dialogfragments

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.appwesome.users.R
import com.appwesome.users.core.extension.decorateMayorSpinnerList
import com.appwesome.users.core.extension.decorateStateSpinnerList
import com.appwesome.users.core.mappers.toMayorModel
import com.appwesome.users.core.mappers.toStateModel
import com.appwesome.users.core.patterns.UserSingleton
import com.appwesome.users.data.model.MayorModel
import com.appwesome.users.data.model.StateModel
import com.appwesome.users.data.model.UserModel
import com.appwesome.users.databinding.UpsertUserFragmentBinding
import com.appwesome.users.ui.view.adapters.SpinnerMayorAdapter
import com.appwesome.users.ui.view.adapters.SpinnerStateAdapter
import com.appwesome.users.ui.viewmodel.MayorViewModel
import com.appwesome.users.ui.viewmodel.StateViewModel
import com.appwesome.users.ui.viewmodel.UserViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpsertUserDialog : Fragment() {

    private lateinit var binding : UpsertUserFragmentBinding
    private val mayorViewModel : MayorViewModel by viewModels()
    private val stateViewModel : StateViewModel by viewModels()
    private val userViewModel : UserViewModel by viewModels()
    private  var spinnerControl = SpinnerControl()
    private val REQUEST_PERMISSION_CODE = 101

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            userViewModel.setUserPhoto(uri.toString())
            showImage(uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UpsertUserFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if(UserSingleton.isNotEmpty()){
            binding.title.text = getString(R.string.update_user_title_fragment)
            fillFields(UserSingleton.getUser()!!)
        }
        fillStateSpinner()
        fillMayorSpinner()
        clickListeners()

    }

    private fun fillFields(userModel: UserModel){
        binding.name.setText(userModel.name)
        binding.age.setText(userModel.age.toString())
        binding.street.setText(userModel.addressStreet)
        binding.exteriorNumber.setText(userModel.addressExteriorNumber)
        binding.interiorNumber.setText(userModel.addressInteriorNumber)
        binding.neighborhood.setText(userModel.neighborhood)
        showImage(Uri.parse(userModel.photo))
        userViewModel.setUserPhoto(userModel.photo)
    }

    private fun fillStateSpinner() {
        lifecycleScope.launch {
            stateViewModel.stateData().observe(viewLifecycleOwner) {

                val states = it.map { state ->
                    state.toStateModel()
                }
                val decoratedStates =
                    states.decorateStateSpinnerList(getString(R.string.select_state_mayor_fragment), -1)
                val adapter = SpinnerStateAdapter(requireContext(), states = decoratedStates)
                binding.spinnerState.adapter = adapter

                if(UserSingleton.isNotEmpty()){
                    val selectedState = states.firstOrNull { it.id == UserSingleton.getUser()!!.state }
                    if(selectedState != null){
                        spinnerControl.state = selectedState
                        val selectedIndex = decoratedStates.indexOf(selectedState)
                        binding.spinnerState.setSelection(selectedIndex)

                    }else{
                        spinnerControl.state = null
                        binding.spinnerState.setSelection(0)
                    }
                }
            }
        }
        clickStateSpinner()
    }

    private fun fillMayorSpinner() {
        spinnerControl.state?.let {state->
            if(state.id != -1){
                lifecycleScope.launch {
                    mayorViewModel.getAllMayorsByState(state.id!!).observe(viewLifecycleOwner){
                        val mayors = it.map {
                            it.toMayorModel()
                        }
                        val decoratedMayors = mayors.decorateMayorSpinnerList(getString(R.string.user_title_selection_mayor), -1)
                        val adapter = SpinnerMayorAdapter(requireContext(), mayors = decoratedMayors)
                        binding.spinnerMayor.adapter = adapter

                        if(UserSingleton.isNotEmpty()){
                            val selectedMayor = mayors.firstOrNull { it.id == UserSingleton.getUser()!!.mayor }
                            if(selectedMayor!=null){
                                spinnerControl.mayor = selectedMayor
                                val selectedIndex = decoratedMayors.indexOf(selectedMayor)
                                binding.spinnerMayor.setSelection(selectedIndex)
                            }else{
                                spinnerControl.mayor = null
                                binding.spinnerMayor.setSelection(0)
                            }
                        }
                    }
                }
            }
            clickMayorSpinner()
        }
    }

    private fun clickListeners(){
        binding.btnImage.setOnClickListener{
            addPhoto()
        }
        binding.btnAddAcept.setOnClickListener{
            if(areAllFieldsFilled() && spinnerControl.state!=null && spinnerControl.mayor!=null){
                if(spinnerControl.state!!.id != -1 && spinnerControl.mayor!!.idState !=-1 ){
                    if(UserSingleton.isNotEmpty()){
                        val user = createUser()
                        val updateUser = UserSingleton.getUser()!!.copy(
                            name = user.name,
                            age = user.age,
                            addressStreet = user.addressStreet,
                            addressExteriorNumber = user.addressExteriorNumber,
                            addressInteriorNumber = user.addressInteriorNumber,
                            neighborhood = user.neighborhood,
                            state = user.state,
                            mayor = user.mayor,
                            photo = user.photo
                        )
                        userViewModel.updateUser(updateUser)
                        UserSingleton.setUser(null)
                    }else{
                        val newUser = createUser()
                        userViewModel.addUser(newUser)
                    }
                    findNavController().navigate(R.id.usersFragment)
                }else{
                    Toast.makeText(requireContext(), getString(R.string.empty_data), Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), getString(R.string.empty_data), Toast.LENGTH_SHORT).show()
            }

        }
        binding.btnAddCancel.setOnClickListener {
            if(UserSingleton.isNotEmpty()) UserSingleton.setUser(null)
            findNavController().navigate(R.id.usersFragment)
        }
    }



    private fun clickStateSpinner(){
        binding.spinnerState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                stateViewModel.stateData().observe(viewLifecycleOwner){
                    val states = it.map { state->
                        state.toStateModel()
                    }
                    val newStates = states.decorateStateSpinnerList(getString(R.string.select_state_mayor_fragment), -1)
                    spinnerControl.state = newStates[position]
                    spinnerControl.mayor = null
                    fillMayorSpinner()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }


    private fun clickMayorSpinner(){
        binding.spinnerMayor.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                spinnerControl.state?.let {state->
                    if(state.id != -1){
                        lifecycleScope.launch {
                            mayorViewModel.getAllMayorsByState(state.id!!).observe(viewLifecycleOwner){

                                val mayors = it.map {
                                    it.toMayorModel()
                                }
                                val newMayor = mayors.decorateMayorSpinnerList(getString(R.string.user_title_selection_mayor), -1)
                                spinnerControl.mayor = newMayor[position]
                            }
                        }
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun addPhoto() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            showPermissionExplanationDialog()
        } else {
            openGallery()
        }
    }

    private fun showPermissionExplanationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.permission_title))
            .setMessage(getString(R.string.permission_message))
            .setPositiveButton(getString(R.string.permission_ok)) { dialog, which ->
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_PERMISSION_CODE)
            }
            .setNegativeButton(getString(R.string.permission_cancel)) { dialog, which ->
            }
        builder.create().show()
    }


    private fun openGallery() {
        getContent.launch("image/*")
    }

    private fun showImage(uri: Uri){
        binding.userPhoto.visibility = View.VISIBLE
        Glide.with(this)
            .load(uri)
            .into(binding.userPhoto)
    }

    private fun areAllFieldsFilled(): Boolean {
        val name = binding.name.text.toString().trim()
        val age = binding.age.text.toString().trim()
        val street = binding.street.text.toString().trim()
        val exteriorNumber = binding.exteriorNumber.text.toString().trim()
        val interiorNumber = binding.interiorNumber.text.toString().trim()
        val neighborhood = binding.neighborhood.text.toString().trim()
        val isPhotoSelected = binding.userPhoto.visibility == View.VISIBLE

        if (name.isEmpty() || age.isEmpty() || street.isEmpty() ||
            exteriorNumber.isEmpty() || interiorNumber.isEmpty() ||
            neighborhood.isEmpty() || !isPhotoSelected
        ) {
            return false
        }
        return true
    }

    private fun createUser() : UserModel{
        return UserModel(
            name = binding.name.text.toString(),
            age = binding.age.text.toString().toInt(),
            addressStreet = binding.street.text.toString(),
            addressExteriorNumber = binding.exteriorNumber.text.toString(),
            addressInteriorNumber = binding.interiorNumber.text.toString(),
            neighborhood = binding.neighborhood.text.toString(),
            state = spinnerControl.state!!.id!!,
            mayor = spinnerControl.mayor!!.id!!,
            photo = userViewModel.getUserPhoto()!!
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        UserSingleton.setUser(null)
    }

    data class SpinnerControl(
        var state: StateModel?=null,
        var mayor: MayorModel?=null
    )
}


