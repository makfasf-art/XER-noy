package com.example.ui.screens

import android.os.Build
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ActivityLogEntry
import com.example.model.ToolCategory
import com.example.model.ToolItem
import com.example.ui.components.HudBadge
import com.example.ui.components.HudCard
import com.example.ui.components.HudHeader
import com.example.ui.components.HudStatCard
import com.example.ui.theme.CyberBorder
import com.example.ui.theme.CyberBorderGlow
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGold
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.NeonRed
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DashboardScreen(
    allTools: List<ToolItem>,
    favoriteIds: Set<String>,
    activityLogs: List<ActivityLogEntry>,
    operationsCount: Int,
    sessionSeconds: Long,
    onSelectTool: (ToolItem) -> Unit,
    accentColor: Color = NeonRed
) {
    val infiniteTransition = rememberInfiniteTransition(label = "gauge_rot")
    val gaugeRot by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(10000, easing = LinearEasing), RepeatMode.Restart),
        label = "gauge_rot_anim"
    )

    val favoriteTools = remember(favoriteIds, allTools) {
        favoriteIds.mapNotNull { id -> allTools.find { it.id == id } }
    }

    val sessionFormatted = "%02d:%02d".format(sessionSeconds / 60, sessionSeconds % 60)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Dashboard Title
        item {
            HudHeader(
                title = "System Telemetry & Analytics",
                code = "SEC-DASH",
                subtitle = "Live metrics, hardware diagnostics, and category telemetry",
                accentColor = accentColor
            )
        }

        // Top 4 Metric Cards Grid
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                    HudStatCard(
                        label = "Total Registry",
                        value = "${allTools.size}",
                        subValue = "15 Sectors Active",
                        accentColor = accentColor,
                        icon = Icons.Default.Build,
                        modifier = Modifier.weight(1f)
                    )
                    HudStatCard(
                        label = "Operations Run",
                        value = "$operationsCount",
                        subValue = "All-time Executions",
                        accentColor = NeonCyan,
                        icon = Icons.Default.Speed,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                    HudStatCard(
                        label = "Favorites Saved",
                        value = "${favoriteIds.size}",
                        subValue = "Pinned Tools",
                        accentColor = NeonPurple,
                        icon = Icons.Default.Favorite,
                        modifier = Modifier.weight(1f)
                    )
                    HudStatCard(
                        label = "Session Uptime",
                        value = sessionFormatted,
                        subValue = "Continuous Buffer",
                        accentColor = NeonGreen,
                        icon = Icons.Default.Schedule,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Futuristic Circular Gauges (CPU & RAM Load)
        item {
            HudCard(
                borderColor = CyberBorderGlow,
                glowColor = NeonCyan,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "CORE TELEMETRY GAUGES",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // CPU Load Gauge
                        CircularHudGauge(
                            label = "CPU LOAD",
                            percent = 28,
                            rotation = gaugeRot,
                            color = accentColor
                        )
                        // Memory Load Gauge
                        CircularHudGauge(
                            label = "RAM LOAD",
                            percent = 46,
                            rotation = -gaugeRot,
                            color = NeonCyan
                        )
                        // Kernel Buffer Gauge
                        CircularHudGauge(
                            label = "BUFFER",
                            percent = 92,
                            rotation = gaugeRot * 0.7f,
                            color = NeonGreen
                        )
                    }
                }
            }
        }

        // Category Distribution Bar Chart
        item {
            HudCard(
                borderColor = CyberBorder,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "REGISTRY SECTOR DISTRIBUTION",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextSecondary
                        )
                        Text(
                            text = "30 TOOLS/SECTOR",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp,
                            color = NeonCyan
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))

                    // Sector bars
                    val topSectors = listOf(
                        "Crypto & Security" to (30 to accentColor),
                        "Developer Encoders" to (30 to NeonCyan),
                        "Science Calculators" to (30 to NeonGreen),
                        "Unit Converters" to (30 to NeonGold),
                        "Hardware Diagnostics" to (30 to NeonPurple),
                        "Media Audio Synth" to (30 to Color(0xFFFF5252))
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        topSectors.forEach { (name, pair) ->
                            val (count, color) = pair
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(name, fontSize = 11.sp, color = TextPrimary)
                                    Text("$count tools", fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = color)
                                }
                                Spacer(modifier = Modifier.height(3.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(5.dp)
                                        .clip(RoundedCornerShape(3.dp))
                                        .background(Color(0xFF151824))
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(0.85f)
                                            .height(5.dp)
                                            .clip(RoundedCornerShape(3.dp))
                                            .background(color)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Real System & Device Hardware Card
        item {
            HudCard(
                borderColor = CyberBorder,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "DEVICE TELEMETRY PROFILE",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    val metrics = listOf(
                        "Manufacturer" to Build.MANUFACTURER.uppercase(),
                        "Model / Board" to "${Build.MODEL} (${Build.BOARD})",
                        "Android Release" to "Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})",
                        "Architecture ABI" to (Build.SUPPORTED_ABIS.firstOrNull() ?: "arm64-v8a"),
                        "Build Display" to Build.DISPLAY,
                        "Hardware Host" to Build.HOST
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        metrics.forEach { (label, value) ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(label, fontFamily = FontFamily.Monospace, fontSize = 11.sp, color = TextMuted)
                                Text(value, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.SemiBold, fontSize = 11.sp, color = TextPrimary)
                            }
                        }
                    }
                }
            }
        }

        // Favorite Tools Quick Grid
        if (favoriteTools.isNotEmpty()) {
            item {
                Column {
                    HudHeader(title = "Pinned Favorites (${favoriteTools.size})", code = "FAV-GRID", accentColor = NeonPurple)
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        favoriteTools.take(4).forEach { tool ->
                            HudCard(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { onSelectTool(tool) }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        HudBadge(text = tool.category.code, accentColor = NeonPurple)
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(tool.name, fontFamily = FontFamily.Monospace, fontSize = 13.sp, color = TextPrimary)
                                    }
                                    Text("LAUNCH", fontFamily = FontFamily.Monospace, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = NeonPurple)
                                }
                            }
                        }
                    }
                }
            }
        }

        // Recent Activity Stream
        item {
            Column {
                HudHeader(title = "Recent Activity Stream", code = "ACT-STREAM", accentColor = NeonGreen)
                Spacer(modifier = Modifier.height(10.dp))
                if (activityLogs.isEmpty()) {
                    HudCard(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                            Text("No activity logged yet. Launch tools to see live traces.", fontSize = 12.sp, color = TextMuted)
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        activityLogs.take(6).forEach { log ->
                            val timeStr = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(log.timestamp))
                            HudCard(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        HudBadge(text = log.categoryCode, accentColor = NeonGreen)
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Column {
                                            Text(log.toolName, fontFamily = FontFamily.Monospace, fontSize = 12.sp, color = TextPrimary)
                                            Text(log.details, fontSize = 10.sp, color = TextMuted)
                                        }
                                    }
                                    Text(timeStr, fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = TextSecondary)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CircularHudGauge(
    label: String,
    percent: Int,
    rotation: Float,
    color: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(76.dp)
                .drawBehind {
                    val center = Offset(size.width / 2, size.height / 2)
                    val radius = size.width * 0.42f

                    // Background track
                    drawCircle(
                        color = Color(0x2238435E),
                        radius = radius,
                        style = Stroke(width = 4f)
                    )

                    // Active sweep arc
                    drawArc(
                        color = color,
                        startAngle = -90f,
                        sweepAngle = (percent / 100f) * 360f,
                        useCenter = false,
                        style = Stroke(width = 4f, cap = StrokeCap.Round)
                    )

                    // Rotating tech tick mark
                    rotate(rotation, center) {
                        drawCircle(
                            color = color,
                            radius = 3.5f,
                            center = Offset(center.x + radius * kotlin.math.cos(Math.toRadians(0.0)).toFloat(),
                                           center.y + radius * kotlin.math.sin(Math.toRadians(0.0)).toFloat())
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$percent%",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = color
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            fontFamily = FontFamily.Monospace,
            fontSize = 9.sp,
            color = TextSecondary
        )
    }
}
