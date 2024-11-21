/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Pedro Manjate
 */


import misau.model.Utente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UtenteDAO {
    private final Conexao conexao = new Conexao();

    // Método para criar um novo registro
   public boolean create(Utente utente) throws SQLException, ClassNotFoundException {
    // Gerar um número de saúde único, se não fornecido
    if (utente.getNumeroSaude() == null || utente.getNumeroSaude().isEmpty()) {
        utente.setNumeroSaude(gerarNumeroSaudeUnico());
    }
int executeUpdate;
    String sql = "INSERT INTO Utente (nome, localidade, genero, tipoSaguineo, email, numeroSaude) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection con = conexao.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, utente.getNome());
        stmt.setString(2, utente.getLocalidade());
        stmt.setString(3, utente.getGenero());
        stmt.setString(4, utente.getTipoSaguineo());
        stmt.setString(5, utente.getEmail());
        stmt.setString(6, utente.getNumeroSaude());
       executeUpdate  = stmt.executeUpdate();
    }return executeUpdate>1;
}


public String gerarNumeroSaudeUnico() throws SQLException, ClassNotFoundException {
    String numeroSaude;
    Random random = new Random();

    do {
        // Gera um número de saúde aleatório com 10 dígitos
        numeroSaude = String.format("%010d", random.nextInt(1_000_000_000));
    } while (isNumeroSaudeDuplicado(numeroSaude)); // Garante que não é duplicado

    return numeroSaude;
}

    public boolean isNumeroSaudeDuplicado(String numeroSaude) throws SQLException, ClassNotFoundException {
    String sql = "SELECT COUNT(*) AS total FROM Utente WHERE numeroSaude = ?";
    try (Connection con = conexao.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, numeroSaude);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total") > 0; // Retorna true se houver duplicação
            }
        }
    }
    return false; // Retorna false se não houver duplicação
}

    
    
    
    // Método para ler todos os registros
    public List<Utente> readAll() throws SQLException, ClassNotFoundException {
        List<Utente> utentes = new ArrayList<>();
        String sql = "SELECT * FROM Utente";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Utente utente = new Utente();
                utente.setId(rs.getInt("id"));
                utente.setNome(rs.getString("nome"));
                utente.setLocalidade(rs.getString("localidade"));
                utente.setGenero(rs.getString("genero"));
                utente.setTipoSaguineo(rs.getString("tipoSaguineo"));
                utente.setEmail(rs.getString("email"));
                utente.setNumeroSaude(rs.getString("numeroSaude"));
                utentes.add(utente);
            }
        }
        return utentes;
    }

    // Método para buscar um registro pelo ID
    public Utente readById(int id) throws SQLException, ClassNotFoundException {
        Utente utente = null;
        String sql = "SELECT * FROM Utente WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    utente = new Utente();
                    utente.setId(rs.getInt("id"));
                    utente.setNome(rs.getString("nome"));
                    utente.setLocalidade(rs.getString("localidade"));
                    utente.setGenero(rs.getString("genero"));
                    utente.setTipoSaguineo(rs.getString("tipoSaguineo"));
                    utente.setEmail(rs.getString("email"));
                    utente.setNumeroSaude(rs.getString("numeroSaude"));
                }
            }
        }
        return utente;
    }

    // Método para atualizar um registro
    public void update(Utente utente) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Utente SET nome = ?, localidade = ?, genero = ?, tipoSaguineo = ?, email = ?, numeroSaude = ? WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, utente.getNome());
            stmt.setString(2, utente.getLocalidade());
            stmt.setString(3, utente.getGenero());
            stmt.setString(4, utente.getTipoSaguineo());
            stmt.setString(5, utente.getEmail());
            stmt.setString(6, utente.getNumeroSaude());
            stmt.setInt(7, utente.getId());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um registro
    public void delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Utente WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
