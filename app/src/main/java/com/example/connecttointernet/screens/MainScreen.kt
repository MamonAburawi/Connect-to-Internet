package com.example.connecttointernet.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.connecttointernet.R
import com.example.connecttointernet.databinding.FragmentMainScreenBinding
import com.example.connecttointernet.viewmodel.UserViewModel

class MainScreen : Fragment() {



    private lateinit var binding : FragmentMainScreenBinding
    private lateinit var viewModel: UserViewModel

    private lateinit var customAdapter: CustomAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_screen,container,false)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        setViews()

        binding.btnGet.setOnClickListener {
            viewModel.refreshData()
        }





//        binding.btnPost.setOnClickListener {
//
//        }
//

        viewModel.dataSize.observe(viewLifecycleOwner){
            if (it != null){
                binding.tvSize.text = "data size = $it"
            }
        }


        viewModel.data.observe(viewLifecycleOwner){ list->
            if(list != null){
                customAdapter.diff.submitList(list)

            }
        }



        return binding.root

    }


    fun setViews(){
        customAdapter = CustomAdapter(requireContext())
        binding.rvMars.adapter = customAdapter
    }

}