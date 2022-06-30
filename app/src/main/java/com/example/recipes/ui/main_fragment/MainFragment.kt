package com.example.recipes.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recipes.databinding.FragmentMainBinding
import com.example.recipes.ui.main_fragment.adapter.MainViewPagerAdapter
import com.example.recipes.ui.online_recipes_screen.RandomRecipesFragment
import com.example.recipes.ui.saved_recipes_screen.SavedRecipesFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragments = listOf(RandomRecipesFragment(), SavedRecipesFragment())
        val adapter = MainViewPagerAdapter(fragments, childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = when(position){
                0 -> "Random Recipes"
                1 -> "Saved Recipes"
                else -> null
            }
        }.attach()
    }
}