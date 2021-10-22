package com.akin.hepsiburada.data

data class FoodsModel(
    var name: String="", var price:Double=0.1, var image:String="", var category:String="",
    var ingredients: List<String> = arrayListOf(), var calory:String="", var id:String="")
