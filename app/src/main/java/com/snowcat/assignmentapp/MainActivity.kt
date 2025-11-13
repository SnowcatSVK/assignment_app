package com.snowcat.assignmentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.app.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.app.navgraphs.MainNavGraph
import com.ramcosta.composedestinations.generated.scratchcard.destinations.ScratchCardScreenDestination
import com.ramcosta.composedestinations.generated.scratchcard.destinations.ValidateCardScreenDestination
import com.ramcosta.composedestinations.spec.TypedDestinationSpec
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.snowcat.designsystem.AssignmentAppTheme
import com.snowcat.designsystem.Spacing
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity hosting the app's navigation.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentScreen by navController.currentDestinationAsState()
            AssignmentAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(132.dp)
                                .padding(Spacing.grid6),
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.BottomCenter),
                                text = currentScreen?.getTitle().orEmpty(),
                                style = MaterialTheme.typography.headlineLarge
                            )
                            AnimatedVisibility(
                                modifier = Modifier.align(Alignment.BottomStart),
                                visible = currentScreen?.canNavigateBack() ?: false,
                                enter = fadeIn(),
                                exit = fadeOut()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .clickable {
                                            navController.popBackStack()
                                        }
                                        .padding(Spacing.grid1)
                                ) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.ic_back_24),
                                        tint = MaterialTheme.colorScheme.primary,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    DestinationsNavHost(
                        navGraph = MainNavGraph,
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun TypedDestinationSpec<*>.getTitle(): String {
    return when (this) {
        is HomeScreenDestination -> stringResource(R.string.home_screen_title)
        is ScratchCardScreenDestination -> stringResource(R.string.scratch_screen_title)
        is ValidateCardScreenDestination -> stringResource(R.string.validate_screen_title)
        else -> ""
    }
}

@Composable
private fun TypedDestinationSpec<*>.canNavigateBack(): Boolean {
    return when (this) {
        is HomeScreenDestination -> false
        is ScratchCardScreenDestination, is ValidateCardScreenDestination -> true
        else -> false
    }
}
