package br.com.exemplo.caronasapp.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import br.com.exemplo.caronasapp.R
import br.com.exemplo.caronasapp.databinding.FragmentRegisterBinding
import br.com.exemplo.caronasapp.network.Carona
import br.com.exemplo.caronasapp.viewModel.RetrofitViewModel
import java.sql.Time

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    private val activityViewModel: RetrofitViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_register, container, false )

        val listCidades = arrayListOf<String>("Selecione", "Coronel Fabriciano", "Ipatinga", "Timóteo")
        val arrayAdapter = this.context?.let { ArrayAdapter<String>(it, android.R.layout.simple_spinner_dropdown_item, listCidades ) }
        binding.spOrigem.adapter = arrayAdapter
        binding.spDestino.adapter = arrayAdapter

        binding.btnRegistrar.setOnClickListener { view: View ->
            val origem = binding.spOrigem.selectedItemPosition
            val destino = binding.spDestino.selectedItemPosition

            //var novaCarona : Carona = Carona(null, origem, null, destino, null, 4, binding.edtTelefone.toString(), binding.edtHrSaida.toString(), binding.edtHrRetorno.toString() )
            //activityViewModel.postCarona(origem, destino, binding.edtVagas.toString().toInt(), binding.edtTelefone.toString(), Time(binding.edtHrSaida.toString().toLong()), Time(binding.edtHrRetorno.toString().toLong()))
            Toast.makeText( context, "Carona Inserida com sucesso! ", Toast.LENGTH_LONG ).show()
            view.findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())

        }

        return binding.root

    }

}