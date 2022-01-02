package com.alicimsamil.flightapp.presentation.flightlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.alicimsamil.flightapp.R
import com.alicimsamil.flightapp.common.Status
import com.alicimsamil.flightapp.databinding.FragmentListScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightListScreenFragment : Fragment() {
    private val viewModel : FlightListViewModel by viewModels()
    private lateinit var adapter: FlightListAdapter
    lateinit var binding: FragmentListScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentListScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFlightList()
    }



    fun observeFlightList(){
        viewModel.fetchAllFlights()

        viewModel.mutableProgressData.observe(viewLifecycleOwner, Observer {

            if(it){
                binding.pbStatusList.visibility=View.VISIBLE
            } else {
                binding.pbStatusList.visibility=View.GONE
            }

        })

        viewModel.mutableFlightData.observe(viewLifecycleOwner, Observer {

            adapter=FlightListAdapter(it,object : OnItemClickListener{
                override fun onItemClicked(flightNumber: Int, airlineIata: String) {
                    val action=FlightListScreenFragmentDirections.actionMainScreenFragmentToFlightDetailFragment(flightNumber,airlineIata)
                    view?.let { view -> Navigation.findNavController(view).navigate(action) }
                }
            })
            binding.rvFlightList.adapter=adapter

        })


    }


}