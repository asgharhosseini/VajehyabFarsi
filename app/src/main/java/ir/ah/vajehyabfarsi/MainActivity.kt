package ir.ah.vajehyabfarsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.*
import androidx.navigation.fragment.*
import dagger.hilt.android.*
import ir.ah.vajehyabfarsi.databinding.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }







}