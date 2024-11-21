package dao;

import misau.model.HospitalFunconario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalFuncionarioDAO {
    private final Conexao conexao = new Conexao();

    // Método para criar um novo registro
    public boolean create(HospitalFunconario funcionario) throws SQLException, ClassNotFoundException {
                   int executeUpdate;
        String sql = "INSERT INTO HospitalFuncionario (username, password, role, especializacao, hospital_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getUsername());
            stmt.setString(2, funcionario.getPassword());
            stmt.setString(3, funcionario.getRole());
            stmt.setString(4, funcionario.getEspecializacao());
            stmt.setInt(5, funcionario.getHospitalId());
             executeUpdate = stmt.executeUpdate();
        } 
        return  executeUpdate>0;
    }

    // Método para ler todos os registros
    public List<HospitalFunconario> readAll() throws SQLException, ClassNotFoundException {
        List<HospitalFunconario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM HospitalFuncionario";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                HospitalFunconario funcionario = new HospitalFunconario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setUsername(rs.getString("username"));
                funcionario.setPassword(rs.getString("password"));
                funcionario.setRole(rs.getString("role"));
                funcionario.setEspecializacao(rs.getString("especializacao"));
                funcionario.setHospitalId(rs.getInt("hospital_id"));
                funcionarios.add(funcionario);
            }
        }
        return funcionarios;
    }

    // Método para buscar um registro pelo ID
    public HospitalFunconario readById(int id) throws SQLException, ClassNotFoundException {
        HospitalFunconario funcionario = null;
        String sql = "SELECT * FROM HospitalFuncionario WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    funcionario = new HospitalFunconario();
                    funcionario.setId(rs.getInt("id"));
                    funcionario.setUsername(rs.getString("username"));
                    funcionario.setPassword(rs.getString("password"));
                    funcionario.setRole(rs.getString("role"));
                    funcionario.setEspecializacao(rs.getString("especializacao"));
                    funcionario.setHospitalId(rs.getInt("hospital_id"));
                }
            }
        }
        return funcionario;
    }

    // Método para atualizar um registro
    public void update(HospitalFunconario funcionario) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE HospitalFuncionario SET username = ?, password = ?, role = ?, especializacao = ?, hospital_id = ? WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getUsername());
            stmt.setString(2, funcionario.getPassword());
            stmt.setString(3, funcionario.getRole());
            stmt.setString(4, funcionario.getEspecializacao());
            stmt.setInt(5, funcionario.getHospitalId());
            stmt.setInt(6, funcionario.getId());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um registro
    public void delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM HospitalFuncionario WHERE id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para buscar funcionários por hospital
    public List<HospitalFunconario> readByHospitalId(int hospitalId) throws SQLException, ClassNotFoundException {
        List<HospitalFunconario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM HospitalFuncionario WHERE hospital_id = ?";
        try (Connection con = conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, hospitalId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    HospitalFunconario funcionario = new HospitalFunconario();
                    funcionario.setId(rs.getInt("id"));
                    funcionario.setUsername(rs.getString("username"));
                    funcionario.setPassword(rs.getString("password"));
                    funcionario.setRole(rs.getString("role"));
                    funcionario.setEspecializacao(rs.getString("especializacao"));
                    funcionario.setHospitalId(rs.getInt("hospital_id"));
                    funcionarios.add(funcionario);
                }
            }
        }
        return funcionarios;
    }
}
