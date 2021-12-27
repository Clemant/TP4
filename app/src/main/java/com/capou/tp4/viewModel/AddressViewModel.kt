package com.capou.tp4.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.capou.tp4.model.AddressDataHeader
import com.capou.tp4.model.AddressRecyclerView
import com.capou.tp4.model.AddressRoom
import com.capou.tp4.model.AddressUi
import com.capou.tp4.repository.AddressRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddressViewModel : ViewModel() {


    private val weatherRepository: AddressRepository by lazy { AddressRepository() }

    var weatherLiveData: LiveData<List<AddressRecyclerView>> =
        weatherRepository.selectAllAddressData().map {
            it.toUi()
        }


    fun fetchNewQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.fetchData()
        }
    }


    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.deleteAllAddressData()
        }
    }
}


private fun List<AddressRoom>.toUi(): List<AddressRecyclerView>{
    var result = mutableListOf<AddressRecyclerView>()
    asSequence().map {
        AddressUi(
            id_default = it.id_default,
            city = it.city,
            street_name = it.street_name,
            street_address = it.street_address,
            secondary_address = it.secondary_address,
            time_zone = it.time_zone,
            full_address = it.full_address,
            latitude = it.latitude,
            longitude = it.longitude,
            country = it.country,
        )
    }.groupBy {
        it.time_zone.split("/")[0]
    }.forEach{  (isModulo, items) ->
        result.add(AddressDataHeader("${isModulo}"))
        result.addAll(items)
    }
    return result

}