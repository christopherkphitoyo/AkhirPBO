/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package akhirpbo;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class MahasiswaDAO {
    private Connection conn;

    public MahasiswaDAO() {
        try {
            // Menghubungkan ke database (pastikan ganti URL, username, dan password sesuai dengan database Anda)
            String url = "jdbc:mysql://localhost:3306/dbmahasiswa";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
   
   public void simpanNilai(String nim, String tugas, String kuis, String mid, String semester, String rata, String huruf) {
    try {
        String sql = "INSERT INTO nilai (nim, tugas, kuis, mid, semester, rata, huruf) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nim);  // Set nim
        ps.setString(2, tugas); // Set tugas
        ps.setString(3, kuis);
        ps.setString(4, mid);
        ps.setString(5, semester);
        ps.setString(6, rata);
        ps.setString(7, huruf);
        ps.executeUpdate();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

   
    public void simpanMahasiswa(String nama, String nim, String jurusan, String tanggalLahir, String nomorTelepon) {
        try {
            // Query SQL untuk menyimpan data mahasiswa
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan, tanggal_lahir, nomor_telepon) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nama);
            statement.setString(2, nim);
            statement.setString(3, jurusan);
            statement.setString(4, tanggalLahir);
            statement.setString(5, nomorTelepon);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String lihatMahasiswa() {
        StringBuilder dataMahasiswa = new StringBuilder();
        try {
            // Query SQL untuk mengambil data mahasiswa
            String sql = "SELECT * FROM mahasiswa";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Mengambil data satu per satu dan menyimpannya ke dalam StringBuilder
            while (resultSet.next()) {
                dataMahasiswa.append("Nama: ").append(resultSet.getString("nama")).append("\n");
                dataMahasiswa.append("NIM: ").append(resultSet.getString("nim")).append("\n");
                dataMahasiswa.append("Jurusan: ").append(resultSet.getString("jurusan")).append("\n");
                dataMahasiswa.append("Tanggal Lahir: ").append(resultSet.getString("tanggal_lahir")).append("\n");
                dataMahasiswa.append("Nomor Telepon: ").append(resultSet.getString("nomor_telepon")).append("\n\n");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataMahasiswa.toString();
    }
}
