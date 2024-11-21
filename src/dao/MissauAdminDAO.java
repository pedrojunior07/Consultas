/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Pedro Manjate
 */
import misau.model.MissauAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MissauAdminDAO {
    private final Conexao conexao = new Conexao();

    // Método para criar um novo registro
    public void create(MissauAdmin admin) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO MissauAdmin (userName, passWord) VALUES (?, ?)";
        try (Connection con = conexao.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, admin.getUserName());
            stmt.setString(2, admin.getPassWord());
            stmt.executeUpdate();
        }
    }

    // Método para ler todos os registros
    public List<MissauAdmin> readAll() throws SQLException, ClassNotFoundException {
        List<MissauAdmin> admins = new ArrayList<>();
        String sql = "SELECT * FROM MissauAdmin";
        try (Connection con = conexao.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                MissauAdmin admin = new MissauAdmin();
                admin.setId(rs.getInt("Id"));
                admin.setUserName(rs.getString("userName"));
                admin.setPassWord(rs.getString("passWord"));
                admins.add(admin);
            }
        }
        return admins;
    }

    // Método para buscar um registro pelo ID
    public MissauAdmin readById(int id) throws SQLException, ClassNotFoundException {
        MissauAdmin admin = null;
        String sql = "SELECT * FROM MissauAdmin WHERE Id = ?";
        try (Connection con = conexao.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    admin = new MissauAdmin();
                    admin.setId(rs.getInt("Id"));
                    admin.setUserName(rs.getString("userName"));
                    admin.setPassWord(rs.getString("passWord"));
                }
            }
        }
        return admin;
    }

    // Método para atualizar um registro
    public void update(MissauAdmin admin) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE MissauAdmin SET userName = ?, passWord = ? WHERE Id = ?";
        try (Connection con = conexao.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, admin.getUserName());
            stmt.setString(2, admin.getPassWord());
            stmt.setInt(3, admin.getId());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um registro
    public void delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM MissauAdmin WHERE Id = ?";
        try (Connection con = conexao.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
