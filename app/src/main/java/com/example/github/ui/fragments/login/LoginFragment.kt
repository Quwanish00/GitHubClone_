package com.example.github.ui.fragments.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chatappwithfirebase.utils.toast
import com.example.github.R
import com.example.github.data.local.LocalStorage
import com.example.github.databinding.FragmentMainBinding
import com.example.github.persentation.UserViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModel<UserViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

      

        initObservers()
        initListeners()
    }
    private fun initListeners() {
        binding.apply {


            signIn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/login/oauth/authorize?client_id=8f3cf5f09bd0c93a0528&scope=repo"))
            startActivity(intent)
        }


        }
    }

    override fun onResume() {
        super.onResume()

        val uri:Uri? =requireActivity().intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            if (code != null) {

                lifecycleScope.launchWhenResumed {

                    viewModel.getAccessToken(code)


                }


            } else if ((uri.getQueryParameter("error")) != null) {
                toast("Something went wrong!")
            }

        }

    }

    private fun initObservers() {
        viewModel.getAccessTokenFlow.onEach {
            LocalStorage().isReg = true
            LocalStorage().token = it.access_token
                findNavController().navigate(LoginFragmentDirections.actionMainFragmentToHomeContainer())
            Log.d("TTTT", LocalStorage().token)


        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            toast("Token kelmedi!")
        }
    }

}