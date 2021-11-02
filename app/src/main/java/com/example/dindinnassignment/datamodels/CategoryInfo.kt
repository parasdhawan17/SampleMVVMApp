package com.example.dindinnassignment.datamodels

data class CategoryInfo(val data: ArrayList<Category>){
    data class Category(val id : Int, val title : String)
}