package com.example.customweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.customweather.databinding.FragmentUvIndexBinding
import com.example.customweather.viewmodel.CurrentWeatherViewModel
import com.example.customweather.R

class UVIndexFragment : Fragment() {
    private var _binding: FragmentUvIndexBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUvIndexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentWeatherViewModel = ViewModelProvider(requireActivity()).get(CurrentWeatherViewModel::class.java)

        currentWeatherViewModel.weatherData.observe(viewLifecycleOwner, Observer { processedData ->
            binding.uvIndexTextView.text = "UV Index: ${getString(R.string.uv_index_format, processedData.averageUvIndex)}"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
