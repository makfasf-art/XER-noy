package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.os.BatteryManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ToolCategory
import com.example.model.ToolItem
import com.example.ui.theme.CyberBlack
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.net.InetAddress
import java.net.NetworkInterface
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolRunnerSheet(
    tool: ToolItem,
    isFavorite: Boolean,
    onToggleFavorite: (String) -> Unit,
    onDismiss: () -> Unit,
    onLogActivity: (toolName: String, categoryCode: String, details: String) -> Unit,
    accentColor: Color = NeonRed
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val context = LocalContext.current
    var copiedFeedback by remember { mutableStateOf(false) }

    LaunchedEffect(tool) {
        onLogActivity(tool.name, tool.category.code, "Executed in terminal")
    }

    fun copyToClipboard(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("XER Terminal", text)
        clipboard.setPrimaryClip(clip)
        copiedFeedback = true
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = CyberSurface,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                .background(CyberSurface)
                .padding(20.dp)
        ) {
            // Sheet Header with Tool Info & Actions
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
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
                            fontSize = 16.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = tool.category.title,
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { onToggleFavorite(tool.id) },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) NeonRed else TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Text(
                text = tool.description,
                fontSize = 12.sp,
                color = TextMuted,
                modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(CyberBorder)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Scrollable interactive workspace for the tool
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    when (tool.category) {
                        ToolCategory.TEXT_TOOLS -> TextToolRunner(tool, ::copyToClipboard, accentColor)
                        ToolCategory.DEV_TOOLS -> DevToolRunner(tool, ::copyToClipboard, accentColor)
                        ToolCategory.SECURITY_TOOLS -> SecurityToolRunner(tool, ::copyToClipboard, accentColor)
                        ToolCategory.CALCULATOR_SCIENCE -> MathToolRunner(tool, ::copyToClipboard, accentColor)
                        ToolCategory.CONVERTER_TOOLS -> ConverterToolRunner(tool, ::copyToClipboard, accentColor)
                        ToolCategory.SYSTEM_TOOLS -> SystemToolRunner(tool, accentColor)
                        ToolCategory.GENERATORS -> GeneratorToolRunner(tool, ::copyToClipboard, accentColor)
                        ToolCategory.MEDIA_AUDIO -> AudioMediaToolRunner(tool, accentColor)
                        ToolCategory.PRODUCTIVITY_TOOLS -> ProductivityToolRunner(tool, accentColor)
                        ToolCategory.ENTERTAINMENT -> EntertainmentToolRunner(tool, accentColor)
                        ToolCategory.CUSTOMIZATION_THEMES -> CustomizationToolRunner(tool, accentColor)
                        ToolCategory.NETWORK_TOOLS -> NetworkToolRunner(tool, ::copyToClipboard, accentColor)
                        ToolCategory.IMAGE_COLOR -> ImageColorToolRunner(tool, ::copyToClipboard, accentColor)
                        ToolCategory.INFORMATION_SPECS -> InfoSpecToolRunner(tool, ::copyToClipboard, accentColor)
                        ToolCategory.AI_QUANTUM_TOOLS -> AiQuantumToolRunner(tool, ::copyToClipboard, accentColor)
                    }
                }
            }

            // Copy feedback toast
            AnimatedVisibility(visible = copiedFeedback) {
                LaunchedEffect(copiedFeedback) {
                    delay(2000)
                    copiedFeedback = false
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(NeonGreen.copy(alpha = 0.2f))
                        .border(1.dp, NeonGreen, RoundedCornerShape(8.dp))
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "COPIED TO TERMINAL CLIPBOARD",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = NeonGreen
                    )
                }
            }
        }
    }
}

// =================== CATEGORY RUNNERS ===================

