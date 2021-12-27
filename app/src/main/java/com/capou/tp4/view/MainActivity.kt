package com.capou.tp4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capou.tp4.R
import com.capou.tp4.databinding.ActivityMainBinding
import com.capou.tp4.model.AddressRecyclerView
import com.capou.tp4.model.AddressUi
import com.capou.tp4.viewModel.AddressViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AddressViewModel
    private lateinit var binding : ActivityMainBinding
    private lateinit var  adapter : AddressAdapter
    private val observer = Observer<List<AddressRecyclerView>> {
        adapter.submitList(it)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(AddressViewModel::class.java)

        adapter = AddressAdapter{
                item,view -> onItemClick(item, view)
        }

        binding.chuckNorrisActivityRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.chuckNorrisActivityRv.adapter = adapter


        binding.chuckNorrisActivityAdd.setOnClickListener {
            viewModel.fetchNewQuote()
        }


        binding.chuckNorrisActivityDelete.setOnClickListener {
            viewModel.deleteAll()
        }
    }
    override fun onStart() {
        super.onStart()
        viewModel.weatherLiveData.observe(this, observer)
    }


    override fun onStop() {
        viewModel.weatherLiveData.removeObserver(observer)
        super.onStop()
    }

    private fun onItemClick(addressUi: AddressUi, view : View) {
        // view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Snackbar.make(binding.root,addressUi.country, Snackbar.LENGTH_LONG).show()
    }


}