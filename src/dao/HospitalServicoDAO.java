/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Pedro Manjate
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalServicoDAO {
    private final Conexao conexao = new Conexao();

    // Método para associar um hospital a um serviço
    public void associate(int hospitalId, int servicoId) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Hospital_Servico (hospital_id, servico_id) VALUES (?, ?)";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, hospitalId);
            stmt.setInt(2, servicoId);
            stmt.executeUpdate();
        }
    }

    // Método para desassociar um serviço de um hospital
    public void disassociate(int hospitalId, int servicoId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Hospital_Servico WHERE hospital_id = ? AND servico_id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, hospitalId);
            stmt.setInt(2, servicoId);
            stmt.executeUpdate();
        }
    }

    // Método para listar os serviços de um hospital
    public List<Integer> getServicosByHospital(int hospitalId) throws SQLException, ClassNotFoundException {
        List<Integer> servicoIds = new ArrayList<>();
        String sql = "SELECT servico_id FROM Hospital_Servico WHERE hospital_id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, hospitalId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    servicoIds.add(rs.getInt("servico_id"));
                }
            }
        }
        return servicoIds;
    }

    // Método para listar os hospitais que oferecem um serviço
    public List<Integer> getHospitalsByServico(int servicoId) throws SQLException, ClassNotFoundException {
        List<Integer> hospitalIds = new ArrayList<>();
        String sql = "SELECT hospital_id FROM Hospital_Servico WHERE servico_id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, servicoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    hospitalIds.add(rs.getInt("hospital_id"));
                }
            }
        }
        return hospitalIds;
    }
}