@Composable
fun TextToolRunner(tool: ToolItem, onCopy: (String) -> Unit, accentColor: Color) {
    var input by remember { mutableStateOf("XER Tactical Cyber Terminal 2026") }
    var output by remember { mutableStateOf("") }

    LaunchedEffect(input, tool.id) {
        output = when (tool.id) {
            "txt_base64_enc" -> android.util.Base64.encodeToString(input.toByteArray(), android.util.Base64.NO_WRAP)
            "txt_base64_dec" -> try {
                String(android.util.Base64.decode(input, android.util.Base64.DEFAULT))
            } catch (e: Exception) {
                "Error: Invalid Base64 payload"
            }
            "txt_url_enc" -> java.net.URLEncoder.encode(input, "UTF-8")
            "txt_url_dec" -> try { java.net.URLDecoder.decode(input, "UTF-8") } catch (e: Exception) { "Invalid URL encoding" }
            "txt_md5" -> MessageDigest.getInstance("MD5").digest(input.toByteArray()).joinToString("") { "%02x".format(it) }
            "txt_sha256" -> MessageDigest.getInstance("SHA-256").digest(input.toByteArray()).joinToString("") { "%02x".format(it) }
            "txt_upper" -> input.uppercase()
            "txt_lower" -> input.lowercase()
            "txt_title" -> input.split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
            "txt_slug" -> input.lowercase().replace(Regex("[^a-z0-9]+"), "-").trim('-')
            "txt_word_count" -> {
                val words = input.trim().split(Regex("\\s+")).filter { it.isNotEmpty() }.size
                val chars = input.length
                val lines = input.lines().size
                "Words: $words\nCharacters: $chars\nLines: $lines\nNon-space chars: ${input.count { !it.isWhitespace() }}"
            }
            "txt_reverse" -> input.reversed()
            "txt_morse_enc" -> encodeToMorse(input)
            "txt_morse_dec" -> decodeFromMorse(input)
            "txt_bin_enc" -> input.toByteArray().joinToString(" ") { String.format("%8s", Integer.toBinaryString(it.toInt() and 0xFF)).replace(' ', '0') }
            "txt_bin_dec" -> try {
                input.trim().split("\\s+".toRegex()).map { it.toInt(2).toByte() }.toByteArray().toString(Charsets.UTF_8)
            } catch (e: Exception) { "Invalid binary string" }
            "txt_hex_enc" -> input.toByteArray().joinToString(" ") { "%02X".format(it) }
            "txt_hex_dec" -> try {
                val cleanHex = input.replace("\\s+".toRegex(), "")
                cleanHex.chunked(2).map { it.toInt(16).toByte() }.toByteArray().toString(Charsets.UTF_8)
            } catch (e: Exception) { "Invalid hex string" }
            "txt_rot13" -> rot13(input)
            "txt_caesar" -> caesarShift(input, 3)
            "txt_dup_lines" -> input.lines().distinct().joinToString("\n")
            "txt_sort_az" -> input.lines().sorted().joinToString("\n")
            "txt_trim" -> input.trim().replace(Regex("\\s+"), " ")
            "txt_leet" -> toLeetSpeak(input)
            "txt_pig_latin" -> toPigLatin(input)
            "txt_vowels" -> {
                val v = input.count { it.lowercaseChar() in "aeiou" }
                val c = input.count { it.isLetter() && it.lowercaseChar() !in "aeiou" }
                "Vowels: $v | Consonants: $c | Letters: ${v + c}"
            }
            "txt_syllable" -> "Estimated Syllables: ${estimateSyllables(input)}"
            "txt_ascii_arr" -> input.toByteArray().joinToString(", ", "[", "]") { it.toString() }
            "txt_camel_case" -> toCamelCase(input)
            "txt_snake_case" -> input.lowercase().replace(Regex("[^a-z0-9]+"), "_").trim('_')
            else -> input.reversed()
        }
    }

    TextIOView(
        input = input,
        onInputChange = { input = it },
        output = output,
        onCopy = onCopy,
        accentColor = accentColor
    )
}

@Composable
fun DevToolRunner(tool: ToolItem, onCopy: (String) -> Unit, accentColor: Color) {
    var input by remember { mutableStateOf("{\"service\":\"XER\",\"version\":\"4.5.9\",\"status\":\"ACTIVE\"}") }
    var output by remember { mutableStateOf("") }

    LaunchedEffect(input, tool.id) {
        output = when (tool.id) {
            "dev_json_fmt" -> try {
                org.json.JSONObject(input).toString(2)
            } catch (e: Exception) {
                try { org.json.JSONArray(input).toString(2) } catch (e2: Exception) { "Invalid JSON: ${e.message}" }
            }
            "dev_json_min" -> try {
                org.json.JSONObject(input).toString()
            } catch (e: Exception) {
                try { org.json.JSONArray(input).toString() } catch (e2: Exception) { "Invalid JSON: ${e.message}" }
            }
            "dev_jwt_dec" -> decodeJwt(input)
            "dev_unix_to_date" -> try {
                val ts = input.trim().toLong()
                val date = if (ts > 100000000000L) Date(ts) else Date(ts * 1000)
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.getDefault()).format(date)
            } catch (e: Exception) { "Invalid Epoch Timestamp" }
            "dev_date_to_unix" -> "Current Timestamp: ${System.currentTimeMillis() / 1000} (seconds) / ${System.currentTimeMillis()} (ms)"
            "dev_uuid_v4" -> UUID.randomUUID().toString()
            "dev_nanoid" -> (1..21).map { "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz-"[Random.nextInt(64)] }.joinToString("")
            "dev_http_status" -> httpStatusLookup(input.trim())
            "dev_hex_to_rgb" -> hexToRgb(input)
            "dev_rgb_to_hex" -> rgbToHex(input)
            "dev_color_invert" -> invertHexColor(input)
            "dev_user_agent" -> "XER Terminal v4.5.9 (Linux; Android ${Build.VERSION.RELEASE}; ${Build.MODEL}; Build/${Build.DISPLAY})"
            "dev_curl_builder" -> "curl -X POST \"https://api.xer-core.net/v1/telemetry\" \\\n  -H \"Authorization: Bearer xer_9842\" \\\n  -H \"Content-Type: application/json\" \\\n  -d '$input'"
            "dev_cron_exp" -> explainCron(input)
            "dev_git_cheat" -> "git status\ngit add -A\ngit commit -m \"feat: quantum pipeline\"\ngit push origin main\ngit rebase -i HEAD~3"
            "dev_lorem" -> "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quantum cryptosystem nullam accumsan interdum orci, sed lacinia ex cursus non. Vivamus sed elementum turpis."
            "dev_rand_str" -> (1..24).map { "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"[Random.nextInt(62)] }.joinToString("")
            else -> "Output generated for ${tool.name}"
        }
    }

    TextIOView(
        input = input,
        onInputChange = { input = it },
        output = output,
        onCopy = onCopy,
        accentColor = accentColor
    )
}

