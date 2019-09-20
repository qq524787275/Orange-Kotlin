package com.zhuzichu.base.binding.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("lineManager")
fun bindViewLine(
    recyclerView: RecyclerView,
    lineManagerFactory: LineManagers.LineManagerFactory
) {
    recyclerView.addItemDecoration(lineManagerFactory.create(recyclerView))
}