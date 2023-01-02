package com.example.feature_marvel.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core_model.marvel.model.CharacterItem
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun MarvelScreen(item: CharacterItem, onItemClick: (CharacterItem) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(20.dp)
            .clickable {

            },
        horizontalArrangement = Arrangement.Start
    ) {
        CoilImage(
            modifier = Modifier
                .fillMaxHeight()
                .weight(3f),
            imageModel = item.image,
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colors.background,
                highlightColor = Color.Black,
                durationMillis = 1000,
                dropOff = 0.65f,
                tilt = 20f
            ),
            contentScale = ContentScale.Crop,
            failure = {
                Text(text = "image request failed.")
            })
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .weight(6.5f)
                .padding(start = 12.dp, end = 4.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.name,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .height(1.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Urls : ${item.urls}")
                Text(text = "Comics : ${item.comics}")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Series : ${item.series}")
                Text(text = "Events : ${item.events}")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Stories : ${item.stories}")
            }

        }
    }


}