package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CyberBorder
import com.example.ui.theme.CyberBorderGlow
import com.example.ui.theme.GlassCard
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonRed
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun HudCard(
    modifier: Modifier = Modifier,
    borderColor: Color = CyberBorder,
    glowColor: Color = Color.Transparent,
    backgroundColor: Color = GlassCard,
    cornerRadius: Dp = 12.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val clickModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else {
        Modifier
    }

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .border(
                BorderStroke(1.dp, borderColor),
                RoundedCornerShape(cornerRadius)
            )
            .then(clickModifier),
        color = backgroundColor,
        shape = RoundedCornerShape(cornerRadius),
        tonalElevation = 2.dp
    ) {
        Box(
            modifier = Modifier.drawBehind {
                if (glowColor != Color.Transparent) {
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(glowColor.copy(alpha = 0.08f), Color.Transparent)
                        )
                    )
                }
            }
        ) {
            content()
        }
    }
}

@Composable
fun HudBadge(
    text: String,
    accentColor: Color = NeonCyan,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(accentColor.copy(alpha = 0.15f))
            .border(BorderStroke(0.8.dp, accentColor.copy(alpha = 0.5f)), RoundedCornerShape(4.dp))
            .padding(horizontal = 7.dp, vertical = 2.5.dp)
    ) {
        Text(
            text = text,
            fontFamily = FontFamily.Monospace,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp,
            color = accentColor
        )
    }
}

@Composable
fun HudHeader(
    title: String,
    code: String? = null,
    subtitle: String? = null,
    accentColor: Color = NeonRed,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(4.dp, 18.dp)
                        .background(accentColor)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title.uppercase(),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = TextPrimary
                )
            }
            if (code != null) {
                HudBadge(text = code, accentColor = accentColor)
            }
        }
        if (subtitle != null) {
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = TextSecondary,
                modifier = Modifier.padding(start = 12.dp, top = 2.dp)
            )
        }
    }
}

@Composable
fun HudButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    accentColor: Color = NeonRed,
    filled: Boolean = false,
    enabled: Boolean = true
) {
    val bg = if (filled) accentColor else accentColor.copy(alpha = 0.12f)
    val textColor = if (filled) Color.Black else TextPrimary
    val border = if (filled) BorderStroke(1.dp, accentColor) else BorderStroke(1.dp, accentColor.copy(alpha = 0.6f))

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (enabled) bg else Color(0x33222634))
            .border(border, RoundedCornerShape(8.dp))
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 11.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (filled) Color.Black else accentColor,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = text.uppercase(),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                letterSpacing = 0.8.sp,
                color = if (enabled) textColor else TextMuted
            )
        }
    }
}

@Composable
fun HudStatCard(
    label: String,
    value: String,
    subValue: String? = null,
    accentColor: Color = NeonCyan,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    HudCard(
        modifier = modifier,
        borderColor = CyberBorderGlow,
        glowColor = accentColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = label.uppercase(),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp,
                    color = TextSecondary,
                    letterSpacing = 0.5.sp
                )
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = accentColor
            )
            if (subValue != null) {
                Text(
                    text = subValue,
                    fontSize = 10.sp,
                    color = TextMuted
                )
            }
        }
    }
}

@Composable
fun HudSegmentedProgress(
    progress: Float, // 0f to 1f
    modifier: Modifier = Modifier,
    accentColor: Color = NeonRed
) {
    Box(
        modifier = modifier
            .height(24.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF0C0E14))
            .border(BorderStroke(1.2.dp, CyberBorderGlow), RoundedCornerShape(4.dp))
            .drawBehind {
                val w = size.width
                val h = size.height
                val activeWidth = (progress.coerceIn(0f, 1f)) * w

                // Draw filled segment
                drawRect(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            accentColor.copy(alpha = 0.4f),
                            accentColor
                        )
                    ),
                    size = androidx.compose.ui.geometry.Size(activeWidth, h)
                )

                // Diagonal hatchings across the entire bar
                val step = 10f
                var x = -h
                while (x < w) {
                    drawLine(
                        color = Color.Black.copy(alpha = 0.35f),
                        start = Offset(x, 0f),
                        end = Offset(x + h, h),
                        strokeWidth = 2.5f
                    )
                    x += step
                }
            }
    )
}
