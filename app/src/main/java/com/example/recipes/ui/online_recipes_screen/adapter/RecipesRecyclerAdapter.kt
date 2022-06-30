package com.example.recipes.ui.online_recipes_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipes.databinding.RecipeRecyclerItemBinding
import com.example.recipes.model.Recipe

class RecipesRecyclerAdapter(private val onRecipeClick: (Recipe) -> Unit):
    RecyclerView.Adapter<RecipesRecyclerAdapter.ViewHolder>() {
    private var data: List<Recipe> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecipeRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position], onRecipeClick)
    }

    override fun getItemCount() = data.size

    fun setData(data: List<Recipe>){
        this.data = data
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: RecipeRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){

        fun onBind(item: Recipe, onRecipeClick: (Recipe) -> Unit){
            with(binding){
                Glide.with(root.context).load(item.image).centerCrop().into(appCompatImageView)
                titleTextView.text = item.title
                root.setOnClickListener {
                    onRecipeClick(item)
                }
            }

        }

    }
}