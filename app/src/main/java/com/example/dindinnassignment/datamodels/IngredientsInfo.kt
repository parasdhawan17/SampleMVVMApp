package com.example.dindinnassignment.datamodels


data class IngredientsInfo(val data : List<Ingredient>){
    data class Ingredient(
        val categoryId : Int,
        val name: String,
        val stock: Int,
        val imageURL: String
    )
}

