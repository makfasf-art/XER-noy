package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import com.example.model.ToolItem
import com.example.model.UserProfile
import com.example.ui.components.HudBadge
import com.example.ui.components.HudButton
import com.example.ui.components.HudCard
import com.example.ui.components.HudHeader
import com.example.ui.theme.CyberBorder
import com.example.ui.theme.CyberBorderGlow
import com.example.ui.theme.CyberSurfaceVariant
import com.example.ui.theme.GlassCard
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.NeonRed
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun HomeScreen(
    userProfile: UserProfile,
    allTools: List<ToolItem>,
    favoriteIds: Set<String>,
    recentToolIds: List<String>,
    onSelectTool: (ToolItem) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onNavigateToTab: (Int) -> Unit,
    accentColor: Color = NeonRed
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredTools = remember(searchQuery, allTools) {
        if (searchQuery.isBlank()) emptyList()
        else allTools.filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
            it.description.contains(searchQuery, ignoreCase = true) ||
            it.category.title.contains(searchQuery, ignoreCase = true)
        }.take(8)
    }

    val featuredTools = remember(allTools) {
        allTools.filter { it.isFeatured }.take(6)
    }

    val recentTools = remember(recentToolIds, allTools) {
        recentToolIds.mapNotNull { id -> allTools.find { it.id == id } }.take(5)
    }

    val popularTools = remember(allTools) {
        listOf(
            "txt_base64_enc", "sec_pass_gen", "mth_sci_calc",
            "cnv_currency", "dev_json_fmt", "med_synth_tone"
        ).mapNotNull { id -> allTools.find { it.id == id } }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Operative Greeting & Profile Banner
        item {
            HudCard(
                borderColor = CyberBorderGlow,
                glowColor = accentColor,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(CyberSurfaceVariant)
                                    .border(1.5.dp, accentColor, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "X",
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = accentColor
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = userProfile.callsign,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = TextPrimary
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(7.dp)
                                            .clip(CircleShape)
                                            .background(NeonGreen)
                                    )
                                }
                                Text(
                                    text = "${userProfile.codeId} // ${userProfile.clearanceLevel}",
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }
                        }
                        HudBadge(text = "ONLINE", accentColor = NeonGreen)
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(CyberBorder)
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("SECURITY CLEARANCE", fontFamily = FontFamily.Monospace, fontSize = 9.sp, color = TextMuted)
                            Text("${userProfile.securityScore}% ENCRYPTED", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = NeonCyan)
                        }
                        Column {
                            Text("ACTIVE TOOLS", fontFamily = FontFamily.Monospace, fontSize = 9.sp, color = TextMuted)
                            Text("450 READY", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = accentColor)
                        }
                        Column {
                            Text("SYSTEM STATE", fontFamily = FontFamily.Monospace, fontSize = 9.sp, color = TextMuted)
                            Text("OPTIMAL", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = NeonGreen)
                        }
                    }
                }
            }
        }

        // Global Search Bar
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Search across 450+ tools, crypto, calculators...",
                        fontSize = 13.sp,
                        color = TextMuted
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = accentColor
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = GlassCard,
                    unfocusedContainerColor = GlassCard,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    focusedIndicatorColor = accentColor,
                    unfocusedIndicatorColor = CyberBorder
                ),
                singleLine = true
            )

            // Instant search results
            if (filteredTools.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "SEARCH RESULTS (${filteredTools.size})",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 10.sp,
                        color = accentColor,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    filteredTools.forEach { tool ->
                        ToolSearchItem(
                            tool = tool,
                            onSelect = {
                                searchQuery = ""
                                onSelectTool(tool)
                            },
                            accentColor = accentColor
                        )
                    }
                }
            }
        }

        // Quick Actions Row
        item {
            Column {
                HudHeader(title = "Quick Actions", code = "ACT-01", accentColor = accentColor)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickActionChip(
                        label = "All Tools",
                        icon = Icons.Default.Build,
                        accentColor = accentColor,
                        onClick = { onNavigateToTab(2) } // Tool tab
                    )
                    QuickActionChip(
                        label = "Dashboard",
                        icon = Icons.Default.Dashboard,
                        accentColor = NeonCyan,
                        onClick = { onNavigateToTab(1) } // Dashboard tab
                    )
                    QuickActionChip(
                        label = "Password Gen",
                        icon = Icons.Default.VpnKey,
                        accentColor = NeonPurple,
                        onClick = { allTools.find { it.id == "sec_pass_gen" }?.let(onSelectTool) }
                    )
                    QuickActionChip(
                        label = "Scientific Calc",
                        icon = Icons.Default.Calculate,
                        accentColor = NeonGreen,
                        onClick = { allTools.find { it.id == "mth_sci_calc" }?.let(onSelectTool) }
                    )
                    QuickActionChip(
                        label = "News Wire",
                        icon = Icons.Default.Newspaper,
                        accentColor = NeonRed,
                        onClick = { onNavigateToTab(3) } // News tab
                    )
                }
            }
        }

        // Featured Tools Carousel
        item {
            Column {
                HudHeader(title = "Featured Utilities", code = "CORE-6", accentColor = accentColor)
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(featuredTools) { tool ->
                        FeaturedToolCard(
                            tool = tool,
                            isFavorite = favoriteIds.contains(tool.id),
                            onToggleFavorite = { onToggleFavorite(tool.id) },
                            onSelect = { onSelectTool(tool) },
                            accentColor = accentColor
                        )
                    }
                }
            }
        }

        // Recently Used Tools
        if (recentTools.isNotEmpty()) {
            item {
                Column {
                    HudHeader(title = "Recently Executed", code = "LOG-REC", accentColor = NeonCyan)
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        recentTools.forEach { tool ->
                            ToolCompactRow(
                                tool = tool,
                                isFavorite = favoriteIds.contains(tool.id),
                                onToggleFavorite = { onToggleFavorite(tool.id) },
                                onSelect = { onSelectTool(tool) },
                                accentColor = NeonCyan
                            )
                        }
                    }
                }
            }
        }

        // Popular Features
        item {
            Column {
                HudHeader(title = "Popular Operations", code = "POP-SYS", accentColor = NeonPurple)
                Spacer(modifier = Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    popularTools.forEach { tool ->
                        ToolCompactRow(
                            tool = tool,
                            isFavorite = favoriteIds.contains(tool.id),
                            onToggleFavorite = { onToggleFavorite(tool.id) },
                            onSelect = { onSelectTool(tool) },
                            accentColor = NeonPurple
                        )
                    }
                }
            }
        }

        // Shortcuts Banner to Other Screens
        item {
            HudCard(
                borderColor = CyberBorderGlow,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "TERMINAL NAVIGATION SHORTCUTS",
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
                        ShortcutNavButton("Dashboard", Icons.Default.Dashboard, NeonCyan) { onNavigateToTab(1) }
                        ShortcutNavButton("450 Tools", Icons.Default.Build, accentColor) { onNavigateToTab(2) }
                        ShortcutNavButton("Berita", Icons.Default.Newspaper, NeonPurple) { onNavigateToTab(3) }
                        ShortcutNavButton("Settings", Icons.Default.Settings, NeonGreen) { onNavigateToTab(5) }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuickActionChip(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(CyberSurfaceVariant)
            .border(1.dp, CyberBorderGlow, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
        }
    }
}

@Composable
private fun FeaturedToolCard(
    tool: ToolItem,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onSelect: () -> Unit,
    accentColor: Color
) {
    HudCard(
        modifier = Modifier
            .width(220.dp)
            .height(150.dp),
        borderColor = CyberBorderGlow,
        glowColor = accentColor,
        onClick = onSelect
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                HudBadge(text = tool.category.code, accentColor = accentColor)
                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) NeonRed else TextMuted,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Column {
                Text(
                    text = tool.name,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = TextPrimary,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = tool.description,
                    fontSize = 11.sp,
                    color = TextSecondary,
                    maxLines = 2
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "EXECUTE",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = accentColor
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}

@Composable
private fun ToolCompactRow(
    tool: ToolItem,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onSelect: () -> Unit,
    accentColor: Color
) {
    HudCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = CyberBorder,
        onClick = onSelect
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                HudBadge(text = tool.category.code, accentColor = accentColor)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = tool.name,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = TextPrimary,
                        maxLines = 1
                    )
                    Text(
                        text = tool.description,
                        fontSize = 11.sp,
                        color = TextMuted,
                        maxLines = 1
                    )
                }
            }
            IconButton(
                onClick = onToggleFavorite,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) NeonRed else TextMuted,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
private fun ToolSearchItem(
    tool: ToolItem,
    onSelect: () -> Unit,
    accentColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(CyberSurfaceVariant)
            .border(1.dp, CyberBorder, RoundedCornerShape(8.dp))
            .clickable(onClick = onSelect)
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                HudBadge(text = tool.category.code, accentColor = accentColor)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = tool.name,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    color = TextPrimary
                )
            }
            Text(
                text = "OPEN",
                fontFamily = FontFamily.Monospace,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = accentColor
            )
        }
    }
}

@Composable
private fun ShortcutNavButton(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.15f))
                .border(1.dp, color, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = color,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontFamily = FontFamily.Monospace,
            fontSize = 10.sp,
            color = TextSecondary
        )
    }
}
