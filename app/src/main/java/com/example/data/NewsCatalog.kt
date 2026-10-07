package com.example.data

import com.example.model.NewsItem

object NewsCatalog {
    val sampleNews: List<NewsItem> = listOf(
        NewsItem(
            id = "news_01",
            title = "Proyek Singularity Quantum Core v4 Meluncurkan Komputasi 10,000 Qubit",
            category = "Quantum & AI",
            summary = "Arsitektur prosesor superkonduktor terbaru berhasil mempertahankan koherensi kuantum selama 42 menit tanpa decoherence termal.",
            content = """Laboratorium Komputasi Kuantum Global hari ini merilis arsitektur prosesor superkonduktor 'Singularity-X'. Dengan 10.240 qubit bertautan, sistem ini membuktikan supremasi kuantum sejati dengan memecahkan simulasi dinamika kisi partikel dalam 1,2 detik—sebuah komputasi yang membutuhkan 40.000 tahun pada superkomputer konvensional tercepat saat ini.

Dr. Elena Vance, direktur riset komputasi kuantum menyatakan: 'Kami telah melampaui batas teoretis stabilitas kristal fluks magnetik. Sekarang, enkripsi kuantum tingkat kisi (Lattice-based cryptography) menjadi standar mutlak bagi semua komunikasi orbital dan terrestrial.'

Implementasi praktis meliputi pemodelan katalis pembersih karbon atmosferik dan percepatan perancangan materi superkonduktor suhu ruang.""",
            author = "XER Intelligence Wire",
            timestamp = "14 menit yang lalu",
            readMinutes = 4,
            tags = listOf("Quantum", "Qubits", "Hardware", "Supercomputing"),
            clearanceLevel = "OMEGA-1"
        ),
        NewsItem(
            id = "news_02",
            title = "Protokol Enkripsi Post-Quantum XER Shield Diadopsi oleh Jaringan Satelit Orbit Rendah",
            category = "Cyber Security",
            summary = "Serangan brute-force kuantum kini sepenuhnya dinetralkan menggunakan algoritma enkripsi berbasis kisi ML-KEM dan XMSS.",
            content = """Konsorsium Telekomunikasi Luar Angkasa mengumumkan migrasi penuh ke standar enkripsi kriptografi pasca-kuantum XER Shield. Langkah ini diambil menyusul peningkatan ancaman intersepsi sinyal optik ruang hampa (Free-Space Optical Intercept).

Sistem ini memadukan pertukaran kunci ML-KEM (Kyber) dengan tanda tangan digital stateless XMSS, menjamin data terenkripsi tetap kebal terhadap teknik 'Harvest Now, Decrypt Later' yang kerap dilakukan organisasi bayangan.

Audit independen mengonfirmasi tidak ada latensi tambahan yang terjadi dalam transmisi inter-satelit berkecepatan 800 Gbps.""",
            author = "Cyber Defense Command",
            timestamp = "1 jam yang lalu",
            readMinutes = 5,
            tags = listOf("Cryptography", "Satellites", "Security", "Post-Quantum"),
            clearanceLevel = "ALPHA-2"
        ),
        NewsItem(
            id = "news_03",
            title = "Teleskop Deep-Space Mengonfirmasi Struktur Event Horizon Black Hole Gargantua Baru",
            category = "Space & Universe",
            summary = "Citra interferometri radio frekuensi tinggi mengungkap cincin foton sempurna dengan anomali polarisasi medan magnetik rotasional.",
            content = """Jaringan Radio Interferometer Skala Planet (Event Horizon Array) berhasil merekonstruksi citra dengan resolusi nano-arcsecond dari lubang hitam supermasif yang baru dikatalogkan di gugus galaksi Hydra-7.

Cakram akresi (accretion disk) memancarkan plasma relativistik yang bergerak pada 99,4% kecepatan cahaya. Para astrofisikawan menemukan anomali polarisasi magnetik yang sesuai dengan prediksi metrik Kerr tergeneralisasi.

Penemuan ini membuka wawasan baru tentang ekstraksi energi rotasi lubang hitam melalui mekanisme Penrose termodifikasi.""",
            author = "Astrophysics Deep Bureau",
            timestamp = "3 jam yang lalu",
            readMinutes = 6,
            tags = listOf("BlackHole", "Astronomy", "Singularity", "Relativity"),
            clearanceLevel = "PUBLIC-EYE"
        ),
        NewsItem(
            id = "news_04",
            title = "Android Kernel 6.x Memperkenalkan Subsistem Micro-Isolation untuk Terminal Operatif",
            category = "Mobile Systems",
            summary = "Pembaruan arsitektur sistem operasi menghadirkan isolasi memori berbasis perangkat keras ARM TrustZone untuk setiap aplikasi utilitas.",
            content = """Komite Pengembang Open Source Mobile meresmikan integrasi modul Hardware Memory Tagging Extension (MTE) dan sandboxing mikro untuk ekosistem Android masa depan.

Setiap modul utilitas dapat berjalan di dalam micro-VM ringan dengan waktu boot kurang dari 12 milidetik, mencegah eksploitasi use-after-free dan buffer overflow secara permanen.

Pengembang kini dapat memanfaatkan pustaka Jetpack modern dengan performa native tanpa penurunan efisiensi baterai.""",
            author = "Android Core Dispatch",
            timestamp = "5 jam yang lalu",
            readMinutes = 3,
            tags = listOf("Android", "Kernel", "Sandboxing", "Security"),
            clearanceLevel = "DEV-SPEC"
        ),
        NewsItem(
            id = "news_05",
            title = "Neural Interface Non-Invasif Generasi Tiga Mencapai Akurasi Decoding Pikiran 99.2%",
            category = "Deep Tech",
            summary = "Sensor magnetoensefalografi optik portabel kini memungkinkan pengetikan kode dan instruksi terminal tanpa sentuhan tangan.",
            content = """Sebuah terobosan dalam bidang antarmuka otak-komputer (BCI) non-invasif telah dicapai melalui sensor optik kuantum (OPM-MEG) yang dipasang pada helm taktis ergonomis.

Sistem mampu menerjemahkan sinyal motorik halus menjadi perintah shell Linux dengan latensi di bawah 25 milidetik dan throughput 140 kata per menit.

'Ini bukan sekadar pengetikan cepat, melainkan fusi langsung antara intent kognitif operatif dan eksekusi instan pada terminal XER,' tutur ketua tim neuro-teknologi.""",
            author = "Neural Foundry Lab",
            timestamp = "8 jam yang lalu",
            readMinutes = 4,
            tags = listOf("BCI", "Neural", "DeepTech", "HCI"),
            clearanceLevel = "SIGMA-4"
        ),
        NewsItem(
            id = "news_06",
            title = "Baterai Solid-State Berbasis Grafena Menggandakan Kepadatan Energi Gadget Lapangan",
            category = "Hardware",
            summary = "Elektrolit padat keramik lithium-sulfur memungkinkan pengisian daya 0-100% dalam 90 detik tanpa risiko termal berlebih.",
            content = """Konsorsium Material Canggih mengumumkan dimulainya produksi massal sel baterai solid-state generasi baru untuk perangkat portabel taktis.

Dengan kepadatan energi mencapai 750 Wh/kg, perangkat dapat beroperasi secara terus menerus selama berminggu-minggu tanpa perlu pengisian ulang. Struktur grafena berlapis mencegah pembentukan dendritik lithium dan mempertahankan 95% kapasitas setelah 5.000 siklus pengisian cepat.""",
            author = "Advanced Materials Institute",
            timestamp = "12 jam yang lalu",
            readMinutes = 3,
            tags = listOf("Battery", "SolidState", "Hardware", "Energy"),
            clearanceLevel = "PUBLIC-EYE"
        ),
        NewsItem(
            id = "news_07",
            title = "Jaringan Mesh P2P Terdesentralisasi Menahan Serangan Blackout Jaringan Total",
            category = "Cyber Security",
            summary = "Protokol routing gossiping berbasis frekuensi radio pita lebar menjaga integritas pertukaran data operatif antar wilayah.",
            content = """Dalam uji simulasi kegagalan infrastruktur global, jaringan peer-to-peer terdesentralisasi berhasil mempertahankan throughput transmisi data 99,8%.

Algoritma perutean dinamis secara otomatis mengalihkan paket melalui hop radio kognitif, laser LoS, dan relay satelit amatir tanpa ketergantungan pada server pusat atau DNS konvensional.""",
            author = "Resilience Task Force",
            timestamp = "1 hari yang lalu",
            readMinutes = 5,
            tags = listOf("Mesh", "P2P", "Resilience", "Decentralized"),
            clearanceLevel = "DEFCON-2"
        ),
        NewsItem(
            id = "news_08",
            title = "Perkembangan Model AI Multimodal Lokal Berukuran Ringan untuk Pemrosesan On-Device",
            category = "Quantum & AI",
            summary = "Model kuantisasi 4-bit berjalan mulus pada NPU mobile dengan konsumsi daya minimal dan privasi data mutlak.",
            content = """Arsitektur Transformer berbasis state-space model (SSM) terbaru mampu mengeksekusi inferensi multimodal secara offline di smartphone kelas menengah.

Data pengguna tidak pernah meninggalkan perangkat, mewujudkan filosofi keamanan 'Zero-Cloud Exposure' yang diutamakan oleh aplikasi terminal operatif generasi baru.""",
            author = "Edge AI Consortium",
            timestamp = "2 hari yang lalu",
            readMinutes = 4,
            tags = listOf("EdgeAI", "LocalInference", "Privacy", "NPU"),
            clearanceLevel = "OMEGA-1"
        )
    )
}
