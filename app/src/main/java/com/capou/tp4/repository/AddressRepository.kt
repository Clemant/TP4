package com.capou.tp4.repository

import androidx.lifecycle.LiveData
import com.capou.tp4.architecture.MyApplication
import com.capou.tp4.architecture.RetrofitBuilder
import com.capou.tp4.model.AddressRetrofit
import com.capou.tp4.model.AddressRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddressRepository {
    private val daoAddress = MyApplication.instance.mApplicationDatabase.addressDao()

    fun selectAllAddressData(): LiveData<List<AddressRoom>> {
        return daoAddress.selectAll()
    }


    private suspend fun insertAddressData(addressRoom: AddressRoom) =
        withContext(Dispatchers.IO) {
            daoAddress.insert(addressRoom)
        }


    suspend fun deleteAllAddressData() = withContext(Dispatchers.IO) {
        daoAddress.deleteAll()
    }


    suspend fun fetchData() {
        insertAddressData(RetrofitBuilder.getAddress().getRandomQuote().toRoom())
    }
}

private fun AddressRetrofit.toRoom(): AddressRoom {
    return AddressRoom(
        id_default = id_default,
        city = city,
        street_name = street_name,
        street_address = street_address,
        secondary_address = secondary_address,
        time_zone = time_zone,
        full_address = full_address,
        latitude = latitude,
        longitude = longitude,
        country = country,
    )
}

