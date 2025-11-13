package com.snowcat.assignmentapp.navigation

import com.ramcosta.composedestinations.annotation.NavGraph

/**
 * Home Navigation Graph.
 */
@NavGraph<MainGraph>(
    route = "home",
    start = true
)
annotation class HomeGraph
