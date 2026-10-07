package com.example.data

import com.example.model.ToolCategory
import com.example.model.ToolExecutionType
import com.example.model.ToolItem

object ToolCatalog {

    val allTools: List<ToolItem> by lazy {
        val list = mutableListOf<ToolItem>()

        // 1. TEXT_TOOLS (30 items)
        val textTools = listOf(
            Triple("txt_base64_enc", "Base64 Encoder", "Encode plain text to base64 format"),
            Triple("txt_base64_dec", "Base64 Decoder", "Decode base64 encoded text"),
            Triple("txt_url_enc", "URL Encoder", "Encode characters into percent-encoded URL values"),
            Triple("txt_url_dec", "URL Decoder", "Decode percent-encoded URL strings"),
            Triple("txt_md5", "MD5 Hash Generator", "Generate 128-bit MD5 cryptographic checksum"),
            Triple("txt_sha256", "SHA-256 Hash Generator", "Compute standard SHA-256 secure hash"),
            Triple("txt_upper", "Uppercase Converter", "Transform text to ALL UPPERCASE letters"),
            Triple("txt_lower", "Lowercase Converter", "Transform text to all lowercase letters"),
            Triple("txt_title", "Title Case Formatter", "Capitalize first character of every word"),
            Triple("txt_slug", "Slugify URL Generator", "Convert string to clean URL-safe slug"),
            Triple("txt_word_count", "Word & Char Counter", "Count words, characters, sentences, paragraphs"),
            Triple("txt_reverse", "Reverse String", "Reverse character sequence completely"),
            Triple("txt_morse_enc", "Morse Code Encoder", "Convert text to international Morse code (. -)"),
            Triple("txt_morse_dec", "Morse Code Decoder", "Convert Morse code dots and dashes back to text"),
            Triple("txt_bin_enc", "Text to Binary", "Convert characters to 8-bit binary representation"),
            Triple("txt_bin_dec", "Binary to Text", "Convert binary stream into readable characters"),
            Triple("txt_hex_enc", "Text to Hex", "Convert ASCII text to hexadecimal bytes"),
            Triple("txt_hex_dec", "Hex to Text", "Convert hexadecimal byte sequence to text"),
            Triple("txt_rot13", "ROT13 Cipher", "Rotate alphabetical characters by 13 positions"),
            Triple("txt_caesar", "Caesar Cipher (+3)", "Classic Caesar shift cipher transformation"),
            Triple("txt_dup_lines", "Remove Duplicate Lines", "Filter out duplicate lines and sort"),
            Triple("txt_sort_az", "Sort Lines A-Z", "Alphabetically sort text lines"),
            Triple("txt_trim", "Whitespace Stripper", "Trim leading, trailing, and duplicate spaces"),
            Triple("txt_leet", "Leet Speak Generator", "Convert characters to l33t h4x0r style"),
            Triple("txt_pig_latin", "Pig Latin Translator", "Transform English words into Pig Latin"),
            Triple("txt_vowels", "Vowel & Consonant Counter", "Analyze count of vowels vs consonants"),
            Triple("txt_syllable", "Syllable Estimator", "Estimate syllable count and readability score"),
            Triple("txt_ascii_arr", "ASCII Byte Array", "Export string as decimal byte values array"),
            Triple("txt_camel_case", "camelCase Converter", "Transform text into camelCase identifier"),
            Triple("txt_snake_case", "snake_case Converter", "Transform text into snake_case identifier")
        )
        textTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.TEXT_TOOLS, desc, ToolExecutionType.TEXT_TRANSFORM, isFeatured = i < 3))
        }

        // 2. DEV_TOOLS (30 items)
        val devTools = listOf(
            Triple("dev_json_fmt", "JSON Formatter & Validator", "Pretty print and validate JSON payload"),
            Triple("dev_json_min", "JSON Minifier", "Compress and strip whitespace from JSON"),
            Triple("dev_jwt_dec", "JWT Token Decoder", "Parse Header and Claims from JSON Web Token"),
            Triple("dev_html_enc", "HTML Entity Encoder", "Escape HTML special characters for safe output"),
            Triple("dev_html_dec", "HTML Entity Decoder", "Unescape HTML entities back to characters"),
            Triple("dev_css_min", "CSS Minifier", "Remove comments and compress CSS rules"),
            Triple("dev_sql_fmt", "SQL Query Formatter", "Format raw SQL statements with uppercase keywords"),
            Triple("dev_regex_test", "Regex Pattern Tester", "Evaluate regular expressions against sample text"),
            Triple("dev_unix_to_date", "UNIX Timestamp to Date", "Convert epoch seconds/millis to UTC/Local time"),
            Triple("dev_date_to_unix", "Date to UNIX Timestamp", "Generate epoch timestamp from current date/time"),
            Triple("dev_uuid_v4", "UUID v4 Generator", "Generate cryptographic unique identifier"),
            Triple("dev_nanoid", "NanoID Generator", "Generate compact URL-friendly unique ID"),
            Triple("dev_http_status", "HTTP Status Code Lookup", "Directory of HTTP response codes and RFC specs"),
            Triple("dev_hex_to_rgb", "Color HEX to RGB", "Convert hex color string to RGB values"),
            Triple("dev_rgb_to_hex", "Color RGB to HEX", "Convert RGB numbers to 6-digit hex code"),
            Triple("dev_rgb_to_hsl", "Color RGB to HSL", "Calculate Hue, Saturation, and Lightness"),
            Triple("dev_hsl_to_rgb", "Color HSL to RGB", "Convert HSL coordinates to RGB values"),
            Triple("dev_color_invert", "Color Inverter", "Calculate photonegative complementary color"),
            Triple("dev_user_agent", "User Agent Inspector", "Inspect client OS, browser, and device telemetry"),
            Triple("dev_curl_builder", "cURL Command Builder", "Generate terminal curl syntax for API testing"),
            Triple("dev_markdown", "Markdown Text Parser", "Test markdown syntax and preview formatting"),
            Triple("dev_cron_exp", "Cron Expression Parser", "Explain human meaning of 5-field cron strings"),
            Triple("dev_git_cheat", "Git Command Cheatsheet", "Quick reference for git branches, rebase, and commit"),
            Triple("dev_subnet_calc", "IP Subnet Calculator", "Calculate network address, broadcast, and host count"),
            Triple("dev_port_lookup", "TCP/UDP Port Directory", "Lookup common service ports (22, 80, 443, 3306)"),
            Triple("dev_semver", "SemVer Version Comparator", "Compare Semantic Versioning release strings"),
            Triple("dev_rand_str", "Random Alphanumeric String", "Generate randomized token strings of custom length"),
            Triple("dev_lorem", "Lorem Ipsum Generator", "Generate placeholder paragraphs for UI testing"),
            Triple("dev_byte_calc", "Byte Storage Calculator", "Calculate exact bytes from KB, MB, GB, TB"),
            Triple("dev_xml_to_json", "XML to JSON Converter", "Convert XML document syntax to JSON tree")
        )
        devTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.DEV_TOOLS, desc, ToolExecutionType.TEXT_TRANSFORM, isFeatured = i < 3))
        }

        // 3. SECURITY_TOOLS (30 items)
        val secTools = listOf(
            Triple("sec_pass_strength", "Password Strength Meter", "Evaluate entropy, length, and brute-force estimate"),
            Triple("sec_pass_gen", "Cyber Password Vault Gen", "Generate high-entropy password with custom symbols"),
            Triple("sec_sha1", "SHA-1 Hash Generator", "Legacy SHA-1 160-bit hash checksum"),
            Triple("sec_sha512", "SHA-512 Hash Generator", "High-security SHA-512 64-byte cryptographic hash"),
            Triple("sec_hmac_256", "HMAC-SHA256 Signer", "Calculate Keyed-Hash Message Authentication Code"),
            Triple("sec_aes_enc", "AES-256 Cipher Simulator", "Symmetric encryption demo with custom cipher key"),
            Triple("sec_aes_dec", "AES-256 Decrypt Simulator", "Decrypt ciphertext payload with decryption key"),
            Triple("sec_pbkdf2", "PBKDF2 Key Derivation Calc", "Estimate key derivation iterations and computational cost"),
            Triple("sec_entropy_calc", "Shannon Entropy Calculator", "Measure information density and unpredictability"),
            Triple("sec_common_ports", "Vulnerable Ports Matrix", "Security reference for high-risk network ports"),
            Triple("sec_sandbox_chk", "App Sandbox & Perms Audit", "Review active sandbox isolation parameters"),
            Triple("sec_root_chk", "Device Integrity & Root Check", "Analyze root indicators, su binary, and build tags"),
            Triple("sec_ssl_ciphers", "TLS/SSL Cipher Suites Guide", "Modern TLS 1.3 cryptographic suites reference"),
            Triple("sec_secret_vault", "Secure Memory Scratchpad", "Volatile zero-trace notepad that auto-clears on exit"),
            Triple("sec_stego_hide", "Zero-Width Steganography", "Hide secret message inside normal text using zero-width chars"),
            Triple("sec_stego_reveal", "Zero-Width Reveal", "Extract hidden zero-width text payload"),
            Triple("sec_otp_sim", "TOTP 2FA Token Generator", "Generate 6-digit Time-based One Time Password"),
            Triple("sec_pin_gen", "Cryptographic PIN Generator", "Generate secure non-repeating 4-8 digit numeric PINs"),
            Triple("sec_biometric_chk", "Biometric Hardware Status", "Check availability of fingerprint & face auth sensors"),
            Triple("sec_keystore_chk", "Android Keystore Provider", "Inspect hardware-backed cryptographic provider"),
            Triple("sec_mem_wipe", "Secure Volatile RAM Flusher", "Simulate cryptographically zeroing out memory buffer"),
            Triple("sec_ip_blacklist", "Cyber Threat IP Check", "Cross-reference IP against known CIDR risk databases"),
            Triple("sec_threat_level", "Cyber Threat Matrix DEFCON", "Interactive global threat status DEFCON 1-5 monitor"),
            Triple("sec_dns_leak", "DNS Leak Protection Guide", "Prevent ISP leakage through DoH and DNSSEC settings"),
            Triple("sec_https_chk", "HTTPS Certificate Audit Guide", "Inspect SSL chain, expiration, and SNI headers"),
            Triple("sec_headers_audit", "Security Headers Checklist", "CSP, HSTS, X-Frame-Options best practices"),
            Triple("sec_breach_guide", "Credential Breach Checklist", "Protocol response for compromised passwords"),
            Triple("sec_cyber_dict", "Cyber Defense Dictionary", "Definitions of zero-day, MITM, DDoS, APT, air-gap"),
            Triple("sec_session_tok", "Session Token Entropy Test", "Verify randomness of session identifiers"),
            Triple("sec_zkp_demo", "Zero-Knowledge Proof Demo", "Interactive simulation of interactive zero-knowledge proofs")
        )
        secTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.SECURITY_TOOLS, desc, ToolExecutionType.HASH_CRYPTO, isFeatured = i < 3))
        }

        // 4. CALCULATOR_SCIENCE (30 items)
        val mathTools = listOf(
            Triple("mth_sci_calc", "Futuristic Scientific Calculator", "Full trigonometry, logarithms, powers, and roots"),
            Triple("mth_basic_calc", "Cyber Quick Calculator", "Fast arithmetic evaluation with history tape"),
            Triple("mth_percentage", "Percentage Calculator", "Calculate percentage increase, decrease, and fractions"),
            Triple("mth_discount", "Discount & Tax Calculator", "Calculate markdown discount, sales tax, final price"),
            Triple("mth_tip_calc", "Tip & Split Bill Calculator", "Split check with tip percentage among operatives"),
            Triple("mth_age_calc", "Chronological Age Calculator", "Calculate exact years, months, days, minutes lived"),
            Triple("mth_date_diff", "Date Difference Calculator", "Calculate elapsed days between two specific dates"),
            Triple("mth_bmi_calc", "BMI & Health Index", "Body Mass Index and ideal category range"),
            Triple("mth_bmr_calc", "BMR & TDEE Calorie Calc", "Basal Metabolic Rate based on Harris-Benedict formula"),
            Triple("mth_compound_int", "Compound Interest Calculator", "Simulate principal growth with compound frequency"),
            Triple("mth_loan_emi", "Loan EMI & Amortization", "Monthly installment calculation with interest breakdown"),
            Triple("mth_circle_calc", "Circle Geometry Solver", "Calculate radius, diameter, circumference, area"),
            Triple("mth_triangle", "Right Triangle Solver", "Pythagorean theorem hypotenuse and angles"),
            Triple("mth_quad_eq", "Quadratic Equation Solver", "Solve ax² + bx + c = 0 for real and complex roots"),
            Triple("mth_matrix_det", "Matrix 2x2 Determinant", "Compute determinant of 2x2 linear matrix"),
            Triple("mth_prime_chk", "Prime Number Tester", "Test primality and find next sequential prime"),
            Triple("mth_factorial", "Factorial Calculator (n!)", "Calculate exact factorial product for integer n"),
            Triple("mth_fibonacci", "Fibonacci Sequence Generator", "Generate n terms of the Fibonacci sequence"),
            Triple("mth_gcd_lcm", "GCD & LCM Calculator", "Greatest Common Divisor and Least Common Multiple"),
            Triple("mth_rand_range", "Random Integer in Range", "Cryptographic random integer between Min and Max"),
            Triple("mth_speed_dist", "Speed, Distance & Time", "Calculate velocity, transit time, or total distance"),
            Triple("mth_ohms_law", "Ohm's Law Calculator", "Voltage (V), Current (I), Resistance (R), Power (P)"),
            Triple("mth_kinetic_en", "Kinetic Energy Calculator", "Calculate Ek = ½mv² in Joules"),
            Triple("mth_wavelength", "Wave Frequency to Wavelength", "Convert electromagnetic frequency to wavelength λ"),
            Triple("mth_resistor", "Resistor 4-Band Color Code", "Lookup resistance value in Ohms from color bands"),
            Triple("mth_decibel", "Sound Decibel Estimator", "Estimate sound pressure level in dB SPL"),
            Triple("mth_fuel_economy", "Fuel Consumption Calculator", "L/100km, MPG, and fuel trip budget estimation"),
            Triple("mth_aspect_ratio", "Screen Aspect Ratio Calculator", "Calculate width:height ratio and scaled dimensions"),
            Triple("mth_golden_ratio", "Golden Ratio Scaler (φ = 1.618)", "Compute aesthetic proportions using golden section"),
            Triple("mth_bitwise", "Bitwise Operations Lab", "Interactive AND, OR, XOR, NOT, shift bitwise sandbox")
        )
        mathTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.CALCULATOR_SCIENCE, desc, ToolExecutionType.CALCULATOR, isFeatured = i < 3))
        }

        // 5. CONVERTER_TOOLS (30 items)
        val convTools = listOf(
            Triple("cnv_length", "Length & Distance Converter", "Meters, Kilometers, Miles, Feet, Inches, Nautical Miles"),
            Triple("cnv_mass", "Weight & Mass Converter", "Kilograms, Grams, Pounds, Ounces, Metric Tons"),
            Triple("cnv_temp", "Temperature Converter", "Celsius, Fahrenheit, Kelvin instant reciprocal conversion"),
            Triple("cnv_speed", "Speed & Velocity Converter", "km/h, mph, m/s, Knots, Mach speed"),
            Triple("cnv_storage", "Digital Storage Converter", "Bytes, Kilobytes, Megabytes, Gigabytes, Terabytes"),
            Triple("cnv_time", "Time Duration Converter", "Milliseconds, Seconds, Minutes, Hours, Days, Weeks"),
            Triple("cnv_currency", "Currency Matrix Converter", "USD, EUR, IDR, JPY, GBP, CNY multi-currency rates"),
            Triple("cnv_pressure", "Pressure Units Converter", "Pascal, Bar, PSI, Atmosphere (atm), mmHg"),
            Triple("cnv_energy", "Energy & Work Converter", "Joules, Kilojoules, Calories, Kilocalories, Watt-hours"),
            Triple("cnv_power", "Power & Wattage Converter", "Watts, Kilowatts, Horsepower (hp), BTU/hr"),
            Triple("cnv_angle", "Angle Converter (Deg/Rad/Grad)", "Degrees to Radians, Gradians, Arcminutes"),
            Triple("cnv_fuel_econ", "Fuel Economy Converter", "MPG (US), MPG (UK), L/100km, km/L"),
            Triple("cnv_area", "Area Units Converter", "Square meters, Square feet, Hectares, Acres"),
            Triple("cnv_volume", "Volume & Fluid Converter", "Liters, Milliliters, Gallons, Fluid Ounces, Cubic Meters"),
            Triple("cnv_freq", "Frequency Units Converter", "Hertz (Hz), Kilohertz (kHz), Megahertz (MHz), Gigahertz (GHz)"),
            Triple("cnv_cooking", "Cooking Measures Converter", "Cups, Tablespoons, Teaspoons, Milliliters"),
            Triple("cnv_roman_to_num", "Roman Numerals to Number", "Convert Roman numerals (MMXXVI) to Arabic integer"),
            Triple("cnv_num_to_roman", "Number to Roman Numerals", "Convert Arabic integer to Roman numerals"),
            Triple("cnv_num_to_words", "Number to Words Spellout", "Spell out numeric amount in plain text"),
            Triple("cnv_density", "Density Converter", "kg/m³, g/cm³, lb/ft³ material density converter"),
            Triple("cnv_force", "Force Units Converter", "Newtons (N), Dynes, Pound-force (lbf)"),
            Triple("cnv_torque", "Torque Units Converter", "Newton-meters (N·m), Pound-feet (lb-ft)"),
            Triple("cnv_hex_dec", "Hexadecimal to Decimal", "Base-16 hex values to Base-10 integers"),
            Triple("cnv_octal", "Octal to Decimal Converter", "Base-8 octal values to Base-10 integers"),
            Triple("cnv_metric_imp", "Metric to Imperial Fast Pad", "General metric quantities to imperial standards"),
            Triple("cnv_imp_metric", "Imperial to Metric Fast Pad", "Imperial measurements to international metric"),
            Triple("cnv_pace_speed", "Running Pace to Speed", "Minutes per km to km/h and mph converter"),
            Triple("cnv_shoe_size", "International Shoe Sizes", "US, UK, EU, Japan shoe sizing cross-reference"),
            Triple("cnv_clothes_size", "Clothing Size Standard", "US, EU, UK clothing size chart"),
            Triple("cnv_paper_iso", "ISO Paper Sizes (A0-A8)", "Standard millimeter dimensions for ISO 216 sheets")
        )
        convTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.CONVERTER_TOOLS, desc, ToolExecutionType.CONVERTER, isFeatured = i < 3))
        }

        // 6. SYSTEM_TOOLS (30 items)
        val sysTools = listOf(
            Triple("sys_battery", "Battery Telemetry Monitor", "Live battery percentage, charging state, health status"),
            Triple("sys_ram", "RAM Memory Telemetry", "Inspect total system RAM, available memory, low-memory state"),
            Triple("sys_storage", "Storage Space Inspector", "Internal flash drive total capacity and free space"),
            Triple("sys_display", "Display Resolution & Metrics", "Screen width, height, density DPI, refresh rate"),
            Triple("sys_cpu_arch", "CPU Architecture & ABI", "Instruction set architecture (arm64-v8a / x86_64)"),
            Triple("sys_device_info", "Device Hardware & Brand", "Manufacturer, model name, device codename, board"),
            Triple("sys_android_ver", "Android OS & API Level", "Android version code, security patch level, SDK release"),
            Triple("sys_kernel", "Kernel & Linux Build", "Operating system kernel version and OS architecture"),
            Triple("sys_uptime", "System Uptime Telemetry", "Total device uptime and time since cold boot"),
            Triple("sys_refresh_rate", "Display Refresh Rate", "Detected display hardware scan rate (60Hz / 90Hz / 120Hz)"),
            Triple("sys_audio_output", "Audio Device Telemetry", "Active output route: Speaker, Headset, Bluetooth"),
            Triple("sys_network_type", "Network Type & State", "Active connection: Wi-Fi, Cellular, Ethernet, or Offline"),
            Triple("sys_sensors_list", "Hardware Sensor Matrix", "Count and identify onboard accelerometer, gyro, light sensors"),
            Triple("sys_vibrate_test", "Haptic Actuator Diagnostic", "Test device vibration motor with custom pulse patterns"),
            Triple("sys_torch", "Flashlight / Torch Trigger", "Toggle camera LED hardware illuminator"),
            Triple("sys_orientation", "Screen Orientation Sensor", "Live portrait / landscape orientation detection"),
            Triple("sys_gc_trigger", "JVM Memory GC Trigger", "Request Java Virtual Machine garbage collection run"),
            Triple("sys_thermal", "Thermal Throttling Monitor", "System thermal headroom and CPU throttling status"),
            Triple("sys_locales", "System Language & Locale", "Active device language, country code, display locale"),
            Triple("sys_font_scale", "System Font Scaling Factor", "User accessibility font scaling multiplier"),
            Triple("sys_dark_mode", "System Dark Mode Detection", "Inspect system-wide UI night mode state"),
            Triple("sys_multiwindow", "Multi-Window Mode Status", "Check if app is running in split-screen or freeform window"),
            Triple("sys_build_id", "Build Fingerprint & ID", "Cryptographic Android OS build fingerprint tag"),
            Triple("sys_powersave", "Power Save Mode Status", "Inspect whether Android battery saver mode is active"),
            Triple("sys_storage_dirs", "Storage Directory Paths", "Standard internal data and cache filesystem directories"),
            Triple("sys_notch_insets", "Display Notch & Insets", "Status bar, navigation bar, and display cutout metrics"),
            Triple("sys_timezone", "Timezone & UTC Offset", "Current device timezone ID, display name, GMT offset"),
            Triple("sys_hardware_accel", "Hardware GPU Acceleration", "Inspect hardware render pipeline and canvas acceleration"),
            Triple("sys_screen_on", "Keep Screen On Controller", "Prevent display dimming during long technical sessions"),
            Triple("sys_nfc_status", "NFC Hardware Detector", "Inspect Near Field Communication radio availability")
        )
        sysTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.SYSTEM_TOOLS, desc, ToolExecutionType.SYSTEM_INFO, isFeatured = i < 3))
        }

        // 7. GENERATORS (30 items)
        val genTools = listOf(
            Triple("gen_strong_pass", "Quantum Secure Password Gen", "Generate customizable entropy-dense passwords"),
            Triple("gen_uuid", "UUID v4 Unique Identifier", "Standard RFC 4122 random unique identifier"),
            Triple("gen_nickname", "Cyber Callsign Generator", "Futuristic operative handles and hacker codenames"),
            Triple("gen_sci_name", "Sci-Fi Starship Name Gen", "Majestic names for dreadnoughts, probes, and stations"),
            Triple("gen_lorem", "Cyber Lorem Ipsum Paragraphs", "Sci-fi and standard placeholder dummy text"),
            Triple("gen_palette", "Neon Cyber Palette Generator", "Generate 5-color neon futuristic color schemes"),
            Triple("gen_dice", "Physics 3D Dice Roller", "Roll d4, d6, d8, d10, d12, d20, d100 polyhedral dice"),
            Triple("gen_coin", "Quantum Coin Flipper", "Binary heads/tails decision generator"),
            Triple("gen_decision", "Decision Matrix Oracle", "Input options and let the quantum algorithm choose"),
            Triple("gen_test_cc", "Test Card Number (Luhn)", "Generate mathematically valid test numbers for dev QA"),
            Triple("gen_mock_user", "Mock Operative Profile Gen", "Generate realistic test persona (name, email, role)"),
            Triple("gen_ip_addr", "Random IPv4 & IPv6 Generator", "Generate random valid IP addresses for testing"),
            Triple("gen_mac_addr", "Random MAC Hardware Address", "Generate randomized IEEE 802 MAC address string"),
            Triple("gen_fake_address", "Mock Geospatial Coordinates", "Generate simulated latitude, longitude, and elevation"),
            Triple("gen_quote", "Cyberpunk Philosophy Quotes", "Thought-provoking quotes on technology, humanity, AI"),
            Triple("gen_joke", "Developer & Tech Joke Bot", "Humorous developer jokes, puns, and one-liners"),
            Triple("gen_facts", "Cosmic & Tech Fact Generator", "Mind-bending facts about the cosmos and computing"),
            Triple("gen_barcode", "Barcode Data Formatter", "Format strings for Code-128 and EAN-13 barcodes"),
            Triple("gen_qr_matrix", "QR Code ASCII Matrix", "Display ASCII/visual representation of text data"),
            Triple("gen_hex_color", "Random Hex Color Gen", "Generate random RGB/Hex color codes with preview"),
            Triple("gen_gradient", "Futuristic CSS Gradient Gen", "Dual-stop neon gradients for UI designers"),
            Triple("gen_alias_email", "Email Alias Generator", "Create disposable email alias syntax with timestamp"),
            Triple("gen_port_num", "Random Ephemeral Port", "Generate random high port number (1024 - 65535)"),
            Triple("gen_date_range", "Random Date in Year Range", "Generate random timestamp between two target dates"),
            Triple("gen_cat_fact", "Random Cybernetic Cat Facts", "Fascinating facts about feline companions"),
            Triple("gen_cyber_mission", "Procedural Cyber Mission Gen", "Generate tactical hacker missions and objectives"),
            Triple("gen_guid_compact", "Compact 32-Char Hex GUID", "Hyphenless uppercase hex unique identifier"),
            Triple("gen_pin_code", "4 & 6-Digit Secure PINs", "Generate random numeric security authentication codes"),
            Triple("gen_ascii_banner", "Cyber ASCII Text Art", "Generate futuristic ASCII boxed banners"),
            Triple("gen_tarot_cyber", "Cybernetic Tarot Card Draw", "Draw cards from the Neon Cyber Tarot Deck")
        )
        genTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.GENERATORS, desc, ToolExecutionType.GENERATOR, isFeatured = i < 3))
        }

        // 8. MEDIA_AUDIO (30 items)
        val mediaTools = listOf(
            Triple("med_synth_tone", "Futuristic Synth Tone Generator", "Synthesize sine audio frequencies from 20Hz to 20kHz"),
            Triple("med_sine_wave", "Pure Sine Wave Generator", "Generate smooth harmonic reference tone"),
            Triple("med_sq_wave", "8-Bit Square Wave Generator", "Retro chiptune synthesizer waveform audio"),
            Triple("med_metronome", "BPM Cyber Metronome", "Audible tempo clicker with customizable BPM (40-240)"),
            Triple("med_tuning_fork", "A440 Orchestral Pitch Fork", "Standard 440.00 Hz concert pitch reference tone"),
            Triple("med_white_noise", "White Noise Sleep Generator", "Full spectrum equal-energy audio noise mask"),
            Triple("med_pink_noise", "Pink Noise Relaxation", "Equal energy per octave acoustic sound generator"),
            Triple("med_morse_audio", "Morse Code Audio Beeper", "Auditory telegraph beeps for encoded text"),
            Triple("med_freq_chart", "Audio Frequency Spectrum Chart", "Sub-bass, bass, midrange, highs, air frequency ranges"),
            Triple("med_db_estimator", "Acoustic Decibel Reference", "Real-world sound loudness benchmarks in dB"),
            Triple("med_aspect_fit", "Video Scaling & Fit Calculator", "Calculate letterbox vs pillarbox dimensions"),
            Triple("med_upscale_calc", "Resolution Upscale Calculator", "Calculate pixel multiplier for 720p, 1080p, 4K, 8K"),
            Triple("med_bitrate_calc", "Audio File Bitrate Estimator", "Estimate MP3, FLAC, WAV file sizes per duration"),
            Triple("med_fps_duration", "Frame Rate to Frame Count", "Calculate exact frames for 24fps, 30fps, 60fps, 120fps"),
            Triple("med_aspect_vis", "Aspect Ratio Visualizer", "Visual comparison of 16:9, 21:9, 4:3, 1:1, 9:16"),
            Triple("med_luminance", "Relative Color Luminance", "WCAG perceived brightness formula calculation"),
            Triple("med_reverb_delay", "Audio Delay & Reverb Time Calc", "Calculate millisecond delay times based on tempo BPM"),
            Triple("med_tap_tempo", "Tap Tempo BPM Detector", "Tap rhythmically to detect musical beats per minute"),
            Triple("med_freq_visualizer", "Audio Wave Visualizer Simulation", "Interactive animated sound spectrum bars"),
            Triple("med_note_to_freq", "Musical Note to Frequency", "Lookup fundamental frequency of all musical notes"),
            Triple("med_eq_helper", "Parametric Equalizer Guide", "Q factor, cutoff frequency, and peaking filters"),
            Triple("med_midi_num", "MIDI Note Number to Key", "Convert MIDI numbers 0-127 to musical notation"),
            Triple("med_bass_sweep", "Subwoofer Bass Sweep (20-100Hz)", "Low-frequency acoustic room response test"),
            Triple("med_sample_rate", "Audio Sample Rate Guide", "44.1kHz, 48kHz, 96kHz, 192kHz comparison"),
            Triple("med_palette_picker", "Image Color Extractor Guide", "Techniques for dominant color clustering"),
            Triple("med_stems_guide", "Audio Stems Separation Guide", "Vocals, drums, bass, instruments mixing reference"),
            Triple("med_video_bitrate", "Video Storage Bitrate Calc", "Calculate gigabytes per hour of 4K H.264 / HEVC video"),
            Triple("med_fov_calc", "Camera Lens Field of View", "Calculate focal length and sensor crop factor"),
            Triple("med_shutter_speed", "Shutter Angle & Speed Guide", "180-degree cinema shutter angle calculator"),
            Triple("med_soundboard", "Cyber Sci-Fi Soundboard", "Futuristic UI clicks, power-up hums, laser FX")
        )
        mediaTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.MEDIA_AUDIO, desc, ToolExecutionType.AUDIO_SYNTH, isFeatured = i < 3))
        }

        // 9. PRODUCTIVITY_TOOLS (30 items)
        val prodTools = listOf(
            Triple("prd_pomodoro", "Cyber Pomodoro Focus Timer", "25m focus, 5m break tactical cycle with HUD sound"),
            Triple("prd_stopwatch", "Millisecond Tactical Stopwatch", "High precision timer with split and lap tracking"),
            Triple("prd_countdown", "Event Countdown Timer", "Set target duration with loud alarm simulation"),
            Triple("prd_scratchpad", "Quick HUD Scratchpad", "Persistent notes with instant character counter"),
            Triple("prd_markdown_notes", "Markdown Quick Editor", "Write formatted markdown documentation"),
            Triple("prd_eisenhower", "Eisenhower Priority Matrix", "Categorize tasks by urgency and importance"),
            Triple("prd_habit_streak", "Habit Streak Tracker", "Log consecutive daily discipline streaks"),
            Triple("prd_reading_time", "Reading Time Estimator", "Calculate minutes to read given word count"),
            Triple("prd_expense_split", "Operative Expense Splitter", "Divide shared group expenses equally or weighted"),
            Triple("prd_days_countdown", "Days Until Target Date", "Live days, hours, minutes until significant milestones"),
            Triple("prd_checklist", "Tactical Mission Checklist", "Interactive task list with strike-through and progress"),
            Triple("prd_kanban_preview", "Mini Kanban Board", "To-Do, In-Progress, and Completed task lanes"),
            Triple("prd_goal_tracker", "Goal Progress Percentage", "Track numerical milestone progression toward targets"),
            Triple("prd_water_intake", "Daily Water Hydration Tracker", "Log milliliters consumed toward daily 2500ml target"),
            Triple("prd_sleep_calc", "Sleep Cycle REM Calculator", "Calculate optimal wake-up times based on 90m cycles"),
            Triple("prd_world_clock", "World Clock Matrix", "Tokyo, London, New York, Jakarta, UTC simultaneous clocks"),
            Triple("prd_tz_diff", "Timezone Difference Calculator", "Compare local time offsets across global regions"),
            Triple("prd_typing_test", "Typing Speed Test (WPM)", "Interactive words per minute and accuracy evaluator"),
            Triple("prd_bullet_journal", "Bullet Journal Notation", "Rapid logging task, note, and event symbols"),
            Triple("prd_focus_sound", "Focus Soundscapes", "Ambient sound generators for uninterrupted focus"),
            Triple("prd_meeting_cost", "Meeting Cost Calculator", "Calculate monetary burn rate per minute of conference"),
            Triple("prd_life_percent", "Year & Life Elapsed Percentage", "Percentage of current year, month, and day completed"),
            Triple("prd_deadline_est", "Project Deadline Estimator", "Compute completion date from hours and velocity"),
            Triple("prd_routine_builder", "Daily Morning Routine", "Step-by-step timed morning protocol"),
            Triple("prd_mood_logger", "Operative Mental State Log", "Track daily emotional resilience and focus rating"),
            Triple("prd_task_random", "Random Task Picker", "Break procrastination paralysis by picking random task"),
            Triple("prd_breathing", "4-7-8 Tactical Breathing", "Box breathing visual guide for instant stress reduction"),
            Triple("prd_deep_work", "Deep Work Session Log", "Record uninterrupted flow hours logged per week"),
            Triple("prd_weekly_review", "Weekly Debrief Prompts", "Structured reflection questions for weekend review"),
            Triple("prd_affirmation", "Daily Stoic Affirmation", "Mental models and reflections from Marcus Aurelius & Epictetus")
        )
        prodTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.PRODUCTIVITY_TOOLS, desc, ToolExecutionType.CALCULATOR, isFeatured = i < 3))
        }

        // 10. ENTERTAINMENT (30 items)
        val entTools = listOf(
            Triple("ent_reaction", "Reaction Time Reflex Test", "Measure neural response speed in milliseconds"),
            Triple("ent_memory_matrix", "Memory Matrix Sequence", "Memorize and repeat glowing futuristic pattern sequences"),
            Triple("ent_number_guess", "Quantum Number Guesser", "Higher or Lower binary search guessing game"),
            Triple("ent_tic_tac_toe", "Tic-Tac-Toe vs Cyber AI", "Play classic tactical grid game against heuristic AI"),
            Triple("ent_2048_mini", "Cyber 2048 Tile Puzzle", "Slide and merge powers of two on 4x4 matrix"),
            Triple("ent_trivia", "Cyberpunk & Sci-Fi Trivia", "Test knowledge on cyberpunk lore, movies, and computing"),
            Triple("ent_magic_8ball", "Quantum Oracle 8-Ball", "Ask any question and consult quantum probability"),
            Triple("ent_soundboard_fx", "Cyberpunk Soundboard FX", "Trigger high-tech futuristic sound pulses"),
            Triple("ent_ascii_gallery", "ASCII Art Gallery & Creator", "Browse and copy intricate cyber ASCII artwork"),
            Triple("ent_tarot", "Neon Cyber Tarot Reading", "Draw past, present, and future cards from futuristic deck"),
            Triple("ent_fortune_cookie", "Digital Fortune Cookie", "Crack open encrypted cookies for words of wisdom"),
            Triple("ent_speedrun", "Micro-Speedrun Tap Challenge", "Tap as fast as possible in a 10-second adrenaline window"),
            Triple("ent_riddle", "Cryptic Tech Riddles", "Solve challenging logical riddles and brain teasers"),
            Triple("ent_cyber_sound", "Synthesized Laser FX", "Play customizable science fiction laser blasts"),
            Triple("ent_terminal_typer", "Hollywood Hacker Typer", "Type gibberish to produce real cyber shell script code"),
            Triple("ent_matrix_rain", "Matrix Digital Rain Screen", "Hypnotic falling green glyph animation simulation"),
            Triple("ent_particles", "Interactive Particle Sandbox", "Touch screen to repel and attract glowing cyber particles"),
            Triple("ent_coin_bet", "Coin Toss Betting Game", "Double your virtual credits with quantum coin flips"),
            Triple("ent_rps_game", "Rock Paper Scissors vs AI", "Play against pattern-learning AI opponent"),
            Triple("ent_anagram", "Cyber Name Anagram Finder", "Find anagram permutations of words and names"),
            Triple("ent_morse_trainer", "Morse Code Listening Trainer", "Audio quiz to identify Morse code letters"),
            Triple("ent_joke_bot", "AI Comedian Joke Stream", "Infinite supply of programming and tech jokes"),
            Triple("ent_drawing_canvas", "Neon Light Drawing Pad", "Draw with glowing cyber neon brush strokes"),
            Triple("ent_space_facts", "Cosmic Wonders Directory", "Interactive guide to black holes, quasars, and exoplanets"),
            Triple("ent_constellation", "Stellar Constellation Guide", "Find star patterns in northern and southern skies"),
            Triple("ent_alien_translator", "Xeno Alien Dialect Encoder", "Transform English words into stylized alien glyphs"),
            Triple("ent_cyber_radio", "Synthwave Cyber Radio Sim", "Ambient futuristic lo-fi and synthwave track info"),
            Triple("ent_wallpaper_pick", "Cyber Wallpaper Roulette", "Generate vibrant neon wallpaper presets"),
            Triple("ent_speed_read", "Speed Reading Flash Trainer", "Flash single words at 300-800 WPM to train optic speed"),
            Triple("ent_sound_synth", "Interactive Audio Frequency Synth", "Modulate oscillator waveforms in real-time")
        )
        entTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.ENTERTAINMENT, desc, ToolExecutionType.SIMULATOR_GAME, isFeatured = i < 3))
        }

        // 11. CUSTOMIZATION_THEMES (30 items)
        val custTools = listOf(
            Triple("cst_accent_picker", "Neon Accent Color Switcher", "Select Crimson Red, Electric Cyan, Violet, or Matrix Green"),
            Triple("cst_bg_selector", "Cinematic Background Selector", "Switch between Singularity, Cyber Grid, and Cyber Girl"),
            Triple("cst_grid_opacity", "HUD Grid Overlay Opacity", "Tune intensity of subtle cyber grid lines"),
            Triple("cst_scanlines", "CRT Scanline Filter Toggle", "Enable retro CRT television scanline raster lines"),
            Triple("cst_particle_density", "Glow Particle Density", "Adjust number of floating ambient particles"),
            Triple("cst_corner_radius", "HUD Card Corner Radius", "Configure sharp rectangular or rounded card edges"),
            Triple("cst_border_stroke", "Neon Border Thickness", "Adjust line weight of cyber card outlines"),
            Triple("cst_font_spacing", "Typography Letter Spacing", "Fine-tune tracking and letter spacing of monospace text"),
            Triple("cst_anim_speed", "Animation Motion Multiplier", "Speed up or slow down interface spring transitions"),
            Triple("cst_sound_fx", "Tactical Audio Click Feedback", "Toggle mechanical sci-fi clicks on button taps"),
            Triple("cst_glass_trans", "Glassmorphism Transparency", "Adjust backdrop blur and glass opacity levels"),
            Triple("cst_status_theme", "Status Bar Inset Style", "Sync Android system status bar with current theme"),
            Triple("cst_clock_style", "HUD Digital Clock Display", "24-hour military format vs 12-hour AM/PM with seconds"),
            Triple("cst_hologram_eff", "Hologram Ripple Effect", "Add subtle chromatic aberration shimmer to card titles"),
            Triple("cst_ambient_pulse", "Ambient Glow Breathing Light", "Pulsing neon glow animation on primary buttons"),
            Triple("cst_custom_hex", "Custom Accent Hex Code", "Enter custom 6-digit hex code for personalized accent"),
            Triple("cst_font_scale_ui", "Internal Font Scale Selector", "Scale app typography independent of OS settings"),
            Triple("cst_contrast_boost", "High Contrast Cyber Mode", "Maximize contrast for harsh sunlight viewing"),
            Triple("cst_oled_black", "True OLED Pitch Black Mode", "Turn off all non-essential surface gray tones (#000000)"),
            Triple("cst_matrix_theme", "Classic Terminal Green Theme", "All-green retro cyberpunk phosphor theme"),
            Triple("cst_synthwave", "Synthwave 80s Sunset Theme", "Deep magenta and neon cyan retro wave styling"),
            Triple("cst_blood_red", "Blood Crimson Cyber Theme", "Aggressive military red tactical HUD theme"),
            Triple("cst_electric_cyan", "Electric Cyan Glacier Theme", "Clean icy blue futuristic corporate theme"),
            Triple("cst_gold_horizon", "Solar Gold Horizon Theme", "Luxurious warm gold cyber aesthetic"),
            Triple("cst_shadows", "Neon Glow Shadows Toggle", "Enable or disable GPU-rendered colored drop shadows"),
            Triple("cst_audio_pitch", "Audio UI Pitch Frequency", "Shift frequency of UI sound feedback clicks"),
            Triple("cst_icon_style", "Icon Active Pill Indicator", "Choose between glowing outline or filled pill"),
            Triple("cst_elevation", "Card Tonal Elevation", "Tune depth elevation and surface layering"),
            Triple("cst_blur_filter", "Surface Backdrop Blur Effect", "Enable modern translucent glass blur renderers"),
            Triple("cst_reset_all", "Factory Default Reset", "Restore all interface styles to default out-of-box settings")
        )
        custTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.CUSTOMIZATION_THEMES, desc, ToolExecutionType.INFO_SPEC, isFeatured = i < 3))
        }

        // 12. NETWORK_TOOLS (30 items)
        val netTools = listOf(
            Triple("net_ip_detect", "Local & Network IP Inspector", "Detect active IP address and gateway configuration"),
            Triple("net_ping_est", "Ping Latency Benchmark", "Measure response latency in milliseconds to servers"),
            Triple("net_dns_explain", "DNS Records Explainer (A, AAAA, MX)", "Guide to DNS domain records, CNAME, TXT, and TTL"),
            Triple("net_http_headers", "HTTP Header Inspector", "Standard request and response headers reference"),
            Triple("net_port_check", "Port Connectivity Checker", "Test reachability of standard service ports"),
            Triple("net_wifi_signal", "Wi-Fi Signal Strength Estimator", "Analyze dBm signal strength and channel quality"),
            Triple("net_data_usage", "Data Transfer Estimator", "Estimate streaming and download data consumption"),
            Triple("net_speed_test", "Network Speed Test Simulator", "Simulate download, upload, and jitter metrics"),
            Triple("net_geo_lookup", "IP Geolocation Lookup", "Inspect country, city, ISP, and coordinates for IP"),
            Triple("net_mac_vendor", "MAC Address OUI Vendor Lookup", "Lookup hardware manufacturer from MAC prefix"),
            Triple("net_subnet_mask", "Subnet Mask & CIDR Matrix", "Cheat sheet for /24, /16, /8 and subnet masks"),
            Triple("net_cidr_range", "CIDR to IP Range Calculator", "Calculate start IP, end IP, and usable host count"),
            Triple("net_public_private", "Public vs Private IP Classifier", "Identify RFC 1918 private ranges (10.x, 192.168.x)"),
            Triple("net_traceroute", "Traceroute Hop Simulator", "Visualize network packet routing across global nodes"),
            Triple("net_user_agent_net", "Client User-Agent Analyzer", "Inspect client network identity headers"),
            Triple("net_http_methods", "HTTP Methods Guide (GET, POST, PUT)", "Idempotency and usage of REST API verbs"),
            Triple("net_webhook_test", "Webhook Payload Formatter", "Generate sample JSON event bodies for webhooks"),
            Triple("net_ssl_expiry", "SSL/TLS Certificate Expiry Checker", "Calculate days remaining until certificate expires"),
            Triple("net_dns_flush", "DNS Cache Flush Guide", "Commands to flush resolver cache on all operating systems"),
            Triple("net_whois_spec", "WHOIS Protocol Reference", "Domain registration and registrar information schema"),
            Triple("net_download_time", "Bandwidth Download Time Calc", "Calculate exact minutes to transfer gigabytes"),
            Triple("net_websocket", "WebSocket Protocol Primer", "Full-duplex RFC 6455 persistent socket connection guide"),
            Triple("net_cloudflare_dns", "Cloudflare 1.1.1.1 & Google 8.8.8.8", "Fastest public recursive resolvers reference"),
            Triple("net_proxy_guide", "Forward vs Reverse Proxy Guide", "Architectures of Squid, Nginx, and Envoy proxies"),
            Triple("net_topology", "Network Topologies (Mesh, Star, Bus)", "Visual comparison of network physical layouts"),
            Triple("net_tor_network", "Tor Onion Routing Primer", "Multi-layer encrypted routing and relay nodes guide"),
            Triple("net_ipv6_validator", "IPv6 Address Syntax Validator", "Check valid hexadecimal notation and compression"),
            Triple("net_ipv4_bin", "IPv4 to Binary Bit Stream", "Convert dotted quad decimal to 32 binary bits"),
            Triple("net_mtu_size", "MTU & Packet MSS Optimizer", "Calculate 1500 byte Maximum Transmission Unit overhead"),
            Triple("net_traffic_sim", "Network Throughput Simulator", "Simulate live bandwidth utilization graphs")
        )
        netTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.NETWORK_TOOLS, desc, ToolExecutionType.INFO_SPEC, isFeatured = i < 3))
        }

        // 13. IMAGE_COLOR (30 items)
        val clrTools = listOf(
            Triple("clr_neon_picker", "Futuristic Neon Color Picker", "Interactive RGB/HEX slider with live luminous preview"),
            Triple("clr_palette_gen", "Harmonious Palette Generator", "Generate complementary and analogous color sets"),
            Triple("clr_contrast_chk", "WCAG 2.1 Contrast Checker", "Verify AA and AAA text contrast ratios"),
            Triple("clr_css_gradient", "CSS Gradient Code Generator", "Copy linear and radial CSS gradient strings"),
            Triple("clr_hex_cmyk", "HEX to CMYK Print Converter", "Convert digital screen hex to Cyan, Magenta, Yellow, Key"),
            Triple("clr_cmyk_hex", "CMYK to HEX Converter", "Convert print ink percentages to screen RGB hex"),
            Triple("clr_colorblind", "Color Blindness Vision Simulator", "Preview colors as Protanopia, Deuteranopia, Tritanopia"),
            Triple("clr_complement", "Complementary Color Finder", "Calculate exact 180-degree opposite wheel color"),
            Triple("clr_analogous", "Analogous Color Harmony", "Generate adjacent 30-degree harmonic hues"),
            Triple("clr_triadic", "Triadic Color Harmony", "Equilateral 120-degree three-color balance"),
            Triple("clr_tetradic", "Tetradic Rectangle Harmony", "Four-color dual complementary palette"),
            Triple("clr_aspect_crop", "Crop Aspect Ratio Calculator", "Calculate exact pixel crop boundaries"),
            Triple("clr_res_resizer", "Image Resolution Resizer Calc", "Calculate scaled dimensions while preserving aspect ratio"),
            Triple("clr_dpi_ppi", "DPI to PPI Density Calculator", "Convert dots per inch to display pixels per inch"),
            Triple("clr_svg_visual", "SVG Path Syntax Visualizer", "Interactive preview of SVG path d=\"M...\" commands"),
            Triple("clr_shades", "Color Shades Generator", "Generate darker steps down to absolute black"),
            Triple("clr_tints", "Color Tints Generator", "Generate lighter steps up to pure white"),
            Triple("clr_monochrome", "Monochromatic Scale Builder", "Generate tone gradations of single base hue"),
            Triple("clr_pastel", "Pastel Soft Color Generator", "Generate desaturated high-luminance tones"),
            Triple("clr_neon_presets", "Cyberpunk Neon Presets", "Curated palette: Laser Pink, Acid Green, Cyan Blue"),
            Triple("clr_bw_filter", "Black & White Grayscale Simulator", "Preview image luminance conversion formulas"),
            Triple("clr_sepia_filter", "Vintage Sepia Tone Simulator", "Calculate warm nostalgic sepia matrix transform"),
            Triple("clr_invert_sim", "Color Inversion Simulator", "Compute 255 - RGB photographic negative"),
            Triple("clr_bright_contrast", "Brightness & Contrast Adjuster", "Simulate gamma and linear gain curve adjustments"),
            Triple("clr_pixel_density", "Screen Pixel Density (PPI) Calc", "Calculate PPI from screen diagonal and resolution"),
            Triple("clr_exif_guide", "Camera EXIF Metadata Guide", "ISO, Aperture f-stop, Shutter, GPS tags reference"),
            Triple("clr_name_finder", "Color Name Identifier", "Lookup closest official CSS color name for any hex code"),
            Triple("clr_alpha_hex", "Hex Alpha Opacity Table", "Reference for 00 to FF percentage opacity hex codes"),
            Triple("clr_base64_img", "Base64 Image Data URI Helper", "Format data:image/png;base64,... strings"),
            Triple("clr_shadow_gen", "CSS Box Shadow Neon Glow Gen", "Generate layered neon glow drop-shadow CSS rules")
        )
        clrTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.IMAGE_COLOR, desc, ToolExecutionType.COLOR_TOOL, isFeatured = i < 3))
        }

        // 14. INFORMATION_SPECS (30 items)
        val infoTools = listOf(
            Triple("inf_android_hist", "Android OS Release History", "Timeline from Cupcake 1.5 to Android 15 Vanilla Ice Cream"),
            Triple("inf_linux_kernel", "Linux Kernel Version Guide", "Milestones of Linux kernel architectures and features"),
            Triple("inf_cpu_tier", "Mobile CPU Processor Hierarchy", "Snapdragon, Tensor, Dimensity, and Apple Bionic specs"),
            Triple("inf_resolutions", "Standard Display Resolutions", "HD, FHD, QHD, 4K UHD, 8K exact pixel dimensions"),
            Triple("inf_battery_tech", "Battery Technology (Li-Ion vs Li-Po)", "Chemistry, charge cycles, and thermal degradation"),
            Triple("inf_ram_ddr", "RAM Generations (LPDDR4X vs LPDDR5X)", "Bandwidth, clock speeds, and power efficiency"),
            Triple("inf_bt_codecs", "Bluetooth Audio Codecs Guide", "SBC, AAC, aptX Adaptive, LDAC bitrates and latency"),
            Triple("inf_wifi_specs", "Wi-Fi Standards (802.11ax/be)", "Wi-Fi 6, 6E, 7 frequencies and gigabit throughput"),
            Triple("inf_cellular_5g", "5G Cellular (Sub-6GHz vs mmWave)", "Standalone vs Non-Standalone carrier technology"),
            Triple("inf_usb_specs", "USB & Thunderbolt Standards", "USB 2.0, USB 3.2 Gen2, USB4, Thunderbolt 4 speeds"),
            Triple("inf_display_panel", "Display Panels (OLED vs AMOLED vs IPS)", "Contrast, PWM dimming, burn-in, refresh latency"),
            Triple("inf_sensors_guide", "Smartphone Sensors Encyclopedia", "IMU, Hall effect, Barometer, ToF sensor mechanics"),
            Triple("inf_gnss_specs", "GNSS Constellations (GPS, Galileo)", "GLONASS, BeiDou satellite positioning accuracy"),
            Triple("inf_biometrics", "Biometric Security Architectures", "Ultrasonic vs Optical fingerprints, 3D Structured Light"),
            Triple("inf_storage_ufs", "Storage Technologies (UFS 4.0 vs eMMC)", "Sequential read/write speeds and IOPS performance"),
            Triple("inf_audio_codecs", "Audio Formats (FLAC vs MP3 vs ALAC)", "Lossless vs lossy compression bitrates"),
            Triple("inf_video_codecs", "Video Codecs (AV1 vs HEVC vs ProRes)", "Compression efficiency, hardware decoding, licensing"),
            Triple("inf_gpu_arch", "Mobile GPU Architecture (Adreno vs Mali)", "Vulkan, OpenGL ES, hardware ray tracing capabilities"),
            Triple("inf_markdown_syntax", "Markdown Full Syntax Cheatsheet", "Headers, tables, checklists, code blocks syntax"),
            Triple("inf_shortcuts", "Terminal & Editor Keyboard Shortcuts", "Essential Vim, Bash, and Android Studio shortcuts"),
            Triple("inf_periodic_table", "Periodic Table of Elements", "Atomic number, symbol, mass, electron configuration"),
            Triple("inf_si_prefixes", "SI Metric Prefixes (Nano to Yotta)", "Orders of magnitude from 10⁻²⁴ to 10²⁴"),
            Triple("inf_constants", "Fundamental Physical Constants", "Speed of light c, Planck's h, Gravitational G, Electron e"),
            Triple("inf_solar_system", "Solar System Planetary Data", "Orbital period, mass, gravity, surface temperature"),
            Triple("inf_space_timeline", "Space Exploration Timeline", "Sputnik, Apollo, Voyager, James Webb, Artemis milestones"),
            Triple("inf_quantum_primer", "Quantum Computing Primer", "Qubits, superposition, entanglement, and quantum gates"),
            Triple("inf_cyber_lore", "Cyberpunk Canon Lore & Timeline", "Evolution of cyberpunk literature and aesthetics"),
            Triple("inf_iso_countries", "ISO 3166 Country Codes", "Alpha-2 and Alpha-3 country codes directory"),
            Triple("inf_timezone_matrix", "World Timezone Reference", "Standard UTC offsets and Daylight Saving rules"),
            Triple("inf_currency_symbols", "World Currency Symbols Directory", "Unicode currency signs ($, €, ¥, £, Rp, ₿)")
        )
        infoTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.INFORMATION_SPECS, desc, ToolExecutionType.INFO_SPEC, isFeatured = i < 3))
        }

        // 15. AI_QUANTUM_TOOLS (30 items)
        val aiTools = listOf(
            Triple("ai_quantum_rng", "Quantum Random Bit Generator", "Simulate true quantum randomness using wave collapse"),
            Triple("ai_superposition", "Superposition Probability Sim", "Bloch sphere state representation and probability amplitudes"),
            Triple("ai_quantum_gates", "Quantum Logic Gates Matrix", "Hadamard, Pauli-X, CNOT gate operations simulator"),
            Triple("ai_neural_viz", "Neural Network Architecture Visualizer", "Feedforward layers, perceptrons, activation functions"),
            Triple("ai_tokenizer_calc", "LLM Tokenizer Word Counter", "Estimate token count for GPT, Claude, and Gemini models"),
            Triple("ai_prompt_helper", "Prompt Engineering Framework", "Role, Context, Task, Constraints, Few-shot formulation"),
            Triple("ai_hallucination", "AI Hallucination Risk Estimator", "Analyze prompts for ambiguous or hallucination-prone tokens"),
            Triple("ai_context_window", "Model Context Window Calculator", "Calculate memory and token budget for 32k, 128k, 1M contexts"),
            Triple("ai_cosine_sim", "Cosine Similarity Vector Calculator", "Calculate angle and similarity between embedding vectors"),
            Triple("ai_temp_guide", "AI Temperature Parameter Guide", "Low temperature (deterministic) vs high (creative) settings"),
            Triple("ai_sentiment", "Sentiment Analysis Classifier", "Evaluate text polarity (positive, neutral, negative score)"),
            Triple("ai_summarizer", "Text Extractive Summarizer", "Identify key sentences and condense long paragraphs"),
            Triple("ai_keywords", "Keyword Extractor Algorithm", "Identify highest-frequency and high-salience terms"),
            Triple("ai_lang_detect", "Language Detection Simulator", "Heuristic identification of language script and vocabulary"),
            Triple("ai_persona_chat", "Cyber Persona Terminal Sim", "Interact with autonomous cybernetic AI terminal persona"),
            Triple("ai_prompt_enhance", "Prompt Enhancement Optimizer", "Transform vague queries into rich engineered system prompts"),
            Triple("ai_refactor_guide", "Clean Code Refactoring Principles", "DRY, SOLID, KISS, and modularity guidelines"),
            Triple("ai_big_o_calc", "Algorithmic Big-O Complexity", "O(1), O(log n), O(n), O(n log n), O(n²) analysis"),
            Triple("ai_regex_explain", "Regex AI Pattern Explainer", "Plain English breakdown of complex regex patterns"),
            Triple("ai_bug_classifier", "Software Bug Severity Triage", "Categorize bugs by impact, reproducibility, urgency"),
            Triple("ai_safety_guide", "AI Safety & Alignment Principles", "RLHF, constitutional AI, and guardrails reference"),
            Triple("ai_turing_test", "Turing Test Philosophy Primer", "Historical and philosophical criteria for machine intelligence"),
            Triple("ai_weights_opt", "Gradient Descent Simulator", "Visualize loss minimization on objective surface"),
            Triple("ai_quantization", "Model Quantization (FP16 vs INT4)", "Calculate memory savings and perplexity trade-offs"),
            Triple("ai_vram_calc", "GPU VRAM Model Requirement Calc", "Calculate required video RAM to run 7B, 13B, 70B models"),
            Triple("ai_epoch_calc", "Training Epochs & Steps Estimator", "Dataset size, batch size, and learning rate calculations"),
            Triple("ai_vector_db", "Vector Database Indexing (HNSW)", "Approximate Nearest Neighbors search mechanisms"),
            Triple("ai_zero_shot", "Zero-Shot Classification Demo", "Classify inputs into dynamic labels without fine-tuning"),
            Triple("ai_self_attention", "Transformer Self-Attention Demo", "Calculate Q, K, V attention matrix weights"),
            Triple("ai_singularity", "Technological Singularity Countdown", "Theoretical roadmap toward Artificial General Intelligence")
        )
        aiTools.forEachIndexed { i, (id, name, desc) ->
            list.add(ToolItem(id, name, ToolCategory.AI_QUANTUM_TOOLS, desc, ToolExecutionType.INFO_SPEC, isFeatured = i < 3))
        }

        list
    }
}
