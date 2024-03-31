package com.example.mvvmdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemo.databinding.ActivityMainBinding
import com.example.mvvmdemo.repository.QuoteRepository
import com.example.mvvmdemo.roomdatabase.Quote
import com.example.mvvmdemo.roomdatabase.QuoteDatabase
import com.example.mvvmdemo.viewmodel.MainViewModel
import com.example.mvvmdemo.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dao = QuoteDatabase.getDatabase(applicationContext).quoteDao()
        val repository = QuoteRepository(dao)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.getQuotes().observe(this, Observer {
            // binding.quote = it.toString()

            val allQuotes = it.joinToString("\n\n") { it.text }
            binding.quote = allQuotes
        })

        binding.button.setOnClickListener {

            val myQuote = binding.edtNewQuote.text

            if(myQuote.isNotEmpty()) {
                val quote = Quote(0, "$myQuote", "Me")
                mainViewModel.insertQuote(quote)
                binding.edtNewQuote.text = null
            }
            else{
                Toast.makeText(this,"Please write your quote first", Toast.LENGTH_SHORT).show()
            }
        }
    }
}