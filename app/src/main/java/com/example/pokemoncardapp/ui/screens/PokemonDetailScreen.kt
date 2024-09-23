package com.example.pokemoncardapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.pokemoncardapp.R
import com.example.pokemoncardapp.data.local.PokemonEntity
import com.example.pokemoncardapp.data.model.PokemonCard
import com.example.pokemoncardapp.utils.DataManager


@Composable
fun PokemonDetailScreen(card: PokemonEntity) {
    BackHandler() {
        DataManager.switchPages(null)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(1f)
            .background(
                Brush.sweepGradient(
                    colors = listOf(
                        Color(0XFFffffff),
                        Color(0XFFE3E3E3)

                    )
                )
            )
    ) {

        Card(
          //  elevation = 4.dp,
            modifier = Modifier.padding(32.dp)
                .wrapContentWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                 //   .align(Alignment.Center)
                    .padding(16.dp, 24.dp)
            ) {


                AsyncImage(
                    model = card.largeImg,
                    contentDescription = "Pokemon Card Image",
                    modifier = Modifier
                        .size(180.dp)
                        .rotate(180F),
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    error = painterResource(R.drawable.ic_launcher_foreground)
                )


                Text(
                    text = "Name : ${card.name}",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Types : ${card.types}",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(16.dp))

                card.level?.let {
                    Text(
                        text = "Level : ${it}",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Hp : ${card.hp}",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(16.dp))

                card.attack?.let {
                    Text(
                        text = "Attacks : ${it}",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                card.weakness?.let {
                    Text(
                        text = "Weaknesses : ${it}",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                card.abilities?.let {
                    Text(
                        text = "Abilities : ${it}",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                card.resistance?.let {
                    Text(
                        text = "Resistances : ${it}",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

        }

    }
}

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