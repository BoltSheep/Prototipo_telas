package com.example.prototipodenovamovimentao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.prototipodenovamovimentao.base.BaseFragment
import com.example.prototipodenovamovimentao.base.listeners.OnButtonClickListener
import com.example.prototipodenovamovimentao.databinding.FragmentFillerBinding
import com.example.prototipodenovamovimentao.forms.FillFormEvent
import com.example.prototipodenovamovimentao.models.TripViewModel
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams

class FillerFragment : BaseFragment(), OnButtonClickListener {

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

        if (generalViewModel.movementType == 1) {
            binding.tvFillerTitle.setText(R.string.tipo_demostrativo)
            binding.clMobileItem.visibility = View.GONE
        }
        if (generalViewModel.movementType == 2) {
            binding.tvFillerTitle.setText(R.string.tipo_demostrativo2)
            binding.clMobileItem.visibility = View.GONE
        }

        if (generalViewModel.movementType == 3) {
            binding.tvFillerTitle.setText(R.string.tipo_demostrativo3)
        }

        binding.ivCancelarDestino.setOnClickListener {
            binding.tvDestino.setText("")
            generalViewModel.onEvent(FillFormEvent.DestinationChanged(""))
            generalViewModel.continueTripFlux()
            binding.btnContinue.isEnabled = false
        }

        binding.ivCancelarOrigem.setOnClickListener {
            binding.tvOrigem.setText("")
            generalViewModel.onEvent(FillFormEvent.OriginChanged(""))
            generalViewModel.continueTripFlux()
            binding.btnContinue.isEnabled = false
        }

        binding.tvOrigem.setOnFocusChangeListener { view, b ->
            if (binding.tvOrigem.text.isNullOrBlank()) {
                binding.btnContinue.isEnabled = false
            } else {
                generalViewModel.onEvent(FillFormEvent.OriginChanged(binding.tvOrigem.text.toString()))
                generalViewModel.continueTripFlux()
                binding.btnContinue.isEnabled = generalViewModel.enableButton
            }
        }

        binding.tvOrigem.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                binding.tvOrigem.clearFocus()
            }
            false
        }

        binding.tvDestino.setOnFocusChangeListener { view, b ->
            if (binding.tvDestino.text.isNullOrEmpty()) {
                binding.btnContinue.isEnabled = false
            } else {
                generalViewModel.onEvent(FillFormEvent.DestinationChanged(binding.tvDestino.text.toString()))
                generalViewModel.continueTripFlux()
                binding.btnContinue.isEnabled = generalViewModel.enableButton
            }
        }

        binding.tvDestino.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                binding.tvDestino.clearFocus()
            }
            false
        }

        binding.tietKm.setOnFocusChangeListener { view, b ->
            if (binding.tietKm.text.isNullOrEmpty()) {
                binding.btnContinue.isEnabled = false
            } else {
                generalViewModel.onEvent(FillFormEvent.KMChanged(binding.tietKm.text.toString()))
                generalViewModel.continueTripFlux()
                binding.btnContinue.isEnabled = generalViewModel.enableButton
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
                generalViewModel.onEvent(FillFormEvent.GasChanged(gasLevel))
                generalViewModel.continueTripFlux()
                binding.btnContinue.isEnabled = generalViewModel.enableButton
            }
        }


        binding.btnContinue.setOnClickListener {
            this.findNavController().navigate(R.id.action_fragmentFiller_to_fragmentTraveling)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}