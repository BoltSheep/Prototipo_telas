package com.example.prototipodenovamovimentao

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.prototipodenovamovimentao.base.BaseFragment
import com.example.prototipodenovamovimentao.databinding.MovimentacoesBinding
import com.example.prototipodenovamovimentao.models.TripViewModel


class MovementFragment : BaseFragment() {

    private val generalViewModel: TripViewModel by activityViewModels()

    private var _binding: MovimentacoesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovimentacoesBinding.inflate(inflater)
        return binding.root
    }

    override fun setupView() {
        super.setupView()

        if (generalViewModel.isTravelFinished) {
            generalViewModel.isTravelFinished = false
            Toast.makeText(requireContext(), "Movimentação realizada", Toast.LENGTH_SHORT).show()
        }

        binding.clRetirada.setOnClickListener {
            generalViewModel.movementType = 1
            Log.d("miojo", "Retirada: ${generalViewModel.movementType}")
            this.findNavController().navigate(R.id.action_movementFragment_to_fragment_filler)

        }

        binding.clEntrega.setOnClickListener {
            generalViewModel.movementType = 2
            Log.d("miojo", "Entrega ${generalViewModel.movementType}")
            this.findNavController().navigate(R.id.action_movementFragment_to_fragment_filler)
        }

        binding.clEntregaCliente.setOnClickListener {
            generalViewModel.movementType = 3
            Log.d("miojo", "Entrega ${generalViewModel.movementType}")
            this.findNavController().navigate(R.id.action_movementFragment_to_fragment_filler)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
