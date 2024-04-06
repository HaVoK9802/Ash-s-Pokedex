package com.example.ashspokedex.data.mappers

import androidx.compose.ui.graphics.Color
import com.example.pokdex.ui.theme.AtkColor
import com.example.pokdex.ui.theme.DefColor
import com.example.pokdex.ui.theme.HPColor
import com.example.pokdex.ui.theme.SpAtkColor
import com.example.pokdex.ui.theme.SpDefColor
import com.example.pokdex.ui.theme.SpdColor
import com.example.pokdex.ui.theme.bug
import com.example.pokdex.ui.theme.dark
import com.example.pokdex.ui.theme.dragon
import com.example.pokdex.ui.theme.electric
import com.example.pokdex.ui.theme.fairy
import com.example.pokdex.ui.theme.fighting
import com.example.pokdex.ui.theme.fire
import com.example.pokdex.ui.theme.flying
import com.example.pokdex.ui.theme.ghost
import com.example.pokdex.ui.theme.grass
import com.example.pokdex.ui.theme.ground
import com.example.pokdex.ui.theme.ice
import com.example.pokdex.ui.theme.normal
import com.example.pokdex.ui.theme.poison
import com.example.pokdex.ui.theme.psychic
import com.example.pokdex.ui.theme.rock
import com.example.pokdex.ui.theme.steel
import com.example.pokdex.ui.theme.water

fun statToColorMapper(type: String): Color {
    val color = when(type){
        "hp"-> HPColor
        "attack"-> AtkColor
        "defense"-> DefColor
        "special-attack"-> SpAtkColor
        "special-defense"-> SpDefColor
        "speed"-> SpdColor
        else -> Color.Black
    }
    return color
}

fun typeToColorMapper(type:String): Color {
    val color =  when (type) {
        "normal" -> normal
        "fire" -> fire
        "water" -> water
        "electric" -> electric
        "grass" -> grass
        "ice" -> ice
        "fighting" -> fighting
        "poison" -> poison
        "ground" -> ground
        "flying" -> flying
        "psychic" -> psychic
        "bug" -> bug
        "rock" -> rock
        "ghost" -> ghost
        "dragon" -> dragon
        "dark" -> dark
        "steel" -> steel
        "fairy" -> fairy
        else -> Color.Black
    }
    return color
}
fun statToShortStatMapping(stat: String):String{
    val shortStat = when(stat){
        "hp"->"HP"
        "attack"->"ATK"
        "defense"->"DEF"
        "special-attack"->"S-ATK"
        "special-defense"->"S-DEF"
        "speed"->"SPD"
        else -> ""
    }
    return shortStat
}