@Composable
fun SecurityToolRunner(tool: ToolItem, onCopy: (String) -> Unit, accentColor: Color) {
    var passLength by remember { mutableIntStateOf(16) }
    var generatedPass by remember { mutableStateOf("") }
    var inputSecret by remember { mutableStateOf("CyberSecurity2026!") }

    LaunchedEffect(passLength, tool.id) {
        generatedPass = generatePassword(passLength)
    }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        when (tool.id) {
            "sec_pass_gen", "sec_pin_gen" -> {
                HudCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "SECURITY ENTROPY KEY",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (tool.id == "sec_pin_gen") (1000..9999).random().toString() else generatedPass,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = NeonCyan
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Key Length: $passLength bytes",
                            fontSize = 12.sp,
                            color = TextMuted
                        )
                        Slider(
                            value = passLength.toFloat(),
                            onValueChange = { passLength = it.toInt() },
                            valueRange = 8f..32f,
                            steps = 23,
                            colors = SliderDefaults.colors(thumbColor = accentColor, activeTrackColor = accentColor)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            HudButton(
                                text = "Regenerate",
                                onClick = { generatedPass = generatePassword(passLength) },
                                icon = Icons.Default.Refresh,
                                accentColor = accentColor
                            )
                            HudButton(
                                text = "Copy Key",
                                onClick = { onCopy(generatedPass) },
                                icon = Icons.Default.ContentCopy,
                                accentColor = NeonCyan,
                                filled = true
                            )
                        }
                    }
                }
            }
            "sec_pass_strength" -> {
                OutlinedTextField(
                    value = inputSecret,
                    onValueChange = { inputSecret = it },
                    label = { Text("Enter Password to Test") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        focusedContainerColor = CyberSurfaceVariant,
                        unfocusedContainerColor = CyberSurfaceVariant
                    )
                )
                val entropy = calculatePasswordEntropy(inputSecret)
                HudCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Entropy Score: $entropy bits", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, color = if (entropy > 60) NeonGreen else NeonRed)
                        Text("Brute-Force Estimate: ${getBruteForceEstimate(entropy)}", fontSize = 12.sp, color = TextSecondary)
                        Text("Requirements: 12+ chars, uppercase, lowercase, numbers, symbols", fontSize = 11.sp, color = TextMuted)
                    }
                }
            }
            else -> {
                val hash256 = MessageDigest.getInstance("SHA-256").digest(inputSecret.toByteArray()).joinToString("") { "%02x".format(it) }
                OutlinedTextField(
                    value = inputSecret,
                    onValueChange = { inputSecret = it },
                    label = { Text("Input Cryptographic Payload") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary)
                )
                HudCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("CRYPTOGRAPHIC CHECKSUM", fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = TextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(hash256, fontFamily = FontFamily.Monospace, fontSize = 12.sp, color = NeonCyan)
                        Spacer(modifier = Modifier.height(8.dp))
                        HudButton(text = "Copy Checksum", onClick = { onCopy(hash256) }, accentColor = accentColor)
                    }
                }
            }
        }
    }
}

@Composable
fun MathToolRunner(tool: ToolItem, onCopy: (String) -> Unit, accentColor: Color) {
    var valA by remember { mutableStateOf("128") }
    var valB by remember { mutableStateOf("16") }
    var result by remember { mutableStateOf("") }

    LaunchedEffect(valA, valB, tool.id) {
        val a = valA.toDoubleOrNull() ?: 0.0
        val b = valB.toDoubleOrNull() ?: 0.0
        result = when (tool.id) {
            "mth_sci_calc" -> "Result: ${a * b} | sin($a): ${"%.4f".format(kotlin.math.sin(Math.toRadians(a)))} | √$a: ${"%.4f".format(kotlin.math.sqrt(a))}"
            "mth_percentage" -> "$valA% of $valB = ${(a / 100.0) * b}"
            "mth_discount" -> "Discount (${b}%): ${a * (b / 100)} | Final Price: ${a - (a * (b / 100))}"
            "mth_bmi_calc" -> {
                val weight = a
                val heightM = b / 100.0
                if (heightM > 0) {
                    val bmi = weight / (heightM * heightM)
                    "BMI: ${"%.2f".format(bmi)} (${if (bmi < 18.5) "Underweight" else if (bmi < 25) "Normal" else "Overweight"})"
                } else "Enter valid height"
            }
            "mth_circle_calc" -> "Radius: $a | Area: ${"%.2f".format(Math.PI * a * a)} | Circumference: ${"%.2f".format(2 * Math.PI * a)}"
            "mth_factorial" -> {
                val n = a.toLong().coerceIn(0, 20)
                var f = 1L
                for (i in 1..n) f *= i
                "$n! = $f"
            }
            "mth_fibonacci" -> {
                val count = a.toInt().coerceIn(1, 25)
                val seq = mutableListOf(0L, 1L)
                while (seq.size < count) seq.add(seq[seq.size - 1] + seq[seq.size - 2])
                seq.take(count).joinToString(", ")
            }
            "mth_ohms_law" -> "Voltage (V) = Current ($a A) × Resistance ($b Ω) = ${a * b} Volts | Power: ${a * a * b} Watts"
            "mth_golden_ratio" -> "Major: ${"%.3f".format(a * 1.6180339887)} | Minor: ${"%.3f".format(a / 1.6180339887)}"
            else -> "Calculation: a + b = ${a + b} | a * b = ${a * b}"
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = valA,
                onValueChange = { valA = it },
                label = { Text("Value A / Input") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary)
            )
            OutlinedTextField(
                value = valB,
                onValueChange = { valB = it },
                label = { Text("Value B / Param") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary)
            )
        }

        HudCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("EVALUATED OUTPUT", fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = TextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(result, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = NeonCyan)
                Spacer(modifier = Modifier.height(10.dp))
                HudButton(text = "Copy Result", onClick = { onCopy(result) }, accentColor = accentColor)
            }
        }
    }
}

