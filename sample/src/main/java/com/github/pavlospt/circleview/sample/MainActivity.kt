package com.github.pavlospt.circleview.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.pavlospt.circleview.CircleView
import io.github.pavlospt.circleview.CircleViewDefaults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CircleViewSampleTheme {
                SampleAppScreen()
            }
        }
    }
}

@Composable
fun CircleViewSampleTheme(content: @Composable () -> Unit) {
    val darkColors = darkColorScheme(
        primary = Color(0xFF00E5FF),
        secondary = Color(0xFFFF4081),
        background = Color(0xFF0F172A),
        surface = Color(0xFF1E293B),
        onBackground = Color(0xFFF8FAFC),
        onSurface = Color(0xFFF8FAFC)
    )
    MaterialTheme(
        colorScheme = darkColors,
        content = content
    )
}

data class ColorPreset(
    val name: String,
    val titleColor: Color,
    val subtitleColor: Color,
    val strokeColor: Color,
    val backgroundColor: Color,
    val fillColor: Color
)

val PRESETS = listOf(
    ColorPreset("Cyberpunk", Color(0xFF00E5FF), Color(0xFFE2E8F0), Color(0xFFFF007A), Color(0xFF0F172A), Color(0xFF1E1B4B)),
    ColorPreset("Neon Mint", Color(0xFF00F5D4), Color(0xFFF1F5F9), Color(0xFF7B2CBF), Color(0xFF101828), Color(0xFF1E293B)),
    ColorPreset("Solar Gold", Color(0xFFFFD166), Color(0xFFFFFBEB), Color(0xFFEF476F), Color(0xFF1A1C23), Color(0xFF2D3142)),
    ColorPreset("Emerald", Color(0xFF10B981), Color(0xFFECFDF5), Color(0xFF059669), Color(0xFF064E3B), Color(0xFF022C22)),
    ColorPreset("Dark Minimal", Color(0xFFF8FAFC), Color(0xFF94A3B8), Color(0xFF475569), Color(0xFF0F172A), Color(0xFF1E293B))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleAppScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Playground", "Showcase Gallery")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "CircleView",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                "Compose 2.0",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            PrimaryTabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title, fontWeight = FontWeight.SemiBold) }
                    )
                }
            }

            when (selectedTab) {
                0 -> PlaygroundTab()
                1 -> ShowcaseGalleryTab()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlaygroundTab() {
    var titleText by remember { mutableStateOf("7,842") }
    var subtitleText by remember { mutableStateOf("STEPS") }

    var titleSizeSp by remember { mutableFloatStateOf(28f) }
    var subtitleSizeSp by remember { mutableFloatStateOf(14f) }
    var strokeWidthDp by remember { mutableFloatStateOf(8f) }
    var fillRadiusRatio by remember { mutableFloatStateOf(0.85f) }
    var titleSubtitleSpaceDp by remember { mutableFloatStateOf(4f) }

    var showTitle by remember { mutableStateOf(true) }
    var showSubtitle by remember { mutableStateOf(true) }
    var animated by remember { mutableStateOf(true) }

    var currentPreset by remember { mutableStateOf(PRESETS[0]) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Preview Header Container
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                CircleView(
                    modifier = Modifier.size(240.dp),
                    titleText = titleText,
                    subtitleText = subtitleText,
                    titleSize = titleSizeSp.sp,
                    subtitleSize = subtitleSizeSp.sp,
                    titleColor = currentPreset.titleColor,
                    subtitleColor = currentPreset.subtitleColor,
                    strokeColor = currentPreset.strokeColor,
                    backgroundColor = currentPreset.backgroundColor,
                    fillColor = currentPreset.fillColor,
                    strokeWidth = strokeWidthDp.dp,
                    fillRadius = fillRadiusRatio,
                    titleSubtitleSpace = titleSubtitleSpaceDp.dp,
                    showTitle = showTitle,
                    showSubtitle = showSubtitle,
                    animated = animated
                )
            }
        }

        // Color Presets
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Color Presets", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(12.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PRESETS.forEach { preset ->
                        FilterChip(
                            selected = currentPreset == preset,
                            onClick = { currentPreset = preset },
                            label = { Text(preset.name) },
                            leadingIcon = {
                                Box(
                                    modifier = Modifier
                                        .size(14.dp)
                                        .clip(CircleShape)
                                        .background(preset.titleColor)
                                )
                            }
                        )
                    }
                }
            }
        }

        // Controls Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Customization Controls", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Smooth Animations")
                    Switch(checked = animated, onCheckedChange = { animated = it })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Show Title")
                    Switch(checked = showTitle, onCheckedChange = { showTitle = it })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Show Subtitle")
                    Switch(checked = showSubtitle, onCheckedChange = { showSubtitle = it })
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = titleText,
                    onValueChange = { titleText = it },
                    label = { Text("Title Text") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = subtitleText,
                    onValueChange = { subtitleText = it },
                    label = { Text("Subtitle Text") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Fill Radius (${(fillRadiusRatio * 100).toInt()}%)", fontSize = 14.sp)
                Slider(
                    value = fillRadiusRatio,
                    onValueChange = { fillRadiusRatio = it },
                    valueRange = 0.1f..1.0f
                )

                Text("Stroke Width (${strokeWidthDp.toInt()} dp)", fontSize = 14.sp)
                Slider(
                    value = strokeWidthDp,
                    onValueChange = { strokeWidthDp = it },
                    valueRange = 0f..24f
                )

                Text("Title Size (${titleSizeSp.toInt()} sp)", fontSize = 14.sp)
                Slider(
                    value = titleSizeSp,
                    onValueChange = { titleSizeSp = it },
                    valueRange = 10f..50f
                )

                Text("Subtitle Size (${subtitleSizeSp.toInt()} sp)", fontSize = 14.sp)
                Slider(
                    value = subtitleSizeSp,
                    onValueChange = { subtitleSizeSp = it },
                    valueRange = 8f..36f
                )

                Text("Title Subtitle Space (${titleSubtitleSpaceDp.toInt()} dp)", fontSize = 14.sp)
                Slider(
                    value = titleSubtitleSpaceDp,
                    onValueChange = { titleSubtitleSpaceDp = it },
                    valueRange = 0f..30f
                )
            }
        }
    }
}

