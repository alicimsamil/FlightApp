package com.alicimsamil.flightapp.presentation.flightlist


import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.flightapp.R
import com.alicimsamil.flightapp.common.Status
import com.alicimsamil.flightapp.domain.model.ListFlightModel
import java.time.OffsetDateTime
import javax.inject.Inject

class FlightListAdapter @Inject constructor(val flightList: ArrayList<ListFlightModel>,private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<FlightListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_reycyler_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding(flightList[position],onItemClickListener)

    }

    override fun getItemCount(): Int = flightList.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun binding(model: ListFlightModel,onItemClickListener: OnItemClickListener) {
            with(itemView) {
                val departureTime = OffsetDateTime.parse(model.departureEstimated)
                val departureFormattedTime = "${departureTime.hour}:${departureTime.minute}"
                val arrivalTime = OffsetDateTime.parse(model.arrivalEstimated)
                val arrivalFormattedTime = "${arrivalTime.hour}:${arrivalTime.minute}"
                findViewById<AppCompatTextView>(R.id.tvAirlineInfo).text = model.airlineName
                findViewById<AppCompatTextView>(R.id.tvDepartureInfo).text =
                    "${model.departureIata} - ${departureFormattedTime}"
                findViewById<AppCompatTextView>(R.id.tvArrivalInfo).text =
                    "${model.arrivalIata} - ${arrivalFormattedTime}"


                when(model.status){
                    Status.active -> findViewById<AppCompatImageView>(R.id.ivStatusColor).background =
                        resources.getDrawable(R.drawable.green_circle_shape)
                    Status.scheduled ->  findViewById<AppCompatImageView>(R.id.ivStatusColor).background =
                        resources.getDrawable(R.drawable.yellow_circle_shape)
                    Status.cancelled ->findViewById<AppCompatImageView>(R.id.ivStatusColor).background =
                        resources.getDrawable(R.drawable.red_circle_shape)
                    null -> findViewById<AppCompatImageView>(R.id.ivStatusColor).background =
                        resources.getDrawable(R.drawable.red_circle_shape)
                }

                if (model.delay == "null") {
                    findViewById<AppCompatTextView>(R.id.tvDelay).text=resources.getString(R.string.delay)
                } else {
                    findViewById<AppCompatTextView>(R.id.tvDelay).text = model.delay
                    findViewById<AppCompatImageView>(R.id.ivStatusColor)
                }

                findViewById<CardView>(R.id.cvInfo).setOnClickListener {

                    onItemClickListener.onItemClicked(model.flightNumber!!,model.airlineIata!!)

                }


            }

        }


    }

}