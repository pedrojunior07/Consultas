/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Pedro Manjate
 */

import misau.model.Consulta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {
    private final Conexao conexao = new Conexao();

    // Create a new Consulta
    public void create(Consulta consulta) throws SQLException, ClassNotFoundException {
         consulta.setFuncionarioHospitalId(findDefaultFuncionarioByHospital(consulta.getHospitalId()));
        String sql = "INSERT INTO Consulta (utente_id, funcionario_hospital_id, servico_id, hospital_id, data_consulta, hora_consulta, status_consulta, prioridade, data_marcacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, consulta.getUtenteId());
            stmt.setInt(2, consulta.getFuncionarioHospitalId());
            stmt.setInt(3, consulta.getServicoId());
            stmt.setInt(4, consulta.getHospitalId());
            stmt.setDate(5, Date.valueOf(consulta.getDataConsulta()));
            stmt.setTime(6, Time.valueOf(consulta.getHoraConsulta()));
            stmt.setString(7, consulta.getStatusConsulta());
            stmt.setString(8, consulta.getPrioridade());
            stmt.setDate(9, consulta.getDataMarcacao() != null ? Date.valueOf(consulta.getDataMarcacao()) : null);
            stmt.executeUpdate();
        }
    }
    
    private int findDefaultFuncionarioByHospital(int hospitalId) throws SQLException, ClassNotFoundException {
    String sql = "SELECT id FROM HospitalFuncionario WHERE hospital_id = ? LIMIT 1";
    try (Connection con = conexao.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setInt(1, hospitalId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
    }
    throw new SQLException("Nenhum funcionário encontrado para o hospital");
}

    // Retrieve all Consultas
    public List<Consulta> readAll() throws SQLException, ClassNotFoundException {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setUtenteId(rs.getInt("utente_id"));
                consulta.setFuncionarioHospitalId(rs.getInt("funcionario_hospital_id"));
                consulta.setServicoId(rs.getInt("servico_id"));
                consulta.setHospitalId(rs.getInt("hospital_id"));
                consulta.setDataConsulta(rs.getString("data_consulta"));
                consulta.setHoraConsulta(rs.getString("hora_consulta"));
                consulta.setStatusConsulta(rs.getString("status_consulta"));
                consulta.setPrioridade(rs.getString("prioridade"));
                consulta.setDataMarcacao(rs.getDate("data_marcacao") != null ? rs.getDate("data_marcacao").toString() : null);
                consultas.add(consulta);
            }
        }
        return consultas;
    }

    // Retrieve a Consulta by ID
    public Consulta readById(int id) throws SQLException, ClassNotFoundException {
        Consulta consulta = null;
        String sql = "SELECT * FROM Consulta WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    consulta = new Consulta();
                    consulta.setId(rs.getInt("id"));
                    consulta.setUtenteId(rs.getInt("utente_id"));
                    consulta.setFuncionarioHospitalId(rs.getInt("funcionario_hospital_id"));
                    consulta.setServicoId(rs.getInt("servico_id"));
                    consulta.setHospitalId(rs.getInt("hospital_id"));
                    consulta.setDataConsulta(rs.getDate("data_consulta").toString());
                    consulta.setHoraConsulta(rs.getTime("hora_consulta").toString());
                    consulta.setStatusConsulta(rs.getString("status_consulta"));
                    consulta.setPrioridade(rs.getString("prioridade"));
                    consulta.setDataMarcacao(rs.getDate("data_marcacao") != null ? rs.getDate("data_marcacao").toString() : null);
                }
            }
        }
        return consulta;
    }

    // Update a Consulta
    public void update(Consulta consulta) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Consulta SET utente_id = ?, funcionario_hospital_id = ?, servico_id = ?, hospital_id = ?, data_consulta = ?, hora_consulta = ?, status_consulta = ?, prioridade = ?, data_marcacao = ? WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, consulta.getUtenteId());
            stmt.setInt(2, consulta.getFuncionarioHospitalId());
            stmt.setInt(3, consulta.getServicoId());
            stmt.setInt(4, consulta.getHospitalId());
            stmt.setString(5, consulta.getDataConsulta());
            stmt.setString(6, consulta.getHoraConsulta());
            stmt.setString(7, consulta.getStatusConsulta());
            stmt.setString(8, consulta.getPrioridade());
            stmt.setDate(9, consulta.getDataMarcacao() != null ? Date.valueOf(consulta.getDataMarcacao()) : null);
            stmt.setInt(10, consulta.getId());
            stmt.executeUpdate();
        }
    }

    // Delete a Consulta by ID
    public void delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Consulta WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
