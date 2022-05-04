package br.com.exemplo.caronasapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import br.com.exemplo.caronasapp.R
import br.com.exemplo.caronasapp.databinding.FragmentHomeBinding
import br.com.exemplo.caronasapp.viewModel.RetrofitViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val activityViewModel: RetrofitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_home, container, false )

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            Toast.makeText( context, "Não pode retornar para tela de login", Toast.LENGTH_SHORT ).show()
        }

        val listCidades = arrayListOf<String>("Coronel Fabriciano", "Ipatinga", "Timóteo")
        val arrayAdapter = this.context?.let { ArrayAdapter<String>(it, android.R.layout.simple_spinner_dropdown_item, listCidades ) }
        binding.spinnerOrigem.adapter = arrayAdapter
        binding.spinnerDestino.adapter = arrayAdapter

        /*binding.spinnerOrigem.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected( parent: AdapterView<*>?, view: View?, position: Int, id: Long ) {
                binding.spinnerOrigem.selectedItem
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }*/

        /*
        activityViewModel.response.observe(viewLifecycleOwner, Observer { minhasCaronas ->
            //binding.txtTeste.text = minhasCaronas?.get(0)?.idcarona.toString()
        })
        */


        binding.btnPesquisar.setOnClickListener { view: View ->
            //var origem = binding.edtCidOrigem.text.toString()
            //var destino = binding.edtCidDestino.text.toString()
            var origem = binding.spinnerOrigem.selectedItemPosition.plus(1).toString()
            var destino = binding.spinnerDestino.selectedItemPosition.plus(1).toString()
            activityViewModel.setCidades(origem, destino)
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFindFragment(origem, destino))
        }

        binding.btnCadastrar.setOnClickListener { view: View ->
            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRegisterFragment())
        }


        return binding.root
    }

}