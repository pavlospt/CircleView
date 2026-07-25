package io.github.pavlospt.circleview

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Defaults used in [CircleView].
 */
object CircleViewDefaults {
    val TitleColor: Color = Color(0xFF00E5FF)
    val SubtitleColor: Color = Color(0xFFFFFFFF)
    val StrokeColor: Color = Color(0xFF00E5FF)
    val BackgroundColor: Color = Color(0xFFFFFFFF)
    val FillColor: Color = Color(0xFF333333)

    val TitleSize: TextUnit = 25.sp
    val SubtitleSize: TextUnit = 20.sp
    val TitleSubtitleSpace: Dp = 4.dp
    val StrokeWidth: Dp = 5.dp
    val FillRadius: Float = 0.9f

    const val DefaultTitle: String = "Title"
    const val DefaultSubtitle: String = "Subtitle"
}

/**
 * Colors used in [CircleView].
 */
@Immutable
data class CircleViewColors(
    val titleColor: Color = CircleViewDefaults.TitleColor,
    val subtitleColor: Color = CircleViewDefaults.SubtitleColor,
    val strokeColor: Color = CircleViewDefaults.StrokeColor,
    val backgroundColor: Color = CircleViewDefaults.BackgroundColor,
    val fillColor: Color = CircleViewDefaults.FillColor
)
