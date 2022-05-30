package com.rasika.planets.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.rasika.planets.databinding.FragmentPlanetDetailBinding
import com.rasika.planets.utils.Resource
import com.rasika.planets.viewModel.PlanetDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanetDetailFragment:Fragment() {
    private lateinit var binding:FragmentPlanetDetailBinding
    private val viewModel:PlanetDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPlanetDetailBinding.inflate(inflater, container, false)
        val view = binding.root


        viewModel.detail.observe(viewLifecycleOwner, Observer { result ->
            binding.planetDetailNameTextViewValue.text = result.name
            binding.planetDetailOrbitalPeriodTextViewValue.text = result.orbital_period
            binding.planetDetailGravityTextViewValue.text = result.gravity
        })


        return view
    }

}