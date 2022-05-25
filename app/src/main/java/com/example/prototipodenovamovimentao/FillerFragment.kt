package com.example.prototipodenovamovimentao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.prototipodenovamovimentao.base.BaseFragment
import com.example.prototipodenovamovimentao.databinding.FragmentFillerBinding
import com.example.prototipodenovamovimentao.forms.FillFormEvent
import com.example.prototipodenovamovimentao.models.TripViewModel
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams

class FillerFragment : BaseFragment() {

    private val generalViewModel: TripViewModel by activityViewModels()


    private var _binding: FragmentFillerBinding? = null
    private val binding get() = _binding!!
    private var gasLevel: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFillerBinding.inflate(inflater)
        return binding.root
    }

    override fun setupView() {
        super.setupView()

        binding.apply {
            if (generalViewModel.movementType == 1) {
                tvFillerTitle.setText(R.string.tipo_demostrativo)
                clMobileItem.visibility = View.GONE
            }
            if (generalViewModel.movementType == 2) {
                tvFillerTitle.setText(R.string.tipo_demostrativo2)
                clMobileItem.visibility = View.GONE
            }

            if (generalViewModel.movementType == 3) {
                tvFillerTitle.setText(R.string.tipo_demostrativo3)
            }

            ivCancelarDestino.setOnClickListener {
                tvDestino.setText("")
                generalViewModel.onEvent(FillFormEvent.DestinationChanged(""))
                generalViewModel.continueTripFlux()
                btnContinue.isEnabled = false
            }

            ivCancelarOrigem.setOnClickListener {
                tvOrigem.setText("")
                generalViewModel.onEvent(FillFormEvent.OriginChanged(""))
                generalViewModel.continueTripFlux()
                btnContinue.isEnabled = false
            }

            tvOrigem.setOnFocusChangeListener { view, b ->
                if (tvOrigem.text.isNullOrBlank()) {
                    btnContinue.isEnabled = false
                } else {
                    generalViewModel.onEvent(FillFormEvent.OriginChanged(tvOrigem.text.toString()))
                    generalViewModel.continueTripFlux()
                    btnContinue.isEnabled = generalViewModel.enableButton
                }
            }

            tvOrigem.setOnEditorActionListener { textView, i, keyEvent ->
                if (i == EditorInfo.IME_ACTION_DONE) {
                    tvOrigem.clearFocus()
                }
                false
            }

            tvDestino.setOnFocusChangeListener { view, b ->
                if (tvDestino.text.isNullOrEmpty()) {
                    btnContinue.isEnabled = false
                } else {
                    generalViewModel.onEvent(FillFormEvent.DestinationChanged(tvDestino.text.toString()))
                    generalViewModel.continueTripFlux()
                    btnContinue.isEnabled = generalViewModel.enableButton
                }
            }

            tvDestino.setOnEditorActionListener { textView, i, keyEvent ->
                if (i == EditorInfo.IME_ACTION_DONE) {
                    tvDestino.clearFocus()
                }
                false
            }

            tietKm.setOnFocusChangeListener { view, b ->
                if (tietKm.text.isNullOrEmpty()) {
                    btnContinue.isEnabled = false
                } else {
                    generalViewModel.onEvent(FillFormEvent.KMChanged(tietKm.text.toString()))
                    generalViewModel.continueTripFlux()
                    btnContinue.isEnabled = generalViewModel.enableButton
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
                    generalViewModel.onEvent(FillFormEvent.GasChanged(gasLevel))
                    generalViewModel.continueTripFlux()
                    btnContinue.isEnabled = generalViewModel.enableButton
                }
            }


            btnContinue.setOnClickListener {
                navigate()
            }
        }
    }

    private fun navigate() {
        this.findNavController().navigate(R.id.action_fragmentFiller_to_fragmentTraveling)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}