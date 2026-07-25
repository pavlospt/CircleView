package com.github.pavlospt.circleview.sample

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun sampleApp_launchesAndDisplaysTopAppBar() {
        composeTestRule.onNodeWithText("CircleView").assertIsDisplayed()
        composeTestRule.onNodeWithText("Compose 2.0").assertIsDisplayed()
    }

    @Test
    fun sampleApp_switchesBetweenTabs() {
        // Initially in Playground tab
        composeTestRule.onNodeWithText("Customization Controls").assertIsDisplayed()

        // Switch to Showcase Gallery tab
        composeTestRule.onNodeWithText("Showcase Gallery").performClick()

        // Verify Showcase Gallery content displays
        composeTestRule.onNodeWithText("Fitness Dashboard Widget").assertIsDisplayed()
    }
}
