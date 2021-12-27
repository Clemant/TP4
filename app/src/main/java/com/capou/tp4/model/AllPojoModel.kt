package com.capou.tp4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

sealed class  AddressRecyclerView()

data class AddressDataHeader(
    val header: String
) : AddressRecyclerView()



//Design UI
data class AddressUi(
    val id_default:String,
    val city:String,
    val street_name:String,
    val street_address:String,
    val secondary_address:String,
    val time_zone:String,
    val full_address:String,
    val latitude:Double,
    val longitude:Double,
    val country:String,
):AddressRecyclerView()



// creata a roomm for Weather
@Entity(tableName = "address")
data class AddressRoom(

    @ColumnInfo(name = "id_default")
    val id_default:String,

    @ColumnInfo(name = "city")
    val city:String,

    @ColumnInfo(name = "street_name")
    val street_name:String,

    @ColumnInfo(name = "street_address")
    val street_address:String,

    @ColumnInfo(name = "secondary_address")
    val secondary_address:String,

    @ColumnInfo(name = "time_zone")
    val time_zone:String,

    @ColumnInfo(name = "full_address")
    val full_address:String,

    @ColumnInfo(name = "latitude")
    val latitude:Double,

    @ColumnInfo(name = "longitude")
    val longitude:Double,

    @ColumnInfo(name = "country")
    val country:String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}


data class AddressRetrofit(

    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("id")
    val id_default:String,

    @Expose
    @SerializedName("city")
    val city:String,

    @Expose
    @SerializedName("street_name")
    val street_name:String,

    @Expose
    @SerializedName("street_address")
    val street_address:String,

    @Expose
    @SerializedName("secondary_address")
    val secondary_address:String,

    @Expose
    @SerializedName("time_zone")
    val time_zone:String,

    @Expose
    @SerializedName("full_address")
    val full_address:String,

    @Expose
    @SerializedName("latitude")
    val latitude:Double,

    @Expose
    @SerializedName("longitude")
    val longitude:Double,

    @Expose
    @SerializedName("country")
    val country:String,
)
