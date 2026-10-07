package com.example.ui.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.AlertDialog
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
import com.example.model.ActivityLogEntry
import com.example.model.ToolItem
import com.example.model.UserProfile
import com.example.ui.components.HudBadge
import com.example.ui.components.HudButton
import com.example.ui.components.HudCard
import com.example.ui.components.HudHeader
import com.example.ui.components.HudStatCard
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AkunScreen(
    userProfile: UserProfile,
    onUpdateProfile: (UserProfile) -> Unit,
    allTools: List<ToolItem>,
    favoriteIds: Set<String>,
    activityLogs: List<ActivityLogEntry>,
    onClearLogs: () -> Unit,
    onSelectTool: (ToolItem) -> Unit,
    accentColor: Color = NeonRed
) {
    var showEditDialog by remember { mutableStateOf(false) }

    val favoriteTools = remember(favoriteIds, allTools) {
        favoriteIds.mapNotNull { id -> allTools.find { it.id == id } }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Operative Identity Header
        item {
            HudHeader(
                title = "Operative Identity & Clearance",
                code = userProfile.codeId,
                subtitle = "User credentials, access levels, and execution telemetry",
                accentColor = accentColor
            )
        }

        // Futuristic Operative Card
        item {
            HudCard(
                borderColor = CyberBorderGlow,
                glowColor = accentColor,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                                    .background(CyberSurfaceVariant)
                                    .border(2.dp, accentColor, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "X-9",
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = accentColor
                                )
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(
                                    text = userProfile.callsign,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp,
                                    color = TextPrimary
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = userProfile.email,
                                    fontSize = 12.sp,
                                    color = TextSecondary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = userProfile.clearanceLevel,
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 11.sp,
                                    color = NeonCyan
                                )
                            }
                        }

                        IconButton(onClick = { showEditDialog = true }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Profile",
                                tint = accentColor
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(CyberBorder))
                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("SECURITY RATING", fontFamily = FontFamily.Monospace, fontSize = 9.sp, color = TextMuted)
                            Text("${userProfile.securityScore}% ENCRYPTED", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = NeonGreen)
                        }
                        Column {
                            Text("CLEARANCE CLASS", fontFamily = FontFamily.Monospace, fontSize = 9.sp, color = TextMuted)
                            Text("TOP SECRET // OMEGA", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = accentColor)
                        }
                        Column {
                            Text("STATUS", fontFamily = FontFamily.Monospace, fontSize = 9.sp, color = TextMuted)
                            Text("VERIFIED", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = NeonCyan)
                        }
                    }
                }
            }
        }

        // Account Statistics Cards
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                    HudStatCard(
                        label = "Total Executions",
                        value = "${userProfile.operationsExecuted}",
                        subValue = "All-time Operations",
                        accentColor = NeonCyan,
                        icon = Icons.Default.VerifiedUser,
                        modifier = Modifier.weight(1f)
                    )
                    HudStatCard(
                        label = "Badges Unlocked",
                        value = "${userProfile.badges.size} / 4",
                        subValue = "All Achievements",
                        accentColor = NeonPurple,
                        icon = Icons.Default.MilitaryTech,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Operative Badges
        item {
            HudCard(
                borderColor = CyberBorder,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "SECURITY CREDENTIALS & BADGES",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        userProfile.badges.forEach { badge ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(CyberSurfaceVariant)
                                    .border(1.dp, CyberBorderGlow, RoundedCornerShape(8.dp))
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Default.Security,
                                        contentDescription = null,
                                        tint = accentColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = badge,
                                        fontFamily = FontFamily.Monospace,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Pinned Favorite Tools
        item {
            Column {
                HudHeader(title = "Pinned Favorites (${favoriteTools.size})", code = "FAV-LIST", accentColor = NeonCyan)
                Spacer(modifier = Modifier.height(10.dp))
                if (favoriteTools.isEmpty()) {
                    HudCard(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                            Text("No tools pinned. Tap the star icon on any tool to save it here.", fontSize = 12.sp, color = TextMuted)
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        favoriteTools.forEach { tool ->
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
                                        HudBadge(text = tool.category.code, accentColor = NeonCyan)
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Column {
                                            Text(tool.name, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextPrimary)
                                            Text(tool.description, fontSize = 11.sp, color = TextMuted, maxLines = 1)
                                        }
                                    }
                                    Text("OPEN", fontFamily = FontFamily.Monospace, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = NeonCyan)
                                }
                            }
                        }
                    }
                }
            }
        }

        // Operative Activity History
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HudHeader(title = "Operation Logs", code = "AUDIT-LOG", accentColor = NeonGreen, modifier = Modifier.weight(1f))
                    if (activityLogs.isNotEmpty()) {
                        IconButton(onClick = onClearLogs, modifier = Modifier.size(32.dp)) {
                            Icon(Icons.Default.DeleteOutline, contentDescription = "Clear Logs", tint = TextMuted, modifier = Modifier.size(18.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (activityLogs.isEmpty()) {
                    HudCard(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                            Text("Audit log clear. Zero trace recorded.", fontSize = 12.sp, color = TextMuted)
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        activityLogs.take(8).forEach { log ->
                            val timeStr = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date(log.timestamp))
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
                                    Text(timeStr, fontFamily = FontFamily.Monospace, fontSize = 9.sp, color = TextSecondary)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Edit Profile Dialog
    if (showEditDialog) {
        var editCallsign by remember { mutableStateOf(userProfile.callsign) }
        var editRank by remember { mutableStateOf(userProfile.clearanceLevel) }
        var editEmail by remember { mutableStateOf(userProfile.email) }

        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            containerColor = CyberSurface,
            title = {
                Text(
                    text = "EDIT OPERATIVE CREDENTIALS",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = accentColor
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = editCallsign,
                        onValueChange = { editCallsign = it },
                        label = { Text("Operative Callsign") },
                        colors = TextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary)
                    )
                    OutlinedTextField(
                        value = editRank,
                        onValueChange = { editRank = it },
                        label = { Text("Clearance Rank Title") },
                        colors = TextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary)
                    )
                    OutlinedTextField(
                        value = editEmail,
                        onValueChange = { editEmail = it },
                        label = { Text("Encrypted Comm Email") },
                        colors = TextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary)
                    )
                }
            },
            confirmButton = {
                HudButton(
                    text = "SAVE PROFILE",
                    onClick = {
                        onUpdateProfile(
                            userProfile.copy(
                                callsign = editCallsign,
                                clearanceLevel = editRank,
                                email = editEmail
                            )
                        )
                        showEditDialog = false
                    },
                    accentColor = accentColor,
                    filled = true
                )
            },
            dismissButton = {
                HudButton(
                    text = "CANCEL",
                    onClick = { showEditDialog = false },
                    accentColor = TextSecondary
                )
            }
        )
    }
}
