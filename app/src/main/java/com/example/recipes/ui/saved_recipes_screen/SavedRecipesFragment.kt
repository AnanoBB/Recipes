package com.example.recipes.ui.saved_recipes_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recipes.databinding.FragmentSavedRecipesBinding
import com.example.recipes.ui.main_fragment.MainFragmentDirections
import com.example.recipes.ui.online_recipes_screen.adapter.RecipesRecyclerAdapter

class SavedRecipesFragment : Fragment() {
    private lateinit var binding: FragmentSavedRecipesBinding
    private lateinit var viewModel: SavedRecipesViewModel
    private lateinit var recipeAdapter: RecipesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedRecipesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
            SavedRecipesViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getSavedRecipes()
        recipeAdapter = RecipesRecyclerAdapter {
            val action = MainFragmentDirections.actionMainFragmentToRecipeDetailsFragment(it)
            findNavController().navigate(action)
        }
        binding.recipesRecycler.adapter = recipeAdapter

        viewModel.recipes.observe(viewLifecycleOwner){
            recipeAdapter.setData(it)
        }
    }
}