@Composable
fun ConverterToolRunner(tool: ToolItem, onCopy: (String) -> Unit, accentColor: Color) {
    var inputAmount by remember { mutableStateOf("100") }
    var convertedResult by remember { mutableStateOf("") }

    LaunchedEffect(inputAmount, tool.id) {
        val num = inputAmount.toDoubleOrNull() ?: 0.0
        convertedResult = when (tool.id) {
            "cnv_length" -> "$num m = ${num * 1000} mm | ${num / 1000} km | ${"%.2f".format(num * 3.28084)} ft | ${"%.2f".format(num * 0.000621371)} miles"
            "cnv_mass" -> "$num kg = ${num * 1000} g | ${"%.2f".format(num * 2.20462)} lbs | ${"%.2f".format(num * 35.274)} oz"
            "cnv_temp" -> "$num°C = ${"%.1f".format(num * 9 / 5 + 32)}°F = ${"%.2f".format(num + 273.15)} K"
            "cnv_speed" -> "$num km/h = ${"%.2f".format(num * 0.621371)} mph = ${"%.2f".format(num / 3.6)} m/s"
            "cnv_storage" -> "$num MB = ${num * 1024} KB = ${num * 1048576} B = ${"%.3f".format(num / 1024.0)} GB"
            "cnv_currency" -> "$num USD ≈ ${"%.2f".format(num * 0.92)} EUR | Rp ${(num * 16250).toLong()} IDR | ¥${(num * 155).toLong()} JPY"
            "cnv_roman_to_num" -> "Roman MMXXVI = 2026 | Decimal input: ${num.toInt()}"
            "cnv_pressure" -> "$num bar = ${num * 100000} Pa = ${"%.2f".format(num * 14.5038)} PSI = ${"%.2f".format(num * 0.986923)} atm"
            else -> "$num units = ${num * 1.618} standardized metric units"
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = inputAmount,
            onValueChange = { inputAmount = it },
            label = { Text("Amount / Quantity to Convert") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(focusedTextColor = TextPrimary, unfocusedTextColor = TextPrimary)
        )
        HudCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("EQUIVALENT MATRIX", fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = TextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(convertedResult, fontFamily = FontFamily.Monospace, fontSize = 14.sp, color = NeonCyan)
                Spacer(modifier = Modifier.height(10.dp))
                HudButton(text = "Copy Equivalent", onClick = { onCopy(convertedResult) }, accentColor = accentColor)
            }
        }
    }
}

@Composable
fun SystemToolRunner(tool: ToolItem, accentColor: Color) {
    val context = LocalContext.current
    var liveBattery by remember { mutableIntStateOf(85) }
    var isCharging by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val bm = context.getSystemService(Context.BATTERY_SERVICE) as? BatteryManager
        if (bm != null) {
            val level = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
            if (level in 0..100) liveBattery = level
            isCharging = bm.isCharging
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        when (tool.id) {
            "sys_battery" -> {
                HudStatCard(label = "Battery Charge", value = "$liveBattery%", subValue = if (isCharging) "Charging (Fast AC)" else "Discharging", accentColor = NeonGreen)
            }
            "sys_vibrate_test" -> {
                HudButton(
                    text = "TRIGGER HAPTIC MOTOR PULSE",
                    onClick = {
                        try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                                val vm = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                                vm?.defaultVibrator?.vibrate(VibrationEffect.createOneShot(120, VibrationEffect.DEFAULT_AMPLITUDE))
                            } else {
                                @Suppress("DEPRECATION")
                                val vib = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
                                @Suppress("DEPRECATION")
                                vib?.vibrate(120)
                            }
                        } catch (e: Exception) { /* vibration fallback */ }
                    },
                    accentColor = NeonRed,
                    filled = true
                )
            }
            else -> {
                HudStatCard(label = "Device Hardware", value = "${Build.MANUFACTURER} ${Build.MODEL}", subValue = "ABI: ${Build.SUPPORTED_ABIS.firstOrNull() ?: "arm64"}", accentColor = NeonCyan)
                HudStatCard(label = "Operating System", value = "Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})", subValue = "Security Build: ${Build.ID}", accentColor = NeonPurple)
            }
        }
    }
}

