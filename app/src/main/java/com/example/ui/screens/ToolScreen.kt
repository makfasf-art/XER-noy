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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import com.example.model.ToolCategory
import com.example.model.ToolItem
import com.example.ui.components.HudBadge
import com.example.ui.components.HudCard
import com.example.ui.components.HudHeader
import com.example.ui.theme.CyberBorder
import com.example.ui.theme.CyberBorderGlow
import com.example.ui.theme.CyberSurfaceVariant
import com.example.ui.theme.GlassCard
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.NeonRed
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun ToolScreen(
    allTools: List<ToolItem>,
    favoriteIds: Set<String>,
    onSelectTool: (ToolItem) -> Unit,
    onToggleFavorite: (String) -> Unit,
    accentColor: Color = NeonRed
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<ToolCategory?>(null) }
    var showOnlyFavorites by remember { mutableStateOf(false) }

    val filteredTools by remember(searchQuery, selectedCategory, showOnlyFavorites, favoriteIds) {
        derivedStateOf {
            allTools.filter { tool ->
                val matchesCategory = selectedCategory == null || tool.category == selectedCategory
                val matchesFavorite = !showOnlyFavorites || favoriteIds.contains(tool.id)
                val matchesSearch = searchQuery.isBlank() ||
                        tool.name.contains(searchQuery, ignoreCase = true) ||
                        tool.description.contains(searchQuery, ignoreCase = true) ||
                        tool.category.title.contains(searchQuery, ignoreCase = true) ||
                        tool.category.code.contains(searchQuery, ignoreCase = true)

                matchesCategory && matchesFavorite && matchesSearch
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Header with active count
        HudHeader(
            title = "Arsenal & Tool Matrix",
            code = "${allTools.size} TOOLS",
            subtitle = "Categorized tactical utilities, calculators, and security modules",
            accentColor = accentColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Search Bar & Favorites Filter Toggle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Filter 450 features...", fontSize = 13.sp, color = TextMuted) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = accentColor) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear", tint = TextMuted)
                        }
                    }
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

            // Favorites Filter Button
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (showOnlyFavorites) NeonRed.copy(alpha = 0.2f) else CyberSurfaceVariant)
                    .border(
                        1.dp,
                        if (showOnlyFavorites) NeonRed else CyberBorder,
                        RoundedCornerShape(10.dp)
                    )
                    .clickable { showOnlyFavorites = !showOnlyFavorites },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (showOnlyFavorites) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Filter Favorites",
                    tint = if (showOnlyFavorites) NeonRed else TextSecondary,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Horizontal Category Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // "ALL" category chip
            val isAllSelected = selectedCategory == null
            CategoryChip(
                label = "ALL (450)",
                isSelected = isAllSelected,
                accentColor = accentColor,
                onClick = { selectedCategory = null }
            )

            ToolCategory.values().forEach { cat ->
                val count = allTools.count { it.category == cat }
                val isSelected = selectedCategory == cat
                CategoryChip(
                    label = "${cat.code} ($count)",
                    isSelected = isSelected,
                    accentColor = accentColor,
                    onClick = { selectedCategory = cat }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Results summary banner
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "MATCHING TOOLS: ${filteredTools.size}",
                fontFamily = FontFamily.Monospace,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = TextSecondary
            )
            if (selectedCategory != null) {
                Text(
                    text = selectedCategory?.title?.uppercase() ?: "",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp,
                    color = NeonCyan
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Scrollable list of tools
        if (filteredTools.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("No matching tools found.", fontFamily = FontFamily.Monospace, fontSize = 13.sp, color = TextMuted)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Try resetting category filter or search query.", fontSize = 11.sp, color = TextSecondary)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredTools, key = { it.id }) { tool ->
                    ToolRowItem(
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
}

@Composable
private fun CategoryChip(
    label: String,
    isSelected: Boolean,
    accentColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(if (isSelected) accentColor else CyberSurfaceVariant)
            .border(
                1.dp,
                if (isSelected) accentColor else CyberBorder,
                RoundedCornerShape(6.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 7.dp)
    ) {
        Text(
            text = label,
            fontFamily = FontFamily.Monospace,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) Color.Black else TextPrimary
        )
    }
}

@Composable
private fun ToolRowItem(
    tool: ToolItem,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onSelect: () -> Unit,
    accentColor: Color
) {
    HudCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = if (isFavorite) NeonPurple.copy(alpha = 0.5f) else CyberBorder,
        onClick = onSelect
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                HudBadge(text = tool.category.code, accentColor = accentColor)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = tool.name,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = tool.description,
                        fontSize = 11.sp,
                        color = TextSecondary,
                        maxLines = 1
                    )
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) NeonRed else TextMuted,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Execute",
                    tint = accentColor,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
