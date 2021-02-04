package com.text.formatter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.text.formatter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.formattedEditText.setText("Hello World", TextView.BufferType.SPANNABLE)

        binding.bold.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.formattedEditText.setStyleForSelection(binding.bold.isChecked, binding.italic.isChecked, binding.underline.isChecked, binding.strike.isChecked)
        }

        binding.italic.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.formattedEditText.setStyleForSelection(binding.bold.isChecked, binding.italic.isChecked, binding.underline.isChecked, binding.strike.isChecked)
        }

        binding.underline.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.formattedEditText.setStyleForSelection(binding.bold.isChecked, binding.italic.isChecked, binding.underline.isChecked, binding.strike.isChecked)
        }

        binding.strike.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.formattedEditText.setStyleForSelection(binding.bold.isChecked, binding.italic.isChecked, binding.underline.isChecked, binding.strike.isChecked)
        }
    }
}