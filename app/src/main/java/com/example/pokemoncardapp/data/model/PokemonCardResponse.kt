package com.example.pokemoncardapp.data.model

data class PokemonCardResponse(
    val data: List<PokemonCard>,
    val page: Int,
    val pageSize: Int,
    val count: Int,
    val totalCount: Int
)

data class PokemonCard(
    val id: String,
    val name: String,
    val supertype: String,
    val subtypes: List<String>,
    val level: String?,
    val hp: String,
    val types: List<String>,
    val evolvesFrom: String?,
    val abilities: List<Ability>?,
    val attacks: List<Attack>?,
    val weaknesses: List<Weakness>?,
    val resistances: List<Resistance>?,
    val retreatCost: List<String>?,
    val convertedRetreatCost: Int,
    val set: CardSet,
    val number: String,
    val artist: String,
    val rarity: String?,
    val flavorText: String?,
    val nationalPokedexNumbers: List<Int>,
    val legalities: Legalities,
    val images: CardImages,
    val tcgplayer: TcgPlayer?,
    val cardmarket: CardMarket?
)

data class Ability(
    val name: String,
    val text: String,
    val type: String
)

data class Attack(
    val name: String,
    val cost: List<String>,
    val convertedEnergyCost: Int,
    val damage: String?,
    val text: String?
)

data class Weakness(
    val type: String,
    val value: String
)

data class Resistance(
    val type: String,
    val value: String
)

data class CardSet(
    val id: String,
    val name: String,
    val series: String,
    val printedTotal: Int,
    val total: Int,
    val legalities: Legalities,
    val ptcgoCode: String?,
    val releaseDate: String,
    val updatedAt: String,
    val images: SetImages
)

data class Legalities(
    val unlimited: String
)

data class CardImages(
    val small: String,
    val large: String
)

data class SetImages(
    val symbol: String,
    val logo: String
)

data class TcgPlayer(
    val url: String,
    val updatedAt: String,
    val prices: TcgPlayerPrices
)

data class TcgPlayerPrices(
    val holofoil: Holofoil?,
    val reverseHolofoil: Holofoil?
)

data class Holofoil(
    val low: Double?,
    val mid: Double?,
    val high: Double?,
    val market: Double?,
    val directLow: Double?
)

data class CardMarket(
    val url: String,
    val updatedAt: String,
    val prices: CardMarketPrices
)

data class CardMarketPrices(
    val averageSellPrice: Double?,
    val lowPrice: Double?,
    val trendPrice: Double?,
    val germanProLow: Double?,
    val suggestedPrice: Double?,
    val reverseHoloSell: Double?,
    val reverseHoloLow: Double?,
    val reverseHoloTrend: Double?,
    val lowPriceExPlus: Double?,
    val avg1: Double?,
    val avg7: Double?,
    val avg30: Double?,
    val reverseHoloAvg1: Double?,
    val reverseHoloAvg7: Double?,
    val reverseHoloAvg30: Double?
)

