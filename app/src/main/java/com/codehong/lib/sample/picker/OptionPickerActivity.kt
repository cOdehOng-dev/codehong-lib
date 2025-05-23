package com.codehong.lib.sample.picker

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.codehong.lib.sample.databinding.ActivityPlaygroundBinding

class OptionPickerActivity : ComponentActivity() {

    private lateinit var binding: ActivityPlaygroundBinding

    private var selectIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaygroundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val testList = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

//        binding.btnTest.setOnClickListener {
//            OptionPickerDialog(
//                this,
//                "테스트",
//                testList,
//                selectIndex
//            ) { selectOption, selectedPosition ->
//                selectIndex = selectedPosition
//                binding.tvOption.text = selectOption
//            }.show()
//        }
    }
}
