package com.example.week5task

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week5task.adapters.OrdersAdapter
import com.example.week5task.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    val orderIdList = ArrayList<String>()
    val orderDateList = ArrayList<String>()
    val orderAmountList = ArrayList<String>()
    val orderMassList = ArrayList<String>()
    val orderStatusList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fullName: String = intent.getStringExtra("fullName").toString()
        val emailAddress: String = intent.getStringExtra("emailAddress").toString()
        val password: String = intent.getStringExtra("password").toString()
        val gender: String = intent.getStringExtra("gender").toString()
        val country: String = intent.getStringExtra("country").toString()
        val city: String = intent.getStringExtra("city").toString()
        val address = "$country, $city"

        if(gender == "Female"){
            binding.userAvatar.setImageResource(R.drawable.avatar_girl)
        } else {
            binding.userAvatar.setImageResource(R.drawable.avatar_boy)
        }

        binding.lblName.text = fullName
        binding.lblEmail.text = emailAddress
        binding.lblGender.text = gender
        binding.lblAddress.text = address

        orderIdList.add("429242424")
        orderIdList.add("429242425")
        orderIdList.add("429242426")
        orderIdList.add("429242427")
        orderIdList.add("429242428")
        orderIdList.add("429242429")

        orderDateList.add("Mon, July 3rd")
        orderDateList.add("Tue, July 4th")
        orderDateList.add("Wed, July 5th")
        orderDateList.add("Thu, July 6th")
        orderDateList.add("Fri, July 7th")
        orderDateList.add("Sat, July 8th")

        orderAmountList.add("$1.50")
        orderAmountList.add("$3.50")
        orderAmountList.add("$5.80")
        orderAmountList.add("$12.90")
        orderAmountList.add("$14.60")
        orderAmountList.add("$8.30")

        orderMassList.add("2.5 lbs")
        orderMassList.add("12.5 lbs")
        orderMassList.add("8.5 lbs")
        orderMassList.add("4 lbs")
        orderMassList.add("20.5 lbs")
        orderMassList.add("2 lbs")

        orderStatusList.add("Shipped")
        orderStatusList.add("Shipped")
        orderStatusList.add("Accepted")
        orderStatusList.add("Shipped")
        orderStatusList.add("Shipped")
        orderStatusList.add("Rejected")

        val adapter = OrdersAdapter(
            this@DashboardActivity,
            orderIdList,
            orderDateList,
            orderAmountList,
            orderMassList,
            orderStatusList
        )

        binding.ordersContainer.adapter = adapter
        binding.ordersContainer.layoutManager = LinearLayoutManager(this@DashboardActivity)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}