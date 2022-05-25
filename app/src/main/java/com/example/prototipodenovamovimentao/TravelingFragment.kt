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

        binding.apply {
            if (generalViewModel.movementType == 1) {
                tvTravelingTitle.setText(R.string.tipo_demostrativo)
                clMobileItem.visibility = View.GONE
            }
            if (generalViewModel.movementType == 2) {
                tvTravelingTitle.setText(R.string.tipo_demostrativo2)
                clMobileItem.visibility = View.GONE
            }

            if (generalViewModel.movementType == 3) {
                tvTravelingTitle.setText(R.string.tipo_demostrativo3)
            }

            tvOrigem.text = generalViewModel.state.origin
            tvDestino.text = generalViewModel.state.destination
            tietKm.hint = generalViewModel.state.initialKM + "Km"
            isbFuelLevel.setProgress(generalViewModel.state.initialGas!!.toFloat())

            btnEndTrip.setOnClickListener {
                navigate()
            }
        }
    }

    private fun navigate() {
        this.findNavController()
            .navigate(R.id.action_fragmentTraveling_to_fragmentTravelFinished)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}