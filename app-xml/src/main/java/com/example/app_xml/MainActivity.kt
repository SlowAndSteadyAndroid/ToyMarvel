package com.example.app_xml

import androidx.activity.viewModels
import com.example.app_xml.base.BaseActivity
import com.example.app_xml.databinding.ActivityMainBinding
import com.example.app_xml.ext.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewState>(R.layout.activity_main) {
    private val marvelAdapter = MarvelCharacterAdapter()

    override val viewModel: MainViewModel by viewModels()
    override fun initUi() {
        with(binding) {
            binding.viewModel = this@MainActivity.viewModel
            rvCharacters.adapter = marvelAdapter
        }
    }

    override fun onChangedViewState(viewState: MainViewState) {
        when (viewState) {
            is MainViewState.GetData -> {
                marvelAdapter.addAll(viewState.list)
            }

            is MainViewState.ShowToast -> {
                showToast(message = viewState.message)
            }

            is MainViewState.Refresh -> {
                marvelAdapter.clear()
                marvelAdapter.addAll(viewState.list)
                binding.srLayout.isRefreshing = false
            }

            is MainViewState.ShowLoading -> {
                binding.progressBar.visibility = viewState.flag

            }
        }
    }


}
