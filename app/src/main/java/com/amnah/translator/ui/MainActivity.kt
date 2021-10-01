package com.amnah.translator.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.amnah.translator.R
import com.amnah.translator.databinding.ActivityMainBinding
import com.amnah.translator.model.data.TranslatorResponse
import com.amnah.translator.util.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).also { binding ->
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
        }

    }
}