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
import com.appwesome.users.core.mappers.toUserModel
import com.appwesome.users.core.patterns.UserSingleton
import com.appwesome.users.data.model.UserModel

import com.appwesome.users.databinding.FragmentUsersBinding
import com.appwesome.users.ui.view.adapters.UserAdapter
import com.appwesome.users.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding : FragmentUsersBinding
    private lateinit var adapter : UserAdapter
    private val userViewModel : UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        updateData()
    }

    private fun initView(){

        adapter = UserAdapter(
            selectListener = { userViewModel.setUserSelected(it)},
            deleteListener = { deleteUser(it)},
            updateListener = { updateUserDialog(it)}
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.btnAddUser.setOnClickListener{
            addUserDialog()
        }
    }

    private fun updateData(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                userViewModel.getAllUsers().observe(viewLifecycleOwner){ userListEntity->
                    val userList = userListEntity.map {
                        it.toUserModel()
                    }
                    adapter.sentData(userList)
                }
            }
        }
    }

    private fun addUserDialog(){
        findNavController().navigate(R.id.upsertUserDialog)
    }

    private fun updateUserDialog(userModel: UserModel){
        UserSingleton.setUser(userModel)
        findNavController().navigate(R.id.upsertUserDialog)
    }

    private fun deleteUser(userModel: UserModel){
        userViewModel.deleteUser(userModel)
    }

}