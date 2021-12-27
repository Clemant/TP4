package com.capou.tp4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capou.tp4.databinding.ItemAddressBinding
import com.capou.tp4.databinding.ItemAddressHeaderBinding
import com.capou.tp4.model.AddressDataHeader
import com.capou.tp4.model.AddressRecyclerView
import com.capou.tp4.model.AddressUi

val diffUtils = object : DiffUtil.ItemCallback<AddressRecyclerView>() {
    override fun areItemsTheSame(oldItem: AddressRecyclerView, newItem: AddressRecyclerView): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: AddressRecyclerView, newItem: AddressRecyclerView): Boolean {
        return oldItem == newItem
    }


}

class ChuckNorrisQuoteViewHolder(
    val binding: ItemAddressBinding,
    onItemClick: (objectDataSample: AddressUi, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var ui: AddressUi
    init {
        binding.root.setOnClickListener {
            onItemClick(ui, itemView)
        }
    }


    fun bind(addressUi: AddressUi ) {
        ui = addressUi
        binding.streetName.text = addressUi.street_name
        binding.streetAddress.text = addressUi.street_address
        binding.secondaryAddress.text = addressUi.secondary_address
        binding.fullAddress.text = addressUi.full_address
        binding.country.text = addressUi.country
        binding.timeZone.text = addressUi.time_zone
        binding.latLong.text = "(${addressUi.longitude}/${addressUi.latitude})"
        /* binding.itemChuckNorrisQuote.text = "Nom: "+chuckNorrisUi.name
         binding.type.text = "Type: "+chuckNorrisUi.type
         binding.address.text = chuckNorrisUi.address
         binding.description.text = chuckNorrisUi.description*/
    }
}




class HeaderViewHolder(
    val binding: ItemAddressHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var header: AddressDataHeader



    fun bind(addressDataHeader: AddressDataHeader) {
        header = addressDataHeader
        binding.itemRecyclerViewHeader.text = "General TimeZone: "+header.header
    }
}

class AddressAdapter(private val onItemClick: (quoteUi: AddressUi, view: View) -> Unit) : ListAdapter<AddressRecyclerView, RecyclerView.ViewHolder>(diffUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        when (viewType) {

            MyItemType.ROW.type -> {
                ChuckNorrisQuoteViewHolder(
                    ItemAddressBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onItemClick
                )
            }


            MyItemType.HEADER.type -> {
                HeaderViewHolder(
                    ItemAddressHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            }



            else -> throw RuntimeException("Wrong view type received $viewType")
        }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AddressUi -> MyItemType.ROW.type
            is AddressDataHeader -> MyItemType.HEADER.type
            else -> MyItemType.ROW.type
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var type = (holder::class.java).simpleName.toString()
        when (holder.itemViewType){
            MyItemType.ROW.type -> (holder as ChuckNorrisQuoteViewHolder).bind(getItem(position) as AddressUi)
            MyItemType.HEADER.type -> (holder as HeaderViewHolder).bind(getItem(position) as AddressDataHeader)
            else -> throw java.lang.RuntimeException("Can't display the right view ${holder.itemViewType} ${holder.itemView}")
        }
    }

    enum class MyItemType(val type: Int) {
        HEADER(0),
        ROW(1)
    }
}