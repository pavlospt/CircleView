package io.github.pavlospt.circleview

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CircleViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun circleView_rendersTitleAndSubtitle() {
        composeTestRule.setContent {
            CircleView(
                modifier = Modifier.size(200.dp),
                titleText = "Test Title",
                subtitleText = "Test Subtitle"
            )
        }

        composeTestRule.onNodeWithText("Test Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test Subtitle").assertIsDisplayed()
    }

    @Test
    fun circleView_hidesTitleWhenShowTitleIsFalse() {
        composeTestRule.setContent {
            CircleView(
                modifier = Modifier.size(200.dp),
                titleText = "Hidden Title",
                subtitleText = "Visible Subtitle",
                showTitle = false,
                showSubtitle = true
            )
        }

        composeTestRule.onNodeWithText("Hidden Title").assertDoesNotExist()
        composeTestRule.onNodeWithText("Visible Subtitle").assertIsDisplayed()
    }

    @Test
    fun circleView_hidesSubtitleWhenShowSubtitleIsFalse() {
        composeTestRule.setContent {
            CircleView(
                modifier = Modifier.size(200.dp),
                titleText = "Visible Title",
                subtitleText = "Hidden Subtitle",
                showTitle = true,
                showSubtitle = false
            )
        }

        composeTestRule.onNodeWithText("Visible Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Hidden Subtitle").assertDoesNotExist()
    }
}
