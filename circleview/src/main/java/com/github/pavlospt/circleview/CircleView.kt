package io.github.pavlospt.circleview

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

/**
 * A modern Jetpack Compose CircleView component that renders a customizable circular layout
 * containing Title and Subtitle text inside a styled, layered circle.
 *
 * @param modifier [Modifier] to apply to the CircleView container.
 * @param titleText Title text displayed in the first row.
 * @param subtitleText Subtitle text displayed in the second row.
 * @param titleSize Font size of the title text.
 * @param subtitleSize Font size of the subtitle text.
 * @param titleColor Color of the title text.
 * @param subtitleColor Color of the subtitle text.
 * @param strokeColor Color of the outer border stroke.
 * @param backgroundColor Color of the background circle between stroke and inner fill.
 * @param fillColor Color of the inner filled circle.
 * @param strokeWidth Thickness of the outer border stroke.
 * @param fillRadius Radius ratio of the inner circle relative to outer circle (0.0f to 1.0f).
 * @param titleSubtitleSpace Vertical spacing between title and subtitle text.
 * @param showTitle Controls visibility of the title text.
 * @param showSubtitle Controls visibility of the subtitle text.
 * @param titleStyle Optional custom [TextStyle] for the title text.
 * @param subtitleStyle Optional custom [TextStyle] for the subtitle text.
 * @param animated Whether parameter changes (colors, sizes, radius) should animate smoothly.
 * @param animationSpec Custom [AnimationSpec] used when [animated] is true.
 */
