package com.example.customweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.customweather.ui.CurrentTemperatureFragment
import com.example.customweather.ui.UVIndexFragment
import com.example.customweather.viewmodel.CurrentWeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize ViewModel with activity scope
        currentWeatherViewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)

        // fetch current weather data if it hasn't been fetched yet
        if (savedInstanceState == null) {
            currentWeatherViewModel.fetchWeatherData("Poznan")
        }

        // add fragments
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CurrentTemperatureFragment())
                .commit()
        }

        if (supportFragmentManager.findFragmentById(R.id.fragment_container2) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container2, UVIndexFragment())
                .commit()
        }
    }
}
