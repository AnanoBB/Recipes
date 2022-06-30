package com.example.recipes.ui.recipe_details_screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.recipes.broadcast_receiver.RecipeBroadcastReceiver
import com.example.recipes.databinding.FragmentRecipeDetailsBinding
import com.example.recipes.model.ExtendedIngredient

class RecipeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailsBinding
    private lateinit var viewModel: RecipeDetailsViewModel
    private val args: RecipeDetailsFragmentArgs by navArgs()
    private var isSaved = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(RecipeDetailsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipe = args.recipe
        viewModel.checkRecipeSaved(recipe.id)
        Glide.with(requireContext()).load(recipe.image).centerCrop().into(binding.appCompatImageView)
        binding.ingredientsInput.setText(parseIngredientsList(recipe.extendedIngredients))
        binding.nameInput.setText(recipe.title)
        binding.instructionsInput.setText(HtmlCompat.fromHtml(recipe.instructions, 0))

        binding.saveRecipeButton.setOnClickListener {
            if (!isSaved) viewModel.saveRecipe(recipe) else viewModel.removeRecipe(recipe.id)
            isSaved = !isSaved
            configureButtonText()
        }

        viewModel.recipeSaved.observe(viewLifecycleOwner){
            isSaved = it
            configureButtonText()
        }

    }

    private fun parseIngredientsList(ingredients: List<ExtendedIngredient>): String{
        var text = ""
        ingredients.forEach {
            text += "*    "
            text += it.original
            text += "\n"
        }
        return text
    }

    private fun configureButtonText(){
        binding.saveRecipeButton.text = if (isSaved) "Delete Recipe" else "Save Recipe"
    }

}