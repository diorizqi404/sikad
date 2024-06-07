-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 17 Bulan Mei 2024 pada 14.24
-- Versi server: 8.0.30
-- Versi PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sikad`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `guru`
--

CREATE TABLE `guru` (
  `id` int NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `nip` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `id_mapel` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `guru`
--

INSERT INTO `guru` (`id`, `nama`, `nip`, `password`, `id_mapel`) VALUES
(1, 'John Doe', '1234', '1111111', 1),
(3, 'Mr. Sanji', '404404', NULL, 7),
(4, 'Zoro Ackerman', '321', '111111', 4),
(5, 'fefweff', '123', 'eefewwfwef', 1),
(6, 'sffsdf', '3423', 'fdsfsdfsfd', 1),
(7, 'sfeefes', 'efeff', 'sfefesf', 1),
(8, 'Sir. Day', '54321', '11111111', 9),
(9, 'Sir. James Adam', '101', '11111111', 8),
(10, 'Mrs. Anna ', '3033', '11111111', 6),
(11, 'Fakhrudin', '199801', 'anaktuhan', 1),
(12, 'Mr Suqi ', '202401', '11111111', 7);

-- --------------------------------------------------------

--
-- Struktur dari tabel `kelas`
--

CREATE TABLE `kelas` (
  `id` int NOT NULL,
  `nama_kelas` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `kelas`
--

INSERT INTO `kelas` (`id`, `nama_kelas`) VALUES
(1, 'XI RPL 1'),
(2, 'XI RPL 2'),
(3, 'XI RPL 3'),
(4, 'XI ANI 1'),
(5, 'XI ANI 2');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mapel`
--

CREATE TABLE `mapel` (
  `id` int NOT NULL,
  `nama_mapel` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `mapel`
--

INSERT INTO `mapel` (`id`, `nama_mapel`) VALUES
(1, 'Bahasa Inggris'),
(2, 'Bahasa Indonesia'),
(3, 'PPKN'),
(4, 'PAI'),
(5, 'PJOK'),
(6, 'Matematika'),
(7, 'Pemrograman Web'),
(8, 'PBTGM'),
(9, 'Kewirausahaan'),
(10, 'Mobile'),
(11, 'Basis Data'),
(12, 'Game'),
(13, 'Sejarah');

-- --------------------------------------------------------

--
-- Struktur dari tabel `siswa`
--

CREATE TABLE `siswa` (
  `id` int NOT NULL,
  `no_absen` int DEFAULT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `id_kelas` int DEFAULT NULL,
  `nisn` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `siswa`
--

INSERT INTO `siswa` (`id`, `no_absen`, `nama`, `id_kelas`, `nisn`, `password`) VALUES
(1, 23, 'zorojuro', 2, '3342', '11111111'),
(2, 24, 'Vinsmoke Levi', 1, '404', '11111111'),
(3, 1, 'Leviathan', 4, '12345', '11111111'),
(4, 22, 'Tom Lembong', 2, '111', '11111111'),
(5, 6, 'Hendro Armadillo', 1, '202201', 'smartberkrakter69'),
(6, 27, 'jaya', 1, '0813725375', '08102007'),
(7, 99, 'Aldivo Rausyan', 1, '202402', '11111111');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tugas`
--

CREATE TABLE `tugas` (
  `id` int NOT NULL,
  `id_mapel` int DEFAULT NULL,
  `nama_tugas` varchar(100) DEFAULT NULL,
  `deskripsi` varchar(1000) NOT NULL,
  `deadline` varchar(100) DEFAULT NULL,
  `id_guru` int DEFAULT NULL,
  `id_kelas` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `tugas`
--

INSERT INTO `tugas` (`id`, `id_mapel`, `nama_tugas`, `deskripsi`, `deadline`, `id_guru`, `id_kelas`) VALUES
(1, 1, 'Assessment', 'Oke', '2024-05-01', 1, 1),
(5, 9, 'Buat Perusw', 'Kelompok minimal 4 orang maksimal 6 orang. ', '1 Minggu / 17-05-2024', 8, 4),
(6, 8, 'Membuat Game', 'Berkelompok', '1 bulan', 9, 2),
(9, 6, 'Membuat/menemukan rumus baru', 'Individu. Menemukan rumus matematika baru', '11/05/2024', 10, 2),
(12, 1, 'Lesson 3 page 45', 'solve the task!', '23 Oktober kkk', 11, 2),
(14, 6, 'Tugas baru', 'ini deskripsi', 'besok', 10, 1),
(15, 6, 'Mengerjakan soal LKS halaman 89', 'Kerjakan di selembar kertas', '20/05/2024', 10, 2);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_mapel` (`id_mapel`);

--
-- Indeks untuk tabel `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `mapel`
--
ALTER TABLE `mapel`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kelas` (`id_kelas`);

--
-- Indeks untuk tabel `tugas`
--
ALTER TABLE `tugas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_mapel` (`id_mapel`),
  ADD KEY `id_guru` (`id_guru`),
  ADD KEY `id_kelas` (`id_kelas`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `guru`
--
ALTER TABLE `guru`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT untuk tabel `kelas`
--
ALTER TABLE `kelas`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `mapel`
--
ALTER TABLE `mapel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT untuk tabel `siswa`
--
ALTER TABLE `siswa`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `tugas`
--
ALTER TABLE `tugas`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `guru`
--
ALTER TABLE `guru`
  ADD CONSTRAINT `guru_ibfk_1` FOREIGN KEY (`id_mapel`) REFERENCES `mapel` (`id`);

--
-- Ketidakleluasaan untuk tabel `siswa`
--
ALTER TABLE `siswa`
  ADD CONSTRAINT `siswa_ibfk_1` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id`);

--
-- Ketidakleluasaan untuk tabel `tugas`
--
ALTER TABLE `tugas`
  ADD CONSTRAINT `tugas_ibfk_1` FOREIGN KEY (`id_mapel`) REFERENCES `mapel` (`id`),
  ADD CONSTRAINT `tugas_ibfk_2` FOREIGN KEY (`id_guru`) REFERENCES `guru` (`id`),
  ADD CONSTRAINT `tugas_ibfk_3` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
