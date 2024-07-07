package com.example.customweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.customweather.databinding.FragmentCurrentTemperatureBinding
import com.example.customweather.viewmodel.CurrentWeatherViewModel
import com.example.customweather.R

class CurrentTemperatureFragment : Fragment() {
    private var _binding: FragmentCurrentTemperatureBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentTemperatureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentWeatherViewModel = ViewModelProvider(requireActivity()).get(CurrentWeatherViewModel::class.java)

        currentWeatherViewModel.weatherData.observe(viewLifecycleOwner, Observer { processedData ->
            binding.currentTempTextView.text = "Current Temperature: ${getString(R.string.temp_format, processedData.averageCurrentTemperature)}"
            binding.feelsLikeTempTextView.text = "Feels Like Temperature: ${getString(R.string.temp_format, processedData.averageFeelsLikeTemperature)}"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
