package com.example.ashspokedex.data.mappers

import com.example.ashspokedex.data.dto.detail.PokemonDetailResultDto
import com.example.ashspokedex.data.dto.detail.Sprites
import com.example.ashspokedex.data.dto.detail.Stat
import com.example.ashspokedex.data.dto.detail.Type
import com.example.ashspokedex.data.dto.list.PokemonListResultDto
import com.example.ashspokedex.data.dto.list.Result
import com.example.ashspokedex.domain.models.detail.PokeStats
import com.example.ashspokedex.domain.models.detail.PokeType
import com.example.ashspokedex.domain.models.detail.PokemonDetailResult
import com.example.ashspokedex.domain.models.list.PokemonListResult
import com.example.ashspokedex.domain.models.list.PokemonNameAndId

//-----------------------------------------------------------------

fun PokemonListResultDto.toPokemonListResult():PokemonListResult{
    return PokemonListResult(mapAllNameListResults(results?: emptyList()))
}

fun Result.toPokemonName():PokemonNameAndId{
    return PokemonNameAndId(name=name?:"", pokeId = calulatePokeId(url?:""))
}

fun calulatePokeId(url:String):Int{
    if(url==""){
        return 0
    }
    val rev = url.reversed()
    var idx = 1
    var numSys = 1
    var pokeId = 0
    while(rev[idx] !='/'){
        pokeId += numSys * rev[idx].digitToInt()
        numSys*=10
        idx++
    }
    return pokeId
}
fun mapAllNameListResults(results:List<Result>):List<PokemonNameAndId>{
    return results.map {
        it.toPokemonName()
    }
}

//-------------------------------------------------------------------

fun PokemonDetailResultDto.toPokemonDetail():PokemonDetailResult{
    return PokemonDetailResult(
        name = name?:"",
        height = height?:0,
        weight = weight?:0,
        imageUrl = sprites?.front_default?:"",
        types = pokemonTypeMapper(types?: emptyList()),
        stats = pokemonStatMapper(stats?: emptyList())
    )
}

fun pokemonTypeMapper(types:List<Type>):List<PokeType>{
    return types.map {
        PokeType(typeName = it.type?.name?:"")
    }
}

fun pokemonStatMapper(stats:List<Stat>):List<PokeStats>{
    return stats.map {
        PokeStats(baseValue = it.base_stat?:0, statName = it.stat?.name?:"")
    }
}

