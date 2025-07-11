package com.maaz.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maaz.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.add.setOnClickListener {
            val a = binding.num1.text.toString()
            val b = binding.num2.text.toString()
            val result = a.toInt() + b.toInt()
            binding.result.text = "Result: $result"
        }

        binding.sub.setOnClickListener {
            val a = binding.num1.text.toString()
            val b = binding.num2.text.toString()
            val result = a.toInt() - b.toInt()
            binding.result.text = "Result: $result"
        }

        binding.mul.setOnClickListener {
            val a = binding.num1.text.toString()
            val b = binding.num2.text.toString()
            val result = a.toInt() * b.toInt()
            binding.result.text = "Result: $result"
        }

        binding.div.setOnClickListener {
            if (binding.num1.text.toString() == "0" || binding.num2.text.toString() == "0") {
                binding.result.text = "Cannot divide by zero"
            } else {

                val a = binding.num1.text.toString()
                val b = binding.num2.text.toString()
                val result = a.toInt() / b.toInt()
                binding.result.text = "Result: $result"
            }
        }
    }
}