@Composable
fun GeneratorToolRunner(tool: ToolItem, onCopy: (String) -> Unit, accentColor: Color) {
    var generatedText by remember { mutableStateOf("") }

    fun refreshGen() {
        generatedText = when (tool.id) {
            "gen_uuid" -> UUID.randomUUID().toString()
            "gen_nickname" -> listOf("GhostCipher", "KuroNeko_X", "Valkyrie-09", "ZeroEntropy", "NeonSpectre", "VoidDrifter", "OmegaOperator").random()
            "gen_sci_name" -> listOf("USSC Gargantua-IX", "Archangel Vanguard", "Nebula Dreadnought", "Solaris Horizon", "Quantum Aegis").random()
            "gen_quote" -> listOf(
                "\"The future is already here — it's just not evenly distributed.\" - William Gibson",
                "\"We are the architects of the invisible grid.\" - XER Core Doctrine",
                "\"In the presence of the event horizon, even information seeks escape.\""
            ).random()
            "gen_dice" -> "Rolled d20: ${(1..20).random()} | d6: ${(1..6).random()}"
            "gen_coin" -> if (Random.nextBoolean()) "HEADS [1]" else "TAILS [0]"
            "gen_palette" -> "#FF1744, #00E5FF, #D500F9, #00E676, #0A0C12"
            else -> "Generated value: 0x" + UUID.randomUUID().toString().take(12).uppercase()
        }
    }

    LaunchedEffect(tool.id) { refreshGen() }

    HudCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("GENERATED RESULT", fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = TextSecondary)
            Spacer(modifier = Modifier.height(6.dp))
            Text(generatedText, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = NeonCyan)
            Spacer(modifier = Modifier.height(14.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                HudButton(text = "Reroll", onClick = { refreshGen() }, icon = Icons.Default.Refresh, accentColor = accentColor)
                HudButton(text = "Copy", onClick = { onCopy(generatedText) }, icon = Icons.Default.ContentCopy, accentColor = NeonCyan, filled = true)
            }
        }
    }
}

@Composable
fun AudioMediaToolRunner(tool: ToolItem, accentColor: Color) {
    var isPlaying by remember { mutableStateOf(false) }
    var frequency by remember { mutableStateOf(440f) }
    val scope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        onDispose { isPlaying = false }
    }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        HudCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("FREQUENCY OSCILLATOR", fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = TextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                Text("${frequency.toInt()} Hz (${if (frequency.toInt() == 440) "Concert Pitch A4" else "Pure Tone"})", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = NeonCyan)
                Slider(
                    value = frequency,
                    onValueChange = { frequency = it },
                    valueRange = 100f..2000f,
                    colors = SliderDefaults.colors(thumbColor = accentColor, activeTrackColor = accentColor)
                )
                Spacer(modifier = Modifier.height(8.dp))
                HudButton(
                    text = if (isPlaying) "STOP TONE" else "SYNTHESIZE AUDIO TONE",
                    onClick = {
                        isPlaying = !isPlaying
                        if (isPlaying) {
                            scope.launch(Dispatchers.Default) {
                                playTone(frequency.toInt()) { isPlaying }
                            }
                        }
                    },
                    icon = Icons.Default.PlayArrow,
                    accentColor = if (isPlaying) NeonRed else NeonGreen,
                    filled = true
                )
            }
        }
    }
}

@Composable
fun ProductivityToolRunner(tool: ToolItem, accentColor: Color) {
    var timerSeconds by remember { mutableIntStateOf(25 * 60) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning && timerSeconds > 0) {
            delay(1000)
            timerSeconds--
        }
        if (timerSeconds == 0) isRunning = false
    }

    val mins = timerSeconds / 60
    val secs = timerSeconds % 60
    val timeFormatted = "%02d:%02d".format(mins, secs)

    HudCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("TACTICAL POMODORO PROTOCOL", fontFamily = FontFamily.Monospace, fontSize = 11.sp, color = TextSecondary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(timeFormatted, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 36.sp, color = accentColor)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HudButton(
                    text = if (isRunning) "PAUSE" else "START FOCUS",
                    onClick = { isRunning = !isRunning },
                    accentColor = if (isRunning) NeonRed else NeonGreen,
                    filled = true
                )
                HudButton(
                    text = "RESET 25M",
                    onClick = {
                        isRunning = false
                        timerSeconds = 25 * 60
                    },
                    accentColor = TextSecondary
                )
            }
        }
    }
}

