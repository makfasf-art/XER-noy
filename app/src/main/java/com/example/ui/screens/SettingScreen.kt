package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AccentTheme
import com.example.model.BackgroundStyle
import com.example.ui.components.HudBadge
import com.example.ui.components.HudButton
import com.example.ui.components.HudCard
import com.example.ui.components.HudHeader
import com.example.ui.theme.CyberBorder
import com.example.ui.theme.CyberBorderGlow
import com.example.ui.theme.CyberSurface
import com.example.ui.theme.CyberSurfaceVariant
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.NeonRed
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun SettingScreen(
    currentBg: BackgroundStyle,
    onSelectBg: (BackgroundStyle) -> Unit,
    currentAccent: AccentTheme,
    onSelectAccent: (AccentTheme) -> Unit,
    animationsEnabled: Boolean,
    onToggleAnimations: (Boolean) -> Unit,
    soundEnabled: Boolean,
    onToggleSound: (Boolean) -> Unit,
    notificationsEnabled: Boolean,
    onToggleNotifications: (Boolean) -> Unit,
    currentLanguage: String,
    onSelectLanguage: (String) -> Unit,
    onReplayBoot: () -> Unit,
    onResetSettings: () -> Unit,
    onNavigateToInfo: () -> Unit
) {
    var showResetConfirm by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            HudHeader(
                title = "System Settings & Configuration",
                code = "CONFIG-SYS",
                subtitle = "Customization, dark backgrounds, telemetry, and terminal rules",
                accentColor = currentAccent.color
            )
        }

        // Alternative Futuristic Backgrounds
        item {
            Column {
                HudHeader(
                    title = "Alternative Background Styles",
                    code = "LATAR",
                    subtitle = "Selectable dark futuristic backdrops with subtle motion",
                    accentColor = currentAccent.color
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    BackgroundStyle.values().forEach { style ->
                        val isSelected = style == currentBg
                        HudCard(
                            modifier = Modifier.fillMaxWidth(),
                            borderColor = if (isSelected) currentAccent.color else CyberBorder,
                            glowColor = if (isSelected) currentAccent.color else Color.Transparent,
                            onClick = { onSelectBg(style) }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(CircleShape)
                                            .background(if (isSelected) currentAccent.color else Color.Transparent)
                                            .border(2.dp, if (isSelected) currentAccent.color else TextMuted, CircleShape)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column {
                                        Text(
                                            text = style.displayName,
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = TextPrimary
                                        )
                                        Text(
                                            text = style.description,
                                            fontSize = 11.sp,
                                            color = TextSecondary
                                        )
                                    }
                                }
                                if (isSelected) {
                                    HudBadge(text = "ACTIVE", accentColor = currentAccent.color)
                                }
                            }
                        }
                    }
                }
            }
        }

        // Theme Customization (Neon Accents)
        item {
            HudCard(
                borderColor = CyberBorder,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "NEON ACCENT PALETTE",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AccentTheme.values().forEach { accent ->
                            val isSelected = accent == currentAccent
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { onSelectAccent(accent) }
                                    .padding(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .clip(CircleShape)
                                        .background(accent.color)
                                        .border(
                                            if (isSelected) 3.dp else 1.dp,
                                            if (isSelected) Color.White else CyberBorderGlow,
                                            CircleShape
                                        )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = accent.displayName.split(" ").last(),
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 10.sp,
                                    color = if (isSelected) accent.color else TextMuted,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }
        }

        // System Toggles (Animations, Audio, Notifications)
        item {
            HudCard(
                borderColor = CyberBorder,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(
                        text = "TERMINAL BEHAVIOR",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )

                    // Animation Toggle
                    SettingToggleRow(
                        title = "Cinematic Animations",
                        subtitle = "Enable 60FPS spring motion and scanning lasers",
                        icon = Icons.Default.Animation,
                        checked = animationsEnabled,
                        onCheckedChange = onToggleAnimations,
                        accentColor = currentAccent.color
                    )

                    // Sound & Haptic Toggle
                    SettingToggleRow(
                        title = "Sound & Haptic Feedback",
                        subtitle = "Auditory UI tone synth and actuator vibration",
                        icon = Icons.Default.VolumeUp,
                        checked = soundEnabled,
                        onCheckedChange = onToggleSound,
                        accentColor = currentAccent.color
                    )

                    // Notification Setting
                    SettingToggleRow(
                        title = "System Telemetry Alerts",
                        subtitle = "Receive encrypted background updates",
                        icon = Icons.Default.Notifications,
                        checked = notificationsEnabled,
                        onCheckedChange = onToggleNotifications,
                        accentColor = currentAccent.color
                    )
                }
            }
        }

        // Language Selection
        item {
            HudCard(
                borderColor = CyberBorder,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "LANGUAGE & LOCALIZATION",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        LanguageOption(
                            title = "Bahasa Indonesia",
                            code = "ID",
                            isSelected = currentLanguage == "ID",
                            accentColor = currentAccent.color,
                            onClick = { onSelectLanguage("ID") },
                            modifier = Modifier.weight(1f)
                        )
                        LanguageOption(
                            title = "English (Global)",
                            code = "EN",
                            isSelected = currentLanguage == "EN",
                            accentColor = currentAccent.color,
                            onClick = { onSelectLanguage("EN") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Cinematic Boot Sequence Replay & About App
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                HudButton(
                    text = "REPLAY CINEMATIC BOOT HUD",
                    onClick = onReplayBoot,
                    icon = Icons.Default.RestartAlt,
                    accentColor = NeonCyan,
                    filled = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    HudButton(
                        text = "About XER",
                        onClick = onNavigateToInfo,
                        icon = Icons.Default.Info,
                        accentColor = NeonGreen,
                        modifier = Modifier.weight(1f)
                    )
                    HudButton(
                        text = "Reset Settings",
                        onClick = { showResetConfirm = true },
                        accentColor = NeonRed,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }

    if (showResetConfirm) {
        AlertDialog(
            onDismissRequest = { showResetConfirm = false },
            containerColor = CyberSurface,
            title = {
                Text(
                    text = "FACTORY RESET CONFIRMATION",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = NeonRed
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to restore default theme, accent color, and background settings?",
                    fontSize = 13.sp,
                    color = TextPrimary
                )
            },
            confirmButton = {
                HudButton(
                    text = "CONFIRM RESET",
                    onClick = {
                        onResetSettings()
                        showResetConfirm = false
                    },
                    accentColor = NeonRed,
                    filled = true
                )
            },
            dismissButton = {
                HudButton(
                    text = "CANCEL",
                    onClick = { showResetConfirm = false },
                    accentColor = TextSecondary
                )
            }
        )
    }
}

@Composable
private fun SettingToggleRow(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    accentColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = TextPrimary
                )
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = TextMuted
                )
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Black,
                checkedTrackColor = accentColor,
                uncheckedThumbColor = TextMuted,
                uncheckedTrackColor = CyberSurfaceVariant
            )
        )
    }
}

@Composable
private fun LanguageOption(
    title: String,
    code: String,
    isSelected: Boolean,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) accentColor.copy(alpha = 0.15f) else CyberSurfaceVariant)
            .border(
                1.dp,
                if (isSelected) accentColor else CyberBorder,
                RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            HudBadge(text = code, accentColor = if (isSelected) accentColor else TextMuted)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                fontFamily = FontFamily.Monospace,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) TextPrimary else TextSecondary
            )
        }
    }
}
