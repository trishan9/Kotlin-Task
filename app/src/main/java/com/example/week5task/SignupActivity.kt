package com.example.week5task

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.week5task.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val countries = arrayOf(
        "Nepal",
        "France",
        "USA",
        "Australia",
        "Canada",
        "India",
        "Japan",
        "Germany",
        "Italy",
        "Brazil"
    )

    private val cities = arrayOf(
        // Cities in Nepal (start with 'Ka')
        "Kathmandu", "Kavre", "Kalaiya", "Kakarvitta", "Kalanki",
        // Cities in France (start with 'Pa')
        "Paris", "Pau", "Pantin", "Parthenay", "Palaiseau",
        // Cities in USA (start with 'New')
        "New York", "New Orleans", "New Haven", "New Jersey", "Newport",
        // Cities in Australia (start with 'Mel')
        "Melbourne", "Melton", "Melrose", "Melville", "Melba",
        // Cities in Canada (start with 'Tor')
        "Toronto", "Torbay", "Torrington", "Torquay", "Torkelton",
        // Cities in India (start with 'Ban')
        "Bangalore", "Bandra", "Banswara", "Banka", "Banshi",
        // Cities in Japan (start with 'To')
        "Tokyo", "Tottori", "Toshima", "Tomioka", "Toyama",
        // Cities in Germany (start with 'Ber')
        "Berlin", "Bergheim", "Bernau", "Berchtesgaden", "Bersenbrück",
        // Cities in Italy (start with 'Ven')
        "Venice", "Ventimiglia", "Venezia", "Venafro", "Veneziano",
        // Cities in Brazil (start with 'Rio')
        "Rio de Janeiro", "Rio Claro", "Rio Grande", "Rio Branco", "Rio Azul"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countriesAdapter = ArrayAdapter(
            this@SignupActivity,
            android.R.layout.simple_spinner_dropdown_item,
            countries
        )
        binding.selectCountry.adapter = countriesAdapter

        val citiesAdapter = ArrayAdapter(
            this@SignupActivity,
            android.R.layout.simple_spinner_dropdown_item,
            cities
        )
        binding.autoCompleteCity.threshold = 1
        binding.autoCompleteCity.setAdapter(citiesAdapter)

        binding.signupBtn.setOnClickListener {
            val fullName = binding.editFullName.text.toString()
            val emailAddress = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            val genderId = binding.genderSelect.checkedRadioButtonId
            val selectedGender = when (genderId) {
                R.id.maleRadio -> "Male"
                R.id.femaleRadio -> "Female"
                R.id.othersRadio -> "Others"
                else -> "Unknown"
            }
            val country = binding.selectCountry.selectedItem.toString()
            val city = binding.autoCompleteCity.text.toString()
            val isTermsAccepted = binding.checkBox.isChecked

            if (fullName.isEmpty() || emailAddress.isEmpty() || password.isEmpty() || selectedGender == "Unknown" || country.isEmpty() || city.isEmpty()) {
                Snackbar.make(
                    binding.main,
                    "Fields can't be empty. All the fields are required!",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                if (isTermsAccepted) {
                    val intent = Intent(this@SignupActivity, DashboardActivity::class.java)

                    intent.putExtra("fullName", fullName)
                    intent.putExtra("emailAddress", emailAddress)
                    intent.putExtra("password", password)
                    intent.putExtra("gender", selectedGender)
                    intent.putExtra("country", country)
                    intent.putExtra("city", city)
                    startActivity(intent)
                    finish()
                } else {
                    Snackbar.make(
                        binding.main,
                        "You must accept our terms and conditions in order to proceed!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}