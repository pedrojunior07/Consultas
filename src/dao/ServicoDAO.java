/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Pedro Manjate
 */


import misau.model.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {
    private final Conexao conexao = new Conexao();

    // Método para criar um novo serviço
    public void create(Servico servico) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Servico (nome, categoria) VALUES (?, ?)";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getCategoria());
            stmt.executeUpdate();
        }
    }

    // Método para ler todos os serviços
    public List<Servico> readAll() throws SQLException, ClassNotFoundException {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM Servico";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Servico servico = new Servico();
                servico.setId(rs.getInt("id"));
                servico.setNome(rs.getString("nome"));
                servico.setCategoria(rs.getString("categoria"));
                servicos.add(servico);
            }
        }
        return servicos;
    }

    // Método para buscar um serviço pelo ID
    public Servico readById(int id) throws SQLException, ClassNotFoundException {
        Servico servico = null;
        String sql = "SELECT * FROM Servico WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    servico = new Servico();
                    servico.setId(rs.getInt("id"));
                    servico.setNome(rs.getString("nome"));
                    servico.setCategoria(rs.getString("categoria"));
                }
            }
        }
        return servico;
    }

    // Método para atualizar um serviço
    public void update(Servico servico) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Servico SET nome = ?, categoria = ? WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getCategoria());
            stmt.setInt(3, servico.getId());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um serviço
    public void delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Servico WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
