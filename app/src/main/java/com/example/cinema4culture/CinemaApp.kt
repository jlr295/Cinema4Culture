package com.example.cinema4culture

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState

/*
*
* Displays top app bar and navigation buttons.
* Navigation page.
*
* */

// Navigation Pages
enum class CinemaAppScreen(@StringRes val title: Int){
    Start(title = R.string.cinema4culture)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinemaAppBar(
    currentScreen: CinemaAppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(currentScreen.title),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        },
        // TODO: Colors
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        modifier = modifier
    )

}

@Composable
fun Cinema4CultureApp(
    navController: NavHostController = rememberNavController(),
    viewModel: CinemaViewModel = viewModel()
){
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = CinemaAppScreen.valueOf(
        backStackEntry?.destination?.route ?: CinemaAppScreen.Start.name
    )

    Scaffold(
        topBar = {
            CinemaAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.pad_medium))
            )
        },
        // TODO: containerColor =
    ) {
        innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
    }
}