@Composable
fun ShowcaseGalleryTab() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Fitness Dashboard Widget", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CircleView(
                        modifier = Modifier.size(100.dp),
                        titleText = "85%",
                        subtitleText = "Goal",
                        titleSize = 18.sp,
                        subtitleSize = 11.sp,
                        strokeColor = Color(0xFF10B981),
                        fillColor = Color(0xFF065F46),
                        titleColor = Color(0xFF34D399),
                        subtitleColor = Color.White,
                        strokeWidth = 4.dp,
                        fillRadius = 0.88f
                    )

                    CircleView(
                        modifier = Modifier.size(100.dp),
                        titleText = "640",
                        subtitleText = "KCAL",
                        titleSize = 18.sp,
                        subtitleSize = 11.sp,
                        strokeColor = Color(0xFFEF476F),
                        fillColor = Color(0xFF881337),
                        titleColor = Color(0xFFF43F5E),
                        subtitleColor = Color.White,
                        strokeWidth = 4.dp,
                        fillRadius = 0.88f
                    )

                    CircleView(
                        modifier = Modifier.size(100.dp),
                        titleText = "7.2",
                        subtitleText = "KM",
                        titleSize = 18.sp,
                        subtitleSize = 11.sp,
                        strokeColor = Color(0xFF3B82F6),
                        fillColor = Color(0xFF1E3A8A),
                        titleColor = Color(0xFF60A5FA),
                        subtitleColor = Color.White,
                        strokeWidth = 4.dp,
                        fillRadius = 0.88f
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Smart Thermostat", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))
                CircleView(
                    modifier = Modifier.size(180.dp),
                    titleText = "22.5°C",
                    subtitleText = "HEATING",
                    titleSize = 32.sp,
                    subtitleSize = 13.sp,
                    strokeColor = Color(0xFFF97316),
                    backgroundColor = Color(0xFF451A03),
                    fillColor = Color(0xFF7C2D12),
                    titleColor = Color(0xFFFDBA74),
                    subtitleColor = Color(0xFFFED7AA),
                    strokeWidth = 6.dp,
                    fillRadius = 0.82f,
                    titleSubtitleSpace = 6.dp
                )
            }
        }
    }
}
