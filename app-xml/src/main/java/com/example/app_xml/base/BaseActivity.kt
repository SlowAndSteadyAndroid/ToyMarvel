package com.example.app_xml.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding, VS : ViewState>(@LayoutRes private val layoutId: Int) :
    AppCompatActivity() {

    abstract val viewModel: BaseViewModel

    protected lateinit var binding: B


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        setContentView(binding.root)
        initUi()
        initViewModel()
    }

    abstract fun initUi()

    private fun initViewModel() {
        viewModel.viewStateLiveData.observe(this) { viewState ->
            (viewState as? VS)?.let {
                onChangedViewState(viewState)
            }
        }
    }

    abstract fun onChangedViewState(viewState: VS)

}