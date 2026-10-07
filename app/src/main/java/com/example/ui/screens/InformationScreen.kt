package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon
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
import com.example.ui.components.HudBadge
import com.example.ui.components.HudCard
import com.example.ui.components.HudHeader
import com.example.ui.theme.CyberBorder
import com.example.ui.theme.CyberBorderGlow
import com.example.ui.theme.CyberSurfaceVariant
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.NeonRed
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun InformationScreen(
    accentColor: Color = NeonRed
) {
    var expandedFaqIndex by remember { mutableStateOf<Int?>(null) }

    val faqs = listOf(
        "Apakah semua 450 fitur berfungsi secara lokal?" to "Ya. Seluruh modul text formatting, encoding/decoding, kriptografi, kalkulator ilmiah, konversi satuan, pengujian sensor, dan sintesis audio dieksekusi 100% langsung di perangkat Anda tanpa ketergantungan server luar.",
        "Bagaimana cara mengganti latar belakang futuristik?" to "Buka menu Setting di navigasi bawah. Pada bagian 'Alternative Background Styles', Anda dapat memilih gaya Singularity Black Hole, Crimson Operative, Deep Matrix HUD, Quantum Void, atau Obsidian Stealth.",
        "Bagaimana cara mengulang animasi boot loading screen?" to "Buka menu Setting, lalu tekan tombol 'REPLAY CINEMATIC BOOT HUD'. Layar animasi pemindaian HUD akan kembali dimainkan secara penuh.",
        "Apakah data atau teks yang saya masukkan disimpan atau dikirim ke internet?" to "Tidak sama sekali. XER memegang filosofi 'Zero-Cloud Exposure'. Semua komputasi bersifat volatile dan privasi Anda terjamin sepenuhnya.",
        "Bagaimana cara menambahkan tool ke daftar Favorit?" to "Tekan ikon bintang pada kartu tool mana pun, baik dari halaman Home, Tool, maupun Dashboard. Tool tersebut akan otomatis disematkan di tab Favorit Anda."
    )

    val changelogs = listOf(
        Triple("v4.5.9 (Cyber Release)", "2026.10", listOf(
            "Integrasi penuh 450 fitur yang dikategorikan ke dalam 15 sektor taktis",
            "Peluncuran boot loading screen sinematik HUD dengan reticle berputar dan segmen NUK10",
            "Penyempurnaan 5 pilihan latar belakang futuristik (Black Hole & Cyber Girl)",
            "Penambahan modul sintesis audio dan game refleks kecepatan"
        )),
        Triple("v4.2.0 (Singularity)", "2026.06", listOf(
            "Arsitektur desentralisasi telemetri hardware Android",
            "Dukungan algoritma pasca-kuantum dan SHA-512 encoder",
            "Peningkatan efisiensi kompilasi Jetpack Compose"
        )),
        Triple("v3.8.0 (Alpha Grid)", "2026.01", listOf(
            "Rilis perdana antarmuka glassmorphism gelap",
            "Katalog 150 modul developer dan enkoder hex"
        ))
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Information Header
        item {
            HudHeader(
                title = "Information & System Blueprint",
                code = "XER-SPEC",
                subtitle = "App specs, developer credentials, changelogs, and security FAQ",
                accentColor = accentColor
            )
        }

        // About Application Card
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
                            Icon(
                                imageVector = Icons.Default.Terminal,
                                contentDescription = null,
                                tint = accentColor,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "XER CYBER TERMINAL",
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = TextPrimary
                            )
                        }
                        HudBadge(text = "v4.5.9", accentColor = NeonCyan)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "XER adalah sistem terminal utilitas multi-fungsi modern bertema dark futuristic. Dirancang untuk para pengembang, analis keamanan, peneliti kuantum, dan pengguna yang membutuhkan alat bantu digital terlengkap dalam satu antarmuka berkecepatan tinggi tanpa kompromi.",
                        fontSize = 13.sp,
                        color = TextSecondary,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(CyberBorder))
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("ENGINE ARCHITECTURE", fontFamily = FontFamily.Monospace, fontSize = 9.sp, color = TextMuted)
                            Text("JETPACK COMPOSE / KOTLIN", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = TextPrimary)
                        }
                        Column {
                            Text("TOTAL CAPABILITIES", fontFamily = FontFamily.Monospace, fontSize = 9.sp, color = TextMuted)
                            Text("450 ACTIVE UTILITIES", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = accentColor)
                        }
                    }
                }
            }
        }

        // Developer & Engineering Bureau
        item {
            HudCard(
                borderColor = CyberBorder,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "DEVELOPER & CORE TEAM",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Developed by XER Core Systems Engineering Bureau.",
                        fontSize = 13.sp,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Lead Architect: Ardiansyah (ardiansya2939@gmail.com)",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = NeonCyan
                    )
                    Text(
                        text = "Designed for high-throughput mobile telemetry and zero-latency execution.",
                        fontSize = 11.sp,
                        color = TextMuted
                    )
                }
            }
        }

        // Privacy Commitment
        item {
            HudCard(
                borderColor = NeonGreen.copy(alpha = 0.4f),
                glowColor = NeonGreen,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = NeonGreen,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "PRIVACY & SECURITY COVENANT",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = NeonGreen
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Aplikasi ini memprioritaskan privasi data mutlak. Tidak ada pelacak pihak ketiga, iklan, atau telemetri tak sah. Semua data teks, hash, dan operasi berada di RAM perangkat Anda dan dapat dihapus seketika.",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Changelogs
        item {
            Column {
                HudHeader(title = "Release Changelogs", code = "LOG-HIST", accentColor = NeonPurple)
                Spacer(modifier = Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    changelogs.forEach { (version, date, notes) ->
                        HudCard(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(version, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = TextPrimary)
                                    Text(date, fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = NeonCyan)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                notes.forEach { note ->
                                    Row(modifier = Modifier.padding(vertical = 2.dp)) {
                                        Text("• ", color = accentColor, fontSize = 12.sp)
                                        Text(note, fontSize = 12.sp, color = TextSecondary)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Interactive FAQ
        item {
            Column {
                HudHeader(title = "Frequently Asked Questions", code = "FAQ-DOC", accentColor = NeonGreen)
                Spacer(modifier = Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    faqs.forEachIndexed { index, (q, a) ->
                        val isExpanded = expandedFaqIndex == index
                        HudCard(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { expandedFaqIndex = if (isExpanded) null else index }
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = q,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 13.sp,
                                        color = if (isExpanded) accentColor else TextPrimary,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Icon(
                                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                        contentDescription = null,
                                        tint = if (isExpanded) accentColor else TextSecondary
                                    )
                                }
                                AnimatedVisibility(visible = isExpanded) {
                                    Column {
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(CyberBorder))
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = a,
                                            fontSize = 12.sp,
                                            color = TextSecondary,
                                            lineHeight = 18.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
