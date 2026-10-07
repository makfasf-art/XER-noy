package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.R
import com.example.model.BackgroundStyle
import com.example.ui.theme.CyberBlack
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonRed

@Composable
fun FuturisticBackground(
    style: BackgroundStyle,
    animationsEnabled: Boolean = true,
    accentColor: Color = NeonRed,
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "bg_anim")
    val animProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = if (animationsEnabled) 12000 else 100000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "ambient_progress"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CyberBlack)
    ) {
        when (style) {
            BackgroundStyle.BLACK_HOLE -> {
                Image(
                    painter = painterResource(id = R.drawable.bg_black_hole),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Darkening overlay for text readability & neon tint
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xCC060709),
                                    Color(0x990A0C12),
                                    Color(0xEE060709)
                                )
                            )
                        )
                )
            }
            BackgroundStyle.CYBER_GIRL -> {
                Image(
                    painter = painterResource(id = R.drawable.bg_cyber_girl),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Subtle crimson glass overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xD9060709),
                                    Color(0xAA0F050B),
                                    Color(0xF2060709)
                                )
                            )
                        )
                )
            }
            BackgroundStyle.CYBER_GRID -> {
                // Animated live cybernetic grid canvas
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawBehind {
                            val w = size.width
                            val h = size.height

                            // Ambient radial neon glow
                            drawCircle(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        accentColor.copy(alpha = 0.12f),
                                        NeonCyan.copy(alpha = 0.04f),
                                        Color.Transparent
                                    ),
                                    center = Offset(w * 0.5f, h * (0.25f + 0.1f * kotlin.math.sin(animProgress * 2 * Math.PI).toFloat())),
                                    radius = w * 0.7f
                                )
                            )

                            // Grid lines
                            val gridSize = 48f
                            val offsetY = (animProgress * gridSize) % gridSize
                            val lineAlpha = 0.08f

                            var x = 0f
                            while (x <= w) {
                                drawLine(
                                    color = NeonCyan.copy(alpha = lineAlpha),
                                    start = Offset(x, 0f),
                                    end = Offset(x, h),
                                    strokeWidth = 1f
                                )
                                x += gridSize
                            }

                            var y = offsetY
                            while (y <= h) {
                                drawLine(
                                    color = NeonCyan.copy(alpha = lineAlpha),
                                    start = Offset(0f, y),
                                    end = Offset(w, y),
                                    strokeWidth = 1f
                                )
                                y += gridSize
                            }

                            // Subtle scanline laser sweep
                            val scanY = animProgress * h
                            drawLine(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        accentColor.copy(alpha = 0.35f),
                                        Color.Transparent
                                    )
                                ),
                                start = Offset(0f, scanY),
                                end = Offset(w, scanY),
                                strokeWidth = 2.5f
                            )
                        }
                )
            }
            BackgroundStyle.QUANTUM_NEBULA -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawBehind {
                            val w = size.width
                            val h = size.height

                            drawCircle(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFF2E0854).copy(alpha = 0.25f),
                                        Color(0xFF0D1B2A).copy(alpha = 0.2f),
                                        Color.Transparent
                                    ),
                                    center = Offset(w * 0.7f, h * 0.3f),
                                    radius = w * 0.8f
                                )
                            )
                            drawCircle(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        accentColor.copy(alpha = 0.15f),
                                        Color.Transparent
                                    ),
                                    center = Offset(w * 0.2f, h * 0.75f),
                                    radius = w * 0.6f
                                )
                            )
                        }
                )
            }
            BackgroundStyle.OBSIDIAN_MINIMAL -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF050608),
                                    Color(0xFF080A0E),
                                    Color(0xFF050608)
                                )
                            )
                        )
                )
            }
        }

        // Foreground content container
        content()
    }
}
