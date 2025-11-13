package com.snowcat.assignmentapp.navigation

import com.ramcosta.composedestinations.annotation.ExternalNavGraph
import com.ramcosta.composedestinations.annotation.NavHostGraph
import com.ramcosta.composedestinations.generated.scratchcard.navgraphs.ScratchNavGraph
import com.snowcat.designsystem.HybridAnimations

/**
 * Main Navigation Graph.
 */
@NavHostGraph(
    route = "main",
    defaultTransitions = HybridAnimations::class
)
annotation class MainGraph {

    /**
     * Includes.
     */
    @ExternalNavGraph<ScratchNavGraph>
    companion object Includes
}
