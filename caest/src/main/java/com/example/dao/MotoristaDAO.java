package com.example.dao;
import com.example.model.Motorista;
import com.example.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MotoristaDAO {
    public void inserirMotorista(Motorista motorista){
        String sql = "INSERT INTO motorista (nome, cpf, matricula, vinculo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCpf());
            stmt.setString(3, motorista.getMatricula());
            stmt.setString(4, motorista.getVinculo());

            stmt.executeUpdate();

            System.out.println("Motorista inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarMotorista(int id){
        String sql = "DELETE FROM motorista WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Motorista deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }

    public void atualizarMotorista(Motorista motorista){
        String sql = "UPDATE motorista SET nome = ?, cpf = ?, matricula = ?, vinculo = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {  
                
            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCpf());
            stmt.setString(3, motorista.getMatricula());
            stmt.setString(4, motorista.getVinculo());
            stmt.setInt(5, motorista.getId());

            stmt.executeUpdate();

            System.out.println("Motorista atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarMotoristas(){
        String sql = "SELECT * FROM motorista";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            var rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("Matrícula: " + rs.getString("matricula"));
                System.out.println("Vínculo: " + rs.getString("vinculo"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buscarMotoristaPorId(int id){
        String sql = "SELECT * FROM motorista WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("Matrícula: " + rs.getString("matricula"));
                System.out.println("Vínculo: " + rs.getString("vinculo"));
            } else {
                System.out.println("Motorista não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }
    } 
}
