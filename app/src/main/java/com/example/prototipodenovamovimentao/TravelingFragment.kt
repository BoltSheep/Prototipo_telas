package com.example.prototipodenovamovimentao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.prototipodenovamovimentao.base.BaseFragment
import com.example.prototipodenovamovimentao.databinding.FragmentTravelingBinding
import com.example.prototipodenovamovimentao.models.TripViewModel

class TravelingFragment : BaseFragment() {

    private val generalViewModel: TripViewModel by activityViewModels()
//    private val state = generalViewModel.state
//    private val fragmentContext = context

    private var _binding: FragmentTravelingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTravelingBinding.inflate(inflater)
        return binding.root
    }

    override fun setupView() {
        super.setupView()

        if (generalViewModel.movementType == 1) {
            binding.tvTravelingTitle.setText(R.string.tipo_demostrativo)
            binding.clMobileItem.visibility = View.GONE
        }
        if (generalViewModel.movementType == 2) {
            binding.tvTravelingTitle.setText(R.string.tipo_demostrativo2)
            binding.clMobileItem.visibility = View.GONE
        }

        if (generalViewModel.movementType == 3) {
            binding.tvTravelingTitle.setText(R.string.tipo_demostrativo3)
        }

        binding.tvOrigem.text = generalViewModel.state.origin
        binding.tvDestino.text = generalViewModel.state.destination
        binding.tietKm.hint = generalViewModel.state.initialKM + "Km"
        binding.isbFuelLevel.setProgress(generalViewModel.state.initialGas!!.toFloat())

        binding.btnEndTrip.setOnClickListener {
            this.findNavController().navigate(R.id.action_fragmentTraveling_to_fragmentTravelFinished)
        }

    }
}