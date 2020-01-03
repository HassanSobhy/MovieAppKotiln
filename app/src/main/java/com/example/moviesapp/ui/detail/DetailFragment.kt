package com.example.moviesapp.ui.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val movieResult = DetailFragmentArgs.fromBundle(arguments!!).selectedMovie

        //Change Fragment title
        //(activity as AppCompatActivity).supportActionBar?.title = movieResult.title

        val viewModelFactory = DetailViewModelFactory(movieResult, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(DetailViewModel::class.java)






        return binding.root
    }


}
