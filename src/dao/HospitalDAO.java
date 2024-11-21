package dao;

import misau.model.Hospital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalDAO {
    private final Conexao conexao = new Conexao();

    // Método para criar um novo hospital
    public boolean create(Hospital hospital) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Hospital (nome, localidade, categoria) VALUES (?, ?, ?)";
        int executeUpdate;
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, hospital.getNome());
            stmt.setString(2, hospital.getLocalidade());
            stmt.setString(3, hospital.getCategoria());
             executeUpdate = stmt.executeUpdate();
        }
        return executeUpdate > 0;
    }

    // Método para ler todos os hospitais
    public List<Hospital> readAll() throws SQLException, ClassNotFoundException {
        List<Hospital> hospitais = new ArrayList<>();
        String sql = "SELECT * FROM Hospital";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Hospital hospital = new Hospital();
                hospital.setId(rs.getInt("id"));
                hospital.setNome(rs.getString("nome"));
                hospital.setLocalidade(rs.getString("localidade"));
                hospital.setCategoria(rs.getString("categoria"));
                hospitais.add(hospital);
            }
        }
        return hospitais;
    }

    // Método para buscar um hospital pelo ID
    public Hospital readById(int id) throws SQLException, ClassNotFoundException {
        Hospital hospital = null;
        String sql = "SELECT * FROM Hospital WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    hospital = new Hospital();
                    hospital.setId(rs.getInt("id"));
                    hospital.setNome(rs.getString("nome"));
                    hospital.setLocalidade(rs.getString("localidade"));
                    hospital.setCategoria(rs.getString("categoria"));
                }
            }
        }
        return hospital;
    }

    // Método para atualizar um hospital
    public void update(Hospital hospital) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Hospital SET nome = ?, localidade = ?, categoria = ? WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, hospital.getNome());
            stmt.setString(2, hospital.getLocalidade());
            stmt.setString(3, hospital.getCategoria());
            stmt.setInt(4, hospital.getId());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um hospital
    public void delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Hospital WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
