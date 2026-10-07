package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.NewsCatalog
import com.example.model.NewsItem
import com.example.ui.components.HudBadge
import com.example.ui.components.HudButton
import com.example.ui.components.HudCard
import com.example.ui.components.HudHeader
import com.example.ui.theme.CyberBorder
import com.example.ui.theme.CyberBorderGlow
import com.example.ui.theme.CyberSurface
import com.example.ui.theme.CyberSurfaceVariant
import com.example.ui.theme.GlassCard
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.NeonRed
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeritaScreen(
    accentColor: Color = NeonRed
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("ALL") }
    var selectedArticle by remember { mutableStateOf<NewsItem?>(null) }
    var bookmarkedIds by remember { mutableStateOf(setOf<String>()) }

    val categories = listOf("ALL", "Quantum & AI", "Cyber Security", "Space & Universe", "Mobile Systems", "Deep Tech", "Hardware")

    val filteredNews = remember(searchQuery, selectedCategory) {
        NewsCatalog.sampleNews.filter { item ->
            val matchesCategory = selectedCategory == "ALL" || item.category.equals(selectedCategory, ignoreCase = true)
            val matchesSearch = searchQuery.isBlank() ||
                    item.title.contains(searchQuery, ignoreCase = true) ||
                    item.summary.contains(searchQuery, ignoreCase = true) ||
                    item.tags.any { it.contains(searchQuery, ignoreCase = true) }
            matchesCategory && matchesSearch
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        HudHeader(
            title = "Cyber Intelligence & News Wire",
            code = "INTEL-FEED",
            subtitle = "Encrypted field dispatches, quantum computing, and cyber security",
            accentColor = accentColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search dispatches, quantum, exploits...", fontSize = 13.sp, color = TextMuted) },
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

        Spacer(modifier = Modifier.height(12.dp))

        // Category Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { cat ->
                val isSelected = selectedCategory == cat
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (isSelected) accentColor else CyberSurfaceVariant)
                        .border(1.dp, if (isSelected) accentColor else CyberBorder, RoundedCornerShape(6.dp))
                        .clickable { selectedCategory = cat }
                        .padding(horizontal = 12.dp, vertical = 7.dp)
                ) {
                    Text(
                        text = cat.uppercase(),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) Color.Black else TextPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // News List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Spotlight / Top Dispatch Card
            if (filteredNews.isNotEmpty() && searchQuery.isBlank() && selectedCategory == "ALL") {
                val featured = filteredNews.first()
                item {
                    HudCard(
                        modifier = Modifier.fillMaxWidth(),
                        borderColor = CyberBorderGlow,
                        glowColor = accentColor,
                        onClick = { selectedArticle = featured }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                HudBadge(text = "TOP DISPATCH", accentColor = NeonRed)
                                Text(
                                    text = featured.clearanceLevel,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 10.sp,
                                    color = NeonCyan
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = featured.title,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = TextPrimary,
                                lineHeight = 22.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = featured.summary,
                                fontSize = 12.sp,
                                color = TextSecondary,
                                lineHeight = 18.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${featured.author} • ${featured.timestamp}",
                                    fontSize = 10.sp,
                                    color = TextMuted
                                )
                                Text(
                                    text = "${featured.readMinutes} min read",
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 10.sp,
                                    color = NeonGreen
                                )
                            }
                        }
                    }
                }
            }

            // Normal News Cards
            items(if (searchQuery.isBlank() && selectedCategory == "ALL") filteredNews.drop(1) else filteredNews) { item ->
                NewsArticleCard(
                    article = item,
                    isBookmarked = bookmarkedIds.contains(item.id),
                    onToggleBookmark = {
                        bookmarkedIds = if (bookmarkedIds.contains(item.id)) {
                            bookmarkedIds - item.id
                        } else {
                            bookmarkedIds + item.id
                        }
                    },
                    onClick = { selectedArticle = item },
                    accentColor = accentColor
                )
            }
        }
    }

    // Full Article Detail Sheet
    selectedArticle?.let { article ->
        NewsDetailSheet(
            article = article,
            isBookmarked = bookmarkedIds.contains(article.id),
            onToggleBookmark = {
                bookmarkedIds = if (bookmarkedIds.contains(article.id)) {
                    bookmarkedIds - article.id
                } else {
                    bookmarkedIds + article.id
                }
            },
            onDismiss = { selectedArticle = null },
            accentColor = accentColor
        )
    }
}

@Composable
private fun NewsArticleCard(
    article: NewsItem,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onClick: () -> Unit,
    accentColor: Color
) {
    HudCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = CyberBorder,
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HudBadge(text = article.category.uppercase(), accentColor = NeonCyan)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${article.readMinutes} min read",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 10.sp,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    IconButton(
                        onClick = onToggleBookmark,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) accentColor else TextMuted,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.title,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = TextPrimary,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = article.summary,
                fontSize = 12.sp,
                color = TextSecondary,
                maxLines = 2,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${article.author} • ${article.timestamp}",
                    fontSize = 10.sp,
                    color = TextMuted
                )
                Text(
                    text = "READ MORE →",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = accentColor
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewsDetailSheet(
    article: NewsItem,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onDismiss: () -> Unit,
    accentColor: Color
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = CyberSurface,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .background(CyberSurface)
                .padding(20.dp)
        ) {
            // Header bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HudBadge(text = article.clearanceLevel, accentColor = NeonGreen)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            clipboard.setPrimaryClip(ClipData.newPlainText(article.title, "${article.title}\n\n${article.content}"))
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(Icons.Default.Share, contentDescription = "Share", tint = TextSecondary, modifier = Modifier.size(20.dp))
                    }
                    IconButton(onClick = onToggleBookmark, modifier = Modifier.size(36.dp)) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) accentColor else TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    IconButton(onClick = onDismiss, modifier = Modifier.size(36.dp)) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = TextSecondary, modifier = Modifier.size(20.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Text(
                        text = article.title,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextPrimary,
                        lineHeight = 24.sp
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "BY ${article.author.uppercase()}",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            color = NeonCyan
                        )
                        Text(
                            text = "${article.timestamp} • ${article.readMinutes} min read",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    }
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(CyberBorder)
                    )
                }

                item {
                    Text(
                        text = article.content,
                        fontSize = 14.sp,
                        color = TextPrimary,
                        lineHeight = 22.sp
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "SECURITY TAGS & METADATA",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 10.sp,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        article.tags.forEach { tag ->
                            HudBadge(text = "#$tag", accentColor = NeonPurple)
                        }
                    }
                }
            }
        }
    }
}
