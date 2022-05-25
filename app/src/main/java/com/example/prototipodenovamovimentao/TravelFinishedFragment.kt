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
        
        binding.apply {
            if (generalViewModel.movementType == 1) {
                clQuemRecebeu.visibility = View.INVISIBLE
                generalViewModel.onEvent(FillFormEvent.WhoReceived("Não Aplicavel"))
                generalViewModel.endTripFlux()
            }

            tvKmInicial.text = generalViewModel.state.initialKM + "Km"
            tvCombustivelInicial.text = generalViewModel.state.initialGas.toString() + "/8"

            radioGroup.setOnCheckedChangeListener { radioGroup, i ->

                if (alright.isChecked) {
                    clWhatHappened.visibility = View.GONE
                    clDescricOdoOcorrido.visibility = View.GONE
                    if (generalViewModel.movementType != 1)
                    clQuemRecebeu.visibility = View.VISIBLE
                }

                if (somethingWrong.isChecked) {
                    clWhatHappened.visibility = View.VISIBLE
                    clDescricOdoOcorrido.visibility = View.VISIBLE
                }

            }

            tietKm.setOnFocusChangeListener { view, b ->
                if (tietKm.text.isNullOrEmpty()) {
                    btnEndTrip.isEnabled = false
                } else {
                    generalViewModel.onEvent(
                        FillFormEvent.FinalKMChanged(
                            tietKm.text.toString(),
                            generalViewModel.state.initialKM
                        )
                    )
                    generalViewModel.endTripFlux()
                    btnEndTrip.isEnabled = generalViewModel.enableEndButton
                }
            }

            tietKm.setOnEditorActionListener { textView, i, keyEvent ->
                if (i == EditorInfo.IME_ACTION_DONE) {
                    tietKm.clearFocus()
                }
                false
            }

            isbFuelLevel.onSeekChangeListener = object : OnSeekChangeListener {
                override fun onSeeking(seekParams: SeekParams) {
                    gasLevel = seekParams.progress
                }


                override fun onStartTrackingTouch(seekBar: IndicatorSeekBar) {
                    tietKm.clearFocus()
                }

                override fun onStopTrackingTouch(seekBar: IndicatorSeekBar) {
                    generalViewModel.onEvent(FillFormEvent.FinalGasChanged(gasLevel))
                    generalViewModel.endTripFlux()
                    btnEndTrip.isEnabled = generalViewModel.enableEndButton
                }
            }

            tietQuemRecebeu.setOnFocusChangeListener { view, b ->
                if (tietQuemRecebeu.text.isNullOrEmpty()) {
                    btnEndTrip.isEnabled = false
                } else {
                    generalViewModel.onEvent(FillFormEvent.WhoReceived(tietQuemRecebeu.text.toString()))
                    generalViewModel.endTripFlux()
                    btnEndTrip.isEnabled = generalViewModel.enableEndButton
                }
            }

            tietQuemRecebeu.setOnEditorActionListener { textView, i, keyEvent ->
                if (i == EditorInfo.IME_ACTION_DONE) {
                    tietQuemRecebeu.clearFocus()
                }
                false
            }

            btnEndTrip.setOnClickListener {
                generalViewModel.clearState()
                generalViewModel.isTravelFinished = true
                navigate()
                Log.d("miojo", "Obtido: \n ${generalViewModel.state}")
            }
        }
    }

    fun navigate() {
        this.findNavController()
            .navigate(R.id.action_finishedTravelFragment_to_movementFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
