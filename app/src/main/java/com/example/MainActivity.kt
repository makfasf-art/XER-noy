package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ToolCatalog
import com.example.model.AccentTheme
import com.example.model.ActivityLogEntry
import com.example.model.BackgroundStyle
import com.example.model.ToolItem
import com.example.model.UserProfile
import com.example.ui.components.FuturisticBackground
import com.example.ui.components.ToolRunnerSheet
import com.example.ui.screens.AkunScreen
import com.example.ui.screens.BeritaScreen
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.InformationScreen
import com.example.ui.screens.LoadingScreen
import com.example.ui.screens.SettingScreen
import com.example.ui.screens.ToolScreen
import com.example.ui.theme.CyberBorder
import com.example.ui.theme.CyberBorderGlow
import com.example.ui.theme.GlassDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.XERTheme
import kotlinx.coroutines.delay
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XERApp()
        }
    }
}

@Composable
fun XERApp() {
    // App State
    var isLoading by remember { mutableStateOf(true) }
    var currentTab by remember { mutableIntStateOf(0) }
    var backgroundStyle by remember { mutableStateOf(BackgroundStyle.BLACK_HOLE) }
    var accentTheme by remember { mutableStateOf(AccentTheme.CRIMSON) }
    var animationsEnabled by remember { mutableStateOf(true) }
    var soundEnabled by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var currentLanguage by remember { mutableStateOf("ID") }

    var userProfile by remember { mutableStateOf(UserProfile()) }
    var activeToolRunner by remember { mutableStateOf<ToolItem?>(null) }
    val favoriteToolIds = remember { mutableStateListOf("sec_pass_gen", "mth_sci_calc", "txt_base64_enc", "cnv_currency") }
    val recentToolIds = remember { mutableStateListOf("sec_pass_gen", "dev_json_fmt", "mth_bmi_calc") }
    val activityLogs = remember {
        mutableStateListOf(
            ActivityLogEntry(UUID.randomUUID().toString(), "Quantum Secure Password Gen", "SEC", System.currentTimeMillis() - 120000, "Entropy verified: 128 bits"),
            ActivityLogEntry(UUID.randomUUID().toString(), "JSON Formatter", "DEV", System.currentTimeMillis() - 360000, "Formatted 48 keys payload"),
            ActivityLogEntry(UUID.randomUUID().toString(), "Scientific Calculator", "MTH", System.currentTimeMillis() - 900000, "Trigonometric evaluation complete")
        )
    }

    var operationsCount by remember { mutableIntStateOf(142) }
    var sessionSeconds by remember { mutableLongStateOf(0L) }

    LaunchedEffect(isLoading) {
        if (!isLoading) {
            while (true) {
                delay(1000)
                sessionSeconds++
            }
        }
    }

    fun logActivity(toolName: String, categoryCode: String, details: String) {
        operationsCount++
        userProfile = userProfile.copy(operationsExecuted = userProfile.operationsExecuted + 1)
        activityLogs.add(0, ActivityLogEntry(UUID.randomUUID().toString(), toolName, categoryCode, System.currentTimeMillis(), details))
    }

    fun toggleFavorite(toolId: String) {
        if (favoriteToolIds.contains(toolId)) {
            favoriteToolIds.remove(toolId)
        } else {
            favoriteToolIds.add(toolId)
        }
    }

    fun openTool(tool: ToolItem) {
        if (!recentToolIds.contains(tool.id)) {
            recentToolIds.add(0, tool.id)
            if (recentToolIds.size > 10) recentToolIds.removeAt(recentToolIds.size - 1)
        }
        activeToolRunner = tool
    }

    // Hardware back handler
    BackHandler(enabled = activeToolRunner != null || currentTab != 0) {
        if (activeToolRunner != null) {
            activeToolRunner = null
        } else if (currentTab != 0) {
            currentTab = 0
        }
    }

    XERTheme(accentColor = accentTheme.color) {
        Crossfade(targetState = isLoading, label = "boot_crossfade") { loadingState ->
            if (loadingState) {
                // Permanent Cinematic Loading Screen (per user specification: MUST REMAIN and NOT BE REPLACED)
                LoadingScreen(
                    onLoadingComplete = {
                        isLoading = false
                    }
                )
            } else {
                // Main Application Workspace with selectable futuristic background
                FuturisticBackground(
                    style = backgroundStyle,
                    animationsEnabled = animationsEnabled,
                    accentColor = accentTheme.color
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color.Transparent,
                        contentWindowInsets = WindowInsets.safeDrawing,
                        bottomBar = {
                            FuturisticNavBar(
                                currentTab = currentTab,
                                onSelectTab = { currentTab = it },
                                accentColor = accentTheme.color
                            )
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            AnimatedContent(
                                targetState = currentTab,
                                transitionSpec = { fadeIn() togetherWith fadeOut() },
                                label = "tab_content"
                            ) { tab ->
                                when (tab) {
                                    0 -> HomeScreen(
                                        userProfile = userProfile,
                                        allTools = ToolCatalog.allTools,
                                        favoriteIds = favoriteToolIds.toSet(),
                                        recentToolIds = recentToolIds.toList(),
                                        onSelectTool = ::openTool,
                                        onToggleFavorite = ::toggleFavorite,
                                        onNavigateToTab = { currentTab = it },
                                        accentColor = accentTheme.color
                                    )
                                    1 -> DashboardScreen(
                                        allTools = ToolCatalog.allTools,
                                        favoriteIds = favoriteToolIds.toSet(),
                                        activityLogs = activityLogs.toList(),
                                        operationsCount = operationsCount,
                                        sessionSeconds = sessionSeconds,
                                        onSelectTool = ::openTool,
                                        accentColor = accentTheme.color
                                    )
                                    2 -> ToolScreen(
                                        allTools = ToolCatalog.allTools,
                                        favoriteIds = favoriteToolIds.toSet(),
                                        onSelectTool = ::openTool,
                                        onToggleFavorite = ::toggleFavorite,
                                        accentColor = accentTheme.color
                                    )
                                    3 -> BeritaScreen(
                                        accentColor = accentTheme.color
                                    )
                                    4 -> AkunScreen(
                                        userProfile = userProfile,
                                        onUpdateProfile = { userProfile = it },
                                        allTools = ToolCatalog.allTools,
                                        favoriteIds = favoriteToolIds.toSet(),
                                        activityLogs = activityLogs.toList(),
                                        onClearLogs = { activityLogs.clear() },
                                        onSelectTool = ::openTool,
                                        accentColor = accentTheme.color
                                    )
                                    5 -> SettingScreen(
                                        currentBg = backgroundStyle,
                                        onSelectBg = { backgroundStyle = it },
                                        currentAccent = accentTheme,
                                        onSelectAccent = { accentTheme = it },
                                        animationsEnabled = animationsEnabled,
                                        onToggleAnimations = { animationsEnabled = it },
                                        soundEnabled = soundEnabled,
                                        onToggleSound = { soundEnabled = it },
                                        notificationsEnabled = notificationsEnabled,
                                        onToggleNotifications = { notificationsEnabled = it },
                                        currentLanguage = currentLanguage,
                                        onSelectLanguage = { currentLanguage = it },
                                        onReplayBoot = { isLoading = true },
                                        onResetSettings = {
                                            backgroundStyle = BackgroundStyle.BLACK_HOLE
                                            accentTheme = AccentTheme.CRIMSON
                                            animationsEnabled = true
                                            soundEnabled = true
                                            notificationsEnabled = true
                                        },
                                        onNavigateToInfo = { currentTab = 6 }
                                    )
                                    6 -> InformationScreen(
                                        accentColor = accentTheme.color
                                    )
                                }
                            }
                        }
                    }

                    // Interactive Tool Runner Bottom Sheet
                    activeToolRunner?.let { tool ->
                        ToolRunnerSheet(
                            tool = tool,
                            isFavorite = favoriteToolIds.contains(tool.id),
                            onToggleFavorite = { toggleFavorite(tool.id) },
                            onDismiss = { activeToolRunner = null },
                            onLogActivity = ::logActivity,
                            accentColor = accentTheme.color
                        )
                    }
                }
            }
        }
    }
}

data class NavItem(val title: String, val icon: ImageVector)

@Composable
fun FuturisticNavBar(
    currentTab: Int,
    onSelectTab: (Int) -> Unit,
    accentColor: Color
) {
    val items = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Dashboard", Icons.Default.Dashboard),
        NavItem("Tool", Icons.Default.Build),
        NavItem("Berita", Icons.Default.Newspaper),
        NavItem("Akun", Icons.Default.Person),
        NavItem("Setting", Icons.Default.Settings),
        NavItem("Information", Icons.Default.Info)
    )

    Surface(
        color = GlassDark,
        tonalElevation = 6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = CyberBorderGlow
            )
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = currentTab == index
                val itemBg = if (isSelected) accentColor.copy(alpha = 0.15f) else Color.Transparent
                val borderStroke = if (isSelected) accentColor.copy(alpha = 0.6f) else Color.Transparent

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(itemBg)
                        .border(1.dp, borderStroke, RoundedCornerShape(8.dp))
                        .clickable { onSelectTab(index) }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (isSelected) accentColor else TextMuted,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.title,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 10.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) TextPrimary else TextMuted
                    )
                }
            }
        }
    }
}