@Composable
fun CircleView(
    modifier: Modifier = Modifier,
    titleText: String = CircleViewDefaults.DefaultTitle,
    subtitleText: String = CircleViewDefaults.DefaultSubtitle,
    titleSize: TextUnit = CircleViewDefaults.TitleSize,
    subtitleSize: TextUnit = CircleViewDefaults.SubtitleSize,
    titleColor: Color = CircleViewDefaults.TitleColor,
    subtitleColor: Color = CircleViewDefaults.SubtitleColor,
    strokeColor: Color = CircleViewDefaults.StrokeColor,
    backgroundColor: Color = CircleViewDefaults.BackgroundColor,
    fillColor: Color = CircleViewDefaults.FillColor,
    strokeWidth: Dp = CircleViewDefaults.StrokeWidth,
    fillRadius: Float = CircleViewDefaults.FillRadius,
    titleSubtitleSpace: Dp = CircleViewDefaults.TitleSubtitleSpace,
    showTitle: Boolean = true,
    showSubtitle: Boolean = true,
    titleStyle: TextStyle = TextStyle.Default,
    subtitleStyle: TextStyle = TextStyle.Default,
    animated: Boolean = false,
    animationSpec: AnimationSpec<Float> = tween(durationMillis = 300)
) {
    val colorSpec = tween<Color>(durationMillis = 300)
    val dpSpec = tween<Dp>(durationMillis = 300)

    val targetTitleColor by animateColorAsState(
        targetValue = titleColor,
        animationSpec = if (animated) colorSpec else tween(0),
        label = "TitleColor"
    )
    val targetSubtitleColor by animateColorAsState(
        targetValue = subtitleColor,
        animationSpec = if (animated) colorSpec else tween(0),
        label = "SubtitleColor"
    )
    val targetStrokeColor by animateColorAsState(
        targetValue = strokeColor,
        animationSpec = if (animated) colorSpec else tween(0),
        label = "StrokeColor"
    )
    val targetBackgroundColor by animateColorAsState(
        targetValue = backgroundColor,
        animationSpec = if (animated) colorSpec else tween(0),
        label = "BackgroundColor"
    )
    val targetFillColor by animateColorAsState(
        targetValue = fillColor,
        animationSpec = if (animated) colorSpec else tween(0),
        label = "FillColor"
    )
    val targetStrokeWidth by animateDpAsState(
        targetValue = strokeWidth,
        animationSpec = if (animated) dpSpec else tween(0),
        label = "StrokeWidth"
    )
    val targetFillRadius by animateFloatAsState(
        targetValue = fillRadius.coerceIn(0f, 1f),
        animationSpec = if (animated) animationSpec else tween(0),
        label = "FillRadius"
    )
    val targetSpace by animateDpAsState(
        targetValue = titleSubtitleSpace,
        animationSpec = if (animated) dpSpec else tween(0),
        label = "TitleSubtitleSpace"
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val minSize = size.minDimension
            val strokePx = targetStrokeWidth.toPx()
            val centerOffset = Offset(size.width / 2f, size.height / 2f)

            val outerRadius = (minSize - strokePx) / 2f
            if (outerRadius > 0f) {
                // Draw background circle inside stroke
                drawCircle(
                    color = targetBackgroundColor,
                    radius = outerRadius,
                    center = centerOffset
                )

                // Draw inner fill circle
                val rawFillRadius = (minSize / 2f) * targetFillRadius - strokePx
                val innerRadius = rawFillRadius.coerceAtLeast(0f)
                if (innerRadius > 0f) {
                    drawCircle(
                        color = targetFillColor,
                        radius = innerRadius,
                        center = centerOffset
                    )
                }

                // Draw outer stroke ring
                if (strokePx > 0f) {
                    drawCircle(
                        color = targetStrokeColor,
                        radius = outerRadius,
                        center = centerOffset,
                        style = Stroke(width = strokePx)
                    )
                }
            }
        }

        // Title and Subtitle text overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = androidx.compose.foundation.layout.PaddingValues(targetStrokeWidth)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showTitle) {
                Text(
                    text = titleText,
                    color = targetTitleColor,
                    fontSize = titleSize,
                    style = titleStyle.copy(fontWeight = titleStyle.fontWeight ?: FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (showTitle && showSubtitle && targetSpace > 0.dp) {
                Box(modifier = Modifier.size(width = 1.dp, height = targetSpace))
            }

            if (showSubtitle) {
                Text(
                    text = subtitleText,
                    color = targetSubtitleColor,
                    fontSize = subtitleSize,
                    style = subtitleStyle,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

/**
 * Overload of [CircleView] that uses [CircleViewColors].
 */
@Composable
fun CircleView(
    colors: CircleViewColors,
    modifier: Modifier = Modifier,
    titleText: String = CircleViewDefaults.DefaultTitle,
    subtitleText: String = CircleViewDefaults.DefaultSubtitle,
    titleSize: TextUnit = CircleViewDefaults.TitleSize,
    subtitleSize: TextUnit = CircleViewDefaults.SubtitleSize,
    strokeWidth: Dp = CircleViewDefaults.StrokeWidth,
    fillRadius: Float = CircleViewDefaults.FillRadius,
    titleSubtitleSpace: Dp = CircleViewDefaults.TitleSubtitleSpace,
    showTitle: Boolean = true,
    showSubtitle: Boolean = true,
    titleStyle: TextStyle = TextStyle.Default,
    subtitleStyle: TextStyle = TextStyle.Default,
    animated: Boolean = false
) {
    CircleView(
        modifier = modifier,
        titleText = titleText,
        subtitleText = subtitleText,
        titleSize = titleSize,
        subtitleSize = subtitleSize,
        titleColor = colors.titleColor,
        subtitleColor = colors.subtitleColor,
        strokeColor = colors.strokeColor,
        backgroundColor = colors.backgroundColor,
        fillColor = colors.fillColor,
        strokeWidth = strokeWidth,
        fillRadius = fillRadius,
        titleSubtitleSpace = titleSubtitleSpace,
        showTitle = showTitle,
        showSubtitle = showSubtitle,
        titleStyle = titleStyle,
        subtitleStyle = subtitleStyle,
        animated = animated
    )
}

@Preview(name = "Default CircleView", showBackground = true)
@Composable
private fun CircleViewDefaultPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            CircleView(
                modifier = Modifier.size(200.dp),
                titleText = "75%",
                subtitleText = "Completed"
            )
        }
    }
}

@Preview(name = "Custom Styled CircleView", showBackground = true)
@Composable
private fun CircleViewCustomPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = Color(0xFF121212)
        ) {
            CircleView(
                modifier = Modifier.size(220.dp),
                titleText = "12,450",
                subtitleText = "Steps",
                titleColor = Color(0xFFFF4081),
                subtitleColor = Color(0xFFE0E0E0),
                strokeColor = Color(0xFFFF4081),
                backgroundColor = Color(0xFF1E1E1E),
                fillColor = Color(0xFF2C2C2C),
                strokeWidth = 8.dp,
                fillRadius = 0.85f,
                titleSubtitleSpace = 6.dp
            )
        }
    }
}
