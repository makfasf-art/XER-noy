package com.example.ui.screens

import androidx.compose.animation.core.Animatable
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CyberBlack
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonRed
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(
    onLoadingComplete: () -> Unit
) {
    val progressAnim = remember { Animatable(0f) }
    var phaseStatus by remember { mutableStateOf("INITIALIZING QUANTUM KERNEL...") }
    var isUnfoldingCore by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition(label = "hud_rotations")
    val rotOuter by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(14000, easing = LinearEasing), RepeatMode.Restart),
        label = "rotOuter"
    )
    val rotInner by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(tween(8000, easing = LinearEasing), RepeatMode.Restart),
        label = "rotInner"
    )
    val pulseGlow by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(800, easing = LinearEasing), RepeatMode.Reverse),
        label = "pulseGlow"
    )

    LaunchedEffect(Unit) {
        // Multi-stage cinematic boot sequence mimicking Video 1
        progressAnim.animateTo(0.37f, animationSpec = tween(900, easing = LinearEasing))
        phaseStatus = "CALIBRATING 450+ TOOL REGISTRIES..."
        delay(350)

        progressAnim.animateTo(0.68f, animationSpec = tween(800, easing = LinearEasing))
        phaseStatus = "LOADING SECURE ENCRYPTION ENCLAVE..."
        delay(250)

        progressAnim.animateTo(0.95f, animationSpec = tween(700, easing = LinearEasing))
        phaseStatus = "SYNCHRONIZING TELEMETRY SENSORS..."
        delay(200)

        progressAnim.animateTo(1.0f, animationSpec = tween(400, easing = LinearEasing))
        phaseStatus = "SECURITY CLEARANCE: VERIFIED // NUK10 ACTIVE"
        isUnfoldingCore = true
        delay(1200) // Brief dramatic hold on verified core

        onLoadingComplete()
    }

    val currentPercent = (progressAnim.value * 100).toInt()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CyberBlack),
        contentAlignment = Alignment.Center
    ) {
        // Background HUD grid and scanning lines canvas
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    val w = size.width
                    val h = size.height

                    // Subtle radial grid
                    drawCircle(
                        color = Color(0x1538435E),
                        radius = w * 0.45f,
                        style = Stroke(width = 1f)
                    )
                    drawCircle(
                        color = Color(0x0F38435E),
                        radius = w * 0.7f,
                        style = Stroke(width = 1f)
                    )

                    // Crosshair axis lines
                    drawLine(
                        color = Color(0x2238435E),
                        start = Offset(0f, h / 2),
                        end = Offset(w, h / 2),
                        strokeWidth = 1f
                    )
                    drawLine(
                        color = Color(0x2238435E),
                        start = Offset(w / 2, 0f),
                        end = Offset(w / 2, h),
                        strokeWidth = 1f
                    )

                    // CRT Scanlines
                    var scanY = 0f
                    while (scanY < h) {
                        drawLine(
                            color = Color.Black.copy(alpha = 0.3f),
                            start = Offset(0f, scanY),
                            end = Offset(w, scanY),
                            strokeWidth = 2f
                        )
                        scanY += 6f
                    }
                }
        )

        // Top HUD Header
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 48.dp, start = 24.dp, end = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SYS.BOOT v4.5.9 // XER CORE",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = NeonRed,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "SECTOR 0x7F-AA",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    color = TextMuted
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(NeonRed, Color(0x33FF1E46), Color.Transparent)
                        )
                    )
            )
        }

        // Center Futuristic HUD Loader matching Video 1
        Box(
            modifier = Modifier.size(340.dp),
            contentAlignment = Alignment.Center
        ) {
            // Rotating outer HUD tech rings
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        val center = Offset(size.width / 2, size.height / 2)
                        val radiusOuter = size.width * 0.44f
                        val radiusInner = size.width * 0.32f

                        rotate(rotOuter, center) {
                            // Segmented arcs
                            drawArc(
                                color = if (isUnfoldingCore) NeonRed else Color(0x77FFFFFF),
                                startAngle = 0f,
                                sweepAngle = 50f,
                                useCenter = false,
                                style = Stroke(width = 2.5f, cap = StrokeCap.Round)
                            )
                            drawArc(
                                color = if (isUnfoldingCore) NeonRed else Color(0x77FFFFFF),
                                startAngle = 180f,
                                sweepAngle = 50f,
                                useCenter = false,
                                style = Stroke(width = 2.5f, cap = StrokeCap.Round)
                            )

                            // Triangular indicator notches
                            drawTriangle(center.x, center.y - radiusOuter - 10f, 10f, isUp = true, color = NeonRed)
                            drawTriangle(center.x, center.y + radiusOuter + 10f, 10f, isUp = false, color = NeonRed)
                        }

                        rotate(rotInner, center) {
                            drawArc(
                                color = Color(0x4400E5FF),
                                startAngle = 90f,
                                sweepAngle = 70f,
                                useCenter = false,
                                style = Stroke(width = 1.5f)
                            )
                            drawArc(
                                color = Color(0x4400E5FF),
                                startAngle = 270f,
                                sweepAngle = 70f,
                                useCenter = false,
                                style = Stroke(width = 1.5f)
                            )

                            // Radial ticks
                            for (angle in 0 until 360 step 30) {
                                val rad = Math.toRadians(angle.toDouble())
                                val startX = (center.x + (radiusInner - 6f) * kotlin.math.cos(rad)).toFloat()
                                val startY = (center.y + (radiusInner - 6f) * kotlin.math.sin(rad)).toFloat()
                                val endX = (center.x + radiusInner * kotlin.math.cos(rad)).toFloat()
                                val endY = (center.y + radiusInner * kotlin.math.sin(rad)).toFloat()
                                drawLine(
                                    color = Color(0x66FFFFFF),
                                    start = Offset(startX, startY),
                                    end = Offset(endX, endY),
                                    strokeWidth = 1.5f
                                )
                            }
                        }
                    }
            )

            // When loading finishes (100%), reveal the cinematic NUK10 core aperture like in the video
            if (isUnfoldingCore) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Glowing cybernetic hexagon badge
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .drawBehind {
                                val center = Offset(size.width / 2, size.height / 2)
                                drawHexagon(center, size.width * 0.45f, NeonRed, filled = false, strokeWidth = 3f)
                                drawHexagon(center, size.width * 0.32f, NeonRed.copy(alpha = 0.35f), filled = true)

                                // Hazard / Quantum radiation blades
                                val bladeRadius = size.width * 0.28f
                                for (i in 0 until 3) {
                                    rotate(i * 120f + (rotOuter * 0.5f), center) {
                                        drawArc(
                                            color = NeonRed,
                                            startAngle = -25f,
                                            sweepAngle = 50f,
                                            useCenter = true,
                                            size = Size(bladeRadius * 2, bladeRadius * 2),
                                            topLeft = Offset(center.x - bladeRadius, center.y - bladeRadius)
                                        )
                                    }
                                }
                                drawCircle(color = CyberBlack, radius = size.width * 0.12f, center = center)
                                drawCircle(color = NeonRed, radius = size.width * 0.05f, center = center)
                            }
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(NeonRed)
                            .padding(horizontal = 12.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "NUK10 // ACTIVE",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                    }
                }
            } else {
                // Segmented Progress Capsule matching Video 1
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(260.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xE608090E))
                            .border(1.5.dp, Color(0x88FFFFFF), RoundedCornerShape(6.dp))
                            .drawBehind {
                                val w = size.width
                                val h = size.height
                                val activeW = progressAnim.value * w

                                // White/Chrome segmented fill
                                drawRect(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xBBFFFFFF),
                                            Color.White
                                        )
                                    ),
                                    size = Size(activeW, h)
                                )

                                // Angled hatchings overlay
                                val hatchStep = 10f
                                var x = -h
                                while (x < w) {
                                    drawLine(
                                        color = Color.Black.copy(alpha = 0.5f),
                                        start = Offset(x, 0f),
                                        end = Offset(x + h, h),
                                        strokeWidth = 2.5f
                                    )
                                    x += hatchStep
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        // Center hexagonal glowing percentage readout
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(CyberBlack)
                                .border(1.dp, NeonRed, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$currentPercent",
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        // Bottom Status Readouts
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 52.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = phaseStatus,
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (isUnfoldingCore) NeonRed else TextPrimary,
                letterSpacing = 0.8.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "MODULES: 450/450 VERIFIED",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp,
                    color = TextMuted
                )
                Text(
                    text = "BUFFER: 100% SECURE",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp,
                    color = NeonCyan
                )
            }
        }
    }
}

private fun DrawScope.drawTriangle(x: Float, y: Float, size: Float, isUp: Boolean, color: Color) {
    val path = Path().apply {
        if (isUp) {
            moveTo(x, y - size / 2)
            lineTo(x - size / 2, y + size / 2)
            lineTo(x + size / 2, y + size / 2)
        } else {
            moveTo(x, y + size / 2)
            lineTo(x - size / 2, y - size / 2)
            lineTo(x + size / 2, y - size / 2)
        }
        close()
    }
    drawPath(path, color)
}

private fun DrawScope.drawHexagon(center: Offset, radius: Float, color: Color, filled: Boolean, strokeWidth: Float = 2f) {
    val path = Path()
    for (i in 0 until 6) {
        val angle = Math.toRadians((i * 60 - 30).toDouble())
        val px = (center.x + radius * kotlin.math.cos(angle)).toFloat()
        val py = (center.y + radius * kotlin.math.sin(angle)).toFloat()
        if (i == 0) path.moveTo(px, py) else path.lineTo(px, py)
    }
    path.close()
    if (filled) {
        drawPath(path, color)
    } else {
        drawPath(path, color, style = Stroke(strokeWidth))
    }
}
