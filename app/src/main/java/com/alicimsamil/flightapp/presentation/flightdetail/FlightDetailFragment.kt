package com.alicimsamil.flightapp.presentation.flightdetail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.alicimsamil.flightapp.databinding.FragmentFlightDetailScreenBinding
import com.alicimsamil.flightapp.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@AndroidEntryPoint
class FlightDetailFragment : Fragment() {

    lateinit var binding: FragmentFlightDetailScreenBinding
    val viewModel: FlightDetailViewModel by viewModels()
    val args: FlightDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity).window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightDetailScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        initDetail()


    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun initDetail() {

        viewModel.mutableFlightDetailData.observe(viewLifecycleOwner, Observer {

            binding.tvFlightDetail.text = "${it.flightCode} Flight Details"
            binding.tvDepartureAirportAbb.text = it.departureAirportAbb
            binding.tvDepartureAirport.text = it.departureAirportName
            binding.tvArrivalAirportAbb.text = it.arrivalAirportAbb
            binding.tvArrivalAirport.text = it.arrivalAirportName
            binding.tvDepartureTime.text = ZonedDateTime.parse(it.departureDate).toLocalDateTime()
                .format(
                    DateTimeFormatter.ofLocalizedDateTime(
                        FormatStyle.MEDIUM,
                        FormatStyle.SHORT
                    )
                )
            binding.tvArrivalTime.text = ZonedDateTime.parse(it.arrivalDate).toLocalDateTime()
                .format(
                    DateTimeFormatter.ofLocalizedDateTime(
                        FormatStyle.MEDIUM,
                        FormatStyle.SHORT
                    )
                )
            binding.tvFlightName.text = it.flightCode
            binding.tvGateName.text = if (it.gateName.isNullOrEmpty()) "-" else it.gateName
            binding.tvTerminalName.text = if (it.terminal.isNullOrEmpty()) "-" else it.terminal
            binding.tvDelayDuration.text = if (it.delay.isNullOrEmpty()) "-" else it.delay

        })

        binding.ivClose.setOnClickListener {

            val action = FlightDetailFragmentDirections.actionFlightDetailFragmentToMainScreenFragment()
            Navigation.findNavController(it).navigate(action)

        }


    }


    fun observeLiveData() {

        viewModel.fetchFlightData(args.flightNumber, args.airlineIata)


        viewModel.mutableProgressData.observe(viewLifecycleOwner, Observer {

            if(it){
                binding.pbStatusDetail.visibility=View.VISIBLE
            } else {
                binding.pbStatusDetail.visibility=View.GONE
            }

        })

    }


}