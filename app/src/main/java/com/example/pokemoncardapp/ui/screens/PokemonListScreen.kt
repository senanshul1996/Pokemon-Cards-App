package com.example.pokemoncardapp.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.pokemoncardapp.R
import com.example.pokemoncardapp.data.local.PokemonEntity
import com.example.pokemoncardapp.data.model.PokemonCard


@Composable
fun PokemonListScreen(data: List<PokemonEntity>, onClick: (PokemonEntity) -> Unit) {

    var searchQuery by remember { mutableStateOf("") }
    var sortOption by remember { mutableStateOf(SortOption.NONE) }


    val filteredData = data.filter { it.name.contains(searchQuery, ignoreCase = true) }
    val sortedData = when (sortOption) {
        SortOption.LEVEL -> filteredData.sortedBy { it.level?.toIntOrNull() ?: 0 }
        SortOption.HP -> filteredData.sortedBy { it.hp.toIntOrNull() ?: 0 }
        else -> filteredData
    }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
    ) {

        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Pokémon") },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            shape = MaterialTheme.shapes.medium
        )
        Spacer(modifier = Modifier.height(8.dp))


        SortOptions(sortOption = sortOption) { selectedOption ->
            sortOption = selectedOption
        }
        Spacer(modifier = Modifier.height(8.dp))


        PokemonList(data = sortedData, onClick = onClick)
    }
}
@Composable
fun SortOptions(sortOption: SortOption, onOptionSelected: (SortOption) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SortButton(
            label = "Sort by Level",
            isSelected = sortOption == SortOption.LEVEL,
            onClick = { onOptionSelected(SortOption.LEVEL) }
        )
        SortButton(
            label = "Sort by HP",
            isSelected = sortOption == SortOption.HP,
            onClick = { onOptionSelected(SortOption.HP) }
        )
        SortButton(
            label = "Reset",
            isSelected = sortOption == SortOption.NONE,
            onClick = { onOptionSelected(SortOption.NONE) }
        )
    }
}

@Composable
fun SortButton(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text = label)
    }
}

@Composable
fun PokemonList(data: List<PokemonEntity>, onClick: (PokemonEntity) -> Unit) {

    LazyColumn(content = {
        items(data){
            QuoteListItem( card = it,onClick)
        }
    })


}

@Composable
fun QuoteListItem(card: PokemonEntity, onClick: (card : PokemonEntity) -> Unit) {
    Card(
      //  elevation = 4.dp,
        modifier = Modifier
            .clickable { onClick(card) }
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {

            AsyncImage(
                model = card.smallImg,
                contentDescription = "Pokemon Card Image",
                alignment = Alignment.CenterStart,
                modifier = Modifier
                    .size(40.dp)
                    .rotate(180F),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_foreground)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Name : ${card.name}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp
                    ),
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                )
                Box(
                    modifier = Modifier
                        .background(Color(0xFFEEEEEE))
                        .fillMaxWidth(.4f)
                        .height(1.dp)
                )


                Text(
                    text = "Types : ${card.types}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier.padding(top = 4.dp)
                )

                card.level?.let {
                    Text(
                        text = "Level : ${it}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Thin,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Text(
                    text = "HP : ${card.hp}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier.padding(top = 4.dp)
                )


            }

        }

    }
}

enum class SortOption {
    NONE,
    LEVEL,
    HP
}