package com.rasika.planets.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.rasika.planets.adapter.PlanetAdapter
import com.rasika.planets.databinding.FragmentPlanetBinding
import com.rasika.planets.utils.hideKeyboard
import com.rasika.planets.viewModel.PlanetViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PlanetFragment :Fragment() {
    private lateinit var binding: FragmentPlanetBinding
    private val viewModel: PlanetViewModel by viewModels()

    private val planetAdapter: PlanetAdapter by lazy {
        PlanetAdapter(PlanetAdapter.OnClickListener { planet ->
            val action =
                PlanetFragmentDirections.actionPlanetFragmentToPlanetDetailsFragment(
                    planet
                )

            findNavController().navigate(action)
            binding.searchTextInput.editText!!.setText("")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlanetBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.searchTextInput.setEndIconOnClickListener {
            setUpObserver(binding.searchTextInput.editText!!.text.toString())
            binding.searchPlanet.isVisible = true
            hideKeyboard()
        }

        setUpObserver("")

        setUpAdapter()

        return view
    }

    private fun setUpObserver(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getPlanets(searchString).collect {
                planetAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun setUpAdapter() {

        binding.planetRecyclerView.apply {
            adapter = planetAdapter
        }

        planetAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                if (planetAdapter.snapshot().isEmpty()) {
                    binding.progressPlanet.isVisible = true
                }
                binding.textViewError.isVisible = false

            } else {
                binding.progressPlanet.isVisible = false

                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (planetAdapter.snapshot().isEmpty()) {
                        binding.textViewError.isVisible = true
                        binding.textViewError.text = it.error.localizedMessage
                    }
                }
            }
        }
    }
}


