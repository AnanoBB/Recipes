package com.example.recipes.ui.main_fragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPagerAdapter(
    private val fragments: List<Fragment>,
    manager: FragmentManager,
    lifecycle: Lifecycle)
    : FragmentStateAdapter(manager, lifecycle) {

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]

}