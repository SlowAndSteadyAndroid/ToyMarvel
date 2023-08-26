package com.example.app_xml.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("onScrolledBottomLine")
fun RecyclerView.onScrolledBottomLine(f: Function1<Boolean, Unit>?) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            f?.invoke(!recyclerView.canScrollVertically(1))
        }
    })
}