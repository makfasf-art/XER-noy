package com.example.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGold
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.NeonRed

enum class ToolCategory(val title: String, val code: String, val iconName: String) {
    TEXT_TOOLS("Text & String Tools", "TXT", "text_fields"),
    DEV_TOOLS("Developer & Encoders", "DEV", "code"),
    SECURITY_TOOLS("Cyber Security & Crypto", "SEC", "security"),
    CALCULATOR_SCIENCE("Calculator & Science", "MTH", "calculate"),
    CONVERTER_TOOLS("Unit & Matrix Converters", "CNV", "swap_horiz"),
    SYSTEM_TOOLS("System & Hardware Specs", "SYS", "memory"),
    GENERATORS("Generators & Randomizers", "GEN", "auto_awesome"),
    MEDIA_AUDIO("Media & Audio Synthesizer", "MED", "graphic_eq"),
    PRODUCTIVITY_TOOLS("Productivity & Operations", "PRD", "timer"),
    ENTERTAINMENT("Cyber Arcade & Games", "ENT", "sports_esports"),
    CUSTOMIZATION_THEMES("Customization & Visuals", "CST", "palette"),
    NETWORK_TOOLS("Network & Telemetry", "NET", "wifi"),
    IMAGE_COLOR("Color & Image Utilities", "CLR", "colorize"),
    INFORMATION_SPECS("Specs & Tech Encyclopedia", "INF", "menu_book"),
    AI_QUANTUM_TOOLS("Quantum & AI Simulation", "AIQ", "psychology")
}

enum class ToolExecutionType {
    TEXT_TRANSFORM,
    HASH_CRYPTO,
    CALCULATOR,
    CONVERTER,
    GENERATOR,
    SYSTEM_INFO,
    SIMULATOR_GAME,
    AUDIO_SYNTH,
    COLOR_TOOL,
    INFO_SPEC
}

data class ToolItem(
    val id: String,
    val name: String,
    val category: ToolCategory,
    val description: String,
    val executionType: ToolExecutionType,
    val isFeatured: Boolean = false,
    val isFavorite: Boolean = false,
    val usageCount: Int = 0
)

data class NewsItem(
    val id: String,
    val title: String,
    val category: String,
    val summary: String,
    val content: String,
    val author: String,
    val timestamp: String,
    val readMinutes: Int,
    val tags: List<String>,
    val clearanceLevel: String = "ALPHA-1"
)

enum class BackgroundStyle(val displayName: String, val description: String) {
    CYBER_GRID("Deep Matrix HUD", "Animated cybernetic scanning grid with scanlines"),
    BLACK_HOLE("Singularity Accretion", "Cosmic Gargantua black hole with event horizon glow"),
    CYBER_GIRL("Crimson Operative", "Cyberpunk anime operative with glowing ruby eyes"),
    QUANTUM_NEBULA("Quantum Void", "Deep space dark nebula with drifting star particles"),
    OBSIDIAN_MINIMAL("Obsidian Stealth", "Pure OLED ultra-black minimal aesthetic")
}

enum class AccentTheme(val displayName: String, val color: Color) {
    CRIMSON("Neon Crimson", NeonRed),
    CYAN("Electric Cyan", NeonCyan),
    PURPLE("Cyber Violet", NeonPurple),
    GREEN("Matrix Green", NeonGreen),
    GOLD("Solar Amber", NeonGold)
}

data class ActivityLogEntry(
    val id: String,
    val toolName: String,
    val categoryCode: String,
    val timestamp: Long,
    val details: String
)

data class UserProfile(
    val callsign: String = "OPERATIVE ARDIANSYAH",
    val codeId: String = "XER-9842-OMEGA",
    val clearanceLevel: String = "CYBER ARCHITECT LV.9",
    val email: String = "ardiansya2939@gmail.com",
    val operationsExecuted: Int = 142,
    val securityScore: Int = 98,
    val badges: List<String> = listOf("Core Architect", "Master Cryptographer", "Singularity Access", "Quantum Analyst")
)