@Composable
fun EntertainmentToolRunner(tool: ToolItem, accentColor: Color) {
    var gameState by remember { mutableStateOf("TAP 'START TEST' TO MEASURE REFLEX") }
    var testStartTime by remember { mutableLongStateOf(0L) }
    var isWaitingGreen by remember { mutableStateOf(false) }
    var isGreenReady by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    HudCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        backgroundColor = if (isGreenReady) NeonGreen.copy(alpha = 0.25f) else CyberSurfaceVariant,
        borderColor = if (isGreenReady) NeonGreen else CyberBorder,
        onClick = {
            if (isWaitingGreen && !isGreenReady) {
                gameState = "TOO EARLY! PENALTY TRIGGERED."
                isWaitingGreen = false
            } else if (isGreenReady) {
                val elapsed = System.currentTimeMillis() - testStartTime
                gameState = "REACTION: $elapsed ms! (${if (elapsed < 250) "ELITE OPERATIVE" else "NOMINAL"})"
                isGreenReady = false
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = gameState,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = if (isGreenReady) NeonGreen else TextPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (!isWaitingGreen && !isGreenReady) {
                HudButton(
                    text = "START TEST",
                    onClick = {
                        gameState = "WAIT FOR GREEN FLASH..."
                        isWaitingGreen = true
                        isGreenReady = false
                        scope.launch {
                            delay(Random.nextLong(1500, 3500))
                            if (isWaitingGreen) {
                                isGreenReady = true
                                isWaitingGreen = false
                                testStartTime = System.currentTimeMillis()
                                gameState = "TAP NOW!"
                            }
                        }
                    },
                    accentColor = accentColor,
                    filled = true
                )
            }
        }
    }
}

@Composable
fun CustomizationToolRunner(tool: ToolItem, accentColor: Color) {
    HudCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("THEME TUNER", fontFamily = FontFamily.Monospace, fontSize = 11.sp, color = TextSecondary)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Use Settings menu to switch between Singularity, Cyber Grid, and Cyber Girl wallpapers.", fontSize = 13.sp, color = TextPrimary)
        }
    }
}

@Composable
fun NetworkToolRunner(tool: ToolItem, onCopy: (String) -> Unit, accentColor: Color) {
    var ipInfo by remember { mutableStateOf("Resolving network adapters...") }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            try {
                val interfaces = NetworkInterface.getNetworkInterfaces().toList()
                val ips = interfaces.flatMap { it.inetAddresses.toList() }
                    .filter { !it.isLoopbackAddress && it is java.net.Inet4Address }
                    .map { "${it.hostAddress} (${it.hostName})" }
                ipInfo = if (ips.isNotEmpty()) ips.joinToString("\n") else "Local: 127.0.0.1\nDefault Gateway: 192.168.1.1\nDNS: 1.1.1.1 / 8.8.8.8"
            } catch (e: Exception) {
                ipInfo = "IP: 192.168.1.104\nGateway: 192.168.1.1\nDNS: 1.1.1.1"
            }
        }
    }

    HudCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("NETWORK TELEMETRY", fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = TextSecondary)
            Spacer(modifier = Modifier.height(6.dp))
            Text(ipInfo, fontFamily = FontFamily.Monospace, fontSize = 13.sp, color = NeonCyan)
            Spacer(modifier = Modifier.height(10.dp))
            HudButton(text = "Copy IP Address", onClick = { onCopy(ipInfo) }, accentColor = accentColor)
        }
    }
}

@Composable
fun ImageColorToolRunner(tool: ToolItem, onCopy: (String) -> Unit, accentColor: Color) {
    var r by remember { mutableStateOf(255f) }
    var g by remember { mutableStateOf(30f) }
    var b by remember { mutableStateOf(70f) }

    val hex = "#%02X%02X%02X".format(r.toInt(), g.toInt(), b.toInt())
    val currentColor = Color(r.toInt(), g.toInt(), b.toInt())

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(currentColor)
                .border(1.dp, CyberBorderGlow, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            val isLight = (r * 0.299f + g * 0.587f + b * 0.114f) > 186f
            Text(hex, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, color = if (isLight) Color.Black else Color.White)
        }

        Text("Red: ${r.toInt()}", fontSize = 11.sp, color = TextSecondary)
        Slider(value = r, onValueChange = { r = it }, valueRange = 0f..255f, colors = SliderDefaults.colors(thumbColor = NeonRed, activeTrackColor = NeonRed))

        Text("Green: ${g.toInt()}", fontSize = 11.sp, color = TextSecondary)
        Slider(value = g, onValueChange = { g = it }, valueRange = 0f..255f, colors = SliderDefaults.colors(thumbColor = NeonGreen, activeTrackColor = NeonGreen))

        Text("Blue: ${b.toInt()}", fontSize = 11.sp, color = TextSecondary)
        Slider(value = b, onValueChange = { b = it }, valueRange = 0f..255f, colors = SliderDefaults.colors(thumbColor = NeonCyan, activeTrackColor = NeonCyan))

        HudButton(text = "Copy Hex: $hex", onClick = { onCopy(hex) }, accentColor = accentColor, filled = true)
    }
}

@Composable
fun InfoSpecToolRunner(tool: ToolItem, onCopy: (String) -> Unit, accentColor: Color) {
    HudCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("SPECIFICATION ARCHIVE", fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = TextSecondary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(tool.description, fontSize = 14.sp, color = TextPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Clearance: ALPHA-1 // Verified On-Device", fontFamily = FontFamily.Monospace, fontSize = 11.sp, color = NeonCyan)
            Spacer(modifier = Modifier.height(10.dp))
            HudButton(text = "Copy Reference", onClick = { onCopy(tool.description) }, accentColor = accentColor)
        }
    }
}

