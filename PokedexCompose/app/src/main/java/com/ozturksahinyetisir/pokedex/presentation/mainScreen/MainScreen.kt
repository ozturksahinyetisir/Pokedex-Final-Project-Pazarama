package com.ozturksahinyetisir.pokedex.presentation.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ozturksahinyetisir.pokedex.R
import com.ozturksahinyetisir.pokedex.data.models.PokedexModel
import com.ozturksahinyetisir.pokedex.ui.theme.Montserrat


@Composable
fun MainScreen(navController: NavController,pViewModel: PokemonMainViewModel){
    var iconState by remember { mutableStateOf(R.drawable.text) }
    Surface(
        color = Color.Red,
        modifier = Modifier.fillMaxSize()
    ) {

        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Spacer(modifier = Modifier.width(15.dp))
                Image(
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "Pokemon",
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Pokédex",
                    color = Color.White,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Box(modifier = Modifier.weight(4f)){
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(55.dp)
                            .padding(
                                top = 10.dp,
                                bottom = 10.dp,
                                start = 40.dp
                            )
                    )
                    SearchBar(
                        hint = "Search...",
                        modifier = Modifier
                            .padding(
                                start = 10.dp
                            )
                    ) {
                        pViewModel.searchPokemonList(it)
                    }
                }
                Spacer(modifier = Modifier.width(40.dp))
                Icon(
                    painter = painterResource(id = iconState),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White, shape = CircleShape)
                        .clip(CircleShape)
                        .padding(
                            top = 8.dp,
                            bottom = 8.dp
                        )
                        .clickable {
                            //TODO
                            if (pViewModel.iconStateValue == true) {
                                pViewModel.iconStateValue = false
                                iconState = R.drawable.number
                            } else {
                                pViewModel.iconStateValue = true
                                iconState = R.drawable.text
                            }
                        }
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            PokemonList(navController,pViewModel)
        }
    }
}
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused && text.isEmpty()
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}
@Composable
fun PokemonList(
    navController: NavController,
    pViewModel: PokemonMainViewModel
) {
    val pokemonList by remember { pViewModel.pokemonList }
    val endReached by remember { pViewModel.endReached }
    val loadError by remember { pViewModel.loadError }
    val isLoading by remember { pViewModel.isLoading }
    val isSearching by remember { pViewModel.isSearching }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = if(pokemonList.size % 2 == 0) {
            pokemonList.size / 2
        } else {
            pokemonList.size / 2 + 1
        }
        items(itemCount) {
            if(it >= itemCount - 1 && !endReached && !isLoading && !isSearching) {
                LaunchedEffect(key1 = true) {
                    pViewModel.loadPokemonPaginated()
                }
            }
            PokedexRow(rowIndex = it, entries = pokemonList, navController = navController,pViewModel)
        }
    }

    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if(isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        if(loadError.isNotEmpty()) {
            RetrySection(error = loadError) {
                pViewModel.loadPokemonPaginated()
            }
        }
    }
}

@Composable
fun PokedexEntry(
    pModel: PokedexModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    pViewModel: PokemonMainViewModel
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(Color.White)
            .clickable {
                navController.navigate(
                    "info_screen/${dominantColor.toArgb()}/${pModel.pokemonName}"
                )
            }
    ) {
        Column(modifier = Modifier
            .shadow(5.dp)
            .background(Brush.verticalGradient(listOf(Color.White, Color.LightGray)))){
            Box(){
                Image(
                    painter = rememberAsyncImagePainter(pModel.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(80.dp))
                Text(
                    text = "#${pModel.number}",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp, top = 5.dp)
                )
            }
            Text(
                text = pModel.pokemonName,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun PokedexRow(
    rowIndex: Int,
    entries: List<PokedexModel>,
    navController: NavController,
    pViewModel: PokemonMainViewModel
) {

        Column (modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(15.dp)){
            Row {
                if (entries.size > rowIndex * 3) {
                    PokedexEntry(
                        pModel = entries[rowIndex * 3],
                        navController = navController,
                        modifier = Modifier.weight(1f),
                        pViewModel
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                if (entries.size > rowIndex * 3 + 1) {
                    PokedexEntry(
                        pModel = entries[rowIndex * 3 + 1],
                        navController = navController,
                        modifier = Modifier.weight(1f),
                        pViewModel
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                if (entries.size > rowIndex * 3 + 2) {
                    PokedexEntry(
                        pModel = entries[rowIndex * 3 + 2],
                        navController = navController,
                        modifier = Modifier.weight(1f),
                        pViewModel
                    )
                }
            }
        }
}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.White, fontSize = 21.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier
                .align(CenterHorizontally)
                .background(Color.Red)
        ) {
            Text(text = "Retry")
        }
    }
}


