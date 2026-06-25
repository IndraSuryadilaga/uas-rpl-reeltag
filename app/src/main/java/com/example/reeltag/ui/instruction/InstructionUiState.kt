package com.example.reeltag.ui.instruction

data class InstructionUiState(

    val title: String = "Instruksi Pengujian",

    val originalInstruction: String =
        """
Pada pengujian ini Anda akan menggunakan alur seperti Instagram Reels saat ini.

Langkah-langkah:
1. Tekan tombol "Mulai".
2. Buka kolom komentar pada Reels.
3. Temukan topik yang sedang dibahas pada komentar.
4. Keluar dari halaman Reels.
5. Buka halaman Search.
6. Cari topik tersebut secara manual.
7. Buka hasil pencarian yang sesuai hingga informasi berhasil ditemukan.

Lakukan tugas secepat dan seefisien mungkin.
""".trimIndent(),

    val reelTagInstruction: String =
        """
Pada pengujian ini Anda akan menggunakan Instagram Reels yang telah dilengkapi fitur ReelTag.

Langkah-langkah:
1. Tekan tombol "Mulai".
2. Buka kolom komentar pada Reels.
3. Temukan ReelTag yang sesuai dengan topik yang sedang dibahas.
4. Tekan ReelTag tersebut.
5. Sistem akan menampilkan konten terkait secara otomatis.
6. Pastikan informasi yang diminta berhasil ditemukan.

Lakukan tugas secepat dan seefisien mungkin.
""".trimIndent(),

    val buttonText: String = "Mulai"

)