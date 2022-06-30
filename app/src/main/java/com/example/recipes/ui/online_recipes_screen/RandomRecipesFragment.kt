package com.example.recipes.ui.online_recipes_screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.recipes.broadcast_receiver.RecipeBroadcastReceiver
import com.example.recipes.databinding.FragmentOnlineRecipesBinding
import com.example.recipes.ui.main_fragment.MainFragmentDirections
import com.example.recipes.ui.online_recipes_screen.adapter.RecipesRecyclerAdapter

class RandomRecipesFragment : Fragment() {
    private lateinit var binding: FragmentOnlineRecipesBinding
    private lateinit var viewModel: RandomRecipesViewModel
    private lateinit var recipeAdapter: RecipesRecyclerAdapter
    private var refreshCount = 0
    set(value) {
        field = value

        if(value % 5 == 0){
            configureTooMuchRefreshMessage()
        }
    }

    private fun configureTooMuchRefreshMessage() {
        val intent = Intent(RecipeBroadcastReceiver.BROADCAST_ACTION)
        intent.putExtra(RecipeBroadcastReceiver.MESSAGE_TITLE, "You refreshed too much")
        intent.putExtra(RecipeBroadcastReceiver.MESSAGE_DESCRIPTION, "We noticed that you refreshed too many times in a row, maybe it will be better to look for something specific, click this message to open online recipes ❤")
        intent.putExtra(RecipeBroadcastReceiver.ACTION_URL, "https://www.allrecipes.com")
        requireActivity().sendBroadcast(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnlineRecipesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(RandomRecipesViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeAdapter = RecipesRecyclerAdapter{
            val action = MainFragmentDirections.actionMainFragmentToRecipeDetailsFragment(it)
            findNavController().navigate(action)
        }
        binding.recipesRecycler.adapter = recipeAdapter
        binding.progressBar.isGone = false

        viewModel.recipes.observe(viewLifecycleOwner){
            recipeAdapter.setData(it)
            binding.progressBar.isGone = true
        }

        binding.reloadButton.setOnClickListener {
            binding.progressBar.isGone = false
            viewModel.getRandomRecipes()
            refreshCount++
        }
    }

}