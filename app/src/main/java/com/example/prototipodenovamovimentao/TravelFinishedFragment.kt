package com.example.prototipodenovamovimentao

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.prototipodenovamovimentao.base.BaseFragment
import com.example.prototipodenovamovimentao.databinding.FragmentTravelFinishedBinding
import com.example.prototipodenovamovimentao.forms.FillFormEvent
import com.example.prototipodenovamovimentao.models.TripViewModel
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams

class TravelFinishedFragment : BaseFragment() {
    private val generalViewModel: TripViewModel by activityViewModels()


    private var _binding: FragmentTravelFinishedBinding? = null
    private val binding get() = _binding!!
    private var gasLevel: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTravelFinishedBinding.inflate(inflater)
        return binding.root
    }

    override fun setupView() {
        super.setupView()

        if (generalViewModel.movementType == 1) {
            binding.clQuemRecebeu.visibility = View.INVISIBLE
            generalViewModel.onEvent(FillFormEvent.WhoReceived("Não Aplicavel"))
            generalViewModel.endTripFlux()
        }

        binding.tvKmInicial.text = generalViewModel.state.initialKM + "Km"
        binding.tvCombustivelInicial.text = generalViewModel.state.initialGas.toString() + "/8"

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->

            if (binding.alright.isChecked) {
                binding.clWhatHappened.visibility = View.GONE
                binding.clDescricOdoOcorrido.visibility = View.GONE
                if (generalViewModel.movementType != 1)
                binding.clQuemRecebeu.visibility = View.VISIBLE
            }

            if (binding.somethingWrong.isChecked) {
                binding.clWhatHappened.visibility = View.VISIBLE
                binding.clDescricOdoOcorrido.visibility = View.VISIBLE
            }

        }

        binding.tietKm.setOnFocusChangeListener { view, b ->
            if (binding.tietKm.text.isNullOrEmpty()) {
                binding.btnEndTrip.isEnabled = false
            } else {
                generalViewModel.onEvent(
                    FillFormEvent.FinalKMChanged(
                        binding.tietKm.text.toString(),
                        generalViewModel.state.initialKM
                    )
                )
                generalViewModel.endTripFlux()
                binding.btnEndTrip.isEnabled = generalViewModel.enableEndButton
            }
        }

        binding.tietKm.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                binding.tietKm.clearFocus()
            }
            false
        }

        binding.isbFuelLevel.onSeekChangeListener = object : OnSeekChangeListener {
            override fun onSeeking(seekParams: SeekParams) {
                gasLevel = seekParams.progress
            }


            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar) {
                binding.tietKm.clearFocus()
            }

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar) {
                generalViewModel.onEvent(FillFormEvent.FinalGasChanged(gasLevel))
                generalViewModel.endTripFlux()
                binding.btnEndTrip.isEnabled = generalViewModel.enableEndButton
            }
        }

        binding.tietQuemRecebeu.setOnFocusChangeListener { view, b ->
            if (binding.tietQuemRecebeu.text.isNullOrEmpty()) {
                binding.btnEndTrip.isEnabled = false
            } else {
                generalViewModel.onEvent(FillFormEvent.WhoReceived(binding.tietQuemRecebeu.text.toString()))
                generalViewModel.endTripFlux()
                binding.btnEndTrip.isEnabled = generalViewModel.enableEndButton
            }
        }

        binding.tietQuemRecebeu.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                binding.tietQuemRecebeu.clearFocus()
            }
            false
        }

        binding.btnEndTrip.setOnClickListener {
            generalViewModel.clearState()
            generalViewModel.isTravelFinished = true
            this.findNavController()
                .navigate(R.id.action_finishedTravelFragment_to_movementFragment)
            Log.d("miojo", "Obtido: \n ${generalViewModel.state}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
