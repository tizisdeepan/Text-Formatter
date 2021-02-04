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

        binding.formattedEditText.listener = object : FormattedTextListener {
            override fun getCurrentSpans(isBold: Boolean, isItalic: Boolean, underlined: Boolean, striked: Boolean, isBullet: Boolean, isNumber: Boolean) {
                binding.bold.isChecked = isBold
                binding.italic.isChecked = isItalic
                binding.underline.isChecked = underlined
                binding.strike.isChecked = striked
                binding.bullet.isChecked = isBullet
                binding.number.isChecked = isNumber
            }
        }

        binding.bold.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.formattedEditText.setStyleForSelection(binding.bold.isChecked, binding.italic.isChecked, binding.underline.isChecked, binding.strike.isChecked, binding.bullet.isChecked, binding.number.isChecked)
        }

        binding.italic.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.formattedEditText.setStyleForSelection(binding.bold.isChecked, binding.italic.isChecked, binding.underline.isChecked, binding.strike.isChecked, binding.bullet.isChecked, binding.number.isChecked)
        }

        binding.underline.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.formattedEditText.setStyleForSelection(binding.bold.isChecked, binding.italic.isChecked, binding.underline.isChecked, binding.strike.isChecked, binding.bullet.isChecked, binding.number.isChecked)
        }

        binding.strike.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.formattedEditText.setStyleForSelection(binding.bold.isChecked, binding.italic.isChecked, binding.underline.isChecked, binding.strike.isChecked, binding.bullet.isChecked, binding.number.isChecked)
        }

        binding.bullet.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.formattedEditText.setStyleForSelection(binding.bold.isChecked, binding.italic.isChecked, binding.underline.isChecked, binding.strike.isChecked, binding.bullet.isChecked, binding.number.isChecked)
        }

        binding.number.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.formattedEditText.setStyleForSelection(binding.bold.isChecked, binding.italic.isChecked, binding.underline.isChecked, binding.strike.isChecked, binding.bullet.isChecked, binding.number.isChecked)
        }
    }
}