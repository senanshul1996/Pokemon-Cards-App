package com.example.pokemoncardapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val smallImg: String,
    val largeImg: String,
    val types: String,
    val subType: String,
    val level : String,
    val hp: String,
    val attack: String,
    val weakness: String,
    val abilities : String,
    val resistance : String,
)


//Screen 2:
//Here you should display following details:
//1. Image
//2. Name
//3. Types
//4. Sub Types
//5. Level
//6. Hp
//7. Attacks
//8. Weakness
//9. Abilities
//10. Resistances