@Composable
fun AiQuantumToolRunner(tool: ToolItem, onCopy: (String) -> Unit, accentColor: Color) {
    var stateVector by remember { mutableStateOf("|0⟩ (100% Probability)") }

    HudCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("QUANTUM STATE MATRIX", fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = TextSecondary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(stateVector, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = NeonCyan)
            Spacer(modifier = Modifier.height(12.dp))
            HudButton(
                text = "COLLAPSE WAVEFUNCTION",
                onClick = {
                    val p = Random.nextFloat()
                    stateVector = if (p > 0.5f) "|0⟩ (Measured spin: UP)" else "|1⟩ (Measured spin: DOWN)"
                },
                accentColor = accentColor,
                filled = true
            )
        }
    }
}

@Composable
fun TextIOView(
    input: String,
    onInputChange: (String) -> Unit,
    output: String,
    onCopy: (String) -> Unit,
    accentColor: Color
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        OutlinedTextField(
            value = input,
            onValueChange = onInputChange,
            label = { Text("Input Terminal Text") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4,
            colors = TextFieldDefaults.colors(
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                focusedContainerColor = CyberSurfaceVariant,
                unfocusedContainerColor = CyberSurfaceVariant
            )
        )
        HudCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text("PROCESSED RESULT", fontFamily = FontFamily.Monospace, fontSize = 10.sp, color = TextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = output.ifEmpty { "(Awaiting input...)" },
                    fontFamily = FontFamily.Monospace,
                    fontSize = 13.sp,
                    color = NeonCyan
                )
                Spacer(modifier = Modifier.height(10.dp))
                HudButton(
                    text = "Copy Output",
                    onClick = { onCopy(output) },
                    icon = Icons.Default.ContentCopy,
                    accentColor = accentColor
                )
            }
        }
    }
}

// =================== HELPER FUNCTIONS ===================

private fun encodeToMorse(text: String): String {
    val map = mapOf(
        'A' to ".-", 'B' to "-...", 'C' to "-.-.", 'D' to "-..", 'E' to ".", 'F' to "..-.",
        'G' to "--.", 'H' to "....", 'I' to "..", 'J' to ".---", 'K' to "-.-", 'L' to ".-..",
        'M' to "--", 'N' to "-.", 'O' to "---", 'P' to ".--.", 'Q' to "--.-", 'R' to ".-.",
        'S' to "...", 'T' to "-", 'U' to "..-", 'V' to "...-", 'W' to ".--", 'X' to "-..-",
        'Y' to "-.--", 'Z' to "--..", '0' to "-----", '1' to ".----", '2' to "..---",
        '3' to "...--", '4' to "....-", '5' to ".....", '6' to "-....", '7' to "--...",
        '8' to "---..", '9' to "----."
    )
    return text.uppercase().map { map[it] ?: " " }.joinToString(" ")
}

private fun decodeFromMorse(morse: String): String {
    val map = mapOf(
        ".-" to "A", "-..." to "B", "-.-." to "C", "-.." to "D", "." to "E", "..-." to "F",
        "--." to "G", "...." to "H", ".." to "I", ".---" to "J", "-.-" to "K", ".-.." to "L",
        "--" to "M", "-." to "N", "---" to "O", ".--." to "P", "--.-" to "Q", ".-." to "R",
        "..." to "S", "-" to "T", "..-" to "U", "...-" to "V", ".--" to "W", "-..-" to "X",
        "-.--" to "Y", "--.." to "Z"
    )
    return morse.trim().split(" ").map { map[it] ?: "?" }.joinToString("")
}

private fun rot13(input: String): String {
    return input.map { c ->
        when (c) {
            in 'a'..'z' -> ((c - 'a' + 13) % 26 + 'a'.code).toChar()
            in 'A'..'Z' -> ((c - 'A' + 13) % 26 + 'A'.code).toChar()
            else -> c
        }
    }.joinToString("")
}

private fun caesarShift(input: String, shift: Int): String {
    return input.map { c ->
        when (c) {
            in 'a'..'z' -> ((c - 'a' + shift) % 26 + 'a'.code).toChar()
            in 'A'..'Z' -> ((c - 'A' + shift) % 26 + 'A'.code).toChar()
            else -> c
        }
    }.joinToString("")
}

private fun toLeetSpeak(input: String): String {
    return input.map {
        when (it.uppercaseChar()) {
            'A' -> '4'; 'E' -> '3'; 'I' -> '1'; 'O' -> '0'; 'T' -> '7'; 'S' -> '5'; else -> it
        }
    }.joinToString("")
}

private fun toPigLatin(input: String): String {
    return input.split(" ").joinToString(" ") { word ->
        if (word.isEmpty()) "" else "${word.drop(1)}${word.first()}ay"
    }
}

private fun toCamelCase(input: String): String {
    val words = input.split(Regex("[^a-zA-Z0-9]+")).filter { it.isNotEmpty() }
    return words.mapIndexed { index, w ->
        if (index == 0) w.lowercase() else w.replaceFirstChar { it.uppercase() }
    }.joinToString("")
}

private fun estimateSyllables(text: String): Int {
    val vowels = "aeiouy"
    var count = 0
    val words = text.lowercase().split("\\s+".toRegex())
    for (word in words) {
        var wordCount = 0
        var prevIsVowel = false
        for (c in word) {
            val isVowel = c in vowels
            if (isVowel && !prevIsVowel) wordCount++
            prevIsVowel = isVowel
        }
        if (word.endsWith("e") && wordCount > 1) wordCount--
        count += wordCount.coerceAtLeast(1)
    }
    return count
}

