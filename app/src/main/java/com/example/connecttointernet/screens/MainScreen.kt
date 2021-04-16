package com.example.connecttointernet.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.connecttointernet.R
import com.example.connecttointernet.databinding.FragmentMainScreenBinding
import com.example.connecttointernet.viewmodel.UserViewModel

class MainScreen : Fragment() {



    private lateinit var binding : FragmentMainScreenBinding
    private lateinit var viewModel: UserViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_screen,container,false)


        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        viewModel.data.observe(viewLifecycleOwner, Observer { data->
            if(data != null){
                binding.TextViewListSize.text = "${data.name} \n ${data.id} "
            }
        })



        return binding.root

    }

}