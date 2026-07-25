package io.github.pavlospt.circleview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.junit.Assert.assertEquals
import org.junit.Test

class CircleViewDefaultsTest {

    @Test
    fun defaultValues_areCorrect() {
        assertEquals("Title", CircleViewDefaults.DefaultTitle)
        assertEquals("Subtitle", CircleViewDefaults.DefaultSubtitle)
        assertEquals(25.sp, CircleViewDefaults.TitleSize)
        assertEquals(20.sp, CircleViewDefaults.SubtitleSize)
        assertEquals(4.dp, CircleViewDefaults.TitleSubtitleSpace)
        assertEquals(5.dp, CircleViewDefaults.StrokeWidth)
        assertEquals(0.9f, CircleViewDefaults.FillRadius, 0.001f)
    }

    @Test
    fun circleViewColors_usesDefaultsWhenNotSpecified() {
        val colors = CircleViewColors()
        assertEquals(CircleViewDefaults.TitleColor, colors.titleColor)
        assertEquals(CircleViewDefaults.SubtitleColor, colors.subtitleColor)
        assertEquals(CircleViewDefaults.StrokeColor, colors.strokeColor)
        assertEquals(CircleViewDefaults.BackgroundColor, colors.backgroundColor)
        assertEquals(CircleViewDefaults.FillColor, colors.fillColor)
    }
}