private fun generatePassword(length: Int): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+="
    return (1..length).map { chars[Random.nextInt(chars.length)] }.joinToString("")
}

private fun calculatePasswordEntropy(pass: String): Int {
    var pool = 0
    if (pass.any { it.isLowerCase() }) pool += 26
    if (pass.any { it.isUpperCase() }) pool += 26
    if (pass.any { it.isDigit() }) pool += 10
    if (pass.any { !it.isLetterOrDigit() }) pool += 32
    if (pool == 0 || pass.isEmpty()) return 0
    return (pass.length * (kotlin.math.log2(pool.toDouble()))).toInt()
}

private fun getBruteForceEstimate(entropy: Int): String {
    return when {
        entropy < 28 -> "Instant (under 1 second)"
        entropy < 40 -> "A few minutes"
        entropy < 60 -> "Several months"
        entropy < 80 -> "Thousands of centuries"
        else -> "Trillions of years (Quantum Safe)"
    }
}

private fun decodeJwt(token: String): String {
    val parts = token.split(".")
    if (parts.size < 2) return "Invalid JWT format (requires header.payload.signature)"
    return try {
        val header = String(android.util.Base64.decode(parts[0], android.util.Base64.URL_SAFE))
        val payload = String(android.util.Base64.decode(parts[1], android.util.Base64.URL_SAFE))
        "HEADER:\n$header\n\nPAYLOAD:\n$payload"
    } catch (e: Exception) {
        "Failed to decode Base64 URL payload"
    }
}

private fun httpStatusLookup(code: String): String {
    return when (code) {
        "200" -> "200 OK: Standard successful HTTP request."
        "201" -> "201 Created: Resource has been created."
        "400" -> "400 Bad Request: Malformed syntax in request."
        "401" -> "401 Unauthorized: Authentication required."
        "403" -> "403 Forbidden: Server refused authorized action."
        "404" -> "404 Not Found: Requested resource missing."
        "500" -> "500 Internal Server Error: Unexpected server condition."
        "502" -> "502 Bad Gateway: Invalid response from upstream."
        "503" -> "503 Service Unavailable: Server overloaded or down."
        else -> "Code '$code' - Check standard IANA HTTP Status Registry."
    }
}

private fun hexToRgb(hex: String): String {
    val clean = hex.trim().removePrefix("#")
    return try {
        val r = clean.substring(0, 2).toInt(16)
        val g = clean.substring(2, 4).toInt(16)
        val b = clean.substring(4, 6).toInt(16)
        "RGB($r, $g, $b)"
    } catch (e: Exception) { "Invalid hex (e.g. #FF1E46)" }
}

private fun rgbToHex(rgb: String): String {
    val parts = rgb.filter { it.isDigit() || it == ',' || it == ' ' }.split(",").mapNotNull { it.trim().toIntOrNull() }
    if (parts.size >= 3) {
        return "#%02X%02X%02X".format(parts[0].coerceIn(0, 255), parts[1].coerceIn(0, 255), parts[2].coerceIn(0, 255))
    }
    return "Enter format: 255, 30, 70"
}

private fun invertHexColor(hex: String): String {
    val clean = hex.trim().removePrefix("#")
    return try {
        val r = 255 - clean.substring(0, 2).toInt(16)
        val g = 255 - clean.substring(2, 4).toInt(16)
        val b = 255 - clean.substring(4, 6).toInt(16)
        "#%02X%02X%02X".format(r, g, b)
    } catch (e: Exception) { "#00E5FF" }
}

private fun explainCron(cron: String): String {
    val parts = cron.trim().split(" ")
    if (parts.size != 5) return "Standard 5-part cron: 'min hour dom mon dow' (e.g., '*/15 * * * *')"
    return "Minute: ${parts[0]} | Hour: ${parts[1]} | Day-of-Month: ${parts[2]} | Month: ${parts[3]} | Day-of-Week: ${parts[4]}"
}

private fun playTone(freqHz: Int, isStillPlaying: () -> Boolean) {
    try {
        val sampleRate = 44100
        val bufferSize = AudioTrack.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )
        val audioTrack = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                    .setSampleRate(sampleRate)
                    .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                    .build()
            )
            .setBufferSizeInBytes(bufferSize)
            .build()

        audioTrack.play()
        val buffer = ShortArray(bufferSize)
        var phase = 0.0
        val phaseIncrement = 2.0 * Math.PI * freqHz / sampleRate

        while (isStillPlaying()) {
            for (i in buffer.indices) {
                buffer[i] = (kotlin.math.sin(phase) * Short.MAX_VALUE * 0.3).toInt().toShort()
                phase += phaseIncrement
                if (phase > 2.0 * Math.PI) phase -= 2.0 * Math.PI
            }
            audioTrack.write(buffer, 0, buffer.size)
        }
        audioTrack.stop()
        audioTrack.release()
    } catch (e: Exception) {
        // AudioTrack graceful fallback in emulated environments
    }
}
