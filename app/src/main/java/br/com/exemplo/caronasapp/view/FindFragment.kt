package br.com.exemplo.caronasapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.exemplo.caronasapp.R
import br.com.exemplo.caronasapp.adapter.MyAdapter
import br.com.exemplo.caronasapp.databinding.FragmentFindBinding
import br.com.exemplo.caronasapp.viewModel.RetrofitViewModel

class FindFragment : Fragment() {

    lateinit var binding: FragmentFindBinding
    private val activityViewModel: RetrofitViewModel by activityViewModels()
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_find, container, false )

        setupRecyclerView()

        /*val args = FindFragmentArgs.fromBundle(requireArguments())
        binding.txtOrigem.text = "Origem: ${args.origem} - Destino: ${args.destino}"
        activityViewModel.response.observe(viewLifecycleOwner, Observer { minhasCaronas ->
            binding.txtRetorno.text = minhasCaronas.toString()
        })*/

        activityViewModel.response.observe(viewLifecycleOwner, Observer { myResponse ->
            myResponse?.let { myAdapter.setData(it.toList()) }
        })

        binding.btnRetorno.setOnClickListener { view: View ->
            view.findNavController().navigate(FindFragmentDirections.actionFindFragmentToHomeFragment())
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

}