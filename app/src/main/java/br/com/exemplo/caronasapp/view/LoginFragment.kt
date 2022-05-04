package br.com.exemplo.caronasapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import br.com.exemplo.caronasapp.R
import br.com.exemplo.caronasapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    //private val activityViewModel: RetrofitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate( inflater, R.layout.fragment_login, container, false )

        binding.btnLogin.setOnClickListener { view: View ->
            view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }

        return binding.root
    }

}